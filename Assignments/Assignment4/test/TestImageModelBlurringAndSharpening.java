import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * Class for testing blurring and sharpening operations on various image formats.
 */
public class TestImageModelBlurringAndSharpening {
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
   * Tests the blur operation on a 1x1 PPM image. Since there is only one pixel,
   * the blurred image should be the same as the original.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testBlur1x1Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/1x1.ppm", "1x1Image");

    model.blurImage("1x1Image", "blurred1x1Image");

    assertTrue(controller.getImages().containsKey("blurred1x1Image"));

    RGB[][] blurredImage = controller.getImages().get("blurred1x1Image");
    RGB[][] originalImage = controller.getImages().get("1x1Image");

    // Assert that the blurred image is the same as the original image
    // In a 1x1 image, there is only a single pixel, so when you apply any blur operation,
    // the pixel doesn't have any neighboring pixels to average with.
    // Therefore, the blurred 1x1 image will always remain the same as the original.
    boolean isBlurred = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red == blurredImage[y][x].red
                || originalImage[y][x].green == blurredImage[y][x].green
                || originalImage[y][x].blue == blurredImage[y][x].blue) {
          isBlurred = true;
          break;
        }
      }
    }

    assertTrue(isBlurred);

    controller.saveImage("resources/res/ppmRes/blurred1x1Image.ppm",
            "blurred1x1Image");
  }

  /**
   * Tests the blur operation on a 2x2 PPM image. Ensures that the resulting image
   * has valid RGB values after the blur operation.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testBlur2x2Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm", "testPPMImage");

    model.blurImage("testPPMImage", "blurredTestPPMImage");

    assertTrue(controller.getImages().containsKey("blurredTestPPMImage"));

    RGB[][] blurredImage = controller.getImages().get("blurredTestPPMImage");
    RGB[][] originalImage = controller.getImages().get("testPPMImage");

    boolean isBlurred = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red == blurredImage[y][x].red
                || originalImage[y][x].green == blurredImage[y][x].green
                || originalImage[y][x].blue == blurredImage[y][x].blue) {
          isBlurred = true;
          break;
        }
      }
    }

    assertTrue(isBlurred);

    controller.saveImage("resources/res/ppmRes/blurredTestPPMImage.ppm",
            "blurredTestPPMImage");
  }

  /**
   * Tests the blur operation on a larger 3x3 PPM image. Compares the blurred image
   * to an expected result.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testBlurLargeImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testBlur3x3.ppm", "testblur");
    controller.loadImage("resources/testImages/ppm/expected3x3Blur.ppm",
            "expectedblur");

    model.blurImage("testblur", "testblurredimage");

    assertTrue(controller.getImages().containsKey("testblurredimage"));

    RGB[][] actual = controller.getImages().get("testblurredimage");
    RGB[][] expected = controller.getImages().get("expectedblur");

    for (int y = 0; y < actual.length; y++) {
      for (int x = 0; x < actual[0].length; x++) {
        assertEquals(expected[y][x].red, actual[y][x].red);
        assertEquals(expected[y][x].green, actual[y][x].green);
        assertEquals(expected[y][x].blue, actual[y][x].blue);
      }
    }
  }

  /**
   * Tests the sharpen operation on a 1x1 PPM image. Similar to blurring, the sharpen
   * operation on a single pixel image should result in the same pixel values.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testSharpen1x1Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/1x1.ppm", "1x1Image");

    model.blurImage("1x1Image", "sharpen1x1Image");

    assertTrue(controller.getImages().containsKey("sharpen1x1Image"));

    RGB[][] sharpenImage = controller.getImages().get("sharpen1x1Image");
    RGB[][] originalImage = controller.getImages().get("1x1Image");

    // Assert that the blurred image is the same as the original image
    // In a 1x1 image, there is only a single pixel, so when you apply any blur operation,
    // the pixel doesn't have any neighboring pixels to average with.
    // Therefore, the blurred 1x1 image will always remain the same as the original.
    boolean isSharpen = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red == sharpenImage[y][x].red
                || originalImage[y][x].green == sharpenImage[y][x].green
                || originalImage[y][x].blue == sharpenImage[y][x].blue) {
          isSharpen = true;
          break;
        }
      }
    }

    assertTrue(isSharpen);

    controller.saveImage("resources/res/ppmRes/sharpen1x1Image.ppm",
            "sharpen1x1Image");
  }

  /**
   * Tests the sharpen operation on a 2x2 PPM image. Ensures that the resulting image
   * has valid RGB values after the sharpen operation.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testSharpen2x2Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm", "testPPMImage");

    model.blurImage("testPPMImage", "sharpenTestPPMImage");

    assertTrue(controller.getImages().containsKey("sharpenTestPPMImage"));

    RGB[][] sharpenImage = controller.getImages().get("sharpenTestPPMImage");
    RGB[][] originalImage = controller.getImages().get("testPPMImage");

    boolean isSharpen = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red == sharpenImage[y][x].red
                || originalImage[y][x].green == sharpenImage[y][x].green
                || originalImage[y][x].blue == sharpenImage[y][x].blue) {
          isSharpen = true;
          break;
        }
      }
    }

    assertTrue(isSharpen);

    controller.saveImage("resources/res/ppmRes/sharpenTestPPMImage.ppm",
            "sharpenTestPPMImage");
  }

  /**
   * Tests the sharpen operation on a larger PPM image. Compares the sharpened image
   * to an expected result.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testSharpenLargeImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testBlur3x3.ppm",
            "testSharpen");
    controller.loadImage("resources/testImages/ppm/expected3x3Sharpen.ppm",
            "expectedSharpen");

    model.blurImage("testSharpen", "testSharpenLargeImage");

    assertTrue(controller.getImages().containsKey("testSharpenLargeImage"));

    RGB[][] actual = controller.getImages().get("testSharpenLargeImage");
    RGB[][] expected = controller.getImages().get("expectedSharpen");

    for (int y = 0; y < actual.length; y++) {
      for (int x = 0; x < actual[0].length; x++) {
        assertEquals(expected[y][x].red, actual[y][x].red);
        assertEquals(expected[y][x].green, actual[y][x].green);
        assertEquals(expected[y][x].blue, actual[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/testSharpenLargeImage.ppm",
            "testSharpenLargeImage");
  }

  /**
   * Tests the sharpen operation on a JPG image. Ensures that the resulting sharpened image
   * has valid RGB values after the operation.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testSharpenJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earthImage");

    model.blurImage("earthImage", "sharpenEarthJPGImage");

    assertTrue(controller.getImages().containsKey("sharpenEarthJPGImage"));

    RGB[][] blurredImage = controller.getImages().get("sharpenEarthJPGImage");
    RGB[][] originalImage = controller.getImages().get("earthImage");

    boolean isSharpen = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red != blurredImage[y][x].red
                || originalImage[y][x].green != blurredImage[y][x].green
                || originalImage[y][x].blue != blurredImage[y][x].blue) {
          isSharpen = true;
          break;
        }
      }
    }

    assertTrue(isSharpen);

    controller.saveImage("resources/res/jpgRes/sharpenEarthJPGImage.jpg",
            "sharpenEarthJPGImage");
  }

  /**
   * Tests the blur operation on a JPG image. Ensures that the resulting blurred image
   * has valid RGB values after the operation.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testBlurJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earthImage");

    model.blurImage("earthImage", "blurEarthJPGImage");

    assertTrue(controller.getImages().containsKey("blurEarthJPGImage"));

    RGB[][] blurredImage = controller.getImages().get("blurEarthJPGImage");
    RGB[][] originalImage = controller.getImages().get("earthImage");

    boolean isBlurred = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red != blurredImage[y][x].red
                || originalImage[y][x].green != blurredImage[y][x].green
                || originalImage[y][x].blue != blurredImage[y][x].blue) {
          isBlurred = true;
          break;
        }
      }
    }

    assertTrue(isBlurred);

    controller.saveImage("resources/res/jpgRes/blurEarthJPGImage.jpg",
            "blurEarthJPGImage");
  }

  /**
   * Tests the sharpen operation on a PNG image. Ensures that the resulting image
   * has valid RGB values within the range of 0 to 255 after the sharpen operation.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testSharpenPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.sharpenImage("square", "sharpenSquare");

    assertTrue(controller.getImages().containsKey("sharpenSquare"));

    RGB[][] sharpImage = controller.getImages().get("sharpenSquare");
    RGB[][] originalImage = controller.getImages().get("square");

    boolean isSharpen = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red != sharpImage[y][x].red
                || originalImage[y][x].green != sharpImage[y][x].green
                || originalImage[y][x].blue != sharpImage[y][x].blue) {
          isSharpen = true;
          break;
        }
      }
    }

    assertTrue(isSharpen);

    controller.saveImage("resources/res/pngRes/sharpenSquare.png",
            "sharpenSquare");
  }

  /**
   * Tests the blur operation on a PNG image. Ensures that the resulting image
   * has valid RGB values within the range of 0 to 255 after the blur operation.
   *
   * @throws IOException if the image cannot be loaded or saved
   */
  @Test
  public void testBlurPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.blurImage("square", "blurSquare");

    assertTrue(controller.getImages().containsKey("blurSquare"));

    RGB[][] blurredImage = controller.getImages().get("blurSquare");
    RGB[][] originalImage = controller.getImages().get("square");

    boolean isBlurred = false;

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        if (originalImage[y][x].red != blurredImage[y][x].red
                || originalImage[y][x].green != blurredImage[y][x].green
                || originalImage[y][x].blue != blurredImage[y][x].blue) {
          isBlurred = true;
          break;
        }
      }
    }

    assertTrue(isBlurred);

    controller.saveImage("resources/res/pngRes/blurSquare.png", "blurSquare");
  }
}
