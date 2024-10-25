import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * The ImageModelGrayscaleTest class tests the functionality of grayscale-related operations
 * on images using the ImageModel and ImageController classes. This class includes test cases
 * for applying various grayscale transformations such as intensity, luma, and value computations.
 */
public class ImageModelGrayscaleTest {

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
   * Tests the calculation of the value component for a PPM image.
   * This verifies the correct calculation of the max RGB value per pixel
   * and ensures that the resulting image is saved properly.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateValuePPMImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm", "ppmImage");

    model.calculateValue("ppmImage", "valuePPMImage");

    assertTrue(controller.getImages().containsKey("valuePPMImage"));

    RGB[][] valueImage = controller.getImages().get("valuePPMImage");

    for (RGB[] rgbs : valueImage) {
      for (RGB valuePixel : rgbs) {
        int expectedValue = Math.max(valuePixel.red, Math.max(valuePixel.green, valuePixel.blue));

        assertEquals(expectedValue, valuePixel.red);
        assertEquals(expectedValue, valuePixel.green);
        assertEquals(expectedValue, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/valuePPMImage.ppm",
            "valuePPMImage");
  }

  /**
   * Tests the calculation of the value component for a pure red image.
   * This ensures that the red channel's value is retained across all RGB channels.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateValuePureRedImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/pureRed.ppm", "pureRedImage");

    model.calculateValue("pureRedImage", "valuePureRedImage");

    assertTrue(controller.getImages().containsKey("valuePureRedImage"));

    RGB[][] valueImage = controller.getImages().get("valuePureRedImage");

    for (RGB[] rgbs : valueImage) {
      for (RGB valuePixel : rgbs) {
        int expectedValue = valuePixel.red;

        assertEquals(expectedValue, valuePixel.red);
        assertEquals(expectedValue, valuePixel.green);
        assertEquals(expectedValue, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/valuePureRedImage.ppm",
            "valuePureRedImage");
  }

  /**
   * Tests the calculation of the value component for a pure black image.
   * This ensures that all RGB channels are set to 0, representing black.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateValuePureBlackImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/black.ppm", "pureBlackImage");

    model.calculateValue("pureBlackImage", "valueBlackImage");

    assertTrue(controller.getImages().containsKey("valueBlackImage"));

    RGB[][] valueImageArray = controller.getImages().get("valueBlackImage");

    for (RGB[] rgbs : valueImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedValue = 0;

        assertEquals(expectedValue, valuePixel.red);
        assertEquals(expectedValue, valuePixel.green);
        assertEquals(expectedValue, valuePixel.blue);
      }
    }

    // Save the output image
    controller.saveImage("resources/res/ppmRes/valueBlackImage.ppm",
            "valueBlackImage");
  }

  /**
   * Tests the calculation of the value component for a pure white image.
   * This ensures that all RGB channels are set to 255, representing white.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateValuePureWhiteImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/white.ppm", "pureWhiteImage");

    model.calculateValue("pureWhiteImage", "valueWhiteImage");

    assertTrue(controller.getImages().containsKey("valueWhiteImage"));

    RGB[][] valueImageArray = controller.getImages().get("valueWhiteImage");

    for (RGB[] rgbs : valueImageArray) {
      for (RGB rgb : rgbs) {
        assertEquals(255, rgb.red);
        assertEquals(255, rgb.green);
        assertEquals(255, rgb.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/valueWhiteImage.ppm",
            "valueWhiteImage");
  }

  /**
   * Tests the calculation of intensity for a pure black image.
   * Verifies that the intensity values for all RGB channels are 0
   * since the image is black.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateIntensityPureBlackImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/black.ppm", "pureBlackImage");

    model.calculateIntensity("pureBlackImage", "intensityBlackImage");

    assertTrue(controller.getImages().containsKey("intensityBlackImage"));

    RGB[][] intensityImageArray = controller.getImages().get("intensityBlackImage");

    for (RGB[] rgbs : intensityImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedIntensity = 0;

        assertEquals(expectedIntensity, valuePixel.red);
        assertEquals(expectedIntensity, valuePixel.green);
        assertEquals(expectedIntensity, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/intensityBlackImage.ppm",
            "intensityBlackImage");
  }

  /**
   * Tests the calculation of intensity for a pure red image.
   * Verifies that the intensity is the average of the red, green, and blue values.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateIntensityPureRedImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/pureRed.ppm", "pureRedImage");

    model.calculateIntensity("pureRedImage", "intensityPureRedImage");

    assertTrue(controller.getImages().containsKey("intensityPureRedImage"));

    RGB[][] intensityImageArray = controller.getImages().get("intensityPureRedImage");

    for (RGB[] rgbs : intensityImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedIntensity = (valuePixel.red + valuePixel.green + valuePixel.blue) / 3;

        assertEquals(expectedIntensity, valuePixel.red);
        assertEquals(expectedIntensity, valuePixel.green);
        assertEquals(expectedIntensity, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/intensityPureRedImage.ppm",
            "intensityPureRedImage");
  }

  /**
   * Tests the calculation of intensity for a pure white image.
   * Ensures that the intensity values for all RGB channels are 255,
   * representing white.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateIntensityPureWhiteImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/white.ppm", "pureWhiteImage");

    model.calculateIntensity("pureWhiteImage", "intensityWhiteImage");

    assertTrue(controller.getImages().containsKey("intensityWhiteImage"));

    RGB[][] intensityImageArray = controller.getImages().get("intensityWhiteImage");

    for (RGB[] rgbs : intensityImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedIntensity = 255;

        assertEquals(expectedIntensity, valuePixel.red);
        assertEquals(expectedIntensity, valuePixel.green);
        assertEquals(expectedIntensity, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/intensityWhiteImage.ppm",
            "intensityWhiteImage");
  }


  /**
   * Tests the calculation of intensity for an image with mixed colors.
   * Verifies that the intensity for each pixel is the average of its
   * red, green, and blue values.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateIntensityMixedColorsImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/mixedColors.ppm",
            "mixedColorsImage");

    model.calculateIntensity("mixedColorsImage",
            "intensityMixedColorsImage");

    assertTrue(controller.getImages().containsKey("intensityMixedColorsImage"));

    RGB[][] intensityImageArray = controller.getImages().get("intensityMixedColorsImage");

    for (RGB[] rgbs : intensityImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedIntensity = (valuePixel.red + valuePixel.green + valuePixel.blue) / 3;

        assertEquals(expectedIntensity, valuePixel.red);
        assertEquals(expectedIntensity, valuePixel.green);
        assertEquals(expectedIntensity, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/intensityMixedColorsImage.ppm",
            "intensityMixedColorsImage");
  }

  /**
   * Tests the calculation of luma for a pure black image.
   * Verifies that the luma values for all RGB channels are 0
   * since the image is pure black.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateLumaPureBlackImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/black.ppm", "pureBlackImage");

    model.calculateLuma("pureBlackImage", "lumaBlackImage");

    assertTrue(controller.getImages().containsKey("lumaBlackImage"));

    RGB[][] lumaImageArray = controller.getImages().get("lumaBlackImage");

    for (RGB[] rgbs : lumaImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedLuma = 0;

        assertEquals(expectedLuma, valuePixel.red);
        assertEquals(expectedLuma, valuePixel.green);
        assertEquals(expectedLuma, valuePixel.blue);
      }
    }

    // Save the output image
    controller.saveImage("resources/res/ppmRes/lumaBlackImage.ppm",
            "lumaBlackImage");
  }

  /**
   * Tests the calculation of luma for a pure red image.
   * Verifies that the luma is computed using the formula for red,
   * green, and blue values based on the pure red pixel.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateLumaPureRedImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/pureRed.ppm", "pureRedImage");

    model.calculateLuma("pureRedImage", "lumaPureRedImage");

    assertTrue(controller.getImages().containsKey("lumaPureRedImage"));

    RGB[][] lumaImageArray = controller.getImages().get("lumaPureRedImage");

    for (RGB[] rgbs : lumaImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedLuma = (int) (0.2126 * valuePixel.red
                + 0.7152 * valuePixel.green
                + 0.0722 * valuePixel.blue);

        assertEquals(expectedLuma, valuePixel.red);
        assertEquals(expectedLuma, valuePixel.green);
        assertEquals(expectedLuma, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/lumaPureRedImage.ppm",
            "lumaPureRedImage");
  }

  /**
   * Tests the calculation of luma for a pure white image.
   * Verifies that the luma values for all RGB channels are 255
   * since the image is pure white.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateLumaPureWhiteImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/white.ppm", "pureWhiteImage");

    model.calculateLuma("pureWhiteImage", "lumaWhiteImage");

    assertTrue(controller.getImages().containsKey("lumaWhiteImage"));

    RGB[][] lumaImageArray = controller.getImages().get("lumaWhiteImage");

    for (RGB[] rgbs : lumaImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedLuma = 255;

        assertEquals(expectedLuma, valuePixel.red);
        assertEquals(expectedLuma, valuePixel.green);
        assertEquals(expectedLuma, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/lumaWhiteImage.ppm",
            "lumaWhiteImage");
  }

  /**
   * Tests the calculation of luma for an image with mixed colors.
   * Verifies that the luma for each pixel is computed based on
   * the weighted formula for red, green, and blue values.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateLumaMixedColorsImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/mixedColors.ppm",
            "mixedColorsImage");

    model.calculateLuma("mixedColorsImage",
            "lumaMixedColorsImage");

    assertTrue(controller.getImages().containsKey("lumaMixedColorsImage"));

    RGB[][] lumaImageArray = controller.getImages().get("lumaMixedColorsImage");

    for (RGB[] rgbs : lumaImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedLuma = (int) Math.round(0.2126 * valuePixel.red
                + 0.7152 * valuePixel.green + 0.0722 * valuePixel.blue);

        assertEquals(expectedLuma, valuePixel.red);
        assertEquals(expectedLuma, valuePixel.green);
        assertEquals(expectedLuma, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/lumaMixedColorsImage.ppm",
            "lumaMixedColorsImage");
  }

  /**
   * Tests the calculation of luma for a JPG image.
   * Verifies that the luma values are computed for each pixel based on
   * the weighted formula using the red, green, and blue channels.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateLumaJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.calculateLuma("earth", "earthLuma");

    assertTrue(controller.getImages().containsKey("earthLuma"));

    RGB[][] lumaImageArray = controller.getImages().get("earthLuma");

    for (RGB[] rgbs : lumaImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedLuma = (int) Math.round(0.2126 * valuePixel.red
                + 0.7152 * valuePixel.green + 0.0722 * valuePixel.blue);

        assertEquals(expectedLuma, valuePixel.red);
        assertEquals(expectedLuma, valuePixel.green);
        assertEquals(expectedLuma, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/earthLuma.jpg", "earthLuma");
  }

  /**
   * Tests the calculation of value for a JPG image.
   * Verifies that the value is the maximum of the red, green, and blue channels
   * for each pixel in the image.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateValueJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.calculateValue("earth", "earthValue");

    assertTrue(controller.getImages().containsKey("earthValue"));

    RGB[][] valueImage = controller.getImages().get("earthValue");

    for (RGB[] rgbs : valueImage) {
      for (RGB valuePixel : rgbs) {
        int expectedValue = Math.max(valuePixel.red, Math.max(valuePixel.green, valuePixel.blue));

        assertEquals(expectedValue, valuePixel.red);
        assertEquals(expectedValue, valuePixel.green);
        assertEquals(expectedValue, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/earthValue.jpg", "earthValue");
  }

  /**
   * Tests the calculation of intensity for a JPG image.
   * Verifies that the intensity for each pixel is calculated as the average
   * of the red, green, and blue channels and applied to all channels.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateIntensityJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.calculateValue("earth", "earthIntensity");

    assertTrue(controller.getImages().containsKey("earthIntensity"));

    RGB[][] valueImage = controller.getImages().get("earthIntensity");

    for (RGB[] rgbs : valueImage) {
      for (RGB valuePixel : rgbs) {
        int expectedIntensity = (valuePixel.red + valuePixel.green + valuePixel.blue) / 3;

        assertEquals(expectedIntensity, valuePixel.red);
        assertEquals(expectedIntensity, valuePixel.green);
        assertEquals(expectedIntensity, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/earthIntensity.jpg",
            "earthIntensity");
  }

  /**
   * Tests the calculation of luma for a PNG image.
   * Verifies that the luma values are computed for each pixel based on
   * the weighted formula using the red, green, and blue channels.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateLumaPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.calculateLuma("square", "squareLuma");

    assertTrue(controller.getImages().containsKey("squareLuma"));

    RGB[][] lumaImageArray = controller.getImages().get("squareLuma");

    for (RGB[] rgbs : lumaImageArray) {
      for (RGB valuePixel : rgbs) {
        int expectedLuma = (int) Math.round(0.2126 * valuePixel.red
                + 0.7152 * valuePixel.green + 0.0722 * valuePixel.blue);

        assertEquals(expectedLuma, valuePixel.red);
        assertEquals(expectedLuma, valuePixel.green);
        assertEquals(expectedLuma, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/pngRes/squareLuma.png", "squareLuma");
  }

  /**
   * Tests the calculation of value for a PNG image.
   * Verifies that the value is the maximum of the red, green, and blue channels
   * for each pixel in the image.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateValuePNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.calculateValue("square", "squareValue");

    assertTrue(controller.getImages().containsKey("squareValue"));

    RGB[][] valueImage = controller.getImages().get("squareValue");

    for (RGB[] rgbs : valueImage) {
      for (RGB valuePixel : rgbs) {
        int expectedValue = Math.max(valuePixel.red, Math.max(valuePixel.green, valuePixel.blue));

        assertEquals(expectedValue, valuePixel.red);
        assertEquals(expectedValue, valuePixel.green);
        assertEquals(expectedValue, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/pngRes/squareValue.png", "squareValue");
  }

  /**
   * Tests the calculation of intensity for a PNG image.
   * Verifies that the intensity for each pixel is calculated as the average
   * of the red, green, and blue channels and applied to all channels.
   *
   * @throws IOException if the image fails to load or save.
   */
  @Test
  public void testCalculateIntensityPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.calculateIntensity("square", "squareIntensity");

    assertTrue(controller.getImages().containsKey("squareIntensity"));

    RGB[][] valueImage = controller.getImages().get("squareIntensity");

    for (RGB[] rgbs : valueImage) {
      for (RGB valuePixel : rgbs) {
        int expectedIntensity = (valuePixel.red + valuePixel.green + valuePixel.blue) / 3;

        assertEquals(expectedIntensity, valuePixel.red);
        assertEquals(expectedIntensity, valuePixel.green);
        assertEquals(expectedIntensity, valuePixel.blue);
      }
    }

    controller.saveImage("resources/res/pngRes/squareIntensity.png",
            "squareIntensity");
  }
}