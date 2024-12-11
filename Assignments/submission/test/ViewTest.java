import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import controller.ImageController;
import model.RGB;
import view.ImageView;

import static org.junit.Assert.*;

/**
 * Unit tests for the ImageView class, focusing on histogram generation
 * and handling of various edge cases in image data.
 */
public class ViewTest {
  private ImageView imageView;
  private ImageController controller;

  /**
   * Unit tests for the ImageView class, focusing on histogram generation
   * and handling of various edge cases in image data.
   */
  @Before
  public void setUp() {
    controller = new ImageController();
    imageView = new ImageView(controller);
  }

  /**
   * Tests that the JPanel within ImageView is initialized with
   * the correct width and height dimensions.
   */
  @Test
  public void testPanelSetup() {
    assertEquals("Panel width should be 1000",
            1000, imageView.getPreferredSize().width);
    assertEquals("Panel height should be 1000",
            1000, imageView.getPreferredSize().height);
  }

  /**
   * Tests that a histogram is generated and stored in the image controller
   * for a valid image provided by the controller.
   */
  @Test
  public void testGenerateAndStoreHistogramImageView() {
    RGB[][] testImage = {
            {new RGB(120, 50, 30), new RGB(130, 70, 60)},
            {new RGB(140, 90, 80), new RGB(150, 110, 100)}
    };
    controller.getImages().put("testImage", testImage);

    imageView.generateAndStoreHistogram("testImage",
            "testImageHistogram");

    assertTrue("Image controller should contain the histogram image",
            controller.getImages().containsKey("testImageHistogram"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testGenerateHistogramImageNotFound() {
    imageView.generateAndStoreHistogram("nonExistentImage",
            "outputHistogram");
  }

  /**
   * Test that the view handles empty image data without error.
   */
  @Test
  public void testGenerateHistogramWithEmptyImage() {
    RGB[][] emptyImage = new RGB[0][0];
    controller.getImages().put("emptyImage", emptyImage);

    imageView.generateAndStoreHistogram("emptyImage",
            "emptyImageHistogram");

    assertTrue("Image controller should contain the histogram image even for empty input",
            controller.getImages().containsKey("emptyImageHistogram"));
  }

  /**
   * Test histogram generation with an image of only extreme values.
   */
  @Test
  public void testGenerateHistogramWithExtremeValues() {
    RGB[][] extremeImage = {
            {new RGB(0, 0, 0), new RGB(255, 255, 255)},
            {new RGB(0, 0, 0), new RGB(255, 255, 255)}
    };
    controller.getImages().put("extremeImage", extremeImage);

    imageView.generateAndStoreHistogram("extremeImage",
            "extremeImageHistogram");

    assertTrue("Image controller should contain the "
                    + "histogram image with extreme pixel values",
            controller.getImages().containsKey("extremeImageHistogram"));
  }

  /**
   * Test histogram generation with a large image to verify performance.
   */
  @Test
  public void testGenerateHistogramWithLargeImage() {
    int size = 1000;
    RGB[][] largeImage = new RGB[size][size];
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        largeImage[y][x] = new RGB(128, 128, 128);  // Mid-range grayscale color
      }
    }
    controller.getImages().put("largeImage", largeImage);

    imageView.generateAndStoreHistogram("largeImage",
            "largeImageHistogram");

    assertTrue("Image controller should contain the histogram image for a large image",
            controller.getImages().containsKey("largeImageHistogram"));
  }

  /**
   * Test histogram generation where only one color channel has high values.
   */
  @Test
  public void testGenerateHistogramSingleHighChannel() {
    RGB[][] singleChannelImage = {
            {new RGB(255, 0, 0), new RGB(255, 0, 0)},
            {new RGB(255, 0, 0), new RGB(255, 0, 0)}
    };
    controller.getImages().put("singleChannelImage", singleChannelImage);

    imageView.generateAndStoreHistogram("singleChannelImage",
            "singleChannelHistogram");

    assertTrue("Image controller should contain the histogram "
                    + "for a single high channel image",
            controller.getImages().containsKey("singleChannelHistogram"));
  }
}