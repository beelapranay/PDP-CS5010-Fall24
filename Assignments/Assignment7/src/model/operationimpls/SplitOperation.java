package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.SplitImageOperation;
import model.utilities.ImageValidator;

/**
 * Operation class that splits an RGB image into its individual color components.
 * Creates three images representing the red, green, and blue channels of the original image.
 * Each resulting image preserves only one color channel while zeroing out the other channels.
 */
public class SplitOperation implements SplitImageOperation {

  /**
   * Splits an RGB image into three separate images containing individual color channels.
   * For each image, one color channel maintains its original values while others are set to 0.
   * The resulting images will appear as:
   * - Red image: Only red channel values preserved
   * - Green image: Only green channel values preserved
   * - Blue image: Only blue channel values preserved
   *
   * @param image The source RGB image to split into components
   * @return An array of three ImageInterface objects in order: [red, green, blue] channel images
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface[] execute(ImageInterface image) {
    ImageValidator.validate(image);

    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();
    int[][][] originalPixels = image.getPixels();

    int[][][] redPixels = new int[height][width][3];
    int[][][] greenPixels = new int[height][width][3];
    int[][][] bluePixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = originalPixels[y][x];

        redPixels[y][x][0] = rgb[0];
        redPixels[y][x][1] = rgb[0];
        redPixels[y][x][2] = rgb[0];

        greenPixels[y][x][0] = rgb[1];
        greenPixels[y][x][1] = rgb[1];
        greenPixels[y][x][2] = rgb[1];

        bluePixels[y][x][0] = rgb[2];
        bluePixels[y][x][1] = rgb[2];
        bluePixels[y][x][2] = rgb[2];
      }
    }

    ImageInterface redImage = new Image(width, height, maxValue, redPixels);
    ImageInterface greenImage = new Image(width, height, maxValue, greenPixels);
    ImageInterface blueImage = new Image(width, height, maxValue, bluePixels);

    return new ImageInterface[]{redImage, greenImage, blueImage};
  }
}