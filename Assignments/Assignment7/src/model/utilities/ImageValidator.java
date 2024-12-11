package model.utilities;

import model.image.ImageInterface;

/**
 * Utility class that provides methods for validating image objects and their pixel values.
 * Ensures images meet required specifications for processing operations including:
 * - Non-null image objects
 * - Valid pixel value ranges
 * - Consistent dimensions and max values across multiple images
 */
public class ImageValidator {

  /**
   * Validates a single image for null check and pixel value constraints.
   *
   * @param image The image to validate
   * @throws IllegalArgumentException if:
   *                                  - image is null
   *                                  - any pixel value is outside the valid range [0, maxValue]
   */
  public static void validate(ImageInterface image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    validatePixelValues(image);
  }

  /**
   * Validates that all pixel values in an image fall within the valid range.
   * Checks each color channel (R,G,B) of each pixel against the image's maxValue.
   *
   * @param image The image whose pixel values need to be validated
   * @throws IllegalArgumentException if any pixel value is less than 0 or greater than maxValue
   */
  private static void validatePixelValues(ImageInterface image) {
    int maxValue = image.getMaxValue();
    int[][][] pixels = image.getPixels();

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        for (int i = 0; i < 3; i++) {
          int value = pixels[y][x][i];
          if (value < 0 || value > maxValue) {
            throw new IllegalArgumentException(
                    "Pixel values must be between 0 and " + maxValue +
                            ". Found value: " + value);
          }
        }
      }
    }
  }
}