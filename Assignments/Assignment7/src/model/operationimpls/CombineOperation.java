package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;
import model.utilities.ImageValidator;

/**
 * Operation class that combines three grayscale images into a single RGB color image.
 * Takes separate red, green, and blue channel images and merges them into one composite image.
 * All input images must have identical dimensions and maximum pixel values.
 */
public class CombineOperation implements ImageOperation {

  /**
   * Combines three grayscale images into a single RGB image.
   * Takes the red channel from the first image, green from the second, and blue from the third.
   *
   * @param images Array containing exactly three images in order: red, green, blue channels
   * @return A new ImageInterface instance representing the combined RGB image
   * @throws IllegalArgumentException if:
   *                                  - images array is null or doesn't contain exactly three images
   *                                  - any input image is invalid
   *                                  - input images have different dimensions
   *                                  - input images have different maximum pixel values
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 3) {
      throw new IllegalArgumentException("CombineOperation requires exactly three images.");
    }
    ImageInterface redImage = images[0];
    ImageInterface greenImage = images[1];
    ImageInterface blueImage = images[2];

    ImageValidator.validate(redImage);
    ImageValidator.validate(greenImage);
    ImageValidator.validate(blueImage);

    int width = redImage.getWidth();
    int height = redImage.getHeight();
    int maxValue = redImage.getMaxValue();

    if (greenImage.getMaxValue() != maxValue || blueImage.getMaxValue() != maxValue) {
      throw new IllegalArgumentException("All images must have the same maximum pixel value.");
    }

    if (greenImage.getWidth() != width || greenImage.getHeight() != height ||
            blueImage.getWidth() != width || blueImage.getHeight() != height) {
      throw new IllegalArgumentException("All images must have the same dimensions");
    }

    int[][][] redPixels = redImage.getPixels();
    int[][][] greenPixels = greenImage.getPixels();
    int[][][] bluePixels = blueImage.getPixels();

    int[][][] newPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] newRgb = new int[3];
        newRgb[0] = redPixels[y][x][0];
        newRgb[1] = greenPixels[y][x][1];
        newRgb[2] = bluePixels[y][x][2];
        newPixels[y][x] = newRgb;
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }
}