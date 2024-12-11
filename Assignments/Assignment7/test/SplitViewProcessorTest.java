import org.junit.Test;

import model.image.Image;
import model.image.ImageInterface;
import model.operationimpls.AdvancedImageProcessorImpl;
import model.operationinterface.AdvancedImageProcessor;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * A class that tests the SplitViewProcessor class via the AdvancedImageProcessor interface.
 */
public class SplitViewProcessorTest {

  private final AdvancedImageProcessor advancedImageProcessor = new AdvancedImageProcessorImpl();

  @Test
  public void testSplitViewWithBlurOperation() {
    int[][][] pixels = {
            {{100, 150, 200}, {200, 250, 100}, {50, 75, 100}, {150, 100, 50}}
    };
    Image image = new Image(4, 1, 255, pixels);

    ImageInterface result = advancedImageProcessor.splitView(image, "blur", 50);

    assertNotNull(result);

    int[][][] originalPixels = image.getPixels();
    int[][][] resultPixels = result.getPixels();

    ImageInterface blurredImage = advancedImageProcessor.blur(image);
    int[][][] blurredPixels = blurredImage.getPixels();

    int splitPixel = (int) ((50 / 100.0) * image.getWidth());

    for (int x = 0; x < image.getWidth(); x++) {
      if (x < splitPixel) {
        assertArrayEquals("Pixel " + x + " should be blurred",
                blurredPixels[0][x], resultPixels[0][x]);
      } else {
        assertArrayEquals("Pixel " + x + " should be original",
                originalPixels[0][x], resultPixels[0][x]);
      }
    }
  }

  @Test
  public void testSplitViewWithLevelsAdjustOperation() {
    int[][][] pixels = {
            {{50, 100, 150}, {200, 150, 100}, {100, 150, 200}, {150, 200, 50}}
    };
    Image image = new Image(4, 1, 255, pixels);

    // Parameters for levels-adjust operation
    int blackPoint = 50;
    int midPoint = 128;
    int whitePoint = 200;

    ImageInterface result = advancedImageProcessor.splitView(image, "levels-adjust",
            50, blackPoint, midPoint, whitePoint);

    assertNotNull(result);

    int[][][] originalPixels = image.getPixels();
    int[][][] resultPixels = result.getPixels();

    ImageInterface adjustedImage =
            advancedImageProcessor.adjustLevels(image, blackPoint, midPoint, whitePoint);
    int[][][] adjustedPixels = adjustedImage.getPixels();

    int splitPixel = (int) ((50 / 100.0) * image.getWidth());

    for (int x = 0; x < image.getWidth(); x++) {
      if (x < splitPixel) {
        assertArrayEquals("Pixel " + x + " should be levels-adjusted",
                adjustedPixels[0][x], resultPixels[0][x]);
      } else {
        assertArrayEquals("Pixel " + x + " should be original",
                originalPixels[0][x], resultPixels[0][x]);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSplitViewWithNullImage() {
    advancedImageProcessor.splitView(null, "blur", 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSplitViewWithInvalidSplitPosition() {
    int[][][] pixels = {{{100, 150, 200}}};
    Image image = new Image(1, 1, 255, pixels);

    advancedImageProcessor.splitView(image, "blur", -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSplitViewWithUnsupportedOperation() {
    int[][][] pixels = {{{100, 150, 200}}};
    Image image = new Image(1, 1, 255, pixels);

    advancedImageProcessor.splitView(image, "unsupported-operation", 50);
  }

  @Test
  public void testSplitViewWithComponentExtraction() {
    int[][][] pixels = {
            {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}, {100, 110, 120}}
    };
    Image image = new Image(4, 1, 255, pixels);

    ImageInterface result =
            advancedImageProcessor.splitView(image, "red-component", 25);

    assertNotNull(result);

    int[][][] originalPixels = image.getPixels();
    int[][][] resultPixels = result.getPixels();

    ImageInterface redComponentImage =
            advancedImageProcessor.extractComponent(image, "red");
    int[][][] redPixels = redComponentImage.getPixels();

    int splitPixel = (int) ((25 / 100.0) * image.getWidth());

    for (int x = 0; x < image.getWidth(); x++) {
      if (x < splitPixel) {
        assertArrayEquals("Pixel " + x + " should be red component",
                redPixels[0][x], resultPixels[0][x]);
      } else {
        assertArrayEquals("Pixel " + x + " should be original",
                originalPixels[0][x], resultPixels[0][x]);
      }
    }
  }

  @Test
  public void testSplitViewWithMaxSplitPosition() {
    int[][][] pixels = {{{100, 150, 200}, {50, 75, 100}}};
    Image image = new Image(2, 1, 255, pixels);

    ImageInterface result =
            advancedImageProcessor.splitView(image, "sepia", 100);

    ImageInterface sepiaImage = advancedImageProcessor.sepia(image);

    assertArrayEquals("Entire image should be processed",
            sepiaImage.getPixels(), result.getPixels());
  }

  @Test
  public void testSplitViewWithPartialSepiaSplitPosition() {
    // Original pixels (for a simple 2x2 image)
    int[][][] pixels = {
            {{100, 150, 200}, {200, 250, 100}},
            {{50, 75, 125}, {125, 100, 150}}
    };
    Image image = new Image(2, 2, 255, pixels);

    int splitPosition = 50;

    ImageInterface result =
            advancedImageProcessor.splitView(image, "sepia", splitPosition);

    assertNotNull(result);

    int[][][] resultPixels = result.getPixels();
    ImageInterface sepiaImage = advancedImageProcessor.sepia(image);
    int[][][] sepiaPixels = sepiaImage.getPixels();

    int splitPixel = (int) ((splitPosition / 100.0) * image.getWidth());

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < splitPixel; x++) {
        assertArrayEquals("Pixel at [" + y + "][" + x + "] should be sepia-toned",
                sepiaPixels[y][x], resultPixels[y][x]);
      }
    }

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = splitPixel; x < image.getWidth(); x++) {
        assertArrayEquals("Pixel at [" + y + "][" + x + "] should be original",
                pixels[y][x], resultPixels[y][x]);
      }
    }
  }

  @Test
  public void testSplitViewWithMinSplitPosition() {
    int[][][] pixels = {{{100, 150, 200}, {50, 75, 100}}};
    Image image = new Image(2, 1, 255, pixels);

    ImageInterface result = advancedImageProcessor.splitView(image, "sepia", 0);

    assertArrayEquals("Entire image should be original",
            image.getPixels(), result.getPixels());
  }


  @Test
  public void testSplitViewWithSharpenOperation() {
    int[][][] pixels = {
            {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}, {100, 110, 120}}
    };
    Image image = new Image(4, 1, 255, pixels);

    ImageInterface result =
            advancedImageProcessor.splitView(image, "sharpen", 75);

    assertNotNull(result);

    int[][][] originalPixels = image.getPixels();
    int[][][] resultPixels = result.getPixels();

    ImageInterface sharpenedImage = advancedImageProcessor.sharpen(image);
    int[][][] sharpenedPixels = sharpenedImage.getPixels();

    int splitPixel = (int) ((75 / 100.0) * image.getWidth());

    for (int x = 0; x < image.getWidth(); x++) {
      if (x < splitPixel) {
        assertArrayEquals("Pixel " + x + " should be sharpened",
                sharpenedPixels[0][x], resultPixels[0][x]);
      } else {
        assertArrayEquals("Pixel " + x + " should be original",
                originalPixels[0][x], resultPixels[0][x]);
      }
    }
  }

  @Test
  public void testSplitViewPreservesImageProperties() {
    int[][][] pixels = {
            {{100, 150, 200}, {200, 250, 100}, {50, 75, 100}, {150, 100, 50}}
    };
    Image image = new Image(4, 1, 255, pixels);

    ImageInterface result = advancedImageProcessor.splitView(image, "blur", 50);

    assertEquals("Width should be preserved", image.getWidth(), result.getWidth());
    assertEquals("Height should be preserved", image.getHeight(), result.getHeight());
    assertEquals("Max value should be preserved",
            image.getMaxValue(), result.getMaxValue());
  }

  @Test
  public void testSplitViewWithHorizontalSplit() {
    int[][][] pixels = {
            {{10, 20, 30}, {40, 50, 60}},
            {{70, 80, 90}, {100, 110, 120}}
    };
    Image image = new Image(2, 2, 255, pixels);

    ImageInterface result = advancedImageProcessor.splitView(image, "blur", 50);

    ImageInterface blurredImage = advancedImageProcessor.blur(image);
    int[][][] resultPixels = result.getPixels();
    int[][][] blurredPixels = blurredImage.getPixels();

    int splitPixel = (int) ((50 / 100.0) * image.getWidth());

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        if (x < splitPixel) {
          assertArrayEquals("Pixel [" + y + "][" + x + "] should be blurred",
                  blurredPixels[y][x], resultPixels[y][x]);
        } else {
          assertArrayEquals("Pixel [" + y + "][" + x + "] should be original",
                  image.getPixels()[y][x], resultPixels[y][x]);
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSplitViewWithInvalidOperationName() {
    int[][][] pixels = {{{100, 150, 200}, {50, 75, 100}}};
    Image image = new Image(2, 1, 255, pixels);

    advancedImageProcessor.splitView(image, "nonexistentOperation", 50);
  }
}
