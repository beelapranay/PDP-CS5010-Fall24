import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * Test class for brightness adjustment in different image formats (PPM, JPEG, PNG).
 */
public class ImageModelBrightnessTest {
  private ImageModel model;
  private ImageController controller;

  /**
   * Set up the model and controller for the tests.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
    model = new ImageModel(controller);
  }

  /**
   * Tests applying positive brightness to a PPM image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyPositiveBrightnessPPMImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm", "ppmImage");

    model.changeBrightness("ppmImage", "brightPPMImage", 50);

    assertTrue(controller.getImages().containsKey("brightPPMImage"));

    RGB[][] brightPPMImage = controller.getImages().get("brightPPMImage");
    RGB[][] originalImage = controller.getImages().get("ppmImage");

    for (int y = 0; y < brightPPMImage.length; y++) {
      for (int x = 0; x < brightPPMImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = Math.min(Math.max(50 + originalPixel.red, 0), 255);
        int expectedGreen = Math.min(Math.max(50 + originalPixel.green, 0), 255);
        int expectedBlue = Math.min(Math.max(50 + originalPixel.blue, 0), 255);

        assertEquals(expectedRed, brightPPMImage[y][x].red);
        assertEquals(expectedGreen, brightPPMImage[y][x].green);
        assertEquals(expectedBlue, brightPPMImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/brightPPMImage.ppm",
            "brightPPMImage");
  }

  /**
   * Tests applying negative brightness to a PPM image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyNegativeBrightnessPPMImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm",
            "ppmImage");

    int brightnessValue = -50;
    model.changeBrightness("ppmImage",
            "darkPPMImage", brightnessValue);

    assertTrue(controller.getImages().containsKey("darkPPMImage"));

    RGB[][] darkPPMImage = controller.getImages().get("darkPPMImage");
    RGB[][] originalImage = controller.getImages().get("ppmImage");

    for (int y = 0; y < darkPPMImage.length; y++) {
      for (int x = 0; x < darkPPMImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = Math.max(originalPixel.red + brightnessValue, 0);
        int expectedGreen = Math.max(originalPixel.green + brightnessValue, 0);
        int expectedBlue = Math.max(originalPixel.blue + brightnessValue, 0);

        assertEquals(expectedRed, darkPPMImage[y][x].red);
        assertEquals(expectedGreen, darkPPMImage[y][x].green);
        assertEquals(expectedBlue, darkPPMImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/darkPPMImage.ppm",
            "darkPPMImage");
  }

  /**
   * Tests applying zero brightness to a PPM image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyZeroBrightnessPPMImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm",
            "ppmImage");

    int brightnessValue = 0;
    model.changeBrightness("ppmImage",
            "samePPMImage", brightnessValue);

    assertTrue(controller.getImages().containsKey("samePPMImage"));

    RGB[][] samePPMImage = controller.getImages().get("samePPMImage");
    RGB[][] originalImage = controller.getImages().get("ppmImage");

    for (int y = 0; y < samePPMImage.length; y++) {
      for (int x = 0; x < samePPMImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        assertEquals(originalPixel.red, samePPMImage[y][x].red);
        assertEquals(originalPixel.green, samePPMImage[y][x].green);
        assertEquals(originalPixel.blue, samePPMImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/samePPMImage.ppm",
            "samePPMImage");
  }

  /**
   * Tests applying positive brightness to a JPG image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyPositiveBrightnessJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "jpgImage");

    model.changeBrightness("jpgImage", "brightJPGImage", 50);

    assertTrue(controller.getImages().containsKey("brightJPGImage"));

    RGB[][] brightJPEGImage = controller.getImages().get("brightJPGImage");
    RGB[][] originalImage = controller.getImages().get("jpgImage");

    for (int y = 0; y < brightJPEGImage.length; y++) {
      for (int x = 0; x < brightJPEGImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = Math.min(Math.max(50 + originalPixel.red, 0), 255);
        int expectedGreen = Math.min(Math.max(50 + originalPixel.green, 0), 255);
        int expectedBlue = Math.min(Math.max(50 + originalPixel.blue, 0), 255);

        assertEquals(expectedRed, brightJPEGImage[y][x].red);
        assertEquals(expectedGreen, brightJPEGImage[y][x].green);
        assertEquals(expectedBlue, brightJPEGImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/brightJPGImage.jpg",
            "brightJPGImage");
  }

  /**
   * Tests applying negative brightness to a JPG image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyNegativeBrightnessJPEGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "jpgImage");

    model.changeBrightness("jpgImage", "darkJPGImage", -50);

    assertTrue(controller.getImages().containsKey("darkJPGImage"));

    RGB[][] darkJPEGImage = controller.getImages().get("darkJPGImage");
    RGB[][] originalImage = controller.getImages().get("jpgImage");

    for (int y = 0; y < darkJPEGImage.length; y++) {
      for (int x = 0; x < darkJPEGImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = Math.max(originalPixel.red - 50, 0);
        int expectedGreen = Math.max(originalPixel.green - 50, 0);
        int expectedBlue = Math.max(originalPixel.blue - 50, 0);

        assertEquals(expectedRed, darkJPEGImage[y][x].red);
        assertEquals(expectedGreen, darkJPEGImage[y][x].green);
        assertEquals(expectedBlue, darkJPEGImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/darkJPGImage.jpg",
            "darkJPGImage");
  }

  /**
   * Tests applying zero brightness to a JPG image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyZeroBrightnessJPEGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "jpgImage");

    model.changeBrightness("jpgImage", "sameJPEGImage", 0);

    assertTrue(controller.getImages().containsKey("sameJPEGImage"));

    RGB[][] sameJPEGImage = controller.getImages().get("sameJPEGImage");
    RGB[][] originalImage = controller.getImages().get("jpgImage");

    for (int y = 0; y < sameJPEGImage.length; y++) {
      for (int x = 0; x < sameJPEGImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        assertEquals(originalPixel.red, sameJPEGImage[y][x].red);
        assertEquals(originalPixel.green, sameJPEGImage[y][x].green);
        assertEquals(originalPixel.blue, sameJPEGImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/sameJPEGImage.jpg",
            "sameJPEGImage");
  }

  /**
   * Tests applying positive brightness to a PNG image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyPositiveBrightnessPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "pngImage");

    model.changeBrightness("pngImage", "brightPNGImage", 50);

    assertTrue(controller.getImages().containsKey("brightPNGImage"));

    RGB[][] brightPNGImage = controller.getImages().get("brightPNGImage");
    RGB[][] originalImage = controller.getImages().get("pngImage");

    for (int y = 0; y < brightPNGImage.length; y++) {
      for (int x = 0; x < brightPNGImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = Math.min(Math.max(50 + originalPixel.red, 0), 255);
        int expectedGreen = Math.min(Math.max(50 + originalPixel.green, 0), 255);
        int expectedBlue = Math.min(Math.max(50 + originalPixel.blue, 0), 255);

        assertEquals(expectedRed, brightPNGImage[y][x].red);
        assertEquals(expectedGreen, brightPNGImage[y][x].green);
        assertEquals(expectedBlue, brightPNGImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/brightPNGImage.png",
            "brightPNGImage");
  }

  /**
   * Tests applying negative brightness to a PNG image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyNegativeBrightnessPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "pngImage");

    model.changeBrightness("pngImage", "darkPNGImage", -50);

    assertTrue(controller.getImages().containsKey("darkPNGImage"));

    RGB[][] darkPNGImage = controller.getImages().get("darkPNGImage");
    RGB[][] originalImage = controller.getImages().get("pngImage");

    for (int y = 0; y < darkPNGImage.length; y++) {
      for (int x = 0; x < darkPNGImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        int expectedRed = Math.max(originalPixel.red - 50, 0);
        int expectedGreen = Math.max(originalPixel.green - 50, 0);
        int expectedBlue = Math.max(originalPixel.blue - 50, 0);

        assertEquals(expectedRed, darkPNGImage[y][x].red);
        assertEquals(expectedGreen, darkPNGImage[y][x].green);
        assertEquals(expectedBlue, darkPNGImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/darkPNGImage.png",
            "darkPNGImage");
  }

  /**
   * Tests applying zero brightness to a JPG image.
   *
   * @throws IOException if loading or saving the image fails.
   */
  @Test
  public void testApplyZeroBrightnessPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "pngImage");

    model.changeBrightness("pngImage", "samePNGImage", 0);

    assertTrue(controller.getImages().containsKey("samePNGImage"));

    RGB[][] samePNGImage = controller.getImages().get("samePNGImage");
    RGB[][] originalImage = controller.getImages().get("pngImage");

    for (int y = 0; y < samePNGImage.length; y++) {
      for (int x = 0; x < samePNGImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        assertEquals(originalPixel.red, samePNGImage[y][x].red);
        assertEquals(originalPixel.green, samePNGImage[y][x].green);
        assertEquals(originalPixel.blue, samePNGImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/samePNGImage.png",
            "samePNGImage");
  }

}
