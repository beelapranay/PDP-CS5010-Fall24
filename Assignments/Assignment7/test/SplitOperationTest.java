import org.junit.Test;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageProcessor;
import model.operationimpls.ImageProcessorImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * A class that tests the SplitOperation class via the ImageProcessor interface.
 */
public class SplitOperationTest {

  private final ImageProcessor imageProcessor = new ImageProcessorImpl();

  @Test
  public void testSplitRGBComponents() {
    int[][][] pixels = {
            {{100, 150, 200}, {50, 75, 100}}
    };
    Image image = new Image(2, 1, 255, pixels);


    ImageInterface[] result = imageProcessor.split(image);

    assertEquals(3, result.length);

    int[][][] expectedRedPixels = {
            {{100, 100, 100}, {50, 50, 50}}
    };
    int[][][] expectedGreenPixels = {
            {{150, 150, 150}, {75, 75, 75}}
    };
    int[][][] expectedBluePixels = {
            {{200, 200, 200}, {100, 100, 100}}
    };

    assertArrayEquals(expectedRedPixels, result[0].getPixels());
    assertArrayEquals(expectedGreenPixels, result[1].getPixels());
    assertArrayEquals(expectedBluePixels, result[2].getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSplitNullImage() {
    imageProcessor.split(null);
  }

  @Test
  public void testSplitGrayscaleImage() {
    int[][][] pixels = {
            {{100, 100, 100}, {50, 50, 50}}
    };
    Image image = new Image(2, 1, 255, pixels);


    ImageInterface[] result = imageProcessor.split(image);

    int[][][] expectedRedPixels = {
            {{100, 100, 100}, {50, 50, 50}}
    };
    int[][][] expectedGreenPixels = {
            {{100, 100, 100}, {50, 50, 50}}
    };
    int[][][] expectedBluePixels = {
            {{100, 100, 100}, {50, 50, 50}}
    };

    assertArrayEquals(expectedRedPixels, result[0].getPixels());
    assertArrayEquals(expectedGreenPixels, result[1].getPixels());
    assertArrayEquals(expectedBluePixels, result[2].getPixels());
  }

  @Test
  public void testSplitWithLargeImage() {
    int[][][] pixels = new int[5][5][3];
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        pixels[y][x][0] = 100;
        pixels[y][x][1] = 150;
        pixels[y][x][2] = 200;
      }
    }
    Image image = new Image(5, 5, 255, pixels);


    ImageInterface[] result = imageProcessor.split(image);

    assertEquals(3, result.length);

    int[][][] expectedRedPixels = new int[5][5][3];
    int[][][] expectedGreenPixels = new int[5][5][3];
    int[][][] expectedBluePixels = new int[5][5][3];

    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        expectedRedPixels[y][x][0] = 100;
        expectedRedPixels[y][x][1] = 100;
        expectedRedPixels[y][x][2] = 100;

        expectedGreenPixels[y][x][0] = 150;
        expectedGreenPixels[y][x][1] = 150;
        expectedGreenPixels[y][x][2] = 150;

        expectedBluePixels[y][x][0] = 200;
        expectedBluePixels[y][x][1] = 200;
        expectedBluePixels[y][x][2] = 200;
      }
    }

    assertArrayEquals(expectedRedPixels, result[0].getPixels());
    assertArrayEquals(expectedGreenPixels, result[1].getPixels());
    assertArrayEquals(expectedBluePixels, result[2].getPixels());
  }
}