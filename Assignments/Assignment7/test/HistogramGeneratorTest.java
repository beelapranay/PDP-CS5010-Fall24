import org.junit.Before;
import org.junit.Test;

import model.image.Image;
import model.image.ImageInterface;
import model.operationimpls.AdvancedImageProcessorImpl;
import model.operationinterface.AdvancedImageProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * A class that tests the HistogramGenerator class via the AdvancedImageProcessor interface.
 */
public class HistogramGeneratorTest {
  private AdvancedImageProcessor advancedImageProcessor;

  @Before
  public void setUp() {
    advancedImageProcessor = new AdvancedImageProcessorImpl();
  }

  @Test
  public void testGenerateHistogramImageSimpleImage() {
    int[][][] pixels = {
            {{0, 0, 0}, {255, 255, 255}},
            {{128, 128, 128}, {64, 64, 64}}
    };
    ImageInterface image = new Image(2, 2, 255, pixels);

    ImageInterface histogramImage = advancedImageProcessor.generateHistogram(image);

    assertEquals(256, histogramImage.getWidth());
    assertEquals(256, histogramImage.getHeight());

    assertPixelColor(histogramImage,
            10, 10, 255, 255, 255);

    assertPixelNotWhite(histogramImage, 0, 255);

    assertPixelNotWhite(histogramImage, 255, 255);

    assertPixelNotWhite(histogramImage, 128, 255);

    assertPixelNotWhite(histogramImage, 64, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateHistogramNullImage() {
    advancedImageProcessor.generateHistogram(null);
  }

  @Test
  public void testGenerateHistogramGradientImage() {
    int[][][] pixels = {
            {{0, 0, 0}, {85, 85, 85}},
            {{170, 170, 170}, {255, 255, 255}}
    };
    ImageInterface image = new Image(2, 2, 255, pixels);

    ImageInterface histogramImage = advancedImageProcessor.generateHistogram(image);

    assertPixelNotWhite(histogramImage, 0, 255);
    assertPixelNotWhite(histogramImage, 85, 255);
    assertPixelNotWhite(histogramImage, 170, 255);
    assertPixelNotWhite(histogramImage, 255, 255);
  }

  private void assertPixelColor(ImageInterface image, int x, int y,
                                int expectedRed, int expectedGreen, int expectedBlue) {
    int[] pixel = image.getPixel(x, y);
    assertEquals("Red channel mismatch at (" + x + "," + y + ")",
            expectedRed, pixel[0]);
    assertEquals("Green channel mismatch at (" + x + "," + y + ")",
            expectedGreen, pixel[1]);
    assertEquals("Blue channel mismatch at (" + x + "," + y + ")",
            expectedBlue, pixel[2]);
  }

  private void assertPixelNotWhite(ImageInterface image, int x, int y) {
    int[] pixel = image.getPixel(x, y);
    boolean isWhite = pixel[0] == 255 && pixel[1] == 255 && pixel[2] == 255;
    assertFalse("Pixel at (" + x + "," + y + ") should not be white", isWhite);
  }
}