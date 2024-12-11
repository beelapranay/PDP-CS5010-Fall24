package model.operationimpls;

import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;
import model.utilities.KernelApplier;

/**
 * Operation class for applying sharpen effect to an image.
 */
public class SharpenOperation implements ImageOperation {
  private static final double[][] SHARPEN_KERNEL = {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
  };

  /**
   * Applies the sharpen effect to the given image.
   *
   * @param images The image to be sharpened.
   * @return The sharpened image.
   * @throws IllegalArgumentException If the input is invalid.
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("SharpenOperation requires exactly one image.");
    }
    ImageInterface image = images[0];
    ImageValidator.validate(image);

    return KernelApplier.applyKernel(image, SHARPEN_KERNEL);
  }
}