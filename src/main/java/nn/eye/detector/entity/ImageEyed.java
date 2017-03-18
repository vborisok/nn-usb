package nn.eye.detector.entity;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Максим on 18.03.2017.
 */
public class ImageEyed {
    private int[][] imagergb;
    private long leftEyeX;
    private long leftEyeY;
    private long rightEyeX;
    private long rightEyeY;
    private long wight;
    private long height;
    private BufferedImage bufferedImage;

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public long getWight() {
        return wight;
    }

    public void setWight(long wight) {
        this.wight = wight;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public int[][] getImagergb() {
        return imagergb;
    }

    public void setImagergb(int[][] imagergb) {
        this.imagergb = imagergb;
    }

    public long getLeftEyeX() {
        return leftEyeX;
    }

    public void setLeftEyeX(Long leftEyeX) {
        this.leftEyeX = leftEyeX;
    }

    public long getLeftEyeY() {
        return leftEyeY;
    }

    public void setLeftEyeY(Long leftEyeY) {
        this.leftEyeY = leftEyeY;
    }

    public long getRightEyeX() {
        return rightEyeX;
    }

    public void setRightEyeX(Long rightEyeX) {
        this.rightEyeX = rightEyeX;
    }

    public long getRightEyeY() {
        return rightEyeY;
    }

    public void setRightEyeY(Long rightEyeY) {
        this.rightEyeY = rightEyeY;
    }
}
