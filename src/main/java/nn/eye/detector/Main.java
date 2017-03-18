package nn.eye.detector;

import nn.eye.detector.entity.ImageEyed;
import nn.eye.detector.utils.InputOutputUtils;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<ImageEyed> list = InputOutputUtils.getImagesFromFolder("D:\\dataset\\", "dataset.json","small");
        List<BufferedImage> listToSave = new ArrayList<BufferedImage>();
        for(ImageEyed image : list){
            listToSave.add(image.getBufferedImage());
        }
        InputOutputUtils.saveImagesToFolder("D:\\save\\",listToSave);
    }
}
