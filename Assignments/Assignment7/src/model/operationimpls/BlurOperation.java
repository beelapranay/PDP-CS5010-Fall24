package model.operationimpls;

import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;
import model.utilities.KernelApplier;

/**
 * Operation class for applying blur effect to an image.
 */
public class BlurOperation implements ImageOperation {
  private static final double[][] BLUR_KERNEL = {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
  };

  /**
   * Applies the blur effect to the given image.
   *
   * @param images The image to be blurred.
   * @return The blurred image.
   * @throws IllegalArgumentException If the input is invalid.
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("BlurOperation requires exactly one image.");
    }

    ImageInterface image = images[0];
    ImageValidator.validate(image);

    return KernelApplier.applyKernel(image, BLUR_KERNEL);
  }
}