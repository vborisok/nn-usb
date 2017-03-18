package nn.eye.detector.utils;

import java.awt.image.BufferedImage;

/**
 * Created by Максим on 18.03.2017.
 */
public class ImageUtils {
    public static double[] getRGBImage(int coordinateX, int coordinateY, int n, int l, BufferedImage image) {

        int size = n * l;
        double RGB[][] = new double[3][size];

        int column = 0;

        for (int j = coordinateY; j < l + coordinateY; j++) {
            for (int i = coordinateX; i < n + coordinateX; i++) {
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                RGB[0][column] = red;
                RGB[1][column] = green;
                RGB[2][column] = blue;
                column++;
            }
        }

        int N = size * 3;
        double[] X1 = new double[N];
        int indexVector = 0;

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < 3; i++) {
                X1[indexVector] = 2 * RGB[i][j] / 255 - 1;
                indexVector++;
            }
        }
        return X1;
    }
}
