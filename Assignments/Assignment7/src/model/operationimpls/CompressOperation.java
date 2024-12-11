package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An operation that compresses an image by removing the least significant values.
 * This operation uses the discrete wavelet transform to compress the image.
 */
public class CompressOperation implements ImageOperation {
  private final double percentage;

  /**
   * Constructs a CompressOperation with the specified compression percentage.
   *
   * @param percentage The percentage to which the image should be compressed (0-100).
   * @throws IllegalArgumentException if the compression percentage is invalid.
   */
  public CompressOperation(double percentage) {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Compression percentage must be between 0 and 100.");
    }
    this.percentage = percentage;
  }

  /**
   * Compresses the image by removing the least significant values.
   * This operation uses the discrete wavelet transform to compress the image.
   *
   * @param images The source image to compress.
   * @return A new compressed version of the input image.
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("CompressOperation requires exactly one image.");
    }

    ImageInterface image = images[0];
    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();

    int workingDim = getNextPowerOfTwo(Math.max(width, height));

    double[][][] channels = initializeChannels(image, workingDim);

    for (int c = 0; c < 3; c++) {
      forwardDWT(channels[c], workingDim);

      List<Double> sortedValues = getSortedUniqueCoefficients(channels[c]);

      thresholdCoefficients(channels[c], sortedValues);

      inverseDWT(channels[c], workingDim);
    }

    int[][][] newPixels = reconstructImage(channels, width, height, maxValue);

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Calculates the next highest power of two for the given number.
   *
   * @param n The input number.
   * @return The next highest power of two.
   */
  private int getNextPowerOfTwo(int n) {
    int power = 1;
    while (power < n) {
      power *= 2;
    }
    return power;
  }

  /**
   * Initializes and pads the channels array for DWT.
   *
   * @param image      The source image.
   * @param workingDim The working dimension (power of two).
   * @return A 3D array containing the padded channels.
   */
  private double[][][] initializeChannels(ImageInterface image, int workingDim) {
    int width = image.getWidth();
    int height = image.getHeight();
    double[][][] channels = new double[3][workingDim][workingDim];
    for (int c = 0; c < 3; c++) {
      for (int y = 0; y < workingDim; y++) {
        for (int x = 0; x < workingDim; x++) {
          int px = x < width ? x : width - 1;
          int py = y < height ? y : height - 1;
          channels[c][y][x] = image.getPixel(px, py)[c];
        }
      }
    }
    return channels;
  }

  /**
   * Performs the forward Discrete Wavelet Transform on a channel.
   *
   * @param channel The 2D array representing the channel.
   * @param size    The current size of the transformation.
   */
  private void forwardDWT(double[][] channel, int size) {
    while (size > 1) {
      for (int row = 0; row < size; row++) {
        double[] temp = new double[size];
        for (int i = 0; i < size / 2; i++) {
          int j = 2 * i;
          temp[i] = (channel[row][j] + channel[row][j + 1]) / Math.sqrt(2);
          temp[i + size / 2] = (channel[row][j] - channel[row][j + 1]) / Math.sqrt(2);
        }
        System.arraycopy(temp, 0, channel[row], 0, size);
      }

      for (int col = 0; col < size; col++) {
        double[] temp = new double[size];
        for (int i = 0; i < size / 2; i++) {
          int j = 2 * i;
          temp[i] = (channel[j][col] + channel[j + 1][col]) / Math.sqrt(2);
          temp[i + size / 2] = (channel[j][col] - channel[j + 1][col]) / Math.sqrt(2);
        }
        for (int i = 0; i < size; i++) {
          channel[i][col] = temp[i];
        }
      }
      size /= 2;
    }
  }

  /**
   * Collects and sorts unique absolute coefficients from a channel.
   *
   * @param channel The 2D array representing the channel.
   * @return A sorted list of unique absolute coefficient values.
   */
  private List<Double> getSortedUniqueCoefficients(double[][] channel) {
    Set<Double> uniqueValues = new HashSet<>();
    int workingDim = channel.length;
    for (int i = 0; i < workingDim; i++) {
      for (int j = 0; j < workingDim; j++) {
        if (channel[i][j] != 0) {
          uniqueValues.add(Math.abs(channel[i][j]));
        }
      }
    }
    List<Double> sortedValues = new ArrayList<>(uniqueValues);
    sortedValues.sort(Double::compareTo);
    return sortedValues;
  }

  /**
   * Thresholds the coefficients in a channel based on the compression percentage.
   *
   * @param channel      The 2D array representing the channel.
   * @param sortedValues The sorted list of unique coefficient values.
   */
  private void thresholdCoefficients(double[][] channel, List<Double> sortedValues) {
    if (!sortedValues.isEmpty()) {
      int thresholdIndex = (int) (sortedValues.size() * percentage * 0.01) - 1;
      if (thresholdIndex >= 0) {
        double threshold = sortedValues.get(thresholdIndex);
        int workingDim = channel.length;
        for (int i = 0; i < workingDim; i++) {
          for (int j = 0; j < workingDim; j++) {
            if (Math.abs(channel[i][j]) <= threshold) {
              channel[i][j] = 0.0;
            }
          }
        }
      }
    }
  }

  /**
   * Performs the inverse Discrete Wavelet Transform on a channel.
   *
   * @param channel     The 2D array representing the channel.
   * @param workingDim  The working dimension (power of two).
   */
  private void inverseDWT(double[][] channel, int workingDim) {
    int size = 2;
    while (size <= workingDim) {
      for (int col = 0; col < size; col++) {
        double[] temp = new double[size];
        for (int i = 0; i < size / 2; i++) {
          double a = channel[i][col];
          double b = channel[i + size / 2][col];
          int j = 2 * i;
          temp[j] = (a + b) / Math.sqrt(2);
          temp[j + 1] = (a - b) / Math.sqrt(2);
        }
        for (int i = 0; i < size; i++) {
          channel[i][col] = temp[i];
        }
      }

      for (int row = 0; row < size; row++) {
        double[] temp = new double[size];
        for (int i = 0; i < size / 2; i++) {
          double a = channel[row][i];
          double b = channel[row][i + size / 2];
          int j = 2 * i;
          temp[j] = (a + b) / Math.sqrt(2);
          temp[j + 1] = (a - b) / Math.sqrt(2);
        }
        System.arraycopy(temp, 0, channel[row], 0, size);
      }
      size *= 2;
    }
  }

  /**
   * Reconstructs the image pixels from the processed channels.
   *
   * @param channels The 3D array containing the processed channels.
   * @param width    The original image width.
   * @param height   The original image height.
   * @param maxValue The maximum pixel value.
   * @return A 3D array of the reconstructed image pixels.
   */
  private int[][][] reconstructImage(double[][][] channels, int width, int height, int maxValue) {
    int[][][] newPixels = new int[height][width][3];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        for (int c = 0; c < 3; c++) {
          newPixels[y][x][c] = clamp((int) Math.round(channels[c][y][x]), 0, maxValue);
        }
      }
    }
    return newPixels;
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
  private static int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }
}