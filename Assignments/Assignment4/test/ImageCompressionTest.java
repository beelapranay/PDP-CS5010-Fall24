import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.ImageController;
import model.ImageModel;
import model.ImageModelInterface;
import model.RGB;
import view.ImageView;
import view.ImageViewInterface;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for verifying image compression functionality for various image
 * formats (JPG, PNG, PPM) with different compression levels (high, medium, low).
 * Ensures that image compression reduces non-zero pixel terms and verifies the ability
 * to save compressed images.
 */
public class ImageCompressionTest {
  ImageController imageController;
  ImageModelInterface imageModel;
  ImageViewInterface imageView;

  /**
   * Set up the model and controller for the tests.
   */
  @Before
  public void setUp() {
    imageController = new ImageController();
    imageView = new ImageView(imageController);
    imageModel = new ImageModel(imageController, imageView);
  }

  /**
   * Tests high compression on a JPG image, ensuring compression reduces non-zero terms.
   */
  @Test
  public void testHighCompressionJPGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/jpg/unnamed.jpg", originalImageName);

    imageModel.compressImage("95", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(compressedNonZeroCount < originalNonZeroCount);
  }

  /**
   * Tests medium compression on a JPG image, verifying minimal change in non-zero terms.
   */
  @Test
  public void testMidCompressionJPGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/jpg/unnamed.jpg", originalImageName);

    imageModel.compressImage("50", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests low compression on a JPG image, ensuring original and compressed non-zero terms match.
   */
  @Test
  public void testLowCompressionJPGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/jpg/unnamed.jpg", originalImageName);

    imageModel.compressImage("20", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests high compression on a PNG image, ensuring non-zero terms remain consistent.
   */
  @Test
  public void testHighCompressionPNGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/png/square.png", originalImageName);

    imageModel.compressImage("95", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests medium compression on a PNG image, verifying non-zero term consistency.
   */
  @Test
  public void testMidCompressionPNGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/png/square.png", originalImageName);

    imageModel.compressImage("50", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests low compression on a PNG image, ensuring non-zero terms remain unchanged.
   */
  @Test
  public void testLowCompressionPNGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/png/square.png", originalImageName);

    imageModel.compressImage("20", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests high compression on a PPM image, checking for consistency in non-zero terms.
   */
  @Test
  public void testHighCompressionPPMImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/ppm/4x4.ppm", originalImageName);

    imageModel.compressImage("95", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests medium compression on a PPM image, ensuring consistent non-zero terms.
   */
  @Test
  public void testMidCompressionPPMImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/ppm/4x4.ppm", originalImageName);

    imageModel.compressImage("50", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests low compression on a PPM image, verifying no change in non-zero terms.
   */
  @Test
  public void testLowCompressionPPMImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/ppm/4x4.ppm", originalImageName);

    imageModel.compressImage("20", originalImageName, compressedImageName);

    RGB[][] originalPixels = imageController.getImages().get(originalImageName);
    RGB[][] compressedPixels = imageController.getImages().get(compressedImageName);

    int originalNonZeroCount = countNonZeroTerms(originalPixels);
    int compressedNonZeroCount = countNonZeroTerms(compressedPixels);

    assertTrue(originalNonZeroCount == compressedNonZeroCount);
  }

  /**
   * Tests saving a compressed PPM image to the specified file path.
   */
  @Test
  public void testSavingCompressedPPMImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/ppm/4x4.ppm", originalImageName);

    imageModel.compressImage("95", originalImageName, compressedImageName);

    imageController.saveImage("resources/res/ppmRes/4x4Compressed.ppm",
            compressedImageName);

    assertTrue(imageController.getImages().containsKey(compressedImageName));
  }

  /**
   * Tests saving a compressed JPG image to the specified file path.
   */

  @Test
  public void testSavingCompressedJPGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/jpg/unnamed.jpg", originalImageName);

    imageModel.compressImage("99", originalImageName, compressedImageName);

    imageController.saveImage("resources/res/jpgRes/unnamedCompressed.jpg",
            compressedImageName);

    assertTrue(imageController.getImages().containsKey(compressedImageName));
  }

  /**
   * Tests saving a compressed PNG image to the specified file path.
   */
  @Test
  public void testSavingCompressedPNGImages() throws IOException {
    String originalImageName = "earth";
    String compressedImageName = "earthCompressed";

    imageController.loadImage("resources/testImages/png/square.png", originalImageName);

    imageModel.compressImage("95", originalImageName, compressedImageName);

    imageController.saveImage("resources/res/pngRes/squareCompressed.png",
            compressedImageName);

    assertTrue(imageController.getImages().containsKey(compressedImageName));
  }

  /**
   * Counts non-zero color terms in the given RGB pixel array.
   *
   * @param pixels the 2D RGB array representing the image pixels
   * @return the count of non-zero terms
   */
  private int countNonZeroTerms(RGB[][] pixels) {
    int nonZeroCount = 0;
    for (RGB[] row : pixels) {
      for (RGB pixel : row) {
        if (pixel.red != 0 || pixel.green != 0 || pixel.blue !=  0) {
          nonZeroCount++;
        }
      }
    }
    return nonZeroCount;
  }
}
