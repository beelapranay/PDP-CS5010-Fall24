package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

/**
 * Operation class that applies a specified image operation to a source image,
 * but only on the pixels where the mask image is black.
 */
public class ApplyMaskOperation implements ImageOperation {
  private final String operationName;
  private final ImageInterface maskImage;

  /**
   * Constructs an ApplyMaskOperation with the specified operation name and mask image.
   *
   * @param operationName The name of the operation to apply.
   * @param maskImage     The mask image.
   */
  public ApplyMaskOperation(String operationName, ImageInterface maskImage) {
    if (operationName == null || maskImage == null) {
      throw new IllegalArgumentException("Operation name and mask image cannot be null.");
    }
    this.operationName = operationName.toLowerCase();
    this.maskImage = maskImage;
  }

  /**
   * Executes the mask operation on the provided image.
   *
   * @param images An array containing a single image to be processed.
   * @return A new ImageInterface instance with the operation applied using the mask.
   * @throws IllegalArgumentException if images array is invalid or
   *                                  images have mismatched dimensions.
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1 || images[0] == null) {
      throw new IllegalArgumentException("ApplyMaskOperation requires exactly one image.");
    }
    ImageInterface sourceImage = images[0];

    if (sourceImage.getWidth() != maskImage.getWidth() ||
            sourceImage.getHeight() != maskImage.getHeight()) {
      throw new IllegalArgumentException("Source image and mask image " +
              "must have the same dimensions.");
    }

    ImageOperation operation = getOperation(operationName);
    ImageInterface operatedImage = operation.execute(sourceImage);

    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();

    int[][][] sourcePixels = sourceImage.getPixels();
    int[][][] operatedPixels = operatedImage.getPixels();
    int[][][] maskPixels = maskImage.getPixels();

    int[][][] resultPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int maskValue = maskPixels[y][x][0]; // Assuming mask is grayscale
        if (maskValue == 0) {
          // Apply operation where mask pixel is black (value 0)
          resultPixels[y][x][0] = operatedPixels[y][x][0];
          resultPixels[y][x][1] = operatedPixels[y][x][1];
          resultPixels[y][x][2] = operatedPixels[y][x][2];
        } else {
          // Keep original pixel where mask pixel is not black
          resultPixels[y][x][0] = sourcePixels[y][x][0];
          resultPixels[y][x][1] = sourcePixels[y][x][1];
          resultPixels[y][x][2] = sourcePixels[y][x][2];
        }
      }
    }

    return new Image(width, height, maxValue, resultPixels);
  }

  /**
   * Helper method to get the ImageOperation instance for the specified operation name.
   *
   * @param operationName The name of the operation.
   * @return The ImageOperation instance.
   * @throws IllegalArgumentException if the operation name is not supported.
   */
  private ImageOperation getOperation(String operationName) {
    switch (operationName.toLowerCase()) {
      case "blur":
        return new BlurOperation();
      case "sharpen":
        return new SharpenOperation();
      case "sepia":
        return new SepiaOperation();
      case "red-component":
      case "green-component":
      case "blue-component":
      case "luma-component":
      case "intensity-component":
      case "value-component":
        return new ComponentOperation(operationName.split("-")[0]);
      default:
        throw new IllegalArgumentException("Unsupported operation for masking: " + operationName);
    }
  }
}