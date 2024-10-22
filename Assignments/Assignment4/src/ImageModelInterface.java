import java.io.IOException;
import java.util.NoSuchElementException;

public interface ImageModelInterface {
  void applySepiaTone(String imageName, String destinationImageName) throws NoSuchElementException;

  RGB[][] convertToGreyscale(RGB[][] pixelArray);

  void blurImage(String imageName, String destinationImageName) throws NoSuchElementException;

  void sharpenImage(String imageName, String destinationImageName) throws NoSuchElementException;

  void changeBrightness(String imageName, String destinationImageName, int value) throws NoSuchElementException;

  void horizontalFlipImage(String imageName, String destinationImageName) throws NoSuchElementException;

  void verticalFlipImage(String imageName, String destinationImageName) throws NoSuchElementException;

  void splitRedChannel(String imageName, String destinationImageName) throws NoSuchElementException;

  void splitGreenChannel(String imageName, String destinationImageName) throws NoSuchElementException;

  void splitBlueChannel(String imageName, String destinationImageName) throws NoSuchElementException;

  void calculateValue(String imageName, String destinationImageName) throws NoSuchElementException;

  void calculateIntensity(String imageName, String destinationImageName) throws NoSuchElementException;

  void calculateLuma(String imageName, String destinationImageName) throws NoSuchElementException;

  RGB[][] combineGreyscale(RGB[][] redPixelArray, RGB[][] greenPixelArray, RGB[][] bluePixelArray);
}
