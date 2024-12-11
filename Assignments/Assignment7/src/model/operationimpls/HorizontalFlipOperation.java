package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;

/**
 * Operation class that flips an image horizontally (left to right).
 * Creates a mirror image by reversing the order of pixels in each row while maintaining
 * the original height and vertical ordering of pixels.
 */
public class HorizontalFlipOperation implements ImageOperation {

  /**
   * Executes the horizontal flip operation on the provided image.
   * Creates a new image where each row of pixels is reversed left-to-right.
   * The operation maintains the original colors and vertical ordering of pixels.
   *
   * @param images Array containing a single image to be flipped horizontally
   * @return A new ImageInterface instance representing the horizontally flipped image
   * @throws IllegalArgumentException if images array is null or does not contain exactly one image
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("HorizontalFlipOperation requires exactly one image.");
    }
    ImageInterface image = images[0];
    ImageValidator.validate(image);

    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] originalPixels = image.getPixels();
    int[][][] newPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        newPixels[y][x] = originalPixels[y][width - x - 1];
      }
    }

    return new Image(width, height, image.getMaxValue(), newPixels);
  }
}