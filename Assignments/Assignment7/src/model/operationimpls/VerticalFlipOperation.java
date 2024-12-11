package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;

/**
 * Operation class that flips an image vertically (top to bottom).
 * Creates a upside-down version of the image by reversing the order of rows while maintaining
 * the original width and horizontal ordering of pixels within each row.
 */
public class VerticalFlipOperation implements ImageOperation {

  /**
   * Executes the vertical flip operation on the provided image.
   * Creates a new image where the rows of pixels are reversed top-to-bottom.
   * The operation maintains the original colors and horizontal ordering of pixels within each row.
   *
   * @param images Array containing a single image to be flipped vertically
   * @return A new ImageInterface instance representing the vertically flipped image
   * @throws IllegalArgumentException if images array is null or does not contain exactly one image
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("VerticalFlipOperation requires exactly one image.");
    }
    ImageInterface image = images[0];
    ImageValidator.validate(image);

    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] originalPixels = image.getPixels();
    int[][][] newPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      newPixels[y] = originalPixels[height - y - 1];
    }

    return new Image(width, height, image.getMaxValue(), newPixels);
  }
}