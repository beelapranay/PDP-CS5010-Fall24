import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * This class contains tests for the channel-splitting functionality (red, green, and blue)
 * of the ImageModel class. It ensures that each channel is split correctly and that the output
 * image contains the correct values.
 */
public class ImageModelChannelSplitTest {
  private ImageModel model;
  private ImageController controller;

  /**
   * Sets up the test environment by initializing the ImageController and ImageModel objects.
   * This method is run before each test case to ensure a
   * fresh instance of the model and controller.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
    model = new ImageModel(controller);
  }

  /**
   * Tests the splitting of the red channel from an image containing only red pixels.
   * Verifies that the red channel is correctly retained, and green and blue values
   * match the red value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitRedComponentPureRedImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/pureRed.ppm", "pureRedImage");

    model.splitRedChannel("pureRedImage", "redChannelImage");

    assertTrue(controller.getImages().containsKey("redChannelImage"));

    RGB[][] redChannelImage = controller.getImages().get("redChannelImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/redChannelImage.ppm",
            "redChannelImage");
  }

  /**
   * Tests the red channel splitting for an image with mixed colors.
   * Verifies that the red channel is retained while green and blue are set to match the red value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitRedComponentMixedColorsImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/mixedColors.ppm",
            "mixedColorsImage");

    model.splitRedChannel("mixedColorsImage",
            "redChannelMixedColorsImage");

    assertTrue(controller.getImages().containsKey("redChannelMixedColorsImage"));

    RGB[][] redChannelImage = controller.getImages().get("redChannelMixedColorsImage");
    RGB[][] originalImage = controller.getImages().get("mixedColorsImage");

    for (int y = 0; y < redChannelImage.length; y++) {
      for (int x = 0; x < redChannelImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        assertEquals(originalPixel.red, redChannelImage[y][x].red);
        assertEquals(redChannelImage[y][x].red, redChannelImage[y][x].green);
        assertEquals(redChannelImage[y][x].green, redChannelImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/redChannelMixedColorsImage.ppm",
            "redChannelMixedColorsImage");
  }

  /**
   * Tests the red channel splitting for an image with white pixels.
   * Verifies that all pixel values in the red, green, and blue channels are set to 255.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitRedComponentWhiteImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/white.ppm", "whiteImage");

    model.splitRedChannel("whiteImage", "redChannelWhiteImage");

    assertTrue(controller.getImages().containsKey("redChannelWhiteImage"));

    RGB[][] redChannelImage = controller.getImages().get("redChannelWhiteImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(255, rgb.red);
        assertEquals(255, rgb.green);
        assertEquals(255, rgb.blue);
      }
    }
  }

  /**
   * Tests the red channel splitting for a JPG image.
   * Verifies that the red channel is retained, and all RGB values are set to the
   * red channel's value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitRedComponentJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.splitRedChannel("earth", "redChannelJPGImage");

    assertTrue(controller.getImages().containsKey("redChannelJPGImage"));

    RGB[][] redChannelImage = controller.getImages().get("redChannelJPGImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/redChannelJPGImage.jpg",
            "redChannelJPGImage");
  }

  /**
   * Tests the green channel splitting for a JPG image.
   * Verifies that the green channel is retained, and all RGB values are set
   * to the green channel's value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitGreenComponentJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.splitGreenChannel("earth", "greenChannelJPGImage");

    assertTrue(controller.getImages().containsKey("greenChannelJPGImage"));

    RGB[][] redChannelImage = controller.getImages().get("greenChannelJPGImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/greenChannelJPGImage.jpg",
            "greenChannelJPGImage");
  }

  /**
   * Tests the blue channel splitting for a JPG image.
   * Verifies that the blue channel is retained, and all RGB values are set
   * to the blue channel's value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitBlueComponentJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.splitBlueChannel("earth", "blueChannelJPGImage");

    assertTrue(controller.getImages().containsKey("blueChannelJPGImage"));

    RGB[][] redChannelImage = controller.getImages().get("blueChannelJPGImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/blueChannelJPGImage.jpg",
            "blueChannelJPGImage");
  }

  /**
   * Tests the red channel splitting for a PNG image.
   * Verifies that the red channel is retained,
   * and all RGB values are set to the red channel's value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitRedComponentPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.splitRedChannel("square", "redChannelPNGImage");

    assertTrue(controller.getImages().containsKey("redChannelPNGImage"));

    RGB[][] redChannelImage = controller.getImages().get("redChannelPNGImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/pngRes/redChannelPNGImage.png",
            "redChannelPNGImage");
  }

  /**
   * Tests the green channel splitting for a PNG image.
   * Verifies that the green channel is retained,
   * and all RGB values are set to the green channel's value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitGreenComponentPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.splitRedChannel("square", "greenChannelPNGImage");

    assertTrue(controller.getImages().containsKey("greenChannelPNGImage"));

    RGB[][] redChannelImage = controller.getImages().get("greenChannelPNGImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/pngRes/greenChannelPNGImage.png",
            "greenChannelPNGImage");
  }

  /**
   * Tests the blue channel splitting for a PNG image.
   * Verifies that the blue channel is retained,
   * and all RGB values are set to the blue channel's value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitBlueComponentPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.splitRedChannel("square", "blueChannelPNGImage");

    assertTrue(controller.getImages().containsKey("blueChannelPNGImage"));

    RGB[][] redChannelImage = controller.getImages().get("blueChannelPNGImage");

    for (RGB[] rgbs : redChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.red, rgb.green);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/pngRes/blueChannelPNGImage.png",
            "blueChannelPNGImage");
  }

  /**
   * Tests the green channel splitting for a pure green PPM image.
   * Verifies that the green channel is retained and all RGB values are
   * set to the green channel value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitGreenComponentPureGreenImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/pureGreen.ppm",
            "pureGreenImage");

    model.splitGreenChannel("pureGreenImage", "greenChannelImage");

    assertTrue(controller.getImages().containsKey("greenChannelImage"));

    RGB[][] greenChannelImage = controller.getImages().get("greenChannelImage");

    for (RGB[] rgbs : greenChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.green, rgb.red);
        assertEquals(rgb.green, rgb.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/greenChannelImage.ppm",
            "greenChannelImage");
  }

  /**
   * Tests the green channel splitting for a mixed color PPM image.
   * Ensures that only the green channel values are retained, with other
   * RGB values matching the green channel.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitGreenComponentMixedColorsImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/mixedColors.ppm",
            "mixedColorsImage");

    model.splitGreenChannel("mixedColorsImage",
            "greenChannelMixedColorsImage");

    assertTrue(controller.getImages().containsKey("greenChannelMixedColorsImage"));

    RGB[][] greenChannelImage = controller.getImages().get("greenChannelMixedColorsImage");
    RGB[][] originalImage = controller.getImages().get("mixedColorsImage");

    for (int y = 0; y < greenChannelImage.length; y++) {
      for (int x = 0; x < greenChannelImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        assertEquals(originalPixel.green, greenChannelImage[y][x].green);
        assertEquals(greenChannelImage[y][x].green, greenChannelImage[y][x].red);
        assertEquals(greenChannelImage[y][x].green, greenChannelImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/greenChannelMixedColorsImage.ppm",
            "greenChannelMixedColorsImage");
  }


  /**
   * Tests the green channel splitting for a white PPM image.
   * Verifies that the resulting image is a grayscale image with all pixel values set to 255.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitGreenComponentWhiteImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/white.ppm", "whiteImage");

    model.splitGreenChannel("whiteImage", "greenChannelWhiteImage");

    assertTrue(controller.getImages().containsKey("greenChannelWhiteImage"));

    RGB[][] greenChannelImage = controller.getImages().get("greenChannelWhiteImage");

    for (RGB[] rgbs : greenChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(255, rgb.red);
        assertEquals(255, rgb.green);
        assertEquals(255, rgb.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/greenChannelWhiteImage.ppm",
            "greenChannelWhiteImage");
  }

  /**
   * Tests the blue channel splitting for a pure blue PPM image.
   * Verifies that the blue channel is retained and all RGB values are set
   * to the blue channel value.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitBlueComponentPureBlueImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/pureBlue.ppm",
            "pureBlueImage");

    model.splitBlueChannel("pureBlueImage", "blueChannelImage");

    assertTrue(controller.getImages().containsKey("blueChannelImage"));

    RGB[][] blueChannelImage = controller.getImages().get("blueChannelImage");

    for (RGB[] rgbs : blueChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(rgb.blue, rgb.red);
        assertEquals(rgb.blue, rgb.green);
      }
    }

    controller.saveImage("resources/res/ppmRes/blueChannelImage.ppm",
            "blueChannelImage");
  }

  /**
   * Tests the blue channel splitting for a mixed color PPM image.
   * Ensures that only the blue channel values are retained, with other
   * RGB values matching the blue channel.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitBlueComponentMixedColorsImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/mixedColors.ppm",
            "mixedColorsImage");

    model.splitBlueChannel("mixedColorsImage",
            "blueChannelMixedColorsImage");

    assertTrue(controller.getImages().containsKey("blueChannelMixedColorsImage"));

    RGB[][] blueChannelImage = controller.getImages().get("blueChannelMixedColorsImage");
    RGB[][] originalImage = controller.getImages().get("mixedColorsImage");

    for (int y = 0; y < blueChannelImage.length; y++) {
      for (int x = 0; x < blueChannelImage[y].length; x++) {
        RGB originalPixel = originalImage[y][x];

        assertEquals(originalPixel.blue, blueChannelImage[y][x].blue);
        assertEquals(blueChannelImage[y][x].blue, blueChannelImage[y][x].red);
        assertEquals(blueChannelImage[y][x].blue, blueChannelImage[y][x].green);
      }
    }

    controller.saveImage("resources/res/ppmRes/blueChannelMixedColorsImage.ppm",
            "blueChannelMixedColorsImage");
  }

  /**
   * Tests the blue channel splitting for a white PPM image.
   * Verifies that the resulting image is a grayscale image with all pixel values set to 255.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testSplitBlueComponentWhiteImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/white.ppm", "whiteImage");

    model.splitBlueChannel("whiteImage", "blueChannelWhiteImage");

    assertTrue(controller.getImages().containsKey("blueChannelWhiteImage"));

    RGB[][] blueChannelImage = controller.getImages().get("blueChannelWhiteImage");

    for (RGB[] rgbs : blueChannelImage) {
      for (RGB rgb : rgbs) {
        assertEquals(255, rgb.red);
        assertEquals(255, rgb.green);
        assertEquals(255, rgb.blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/blueChannelWhiteImage.ppm",
            "blueChannelWhiteImage");
  }


}
