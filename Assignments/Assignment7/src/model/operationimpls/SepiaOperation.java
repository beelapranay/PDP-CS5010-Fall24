package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;

/**
 * Implements a sepia tone filter operation for image processing.
 * The sepia effect creates a warm, brownish tone commonly associated with vintage photographs.
 * Uses a 3x3 transformation matrix to convert RGB values to sepia-toned equivalents.
 */
public class SepiaOperation implements ImageOperation {
  private static final double[][] SEPIA_MATRIX = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  /**
   * Applies the sepia tone transformation to the provided image.
   *
   * @param images An array containing a single image to be processed
   * @return A new ImageInterface instance containing the sepia-toned version of the input image
   * @throws IllegalArgumentException if images is null or contains more/less than one image
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("SepiaOperation requires exactly one image.");
    }
    ImageInterface image = images[0];
    ImageValidator.validate(image);

    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();
    int[][][] originalPixels = image.getPixels();
    int[][][] newPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = originalPixels[y][x];
        int[] newRgb = new int[3];

        for (int i = 0; i < 3; i++) {
          newRgb[i] = clamp(
                  (int) (SEPIA_MATRIX[i][0] * rgb[0] +
                          SEPIA_MATRIX[i][1] * rgb[1] +
                          SEPIA_MATRIX[i][2] * rgb[2]),
                  0, maxValue);
        }

        newPixels[y][x] = newRgb;
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Ensures a value falls within a specified range.
   *
   * @param value The number to be clamped
   * @param min   The minimum allowed value
   * @param max   The maximum allowed value
   * @return The clamped value, guaranteed to be between min and max inclusive
   */
  private int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }
}
