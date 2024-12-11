import org.junit.Test;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;
import model.operationimpls.AdvancedImageProcessorImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * A class that tests back-to-back advanced operations and verifies the final pixels.
 */
public class ImageMultipleAdvancedOperationsTest {

  private final AdvancedImageProcessor advancedImageProcessor = new AdvancedImageProcessorImpl();

  @Test
  public void testMultipleAdvancedOperations() {
    int[][][] pixels = {
            {{100, 150, 200}, {150, 200, 250}, {200, 250, 100}},
            {{250, 100, 150}, {100, 150, 200}, {150, 200, 250}},
            {{200, 250, 100}, {250, 100, 150}, {100, 150, 200}}
    };

    ImageInterface image = new Image(3, 3, 255, pixels);

    ImageInterface compressedImage = advancedImageProcessor.compress(image, 50);

    ImageInterface colorCorrectedImage = advancedImageProcessor.colorCorrect(compressedImage);

    ImageInterface levelsAdjustedImage = advancedImageProcessor.adjustLevels(colorCorrectedImage,
            50, 128, 200);

    ImageInterface finalImage = advancedImageProcessor.splitView(levelsAdjustedImage,
            "blur", 50, advancedImageProcessor);

    assertNotNull(finalImage);

    assertEquals("Width should be preserved", image.getWidth(), finalImage.getWidth());
    assertEquals("Height should be preserved", image.getHeight(), finalImage.getHeight());

    int[][][] finalPixels = finalImage.getPixels();
    for (int y = 0; y < finalImage.getHeight(); y++) {
      for (int x = 0; x < finalImage.getWidth(); x++) {
        for (int c = 0; c < 3; c++) {
          int value = finalPixels[y][x][c];
          assertTrue("Pixel value should be between 0 and 255",
                  value >= 0 && value <= 255);
        }
      }
    }
  }


  @Test
  public void testMultipleAdvancedOperationsWithPixelComparison() {
    int[][][] pixels = {
            {{50, 100, 150}, {200, 150, 100}},
            {{100, 150, 200}, {150, 200, 50}}
    };

    ImageInterface image = new Image(2, 2, 255, pixels);

    ImageInterface compressedImage = advancedImageProcessor.compress(image, 0);
    assertArrayEquals("Compressed image pixels should match original pixels",
            pixels, compressedImage.getPixels());

    ImageInterface colorCorrectedImage = advancedImageProcessor.colorCorrect(compressedImage);

    int[][][] colorCorrectedPixels = colorCorrectedImage.getPixels();
    for (int y = 0; y < 2; y++) {
      for (int x = 0; x < 2; x++) {
        for (int c = 0; c < 3; c++) {
          int correctedValue = colorCorrectedPixels[y][x][c];
          assertTrue("Color-corrected pixel value should be between 0 and 255",
                  correctedValue >= 0 && correctedValue <= 255);
        }
      }
    }

    ImageInterface levelsAdjustedImage = advancedImageProcessor.adjustLevels(colorCorrectedImage,
            0, 128, 255);

    int[][][] levelsAdjustedPixels = levelsAdjustedImage.getPixels();
    for (int y = 0; y < 2; y++) {
      for (int x = 0; x < 2; x++) {
        for (int c = 0; c < 3; c++) {
          int adjustedValue = levelsAdjustedPixels[y][x][c];
          assertTrue("Levels-adjusted pixel value should be between 0 and 255",
                  adjustedValue >= 0 && adjustedValue <= 255);
        }
      }
    }

    ImageInterface finalImage = advancedImageProcessor.splitView(levelsAdjustedImage,
            "blur", 50, advancedImageProcessor);

    ImageInterface blurredImage = advancedImageProcessor.blur(levelsAdjustedImage);
    int[][][] finalPixels = finalImage.getPixels();
    int[][][] expectedFinalPixels = new int[2][2][3];

    for (int y = 0; y < 2; y++) {
      for (int x = 0; x < 2; x++) {
        if (x < 1) {
          expectedFinalPixels[y][x] = blurredImage.getPixels()[y][x];
        } else {
          expectedFinalPixels[y][x] = levelsAdjustedImage.getPixels()[y][x];
        }
      }
    }

    assertArrayEquals("Final image pixels should match expected pixels after split view",
            expectedFinalPixels, finalPixels);
  }

  @Test
  public void testChainAllNewOperations() {
    int[][][] pixels = {
            {{100, 100, 100}, {150, 150, 150}},
            {{200, 200, 200}, {250, 250, 250}}
    };

    ImageInterface image = new Image(2, 2, 255, pixels);

    ImageInterface compressedImage = advancedImageProcessor.compress(image, 40);

    ImageInterface colorCorrectedImage = advancedImageProcessor.colorCorrect(compressedImage);

    ImageInterface levelsAdjustedImage = advancedImageProcessor.adjustLevels(colorCorrectedImage,
            30, 128, 220);

    ImageInterface finalImage = advancedImageProcessor.splitView(levelsAdjustedImage,
            "sharpen", 50, advancedImageProcessor);

    assertNotNull(finalImage);
    assertEquals("Width should be preserved", image.getWidth(), finalImage.getWidth());
    assertEquals("Height should be preserved", image.getHeight(), finalImage.getHeight());
    assertEquals("Max value should be preserved",
            image.getMaxValue(), finalImage.getMaxValue());

    int[][][] finalPixels = finalImage.getPixels();
    for (int y = 0; y < finalImage.getHeight(); y++) {
      for (int x = 0; x < finalImage.getWidth(); x++) {
        for (int c = 0; c < 3; c++) {
          int value = finalPixels[y][x][c];
          assertTrue("Pixel value should be between 0 and 255",
                  value >= 0 && value <= 255);
        }
      }
    }
  }

  @Test
  public void testMultipleAdvancedOperationsIncludingExtractComponent() {
    int[][][] pixels = {
            {{120, 80, 200}, {100, 150, 200}},
            {{90, 60, 180}, {70, 130, 170}}
    };

    ImageInterface image = new Image(2, 2, 255, pixels);

    ImageInterface compressedImage = advancedImageProcessor.compress(image, 20);

    ImageInterface colorCorrectedImage = advancedImageProcessor.colorCorrect(compressedImage);

    ImageInterface levelsAdjustedImage = advancedImageProcessor.adjustLevels(colorCorrectedImage,
            50, 128, 200);

    ImageInterface redComponentImage =
            advancedImageProcessor.extractComponent(levelsAdjustedImage, "red");

    ImageInterface finalImage = advancedImageProcessor.splitView(redComponentImage,
            "sepia", 75, advancedImageProcessor);

    int[][][] expectedPixels = new int[2][2][3];

    assertNotNull(finalImage);
    assertEquals("Width should be preserved", image.getWidth(), finalImage.getWidth());
    assertEquals("Height should be preserved", image.getHeight(), finalImage.getHeight());
  }

  @Test
  public void testOperationsWithDifferentMaxValues() {
    int[][][] pixels = {
            {{25, 50, 75}, {50, 75, 100}},
            {{75, 100, 125}, {100, 125, 150}}
    };

    ImageInterface image = new Image(2, 2, 150, pixels);

    ImageInterface compressedImage = advancedImageProcessor.compress(image, 30);

    ImageInterface colorCorrectedImage = advancedImageProcessor.colorCorrect(compressedImage);

    ImageInterface levelsAdjustedImage = advancedImageProcessor.adjustLevels(colorCorrectedImage,
            20, 75, 130);

    ImageInterface finalImage = advancedImageProcessor.splitView(levelsAdjustedImage,
            "blur", 60, advancedImageProcessor);

    assertEquals("Max value should be preserved",
            image.getMaxValue(), finalImage.getMaxValue());

    int[][][] finalPixels = finalImage.getPixels();
    for (int y = 0; y < finalImage.getHeight(); y++) {
      for (int x = 0; x < finalImage.getWidth(); x++) {
        for (int c = 0; c < 3; c++) {
          int value = finalPixels[y][x][c];
          assertTrue("Pixel value should be between 0 and " + image.getMaxValue(),
                  value >= 0 && value <= image.getMaxValue());
        }
      }
    }
  }
}
