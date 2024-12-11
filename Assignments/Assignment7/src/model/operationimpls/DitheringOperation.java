package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

/**
 * An operation that applies Floyd-Steinberg dithering to an image.
 * This converts the image to black and white using error diffusion.
 */
public class DitheringOperation implements ImageOperation {

  /**
   * Applies Floyd-Steinberg dithering to an image.
   * The resulting image is converted to black and white.
   *
   * @param images The source image to dither.
   * @return A new dithered version of the input image.
   * @throws IllegalArgumentException if the input image is null or invalid.
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("DitheringOperation requires exactly one image.");
    }

    ImageInterface image = images[0];
    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();

    double[][] grayscale = toGrayscale(image, width, height);

    applyDithering(grayscale, width, height, maxValue);

    int[][][] newPixels = reconstructBinaryImage(grayscale, width, height, maxValue);

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Converts the input image to grayscale.
   *
   * @param image  The source image.
   * @param width  The width of the image.
   * @param height The height of the image.
   * @return A 2D array containing grayscale values.
   */
  private double[][] toGrayscale(ImageInterface image, int width, int height) {
    double[][] grayscale = new double[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] pixel = image.getPixel(x, y);
        //luma-component grayscale
        grayscale[y][x] = (pixel[0] * 0.2126 + pixel[1] * 0.7152 + pixel[2] * 0.0722);
      }
    }
    return grayscale;
  }

  /**
   * Applies Floyd-Steinberg dithering to the grayscale image.
   *
   * @param grayscale The grayscale image as a 2D array.
   * @param width     The width of the image.
   * @param height    The height of the image.
   * @param maxValue  The maximum pixel value.
   */
  private void applyDithering(double[][] grayscale, int width, int height, int maxValue) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double oldColor = grayscale[y][x];
        double newColor = (oldColor > maxValue / 2) ? maxValue : 0;
        double error = oldColor - newColor;
        grayscale[y][x] = newColor;

        if (x + 1 < width) {
          grayscale[y][x + 1] += error * 7 / 16.0;
        }
        if (y + 1 < height) {
          if (x - 1 >= 0) {
            grayscale[y + 1][x - 1] += error * 3 / 16.0;
          }
          grayscale[y + 1][x] += error * 5 / 16.0;
          if (x + 1 < width) {
            grayscale[y + 1][x + 1] += error * 1 / 16.0;
          }
        }
      }
    }
  }

  /**
   * Reconstructs a binary black and white image from the processed grayscale values.
   *
   * @param grayscale The processed grayscale values.
   * @param width     The width of the image.
   * @param height    The height of the image.
   * @param maxValue  The maximum pixel value.
   * @return A 3D array representing the dithered image.
   */
  private int[][][] reconstructBinaryImage(double[][] grayscale, int width,
                                           int height, int maxValue) {
    int[][][] newPixels = new int[height][width][3];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int value = (int) Math.round(grayscale[y][x]);
        value = clamp(value, 0, maxValue);
        newPixels[y][x][0] = value;
        newPixels[y][x][1] = value;
        newPixels[y][x][2] = value;
      }
    }
    return newPixels;
  }

  /**
   * Ensures a value falls within a specified range.
   *
   * @param value The number to be clamped.
   * @param min   The minimum allowed value.
   * @param max   The maximum allowed value.
   * @return The clamped value, guaranteed to be between min and max inclusive.
   */
  private static int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }
}