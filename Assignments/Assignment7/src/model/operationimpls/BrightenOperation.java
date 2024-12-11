package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;

/**
 * Operation class that adjusts the brightness of an image by adding a constant value to all pixels.
 * Can both brighten (positive increment) and darken (negative increment) an image while maintaining
 * color relationships and ensuring values stay within valid range.
 */
public class BrightenOperation implements ImageOperation {
  /**
   * The value to add to each RGB component. Positive values brighten, negative values darken.
   */
  private final int increment;

  /**
   * Constructs a new BrightenOperation with the specified brightness adjustment.
   *
   * @param increment The amount to add to each RGB value (negative for darkening)
   */
  public BrightenOperation(int increment) {
    this.increment = increment;
  }

  /**
   * Executes the brightness adjustment operation on the provided image.
   * Creates a new image with all RGB values adjusted by the increment value.
   *
   * @param images Array containing a single image to be brightened/darkened
   * @return A new ImageInterface instance with adjusted brightness
   * @throws IllegalArgumentException if images is null or contains more/less than one image
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("BrightenOperation requires exactly one image.");
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
          newRgb[i] = clamp(rgb[i] + increment, 0, maxValue);
        }
        newPixels[y][x] = newRgb;
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Ensures a value falls within a specified range.
   * Used to keep RGB values within valid bounds after brightness adjustment.
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