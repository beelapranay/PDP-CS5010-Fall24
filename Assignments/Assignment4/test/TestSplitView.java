import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import controller.ImageController;
import model.ImageModel;
import model.ImageModelInterface;
import model.RGB;
import view.ImageView;
import view.ImageViewInterface;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for image processing transformations with a split view applied to the image.
 * Tests validate transformations like blurring, sharpening, sepia tone, and various grayscale
 * transformations with a split effect where only a portion of the image is modified.
 */
public class TestSplitView {

  private ImageController imageController;
  private ImageModelInterface imageModel;
  private ImageViewInterface imageView;
  private String originalImageName;
  private String transformedImageName;
  private int splitPercentage;

  /**
   * Set up the model and controller for the tests.
   */
  @Before
  public void setUp() throws IOException {
    imageController = new ImageController();
    imageView = new ImageView(imageController);
    imageModel = new ImageModel(imageController, imageView);
    originalImageName = "testImage";
    transformedImageName = "transformedImage";
    splitPercentage = 50;
    imageController.loadImage("resources/testImages/jpg/earth.jpg", originalImageName);
  }

  /**
   * Tests blur transformation on the split region of the image using a Gaussian kernel.
   */
  @Test
  public void testApplyBlurWithSplit() {
    imageModel.blurOrSharpen(originalImageName, transformedImageName,
            splitPercentage, "blur");

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] blurredPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    double[][] blurKernel = new double[][]{
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };

    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < expectedSplitPoint; x++) {
        double expectedRed = 0;
        double expectedGreen = 0;
        double expectedBlue = 0;

        for (int ky = -1; ky <= 1; ky++) {
          for (int kx = -1; kx <= 1; kx++) {
            RGB neighbor = originalPixels[y + ky][x + kx];
            expectedRed += neighbor.red * blurKernel[ky + 1][kx + 1];
            expectedGreen += neighbor.green * blurKernel[ky + 1][kx + 1];
            expectedBlue += neighbor.blue * blurKernel[ky + 1][kx + 1];
          }
        }

        RGB blurredPixel = blurredPixels[y][x];
        assertEquals("Blurred red mismatch at (" + y + ", " + x + ")",
                (int) Math.min(Math.max(expectedRed, 0), 255), blurredPixel.red);
        assertEquals("Blurred green mismatch at (" + y + ", " + x + ")",
                (int) Math.min(Math.max(expectedGreen, 0), 255), blurredPixel.green);
        assertEquals("Blurred blue mismatch at (" + y + ", " + x + ")",
                (int) Math.min(Math.max(expectedBlue, 0), 255), blurredPixel.blue);
      }
    }
  }

  /**
   * Tests sharpen transformation on the split region of the image using a sharpen kernel.
   */
  @Test
  public void testApplySharpenWithSplit() {
    imageModel.blurOrSharpen(originalImageName, transformedImageName,
            splitPercentage, "sharpen");

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] sharpenedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    final double[][] sharpenKernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };

    for (int y = 2; y < height - 2; y++) {
      for (int x = 2; x < expectedSplitPoint; x++) {
        double expectedRed = 0;
        double expectedGreen = 0;
        double expectedBlue = 0;

        for (int ky = -2; ky <= 2; ky++) {
          for (int kx = -2; kx <= 2; kx++) {
            RGB neighbor = originalPixels[y + ky][x + kx];
            expectedRed += neighbor.red * sharpenKernel[ky + 2][kx + 2];
            expectedGreen += neighbor.green * sharpenKernel[ky + 2][kx + 2];
            expectedBlue += neighbor.blue * sharpenKernel[ky + 2][kx + 2];
          }
        }

        RGB sharpenedPixel = sharpenedPixels[y][x];
        assertEquals((int) Math.min(Math.max(expectedRed, 0), 255), sharpenedPixel.red);
        assertEquals((int) Math.min(Math.max(expectedGreen, 0), 255), sharpenedPixel.green);
        assertEquals((int) Math.min(Math.max(expectedBlue, 0), 255), sharpenedPixel.blue);
      }
    }

  }

  /**
   * Tests sepia tone transformation on the split region of the image.
   * Verifies that the sepia effect is applied within the split region.
   */
  @Test
  public void testApplySepiaToneWithSplit() {
    imageModel.applySepiaTone(originalImageName, transformedImageName, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] sepiaPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < originalPixels.length; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB sepiaPixel = sepiaPixels[y][x];

        int expectedRed = (int) Math.min(0.393 * originalPixel.red
                + 0.769 * originalPixel.green + 0.189 * originalPixel.blue, 255);
        int expectedGreen = (int) Math.min(0.349 * originalPixel.red
                + 0.686 * originalPixel.green + 0.168 * originalPixel.blue, 255);
        int expectedBlue = (int) Math.min(0.272 * originalPixel.red
                + 0.534 * originalPixel.green + 0.131 * originalPixel.blue, 255);

        assertEquals(expectedRed, sepiaPixel.red);
        assertEquals(expectedGreen, sepiaPixel.green);
        assertEquals(expectedBlue, sepiaPixel.blue);
      }
    }

    for (int y = 0; y < originalPixels.length; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB sepiaPixel = sepiaPixels[y][x];

        assertEquals(originalPixel.red, sepiaPixel.red);
        assertEquals(originalPixel.green, sepiaPixel.green);
        assertEquals(originalPixel.blue, sepiaPixel.blue);
      }
    }
  }

  /**
   * Tests grayscale transformation using luma-component on the split region of the image.
   */
  @Test
  public void testApplyLumaTransformationWithSplit() {
    String transformationType = "luma-component";
    imageModel.applyGreyscaleTransformation(originalImageName, transformedImageName,
            transformationType, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] transformedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        RGB expectedTransformedPixel = transformPixel(originalPixel, transformationType);

        assertEquals(expectedTransformedPixel.red, transformedPixel.red);
        assertEquals(expectedTransformedPixel.green, transformedPixel.green);
        assertEquals(expectedTransformedPixel.blue, transformedPixel.blue);
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        assertEquals(originalPixel.red, transformedPixel.red);
        assertEquals(originalPixel.green, transformedPixel.green);
        assertEquals(originalPixel.blue, transformedPixel.blue);
      }
    }
  }

  /**
   * Tests grayscale transformation using intensity-component on the split region of the image.
   */
  @Test
  public void testApplyIntensityTransformationWithSplit() {
    String transformationType = "intensity-component";
    imageModel.applyGreyscaleTransformation(originalImageName, transformedImageName,
            transformationType, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] transformedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        RGB expectedTransformedPixel = transformPixel(originalPixel, transformationType);

        assertEquals(expectedTransformedPixel.red, transformedPixel.red);
        assertEquals(expectedTransformedPixel.green, transformedPixel.green);
        assertEquals(expectedTransformedPixel.blue, transformedPixel.blue);
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        assertEquals(originalPixel.red, transformedPixel.red);
        assertEquals(originalPixel.green, transformedPixel.green);
        assertEquals(originalPixel.blue, transformedPixel.blue);
      }
    }
  }

  /**
   * Tests grayscale transformation using value-component on the split region of the image.
   */
  @Test
  public void testApplyValueTransformationWithSplit() {
    String transformationType = "value-component";
    imageModel.applyGreyscaleTransformation(originalImageName, transformedImageName,
            transformationType, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] transformedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        RGB expectedTransformedPixel = transformPixel(originalPixel, transformationType);

        assertEquals(expectedTransformedPixel.red, transformedPixel.red);
        assertEquals(expectedTransformedPixel.green, transformedPixel.green);
        assertEquals(expectedTransformedPixel.blue, transformedPixel.blue);
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        assertEquals(originalPixel.red, transformedPixel.red);
        assertEquals(originalPixel.green, transformedPixel.green);
        assertEquals(originalPixel.blue, transformedPixel.blue);
      }
    }
  }

  /**
   * Tests grayscale transformation using red component only on the split region of the image.
   */
  @Test
  public void testApplyRedTransformationWithSplit() {
    String transformationType = "red-component";
    imageModel.applyGreyscaleTransformation(originalImageName, transformedImageName,
            transformationType, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] transformedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        RGB expectedTransformedPixel = transformPixel(originalPixel, transformationType);

        assertEquals(expectedTransformedPixel.red, transformedPixel.red);
        assertEquals(expectedTransformedPixel.green, transformedPixel.green);
        assertEquals(expectedTransformedPixel.blue, transformedPixel.blue);
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        assertEquals(originalPixel.red, transformedPixel.red);
        assertEquals(originalPixel.green, transformedPixel.green);
        assertEquals(originalPixel.blue, transformedPixel.blue);
      }
    }
  }

  /**
   * Tests grayscale transformation using green component only on the split region of the image.
   */
  @Test
  public void testApplyGreenTransformationWithSplit() {
    imageModel.applyGreyscaleTransformation(originalImageName, transformedImageName,
            "green-component", splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] transformedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        RGB expectedTransformedPixel = transformPixel(originalPixel,
                "green-component");

        assertEquals(expectedTransformedPixel.red, transformedPixel.red);
        assertEquals(expectedTransformedPixel.green, transformedPixel.green);
        assertEquals(expectedTransformedPixel.blue, transformedPixel.blue);
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        assertEquals(originalPixel.red, transformedPixel.red);
        assertEquals(originalPixel.green, transformedPixel.green);
        assertEquals(originalPixel.blue, transformedPixel.blue);
      }
    }
  }

  /**
   * Tests grayscale transformation using blue component only on the split region of the image.
   */
  @Test
  public void testApplyBlueTransformationWithSplit() {
    String transformationType = "blue-component";
    imageModel.applyGreyscaleTransformation(originalImageName, transformedImageName,
            transformationType, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] transformedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        RGB expectedTransformedPixel = transformPixel(originalPixel, transformationType);

        assertEquals(expectedTransformedPixel.red, transformedPixel.red);
        assertEquals(expectedTransformedPixel.green, transformedPixel.green);
        assertEquals(expectedTransformedPixel.blue, transformedPixel.blue);
      }
    }

    for (int y = 0; y < height; y++) {
      for (int x = expectedSplitPoint; x < width; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB transformedPixel = transformedPixels[y][x];

        assertEquals(originalPixel.red, transformedPixel.red);
        assertEquals(originalPixel.green, transformedPixel.green);
        assertEquals(originalPixel.blue, transformedPixel.blue);
      }
    }
  }

  /**
   * Tests color correction on the split region of the image, adjusting each color channel
   * based on peak values to equalize color distribution.
   */
  @Test
  public void testColorCorrectWithSplit() {
    int splitPercentage = 50;
    imageModel.colorCorrect(originalImageName, transformedImageName, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] correctedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    Map<String, int[]> histogramData = imageView.calculateHistogramData(originalPixels);
    int redPeak = findPeak(histogramData.get("Red"));
    int greenPeak = findPeak(histogramData.get("Green"));
    int bluePeak = findPeak(histogramData.get("Blue"));

    int targetPeak = calculateTargetPeak(redPeak, greenPeak, bluePeak);
    int redOffset = calculateOffset(redPeak, targetPeak);
    int greenOffset = calculateOffset(greenPeak, targetPeak);
    int blueOffset = calculateOffset(bluePeak, targetPeak);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB correctedPixel = correctedPixels[y][x];

        int expectedRed = Math.min(255, Math.max(0, originalPixel.red + redOffset));
        int expectedGreen = Math.min(255, Math.max(0, originalPixel.green + greenOffset));
        int expectedBlue = Math.min(255, Math.max(0, originalPixel.blue + blueOffset));

        assertEquals(expectedRed, correctedPixel.red);
        assertEquals(expectedGreen, correctedPixel.green);
        assertEquals(expectedBlue, correctedPixel.blue);
      }
    }
  }

  /**
   * Tests levels adjustment on the split region of the image, modifying brightness, midpoint,
   * and white levels to control contrast.
   */
  @Test
  public void testLevelsAdjustWithSplit() {
    int b = 50;
    int m = 128;
    int w = 200;

    imageModel.levelsAdjust(originalImageName, transformedImageName, b, m, w, splitPercentage);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] adjustedPixels = imageController.getImages().get(transformedImageName);

    int width = originalPixels[0].length;
    int height = originalPixels.length;
    int expectedSplitPoint = (int) (width * (splitPercentage / 100.0));

    double a = b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w;
    double aa = -b * (128 - 255) + 128 * w - 255 * m;
    double ab = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    double ac = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);

    double aaa = aa / a;
    double bCoef = ab / a;
    double c = ac / a;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < expectedSplitPoint; x++) {
        RGB originalPixel = originalPixels[y][x];
        RGB adjustedPixel = adjustedPixels[y][x];

        int expectedRed = applyQuadraticLevelsAdjustment(originalPixel.red, aaa, bCoef, c);
        int expectedGreen = applyQuadraticLevelsAdjustment(originalPixel.green, aaa, bCoef, c);
        int expectedBlue = applyQuadraticLevelsAdjustment(originalPixel.blue, aaa, bCoef, c);

        assertEquals(expectedRed, adjustedPixel.red);
        assertEquals(expectedGreen, adjustedPixel.green);
        assertEquals(expectedBlue, adjustedPixel.blue);
      }
    }
  }

  /**
   * Calculates the adjusted intensity for a pixel using a quadratic levels adjustment formula.
   *
   * @param intensity the pixel intensity.
   * @param a         the quadratic coefficient.
   * @param b         the linear coefficient.
   * @param c         the constant.
   * @return adjusted intensity value, clamped between 0 and 255.
   */
  private int applyQuadraticLevelsAdjustment(int intensity, double a, double b, double c) {
    double output = a * intensity * intensity + b * intensity + c;
    return (int) Math.min(255, Math.max(0, Math.round(output)));
  }

  /**
   * Finds the peak intensity value in a color channel histogram, excluding the extremes.
   *
   * @param histogram the color channel histogram.
   * @return the intensity value with the highest frequency.
   */
  private int findPeak(int[] histogram) {
    int peakIntensity = 0;
    int peakFrequency = 0;

    for (int i = 10; i < 245; i++) {
      if (histogram[i] > peakFrequency) {
        peakFrequency = histogram[i];
        peakIntensity = i;
      }
    }

    return peakIntensity;
  }

  /**
   * Calculates the target peak intensity for color correction.
   *
   * @param redPeak   peak red intensity.
   * @param greenPeak peak green intensity.
   * @param bluePeak  peak blue intensity.
   * @return the average target peak for balancing color.
   */
  private int calculateTargetPeak(int redPeak, int greenPeak, int bluePeak) {
    return (redPeak + greenPeak + bluePeak) / 3;
  }

  /**
   * Calculates the offset needed for a color channel to match the target peak intensity.
   *
   * @param channelPeak the current peak of the color channel.
   * @param targetPeak  the target peak intensity.
   * @return the offset to adjust the channel to the target peak.
   */
  private int calculateOffset(int channelPeak, int targetPeak) {
    return targetPeak - channelPeak;
  }

  /**
   * Transforms a pixel according to a specified grayscale transformation type.
   *
   * @param pixel              the RGB pixel to transform.
   * @param transformationType the type of transformation ("red", "green", "blue", etc.).
   * @return the transformed RGB pixel.
   */
  private RGB transformPixel(RGB pixel, String transformationType) {
    int value;
    switch (transformationType.toLowerCase()) {
      case "red-component":
        return new RGB(pixel.red, pixel.red, pixel.red);

      case "green-component":
        return new RGB(pixel.green, pixel.green, pixel.green);

      case "blue-component":
        return new RGB(pixel.blue, pixel.blue, pixel.blue);

      case "value-component":
        value = Math.max(pixel.red, Math.max(pixel.green, pixel.blue));
        return new RGB(value, value, value);

      case "intensity-component":
        value = (pixel.red + pixel.green + pixel.blue) / 3;
        return new RGB(value, value, value);

      case "luma-component":
        value = (int) Math.round(0.2126 * pixel.red + 0.7152 * pixel.green + 0.0722 * pixel.blue);
        return new RGB(value, value, value);

      default:
        throw new IllegalArgumentException("Unknown transformation type: " + transformationType);
    }
  }
}
