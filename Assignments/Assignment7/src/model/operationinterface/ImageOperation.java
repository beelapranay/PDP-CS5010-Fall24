package model.operationinterface;

import model.image.ImageInterface;

/**
 * A class that represents an image operation.
 * This class contains the operation to be applied to an image.
 */
public interface ImageOperation {
  /**
   * Executes the operation on the given image.
   *
   * @param images The image/images to apply the operation to.
   * @return A new image with the operation applied.
   * @throws IllegalArgumentException if the image is invalid or null.
   */
  ImageInterface execute(ImageInterface... images);
}
