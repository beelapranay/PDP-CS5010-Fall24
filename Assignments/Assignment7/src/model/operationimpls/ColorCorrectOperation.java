package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

/**
 * Operation class that adjusts the color balance of an image by shifting the RGB values of each
 * pixel. The operation calculates the peak positions of the RGB histograms and shifts the values to
 * align them.
 */
public class ColorCorrectOperation implements ImageOperation {

  /**
   * Adjusts the color balance of the input image by shifting the RGB values of each pixel.
   * The operation calculates the peak positions of the RGB histograms and shifts the values to
   * align them.
   *
   * @param images The input image to adjust.
   * @return A new image with the adjusted color balance.
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("ColorCorrectOperation requires exactly one image.");
    }
    ImageInterface image = images[0];

    int[][] histograms = HistogramOperation.calculateHistograms(image);

    int[] peakPositions = findMeaningfulPeaks(histograms);
    int averagePeak = (peakPositions[0] + peakPositions[1] + peakPositions[2]) / 3;

    int[] shifts = new int[3];
    for (int c = 0; c < 3; c++) {
      shifts[c] = averagePeak - peakPositions[c];
    }

    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();
    int[][][] newPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = image.getPixel(x, y);
        int[] newRgb = new int[3];
        for (int c = 0; c < 3; c++) {
          newRgb[c] = clamp(rgb[c] + shifts[c], 0, maxValue);
        }
        newPixels[y][x] = newRgb;
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Finds the peak positions of the RGB histograms.
   *
   * @param histograms The RGB histograms.
   * @return The peak positions of the RGB histograms.
   */
  private int[] findMeaningfulPeaks(int[][] histograms) {
    int[] peaks = new int[3];

    for (int c = 0; c < 3; c++) {
      int maxFrequency = 0;
      int peakPosition = 0;
      for (int i = 10; i < 245; i++) {
        int frequency = histograms[c][i];
        if (frequency > maxFrequency) {
          maxFrequency = frequency;
          peakPosition = i;
        }
      }
      peaks[c] = peakPosition;
    }

    return peaks;
  }

  private int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }
}
