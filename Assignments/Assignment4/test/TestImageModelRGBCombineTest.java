import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * Test class for verifying the functionality of combining images from
 * the red, green, and blue channels into a single image.
 * This ensures that the image combination method in the model works as expected
 * across various image sizes and formats.
 */
public class TestImageModelRGBCombineTest {
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
   * Tests the greyscale combination for a small image. Verifies that the red, green, and blue
   * components of the image, when combined, match the original image.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscaleSmallImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/testppm.ppm", "testppm");

    model.splitRedChannel("testppm", "redImage");
    model.splitGreenChannel("testppm", "greenImage");
    model.splitBlueChannel("testppm", "blueImage");

    model.combineGreyscale("combinedImage",
            "redImage", "greenImage", "blueImage");

    assertTrue(controller.getImages().containsKey("combinedImage"));

    RGB[][] combinedImage = controller.getImages().get("combinedImage");
    RGB[][] originalImage = controller.getImages().get("testppm");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/combinedImage.ppm",
            "combinedImage");
  }

  /**
   * Tests the greyscale combination for a 1x1 image. Ensures that the combined image is the same
   * as the original when split and recombined.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscale1x1Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/1x1.ppm", "1x1Image");

    model.splitRedChannel("1x1Image", "redImage1x1");
    model.splitGreenChannel("1x1Image", "greenImage1x1");
    model.splitBlueChannel("1x1Image", "blueImage1x1");

    model.combineGreyscale("combined1x1Image",
            "redImage1x1", "greenImage1x1", "blueImage1x1");

    assertTrue(controller.getImages().containsKey("combined1x1Image"));

    RGB[][] combinedImage = controller.getImages().get("combined1x1Image");
    RGB[][] originalImage = controller.getImages().get("1x1Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/combined1x1Image.ppm",
            "combined1x1Image");
  }

  /**
   * Tests the greyscale combination for a 3x2 image. Verifies that splitting and recombining
   * the red, green, and blue channels results in the original image.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscale3x2Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/3x2.ppm", "3x2Image");

    model.splitRedChannel("3x2Image", "redImage3x2");
    model.splitGreenChannel("3x2Image", "greenImage3x2");
    model.splitBlueChannel("3x2Image", "blueImage3x2");

    model.combineGreyscale("combined3x2Image",
            "redImage3x2", "greenImage3x2", "blueImage3x2");

    assertTrue(controller.getImages().containsKey("combined3x2Image"));

    RGB[][] combinedImage = controller.getImages().get("combined3x2Image");
    RGB[][] originalImage = controller.getImages().get("3x2Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/combined3x2Image.ppm",
            "combined3x2Image");
  }

  /**
   * Tests the greyscale combination for a single-pixel image. Ensures that
   * splitting and recombining
   * the channels for a single pixel results in the original pixel.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscaleSinglePixelImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/singlePixel.ppm",
            "singlePixelImage");

    model.splitRedChannel("singlePixelImage",
            "redImageSinglePixel");
    model.splitGreenChannel("singlePixelImage",
            "greenImageSinglePixel");
    model.splitBlueChannel("singlePixelImage",
            "blueImageSinglePixel");

    model.combineGreyscale("combinedSinglePixelImage",
            "redImageSinglePixel",
            "greenImageSinglePixel", "blueImageSinglePixel");

    assertTrue(controller.getImages().containsKey("combinedSinglePixelImage"));

    RGB[][] combinedImage = controller.getImages().get("combinedSinglePixelImage");
    RGB[][] originalImage = controller.getImages().get("singlePixelImage");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/combinedSinglePixelImage.ppm",
            "combinedSinglePixelImage");
  }

  /**
   * Tests the greyscale combination for a large image. Verifies that the combined image matches
   * the original after splitting and recombining the red, green, and blue channels.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscaleLargePixelImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/earth.ppm", "earth");

    model.splitRedChannel("earth", "redEarthImage");
    model.splitGreenChannel("earth", "greenEarthImage");
    model.splitBlueChannel("earth", "blueEarthImage");

    model.combineGreyscale("combinedEarthImage",
            "redEarthImage", "greenEarthImage", "blueEarthImage");

    assertTrue(controller.getImages().containsKey("combinedEarthImage"));

    RGB[][] combinedImage = controller.getImages().get("combinedEarthImage");
    RGB[][] originalImage = controller.getImages().get("earth");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/combinedEarthImage.ppm",
            "combinedEarthImage");
  }

  /**
   * Tests the greyscale combination for JPG images. Ensures that splitting and recombining the
   * red, green, and blue channels produces the original image.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscaleJPGImages() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.splitRedChannel("earth", "redEarthImage");
    model.splitGreenChannel("earth", "greenEarthImage");
    model.splitBlueChannel("earth", "blueEarthImage");

    model.combineGreyscale("combinedEarthImage",
            "redEarthImage", "greenEarthImage", "blueEarthImage");

    assertTrue(controller.getImages().containsKey("combinedEarthImage"));

    RGB[][] combinedImage = controller.getImages().get("combinedEarthImage");
    RGB[][] originalImage = controller.getImages().get("earth");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/combinedEarthImage.jpg",
            "combinedEarthImage");
  }

  /**
   * Tests the greyscale combination for PNG images. Verifies that the combined image is the same
   * as the original after splitting and recombining the red, green, and blue channels.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscalePNGImages() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.splitRedChannel("square", "redSquareImage");
    model.splitGreenChannel("square", "greenSquareImage");
    model.splitBlueChannel("square", "blueSquareImage");

    model.combineGreyscale("combinedSquareImage",
            "redSquareImage", "greenSquareImage", "blueSquareImage");

    assertTrue(controller.getImages().containsKey("combinedSquareImage"));

    RGB[][] combinedImage = controller.getImages().get("combinedSquareImage");
    RGB[][] originalImage = controller.getImages().get("square");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/combinedSquareImage.png",
            "combinedSquareImage");
  }

  /**
   * Tests the greyscale combination for a custom 2x2 array. Ensures that the split and recombined
   * images match the original array.
   *
   * @throws IOException if there is an error loading or saving the image
   */
  @Test
  public void testCombineGreyscaleArrays() throws IOException {
    RGB[][] imageMatrix = {
            {new RGB(255, 0, 0), new RGB(0, 255, 0)},
            {new RGB(0, 0, 255), new RGB(255, 255, 0)}
    };

    controller.getImages().put("newImage", imageMatrix);

    model.splitRedChannel("newImage", "redImage");
    model.splitGreenChannel("newImage", "greenImage");
    model.splitBlueChannel("newImage", "blueImage");

    model.combineGreyscale("combinedMatrixImage",
            "redImage", "greenImage", "blueImage");

    assertTrue(controller.getImages().containsKey("combinedMatrixImage"));

    RGB[][] combinedImage = controller.getImages().get("combinedMatrixImage");
    RGB[][] originalImage = controller.getImages().get("newImage");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red, combinedImage[y][x].red);
        assertEquals(originalImage[y][x].green, combinedImage[y][x].green);
        assertEquals(originalImage[y][x].blue, combinedImage[y][x].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/redImage.png", "redImage");
    controller.saveImage("resources/res/pngRes/greenImage.png", "greenImage");
    controller.saveImage("resources/res/pngRes/blueImage.png", "blueImage");

    controller.saveImage("resources/res/pngRes/combinedMatrixImage.png",
            "combinedMatrixImage");
  }
}
