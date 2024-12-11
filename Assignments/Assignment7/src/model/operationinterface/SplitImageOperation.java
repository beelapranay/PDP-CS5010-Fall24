package model.operationinterface;

import model.image.ImageInterface;

/**
 * Interface for image operations that return multiple images.
 */
public interface SplitImageOperation {
  /**
   * Executes the operation on the given image.
   *
   * @param image The input image.
   * @return An array of images resulting from the operation.
   */
  ImageInterface[] execute(ImageInterface image);
}
