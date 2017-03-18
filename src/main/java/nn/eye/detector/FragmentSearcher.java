package nn.eye.detector;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class FragmentSearcher {
    private ScheduledExecutorService timer;
    private CascadeClassifier faceCascade;
    private int absoluteFaceSize;

    public FragmentSearcher() {
    }

    public List<BufferedImage> search(List<BufferedImage> images) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        List<BufferedImage> result = new ArrayList<BufferedImage>();
        faceCascade = new CascadeClassifier();
        faceCascade.load("D:\\JavaPrj\\nn-usb\\src\\main\\resources\\haarcascade_eye.xml");
        absoluteFaceSize = 0;

        for (BufferedImage image : images) {
            result.add(proccessImage(image));
        }

        return result;
    }

    private void detectAndDisplay(Mat frame) {
        MatOfRect matOfRect = new MatOfRect();
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(grayFrame, grayFrame);
        if (this.absoluteFaceSize == 0) {
            int height = grayFrame.rows();
            if (Math.round(height * 0.2f) > 0) {
                absoluteFaceSize = Math.round(height * 0.2f);
            }
        }
        this.faceCascade.detectMultiScale(grayFrame, matOfRect, 1.1, 2,
                0 | Objdetect.CASCADE_SCALE_IMAGE, new Size(
                        this.absoluteFaceSize, this.absoluteFaceSize),
                new Size());
        Rect[] facesArray = matOfRect.toArray();
        for (int i = 0; i < facesArray.length; i++) {
            Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(),
                    new Scalar(255, 255, 255), 3);
        }
    }

    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }


    private BufferedImage proccessImage(BufferedImage image) {
        BufferedImage imageToShow = null;
        Mat frame = bufferedImageToMat(image);
        detectAndDisplay(frame);
        imageToShow = mat2Image(frame);
        return imageToShow;
    }

    private BufferedImage mat2Image(Mat frame) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(buffer.toArray()));
        } catch (IOException e) {

        }
        return image;
    }
}
