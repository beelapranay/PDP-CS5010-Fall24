import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.RGB;

/**
 * The ImageModelFlippingTest class tests the functionality of image flipping operations,
 * including horizontal and vertical flips, using the ImageModel and ImageController classes.
 */
public class ImageModelFlippingTest {

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
   * Tests the horizontal flip for a 1x1 PPM image.
   * Ensures that flipping a single pixel image does not change the pixel's position or values.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testHorizontalFlip1x1Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/1x1.ppm", "1x1Image");

    model.horizontalFlipImage("1x1Image", "flipped1x1Image");

    assertTrue(controller.getImages().containsKey("flipped1x1Image"));

    RGB[][] flippedImage = controller.getImages().get("flipped1x1Image");
    RGB[][] originalImage = controller.getImages().get("1x1Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/flipped1x1Image.ppm",
            "flipped1x1Image");
  }

  /**
   * Tests the horizontal flip for a 4x4 PPM image.
   * Verifies that all rows of the image are flipped horizontally.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testHorizontalFlip4x4Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/4x4.ppm", "4x4Image");

    model.horizontalFlipImage("4x4Image", "flipped4x4Image");

    assertTrue(controller.getImages().containsKey("flipped4x4Image"));

    RGB[][] flippedImage = controller.getImages().get("flipped4x4Image");
    RGB[][] originalImage = controller.getImages().get("4x4Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/flipped4x4Image.ppm",
            "flipped4x4Image");
  }

  /**
   * Tests the horizontal flip for a 3x2 PPM image.
   * Ensures that all rows of the image are flipped horizontally,
   * with each row being checked individually.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testHorizontalFlip3x2Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/3x2.ppm", "3x2Image");

    model.horizontalFlipImage("3x2Image", "flipped3x2Image");

    assertTrue(controller.getImages().containsKey("flipped3x2Image"));

    RGB[][] flippedImage = controller.getImages().get("flipped3x2Image");
    RGB[][] originalImage = controller.getImages().get("3x2Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/flipped3x2Image.ppm",
            "flipped3x2Image");
  }

  /**
   * Tests the horizontal flip for a large PPM image.
   * Verifies that all rows of the image are flipped horizontally.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testHorizontalFlipLargeImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/earth.ppm", "earth");

    model.horizontalFlipImage("earth", "flippedEarthImage");

    assertTrue(controller.getImages().containsKey("flippedEarthImage"));

    RGB[][] flippedImage = controller.getImages().get("flippedEarthImage");
    RGB[][] originalImage = controller.getImages().get("earth");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/flippedEarthImage.ppm",
            "flippedEarthImage");
  }


  /**
   * Tests the vertical flip for a 1x1 PPM image.
   * Ensures that flipping a single pixel image vertically does not
   * change the pixel's position or values.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testVerticalFlip1x1Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/1x1.ppm", "1x1Image");

    model.verticalFlipImage("1x1Image", "verticalFlipped1x1Image");

    assertTrue(controller.getImages().containsKey("verticalFlipped1x1Image"));

    RGB[][] flippedImage = controller.getImages().get("verticalFlipped1x1Image");
    RGB[][] originalImage = controller.getImages().get("1x1Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/verticalFlipped1x1Image.ppm",
            "verticalFlipped1x1Image");
  }

  /**
   * Tests the vertical flip for a 4x4 PPM image.
   * Verifies that all columns of the image are flipped vertically.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testVerticalFlip4x4Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/4x4.ppm", "4x4Image");

    model.verticalFlipImage("4x4Image", "verticalFlipped4x4Image");

    assertTrue(controller.getImages().containsKey("verticalFlipped4x4Image"));

    RGB[][] flippedImage = controller.getImages().get("verticalFlipped4x4Image");
    RGB[][] originalImage = controller.getImages().get("4x4Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[originalImage.length - y - 1][x].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[originalImage.length - y - 1][x].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[originalImage.length - y - 1][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/verticalFlipped4x4Image.ppm",
            "verticalFlipped4x4Image");
  }

  /**
   * Tests the vertical flip for a 3x2 PPM image.
   * Ensures that all columns of the image are flipped vertically,
   * with each column being checked individually.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testVerticalFlip3x2Image() throws IOException {
    controller.loadImage("resources/testImages/ppm/3x2.ppm", "3x2Image");

    model.verticalFlipImage("3x2Image", "verticalFlipped3x2Image");

    assertTrue(controller.getImages().containsKey("verticalFlipped3x2Image"));

    RGB[][] flippedImage = controller.getImages().get("verticalFlipped3x2Image");
    RGB[][] originalImage = controller.getImages().get("3x2Image");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[originalImage.length - y - 1][x].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[originalImage.length - y - 1][x].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[originalImage.length - y - 1][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/verticalFlipped3x2Image.ppm",
            "verticalFlipped3x2Image");
  }

  /**
   * Tests the vertical flip for a large PPM image.
   * Ensures that the image is flipped correctly by comparing the pixel values.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testVerticalFlipLargeImage() throws IOException {
    controller.loadImage("resources/testImages/ppm/earth.ppm", "earth");

    model.verticalFlipImage("earth", "verticalFlippedEarthImage");

    assertTrue(controller.getImages().containsKey("verticalFlippedEarthImage"));

    RGB[][] flippedImage = controller.getImages().get("verticalFlippedEarthImage");
    RGB[][] originalImage = controller.getImages().get("earth");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[originalImage.length - y - 1][x].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[originalImage.length - y - 1][x].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[originalImage.length - y - 1][x].blue);
      }
    }

    controller.saveImage("resources/res/ppmRes/verticalFlippedEarthImage.ppm",
            "verticalFlippedEarthImage");
  }

  /**
   * Tests the vertical flip for a large JPG image.
   * Verifies that all rows of the JPG image are flipped vertically.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testVerticalFlipJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.verticalFlipImage("earth", "verticalFlippedEarthJPG");

    assertTrue(controller.getImages().containsKey("verticalFlippedEarthJPG"));

    RGB[][] flippedImage = controller.getImages().get("verticalFlippedEarthJPG");
    RGB[][] originalImage = controller.getImages().get("earth");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[originalImage.length - y - 1][x].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[originalImage.length - y - 1][x].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[originalImage.length - y - 1][x].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/verticalFlippedEarthJPG.jpg",
            "verticalFlippedEarthJPG");
  }

  /**
   * Tests the horizontal flip for a JPG image.
   * Verifies that all rows of the JPG image are flipped horizontally.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testHorizontalFlipJPGImage() throws IOException {
    controller.loadImage("resources/testImages/jpg/earth.jpg", "earth");

    model.horizontalFlipImage("earth", "horizontalFlippedEarthJPG");

    assertTrue(controller.getImages().containsKey("horizontalFlippedEarthJPG"));

    RGB[][] flippedImage = controller.getImages().get("horizontalFlippedEarthJPG");
    RGB[][] originalImage = controller.getImages().get("earth");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/jpgRes/horizontalFlippedEarthJPG.jpg",
            "horizontalFlippedEarthJPG");
  }

  /**
   * Tests the vertical flip for a PNG image.
   * Ensures that the image is flipped vertically and checks all pixel values.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testVerticalFlipPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.verticalFlipImage("square", "verticalFlippedSquarePNG");

    assertTrue(controller.getImages().containsKey("verticalFlippedSquarePNG"));

    RGB[][] flippedImage = controller.getImages().get("verticalFlippedSquarePNG");
    RGB[][] originalImage = controller.getImages().get("square");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[originalImage.length - y - 1][x].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[originalImage.length - y - 1][x].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[originalImage.length - y - 1][x].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/verticalFlippedSquarePNG.png",
            "verticalFlippedSquarePNG");
  }

  /**
   * Tests the horizontal flip for a PNG image.
   * Verifies that all rows of the PNG image are flipped horizontally.
   *
   * @throws IOException if there is an issue loading or saving the image.
   */
  @Test
  public void testHorizontalFlipPNGImage() throws IOException {
    controller.loadImage("resources/testImages/png/square.png", "square");

    model.horizontalFlipImage("square", "horizontalFlippedSquarePNG");

    assertTrue(controller.getImages().containsKey("horizontalFlippedSquarePNG"));

    RGB[][] flippedImage = controller.getImages().get("horizontalFlippedSquarePNG");
    RGB[][] originalImage = controller.getImages().get("square");

    for (int y = 0; y < originalImage.length; y++) {
      for (int x = 0; x < originalImage[0].length; x++) {
        assertEquals(originalImage[y][x].red,
                flippedImage[y][originalImage[0].length - x - 1].red);
        assertEquals(originalImage[y][x].green,
                flippedImage[y][originalImage[0].length - x - 1].green);
        assertEquals(originalImage[y][x].blue,
                flippedImage[y][originalImage[0].length - x - 1].blue);
      }
    }

    controller.saveImage("resources/res/pngRes/horizontalFlippedSquarePNG.png"
            , "horizontalFlippedSquarePNG");
  }
}
