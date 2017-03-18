package nn.eye.detector;

import nn.eye.detector.utils.InputOutputUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List list = InputOutputUtils.getImagesFromFolder("D:\\dataset\\", "dataset.json","small");
        list.size();
    }
}
