package nn.eye.detector.utils;

import nn.eye.detector.entity.ImageEyed;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 18.03.2017.
 */
public class InputOutputUtils {
    public static List<ImageEyed> getImagesFromFolder(String folderStr, String pathJsonConfigFile, String size) throws IOException {
        List<ImageEyed> list = new ArrayList<ImageEyed>();
        JSONParser parser = new JSONParser();
        Object obj = new Object();
        try {
            obj = parser.parse(new FileReader(folderStr + pathJsonConfigFile));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray json = (JSONArray) obj;
        for (int i = 0; i<json.size();i++){
            ImageEyed imageItem = new ImageEyed();
            JSONObject item = (JSONObject) json.get(i);
            JSONObject images = (JSONObject) item.get("images");
            JSONObject leftPupil = (JSONObject) item.get("leftPupil");
            JSONObject rightPupil = (JSONObject) item.get("rightPupil");
            JSONObject imageScaleAndPath = (JSONObject) images.get(size);
            imageItem.setLeftEyeX((Long) leftPupil.get("x"));
            imageItem.setLeftEyeY((Long) leftPupil.get("y"));
            imageItem.setRightEyeX((Long) rightPupil.get("x"));
            imageItem.setRightEyeY((Long) rightPupil.get("y"));
            BufferedImage image = ImageIO.read(new File(folderStr+imageScaleAndPath.get("url")));
            imageItem.setHeight(image.getHeight());
            imageItem.setWight(image.getWidth());
            imageItem.setBufferedImage(image);
            int[][] pixels = new int[image.getWidth()][image.getHeight()];

            for( int k = 0; k < image.getWidth(); k++ )
                for( int j = 0; j < image.getHeight(); j++ )
                    pixels[k][j] = image.getRGB( k, j );

            imageItem.setImagergb(pixels);
            list.add(imageItem);
        }

        return list;
    }

    public static void saveImagesToFolder(String folder, List<BufferedImage> images) throws IOException {
        int i = 0;
        for(BufferedImage image : images){
            File outputfile = new File(folder + i++ + ".png");
            ImageIO.write(image, "png", outputfile);
        }
    }
}
