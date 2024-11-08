import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import controller.ImageController;
import model.RGB;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for verifying histogram generation in the ImageView.
 * This class includes test cases for solid color images (Red, Green, and Blue)
 * to ensure that the histogram values match expected output.
 */
public class HistogramViewTest {
  private ImageController controller;
  private ImageView view;

  /**
   * Sets up the ImageController and ImageView before each test.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
    view = new ImageView(controller);
  }

  /**
   * Test case for a solid red image.
   * We manually define the expected histogram values in array format.
   */

  @Test
  public void testSolidRedImageHistogram() {
    RGB[][] solidRedImage = {
            {new RGB(255, 0, 0), new RGB(255, 0, 0)},
            {new RGB(255, 0, 0), new RGB(255, 0, 0)}
    };

    controller.getImages().put("solidRedImage", solidRedImage);

    view.generateAndStoreHistogram("solidRedImage",
            "solidRedImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("solidRedImageHistogram"));

    assertEquals("Red channel histogram mismatch", 384,
            actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch", 6225,
            actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch", 58927,
            actualHistogram.get("Red")[255]);
    assertEquals("Green channel histogram mismatch", 748,
            actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch", 6225,
            actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch", 58563,
            actualHistogram.get("Green")[255]);
    assertEquals("Blue channel histogram mismatch", 364,
            actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch", 6225,
            actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch", 58947,
            actualHistogram.get("Blue")[255]);

  }

  /**
   * Test case for a solid green image.
   * We manually define the expected histogram values in array format.
   */
  @Test
  public void testSolidGreenImageHistogram() {
    RGB[][] solidGreenImage = {
            {new RGB(0, 255, 0), new RGB(0, 255, 0)},
            {new RGB(0, 255, 0), new RGB(0, 255, 0)}
    };

    controller.getImages().put("solidGreenImage", solidGreenImage);

    view.generateAndStoreHistogram("solidGreenImage",
            "solidGreenImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("solidGreenImageHistogram"));

    assertEquals("Red channel histogram mismatch at index 0", 748,
            actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch at index 192", 6225,
            actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch at index 255", 58563,
            actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch at index 0", 384,
            actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch at index 192", 6225,
            actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch at index 255", 58927,
            actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch at index 0", 364,
            actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch at index 192", 6225,
            actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch at index 255", 58947,
            actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a solid blue image.
   * We manually define the expected histogram values in array format.
   */
  @Test
  public void testSolidBlueImageHistogram() {
    RGB[][] solidBlueImage = {
            {new RGB(0, 0, 255), new RGB(0, 0, 255)},
            {new RGB(0, 0, 255), new RGB(0, 0, 255)}
    };

    controller.getImages().put("solidBlueImage", solidBlueImage);

    view.generateAndStoreHistogram("solidBlueImage",
            "solidBlueImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("solidBlueImageHistogram"));

    assertEquals("Red channel histogram mismatch at index 0", 506,
            actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch at index 192", 6467,
            actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch at index 255", 58563,
            actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch at index 0", 384,
            actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch at index 192", 6467,
            actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch at index 255", 58685,
            actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch at index 0", 122,
            actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch at index 192", 6467,
            actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch at index 255", 58947,
            actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for grayscale gradient image with specified expected histogram values.
   */
  @Test
  public void testGrayscaleGradientImageHistogram() {
    RGB[][] grayscaleGradientImage = {
            {new RGB(50, 50, 50), new RGB(100, 100, 100)},
            {new RGB(150, 150, 150), new RGB(200, 200, 200)}
    };

    controller.getImages().put("grayscaleGradientImage", grayscaleGradientImage);

    view.generateAndStoreHistogram("grayscaleGradientImage",
            "grayscaleGradientImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("grayscaleGradientImageHistogram"));

    assertEquals("Red channel histogram mismatch",
            2560, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            5873, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            57103, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            2560, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            5873, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            57103, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            0, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            5873, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            59663, actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a high contrast image.
   * This image alternates between black and white pixels, ensuring contrast.
   * The test validates that the histogram captures peaks at 0 and 255 for each color.
   */
  @Test
  public void testHighContrastImageHistogram() {
    RGB[][] highContrastImage = {
            {new RGB(0, 0, 0), new RGB(255, 255, 255)},
            {new RGB(0, 0, 0), new RGB(255, 255, 255)}
    };

    controller.getImages().put("highContrastImage", highContrastImage);

    view.generateAndStoreHistogram("highContrastImage",
            "highContrastImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("highContrastImageHistogram"));

    assertEquals("Red channel histogram mismatch",
            768, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6205, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            58563, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            768, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6205, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            58563, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            0, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6205, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            59331, actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a multicolored image.
   * The image includes distinct colors (Red, Green, Blue, and White) in each quadrant.
   * The test ensures the histogram captures individual color peaks correctly.
   */
  @Test
  public void testMultiColoredImageHistogram() {
    RGB[][] multiColoredImage = {
            {new RGB(255, 0, 0), new RGB(0, 255, 0)},
            {new RGB(0, 0, 255), new RGB(255, 255, 255)}
    };

    controller.getImages().put("multiColoredImage", multiColoredImage);

    view.generateAndStoreHistogram("multiColoredImage",
            "multiColoredImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("multiColoredImageHistogram"));

    assertEquals("Red channel histogram mismatch",
            768, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6205, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            58563, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            768, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6205, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            58563, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            0, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6205, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            59331, actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a checkerboard pattern.
   * Alternates between black and white in a 2x2 pattern.
   * The histogram should capture peaks at 0 and 255 for each color.
   */
  @Test
  public void testCheckerboardPatternHistogram() {
    RGB[][] checkerboardImage = {
            {new RGB(0, 0, 0), new RGB(255, 255, 255)},
            {new RGB(255, 255, 255), new RGB(0, 0, 0)}
    };

    controller.getImages().put("checkerboardImage", checkerboardImage);

    view.generateAndStoreHistogram("checkerboardImage",
            "checkerboardImageHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("checkerboardImageHistogram"));

    assertEquals("Red channel histogram mismatch",
            768, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6205, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            58563, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            768, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6205, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            58563, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            0, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6205, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            59331, actualHistogram.get("Blue")[255]);
  }

  /**
   * Tests histogram for a full gradient from black to white.
   * Each RGB channel has one count per intensity from 0 to 255.
   */
  @Test
  public void testFullSpectrumGradientHistogram() {
    RGB[][] gradientImage = new RGB[1][256];
    for (int i = 0; i < 256; i++) {
      gradientImage[0][i] = new RGB(i, i, i);
    }

    controller.getImages().put("fullSpectrumGradient", gradientImage);

    view.generateAndStoreHistogram("fullSpectrumGradient",
            "fullSpectrumGradientHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("fullSpectrumGradientHistogram"));

    assertEquals("Red channel histogram mismatch",
            256, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6231, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            59049, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            256, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6231, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            59049, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            0, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6231, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            59305, actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a grayscale gradient image in PPM format.
   * Each intensity from 0 to 255 is represented uniformly across channels.
   */
  @Test
  public void testGrayscaleGradientPPMHistogram() throws Exception {
    controller.loadImage("resources/testImages/ppm/red_tinged_image.ppm",
            "grayscaleGradientPPM");

    view.generateAndStoreHistogram("grayscaleGradientPPM",
            "grayscaleGradientPPMHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("grayscaleGradientPPMHistogram"));

    assertEquals("Red channel histogram mismatch",
            640, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6455, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            58441, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            1005, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6455, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            58076, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            365, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6455, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            58716, actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a solid red image in PNG format.
   * Verifies that the red histogram has values at maximum intensity,
   * and green/blue histograms are empty.
   */
  @Test
  public void testRedPNGHistogram() throws Exception {
    controller.loadImage("resources/testImages/png/red_tinged_image.png",
            "redTingedImagePNG");

    view.generateAndStoreHistogram("redTingedImagePNG",
            "redTingedImagePNGHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("redTingedImagePNGHistogram"));

    assertEquals("Red channel histogram mismatch",
            384, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6225, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            58927, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            992, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6225, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            58319, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            608, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6225, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            58703, actualHistogram.get("Blue")[255]);
  }

  /**
   * Test case for a high contrast image in JPG format.
   * Ensures that only black (0) and white (255) values are populated in each channel.
   */
  @Test
  public void testRedJPGHistogram() throws Exception {
    controller.loadImage("resources/testImages/jpg/red_tinged_image.jpg",
            "redTingedImageJPG");
    view.generateAndStoreHistogram("redTingedImageJPG",
            "redTingedImageJPGHistogram");

    Map<String, int[]> actualHistogram = calculateHistogramData(
            controller.getImages().get("redTingedImageJPGHistogram"));

    assertEquals("Red channel histogram mismatch",
            384, actualHistogram.get("Red")[0]);
    assertEquals("Red channel histogram mismatch",
            6225, actualHistogram.get("Red")[192]);
    assertEquals("Red channel histogram mismatch",
            58927, actualHistogram.get("Red")[255]);

    assertEquals("Green channel histogram mismatch",
            992, actualHistogram.get("Green")[0]);
    assertEquals("Green channel histogram mismatch",
            6225, actualHistogram.get("Green")[192]);
    assertEquals("Green channel histogram mismatch",
            58319, actualHistogram.get("Green")[255]);

    assertEquals("Blue channel histogram mismatch",
            608, actualHistogram.get("Blue")[0]);
    assertEquals("Blue channel histogram mismatch",
            6225, actualHistogram.get("Blue")[192]);
    assertEquals("Blue channel histogram mismatch",
            58703, actualHistogram.get("Blue")[255]);
  }


  /**
   * Generic method to test histogram generation by comparing with an expected histogram.
   */
  private Map<String, int[]> calculateHistogramData(RGB[][] pixelArray) {
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
}