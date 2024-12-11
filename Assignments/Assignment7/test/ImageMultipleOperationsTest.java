import org.junit.Test;

import model.image.Image;
import model.image.ImageInterface;
import model.operationimpls.DitheringOperation;
import model.operationimpls.ImageProcessorImpl;
import model.operationinterface.ImageProcessor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * A class that tests back to back pixel operations and verifies the final pixels.
 */
public class ImageMultipleOperationsTest {

  private final ImageProcessor imageProcessor = new ImageProcessorImpl();

  @Test
  public void testMultipleOperations() {
    int[][][] pixels = {
            {{100, 150, 200}, {100, 150, 200}, {100, 150, 200}},
            {{100, 150, 200}, {100, 150, 200}, {100, 150, 200}},
            {{100, 150, 200}, {100, 150, 200}, {100, 150, 200}}
    };

    ImageInterface image = new Image(3, 3, 255, pixels);

    ImageInterface blurredImage = imageProcessor.blur(image);
    ImageInterface sharpenedImage = imageProcessor.sharpen(blurredImage);

    ImageInterface grayscaleImage =
            imageProcessor.extractComponent(sharpenedImage, "luma");

    int[][][] expectedPixels = {
            {{112, 112, 112}, {201, 201, 201}, {112, 112, 112}},
            {{201, 201, 201}, {249, 249, 249}, {201, 201, 201}},
            {{112, 112, 112}, {201, 201, 201}, {112, 112, 112}}
    };

    assertArrayEquals(expectedPixels, grayscaleImage.getPixels());
  }

  @Test
  public void testMultipleOperationsIncludingExtractComponent() {
    int[][][] pixels = {
            {{100, 150, 200}, {100, 150, 200}, {100, 150, 200}},
            {{100, 150, 200}, {100, 150, 200}, {100, 150, 200}},
            {{100, 150, 200}, {100, 150, 200}, {100, 150, 200}}
    };

    ImageInterface image = new Image(3, 3, 255, pixels);

    ImageInterface blurredImage = imageProcessor.blur(image);

    ImageInterface sharpenedImage = imageProcessor.sharpen(blurredImage);

    ImageInterface grayscaleImage =
            imageProcessor.extractComponent(sharpenedImage, "luma");

    ImageInterface redComponentImage =
            imageProcessor.extractComponent(grayscaleImage, "red");

    ImageInterface extractedImage =
            imageProcessor.extractComponent(redComponentImage, "red");

    int[][][] expectedPixels = {
            {{112, 112, 112}, {201, 201, 201}, {112, 112, 112}},
            {{201, 201, 201}, {249, 249, 249}, {201, 201, 201}},
            {{112, 112, 112}, {201, 201, 201}, {112, 112, 112}}
    };

    assertArrayEquals(expectedPixels, extractedImage.getPixels());
  }

  @Test
  public void testDitheringOperation() {
    int[][][] pixelData = {
            {{100, 100, 100}, {150, 150, 150}, {200, 200, 200}},
            {{50, 50, 50}, {180, 180, 180}, {220, 220, 220}},
            {{80, 80, 80}, {140, 140, 140}, {240, 240, 240}}
    };
    int width = 3;
    int height = 3;
    int maxValue = 255;

    ImageInterface image = new Image(width, height, maxValue, pixelData);

    DitheringOperation ditheringOperation = new DitheringOperation();
    ImageInterface result = ditheringOperation.execute(image);

    int[][][] expectedPixels = {
            {{0, 0, 0}, {255, 255, 255}, {255, 255, 255}},
            {{0, 0, 0}, {255, 255, 255}, {255, 255, 255}},
            {{0, 0, 0}, {255, 255, 255}, {255, 255, 255}}
    };

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] expectedPixel = expectedPixels[y][x];
        int[] actualPixel = result.getPixel(x, y);

        assertEquals(expectedPixel[0], actualPixel[0]);
        assertEquals(expectedPixel[1], actualPixel[1]);
        assertEquals(expectedPixel[2], actualPixel[2]);
      }
    }
  }
}
