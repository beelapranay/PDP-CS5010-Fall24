package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;

/**
 * Operation class that extracts a specific component or characteristic from an RGB image.
 * Can extract individual color channels (R,G,B) or calculate various grayscale representations
 * like luma, intensity, and value. The result is a grayscale image where all RGB channels
 * contain the extracted/calculated value.
 */
public class ComponentOperation implements ImageOperation {

  /**
   * The type of component to extract from the image.
   */
  private final String componentName;

  /**
   * Constructs a new ComponentOperation for the specified component type.
   *
   * @param componentName The type of component extracted (RED, GREEN, BLUE, etc.)
   */
  public ComponentOperation(String componentName) {
    this.componentName = componentName.toLowerCase();
  }

  /**
   * Extracts the specified component from the input image.
   * Creates a new grayscale image where all channels contain the extracted component value.
   *
   * @param images Array containing a single image to extract component from
   * @return A new grayscale ImageInterface containing the extracted component
   * @throws IllegalArgumentException if:
   *                                  - images array is null or does not contain exactly one image
   *                                  - input image is invalid
   *                                  - componentName is not recognized
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("ComponentOperation requires exactly one image.");
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
        int value = extractComponent(rgb, maxValue);

        newPixels[y][x][0] = value;
        newPixels[y][x][1] = value;
        newPixels[y][x][2] = value;
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Helper method that extracts the appropriate component value based on the componentName.
   *
   * @param rgb      The RGB array containing the red, green, and blue values of the pixel
   * @param maxValue The maximum allowed value for a pixel
   * @return The extracted component value
   */
  private int extractComponent(int[] rgb, int maxValue) {
    switch (componentName) {
      case "red":
        return rgb[0];
      case "green":
        return rgb[1];
      case "blue":
        return rgb[2];
      case "luma":
        return clamp((int) (0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2]), 0, maxValue);
      case "intensity":
        return (rgb[0] + rgb[1] + rgb[2]) / 3;
      case "value":
        return Math.max(rgb[0], Math.max(rgb[1], rgb[2]));
      default:
        throw new IllegalArgumentException("Unknown component: " + componentName);
    }
  }

  /**
   * Ensures a value falls within a specified range.
   * Used to keep calculated values within valid pixel value bounds.
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