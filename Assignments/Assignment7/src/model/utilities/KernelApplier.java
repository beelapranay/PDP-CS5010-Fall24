package model.utilities;

import model.image.Image;
import model.image.ImageInterface;

/**
 * Utility class that provides functionality for applying convolution kernels to images.
 * Supports image filtering operations such as blur and sharpen by applying a matrix of weights.
 * to each pixel's neighborhood.
 */
public class KernelApplier {

  /**
   * Applies a convolution kernel to an image to create various filtering effects.
   * For each pixel, computes a weighted sum of the pixel and its neighbors using the kernel matrix.
   * Handles image boundaries by only including valid neighboring pixels.
   *
   * @param image        The input image to apply the kernel to
   * @param kernelMatrix The convolution kernel matrix defining the weights for the operation
   * @return A new ImageInterface containing the filtered result
   * @throws IllegalArgumentException if the image is null or invalid
   */
  public static ImageInterface applyKernel(ImageInterface image, double[][] kernelMatrix) {
    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();
    int[][][] originalPixels = image.getPixels();
    int[][][] newPixels = new int[height][width][3];

    int kernelHeight = kernelMatrix.length;
    int kernelWidth = kernelMatrix[0].length;
    int kernelCenterY = kernelHeight / 2;
    int kernelCenterX = kernelWidth / 2;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double[] newRgb = {0.0, 0.0, 0.0};

        for (int ky = 0; ky < kernelHeight; ky++) {
          for (int kx = 0; kx < kernelWidth; kx++) {
            int pixelY = y + ky - kernelCenterY;
            int pixelX = x + kx - kernelCenterX;

            if (pixelY >= 0 && pixelY < height && pixelX >= 0 && pixelX < width) {
              double kernelValue = kernelMatrix[ky][kx];
              int[] rgb = originalPixels[pixelY][pixelX];
              for (int i = 0; i < 3; i++) {
                newRgb[i] += rgb[i] * kernelValue;
              }
            }
          }
        }

        for (int i = 0; i < 3; i++) {
          newPixels[y][x][i] = clamp((int) Math.round(newRgb[i]), 0, maxValue);
        }
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Ensures a value falls within a specified range.
   * Used to keep computed pixel values within valid bounds.
   *
   * @param value The number to be clamped
   * @param min   The minimum allowed value
   * @param max   The maximum allowed value
   * @return The clamped value, guaranteed to be between min and max inclusive
   */
  private static int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }
}