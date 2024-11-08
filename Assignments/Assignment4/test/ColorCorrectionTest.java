import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import controller.ImageController;
import model.ImageModel;
import model.RGB;
import view.ImageView;
import view.ImageViewInterface;

import static org.junit.Assert.assertEquals;

/**
 * Contains all the tests to test for color correction functionality.
 */

public class ColorCorrectionTest {
  private ImageModel model;
  private ImageController controller;
  private int splitPercentage;

  /**
   * Set up method to initialise all the required values.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
    ImageViewInterface view = new ImageView(controller);
    model = new ImageModel(controller, view);
    splitPercentage = 100;
  }

  /**
   * Helper method to calculate histogram data for each color channel from an RGB array.
   *
   * @param pixelArray the image's pixel data as an RGB array
   * @return a map with histogram arrays for each color channel
   */
  private Map<String, int[]> getHistogramData(RGB[][] pixelArray) {
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];

    for (RGB[] row : pixelArray) {
      for (RGB pixel : row) {
        redHistogram[pixel.red]++;
        greenHistogram[pixel.green]++;
        blueHistogram[pixel.blue]++;
      }
    }

    Map<String, int[]> histogramData = new HashMap<>();
    histogramData.put("Red", redHistogram);
    histogramData.put("Green", greenHistogram);
    histogramData.put("Blue", blueHistogram);

    return histogramData;
  }

  /**
   * Helper method to find the peak intensity in a given histogram.
   *
   * @param histogram the color channel histogram array
   * @return the intensity level with the highest frequency
   */
  private int findPeak(int[] histogram) {
    int peakIntensity = 0;
    int peakFrequency = 0;
    for (int i = 10; i < 245; i++) { // Ignore extremes to avoid noise
      if (histogram[i] > peakFrequency) {
        peakFrequency = histogram[i];
        peakIntensity = i;
      }
    }
    return peakIntensity;
  }

  /**
   * Test case for a red-tinted image.
   */
  @Test
  public void testRedTintedImageCorrection() {
    RGB[][] redTingedImage = {
            {new RGB(255, 0, 0), new RGB(255, 0, 0)},
            {new RGB(255, 0, 0), new RGB(255, 0, 0)}
    };
    controller.getImages().put("redTingedImage", redTingedImage);
    model.colorCorrect("redTingedImage", "correctedRedTingedImage",
            splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("redTingedImage");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedRedTingedImage");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a green-tinted image.
   */
  @Test
  public void testGreenTintedImageCorrection() {
    RGB[][] greenTingedImage = {
            {new RGB(0, 255, 0), new RGB(0, 255, 0)},
            {new RGB(0, 255, 0), new RGB(0, 255, 0)}
    };
    controller.getImages().put("greenTingedImage", greenTingedImage);
    model.colorCorrect("greenTingedImage",
            "correctedGreenTingedImage", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("greenTingedImage");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedGreenTingedImage");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a blue-tinted image.
   */
  @Test
  public void testBlueTintedImageCorrection() {
    RGB[][] blueTingedImage = {
            {new RGB(0, 0, 255), new RGB(0, 0, 255)},
            {new RGB(0, 0, 255), new RGB(0, 0, 255)}
    };
    controller.getImages().put("blueTingedImage", blueTingedImage);
    model.colorCorrect("blueTingedImage",
            "correctedBlueTingedImage", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("blueTingedImage");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedBlueTingedImage");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for grayscale gradient image.
   */
  @Test
  public void testGrayscaleGradientImageCorrection() {
    RGB[][] grayscaleGradient = {
            {new RGB(64, 64, 64), new RGB(128, 128, 128)},
            {new RGB(192, 192, 192), new RGB(255, 255, 255)}
    };
    controller.getImages().put("grayscaleGradient", grayscaleGradient);
    model.colorCorrect("grayscaleGradient",
            "correctedGrayscaleGradient", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("grayscaleGradient");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedGrayscaleGradient");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for high contrast image.
   */
  @Test
  public void testHighContrastImageCorrection() {
    RGB[][] highContrast = {
            {new RGB(0, 0, 0), new RGB(255, 255, 255)},
            {new RGB(0, 0, 0), new RGB(255, 255, 255)}
    };
    controller.getImages().put("highContrast", highContrast);
    model.colorCorrect("highContrast", "correctedHighContrast", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("highContrast");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedHighContrast");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for an image with already aligned peaks.
   */
  @Test
  public void testAlignedPeaksImageCorrection() {
    RGB[][] alignedPeaks = {
            {new RGB(128, 128, 128), new RGB(128, 128, 128)},
            {new RGB(128, 128, 128), new RGB(128, 128, 128)}
    };
    controller.getImages().put("alignedPeaks", alignedPeaks);
    model.colorCorrect("alignedPeaks", "correctedAlignedPeaks", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("alignedPeaks");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedAlignedPeaks");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for low contrast image.
   */
  @Test
  public void testLowContrastImageCorrection() {
    RGB[][] lowContrast = {
            {new RGB(100, 100, 100), new RGB(110, 110, 110)},
            {new RGB(120, 120, 120), new RGB(130, 130, 130)}
    };
    controller.getImages().put("lowContrast", lowContrast);
    model.colorCorrect("lowContrast", "correctedLowContrast", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("lowContrast");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedLowContrast");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for an image with saturated colors.
   * Ensures that color correction does not clip high-intensity values.
   */
  @Test
  public void testSaturatedColorsImageCorrection() {
    RGB[][] saturatedColors = {
            {new RGB(255, 0, 0), new RGB(0, 255, 0)},
            {new RGB(0, 0, 255), new RGB(255, 255, 0)}
    };
    controller.getImages().put("saturatedColors", saturatedColors);
    model.colorCorrect("saturatedColors", "correctedSaturatedColors", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("saturatedColors");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedSaturatedColors");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a dark-toned image.
   * Verifies that color correction works with images containing only dark values.
   */
  @Test
  public void testDarkTonesImageCorrection() {
    RGB[][] darkTones = {
            {new RGB(10, 10, 10), new RGB(20, 20, 20)},
            {new RGB(30, 30, 30), new RGB(40, 40, 40)}
    };
    controller.getImages().put("darkTones", darkTones);
    model.colorCorrect("darkTones", "correctedDarkTones", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("darkTones");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedDarkTones");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a multicolored image.
   * Ensures that color correction maintains the natural balance of multiple colors.
   */
  @Test
  public void testMultiColoredImageCorrection() {
    RGB[][] multiColored = {
            {new RGB(255, 0, 0), new RGB(0, 255, 0)},
            {new RGB(0, 0, 255), new RGB(255, 255, 255)}
    };
    controller.getImages().put("multiColored", multiColored);
    model.colorCorrect("multiColored", "correctedMultiColored", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("multiColored");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedMultiColored");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for an image with random noise.
   * Verifies that color correction behaves consistently with unstructured input.
   */
  @Test
  public void testRandomNoiseImageCorrection() {
    RGB[][] randomNoise = {
            {new RGB(123, 111, 45), new RGB(210, 34, 180)},
            {new RGB(87, 156, 223), new RGB(92, 14, 201)}
    };
    controller.getImages().put("randomNoise", randomNoise);
    model.colorCorrect("randomNoise", "correctedRandomNoise", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("randomNoise");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedRandomNoise");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a JPEG image with a red tint.
   * Ensures that color correction aligns the color peaks appropriately.
   */
  @Test
  public void testRedTintedJPEGImageCorrection() throws Exception {
    controller.loadImage("resources/testImages/jpg/red_tinged_image.jpg", "redTingedJPEG");
    model.colorCorrect("redTingedJPEG", "correctedRedTingedJPEG", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("redTingedJPEG");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedRedTingedJPEG");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a PNG image with a red tint.
   * Ensures that color correction aligns the color peaks appropriately.
   */
  @Test
  public void testRedTintedPNGImageCorrection() throws Exception {
    controller.loadImage("resources/testImages/png/red_tinged_image.png", "redTingedPNG");
    model.colorCorrect("redTingedPNG", "correctedRedTingedPNG", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("redTingedPNG");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedRedTingedPNG");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }

  /**
   * Test case for a PPM image with a red tint.
   * Ensures that color correction aligns the color peaks appropriately.
   */
  @Test
  public void testRedTintedImageCorrectionPPM() throws Exception {
    controller.loadImage("resources/testImages/ppm/red_tinged_image.ppm", "redTingedImage");
    model.colorCorrect("redTingedImage", "correctedRedTingedImage", splitPercentage);

    RGB[][] originalPixels = controller.getImages().get("redTingedImage");
    Map<String, int[]> originalHistogram = getHistogramData(originalPixels);

    int redPeak = findPeak(originalHistogram.get("Red"));
    int greenPeak = findPeak(originalHistogram.get("Green"));
    int bluePeak = findPeak(originalHistogram.get("Blue"));
    int expectedTargetPeak = (redPeak + greenPeak + bluePeak) / 3;

    RGB[][] correctedPixels = controller.getImages().get("correctedRedTingedImage");
    Map<String, int[]> correctedHistogram = getHistogramData(correctedPixels);

    int correctedRedPeak = findPeak(correctedHistogram.get("Red"));
    int correctedGreenPeak = findPeak(correctedHistogram.get("Green"));
    int correctedBluePeak = findPeak(correctedHistogram.get("Blue"));

    assertEquals(expectedTargetPeak, correctedRedPeak);
    assertEquals(expectedTargetPeak, correctedGreenPeak);
    assertEquals(expectedTargetPeak, correctedBluePeak);
  }
}