import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * Test class for verifying sepia tone transformations in {@link ImageModel}.
 * Tests sepia filter on various image formats and color conditions.
 */
public class ImageModelSepiaTest {
  private ImageModel model;
  private ImageController controller;

  /**
   * Sets up the environment for each test by initializing the ImageController and ImageModel.
   * This method is run before each test case to ensure a fresh setup.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
    model = new ImageModel(controller);
  }

  /**
   * Tests the application of sepia tone on a PPM image.
   * Ensures the sepia tone transformation is applied correctly to each pixel in the image.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaTonePPMImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm", "ppmImage");

    model.applySepiaTone("ppmImage", "sepiaPPMImage");

    assertTrue(controller.getImages().containsKey("sepiaPPMImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaPPMImage");
    RGB[][] originalImage = controller.getImages().get("ppmImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/sepiaPPMImage.ppm",
            "sepiaPPMImage");
  }

  /**
   * Tests the application of sepia tone on an image with black pixels.
   * Ensures the transformation is applied correctly and values remain within bounds.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneBlackPixels() throws IOException {
    controller.loadImage("resources/testImages/ppm/black.ppm", "blackImage");

    model.applySepiaTone("blackImage", "sepiaBlackImage");

    assertTrue(controller.getImages().containsKey("sepiaBlackImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaBlackImage");
    RGB[][] originalImage = controller.getImages().get("blackImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/sepiaBlackImage.ppm",
            "sepiaBlackImage");
  }

  /**
   * Tests the application of sepia tone on an image with maximum RGB values.
   * Ensures that pixel values are capped at 255 after applying sepia transformation.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneMaxRGBValues() throws IOException {
    controller.loadImage("resources/testImages/ppm/maxrgb.ppm", "maxRGBImage");

    model.applySepiaTone("maxRGBImage", "sepiaMaxRGBImage");

    assertTrue(controller.getImages().containsKey("sepiaMaxRGBImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaMaxRGBImage");
    RGB[][] originalImage = controller.getImages().get("maxRGBImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/sepiaMaxRGBImage.ppm",
            "sepiaMaxRGBImage");
  }

  /**
   * Tests the application of sepia tone on a grayscale PPM image.
   * Ensures that the sepia tone transformation is applied correctly on grayscale values.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneGrayscalePixels() throws IOException {
    controller.loadImage("resources/testImages/ppm/grayscale.ppm",
            "grayscaleImage");

    model.applySepiaTone("grayscaleImage", "sepiaGrayscaleImage");

    assertTrue(controller.getImages().containsKey("sepiaGrayscaleImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaGrayscaleImage");
    RGB[][] grayscaleImage = controller.getImages().get("grayscaleImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = grayscaleImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/sepiaGrayscaleImage.ppm",
            "sepiaGrayscaleImage");
  }

  /**
   * Tests the application of sepia tone on an image with dark pixels.
   * Verifies that the sepia tone transformation is applied correctly on darker tones.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneDarkPixels() throws IOException {
    controller.loadImage("resources/testImages/ppm/dark.ppm", "darkImage");

    model.applySepiaTone("darkImage", "sepiaDarkImage");

    assertTrue(controller.getImages().containsKey("sepiaDarkImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaDarkImage");
    RGB[][] originalImage = controller.getImages().get("darkImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/sepiaDarkImage.ppm",
            "sepiaDarkImage");
  }

  /**
   * Tests the application of sepia tone on an image with bright red pixels.
   * Ensures the sepia tone transformation is correctly applied without exceeding RGB bounds.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneBrightRedPixels() throws IOException {
    controller.loadImage("resources/testImages/ppm/brightRed.ppm",
            "brightRedImage");

    model.applySepiaTone("brightRedImage", "sepiaBrightRedImage");

    assertTrue(controller.getImages().containsKey("sepiaBrightRedImage"));

    RGB[][] sepiaImage = controller.getImages().get("sepiaBrightRedImage");
    RGB[][] originalImage = controller.getImages().get("brightRedImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/sepiaBrightRedImage.ppm",
            "sepiaBrightRedImage");
  }

  /**
   * Tests the application of sepia tone on a JPG image.
   * Ensures that the sepia tone transformation is applied correctly to a JPG file.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneEarthJPG() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earthImage");

    model.applySepiaTone("earthImage", "sepiaEarthImage");

    assertTrue(controller.getImages().containsKey("sepiaEarthImage"));

    RGB[][] originalImage = controller.getImages().get("earthImage");
    RGB[][] sepiaImage = controller.getImages().get("sepiaEarthImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/sepiaEarthImage.jpg",
            "sepiaEarthImage");
  }

  /**
   * Tests the application of sepia tone on a PNG image.
   * Ensures that the sepia tone transformation is correctly applied to a PNG file.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testApplySepiaToneSquarePNG() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "squareImage");

    model.applySepiaTone("squareImage", "sepiaSquareImage");

    assertTrue(controller.getImages().containsKey("sepiaSquareImage"));

    RGB[][] originalImage = controller.getImages().get("squareImage");
    RGB[][] sepiaImage = controller.getImages().get("sepiaSquareImage");

    for (int y = 0; y < sepiaImage.length; y++) {
      for (int x = 0; x < sepiaImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaImage[y][x].red);
        assertEquals(expectedGreen, sepiaImage[y][x].green);
        assertEquals(expectedBlue, sepiaImage[y][x].blue);
      }
    }

    // Save the resulting sepia-toned image
    controller.saveImage("resources/res/pngRes/sepiaSquareImage.png",
            "sepiaSquareImage");
  }

}
