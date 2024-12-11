package model.operationimpls;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

/**
 * Operation class for generating a histogram of an image.
 */
public class HistogramOperation implements ImageOperation {

  /**
   * Generates a histogram of the input image.
   *
   * @param images The input image to generate histogram from.
   * @return A new image representing the histogram.
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("HistogramOperation requires exactly one image.");
    }
    ImageInterface image = images[0];

    int[][] histograms = calculateHistograms(image);
    int maxWidth = 256;
    int maxHeight = 256;

    double redMax = maxFrequency(histograms[0]);
    double greenMax = maxFrequency(histograms[1]);
    double blueMax = maxFrequency(histograms[2]);
    double maxFrequency = Math.max(redMax, Math.max(greenMax, blueMax));
    double scalingFactor = maxHeight / maxFrequency;

    BufferedImage histogramImage =
            new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = histogramImage.createGraphics();

    drawHistogram(graphics, maxWidth, maxHeight);

    drawLineChart(graphics, histograms[0], scalingFactor, Color.RED);
    drawLineChart(graphics, histograms[1], scalingFactor, Color.GREEN);
    drawLineChart(graphics, histograms[2], scalingFactor, Color.BLUE);

    graphics.dispose();

    int[][][] pixels = new int[256][256][3];
    for (int y = 0; y < 256; y++) {
      for (int x = 0; x < 256; x++) {
        int rgb = histogramImage.getRGB(x, y);
        Color color = new Color(rgb);
        pixels[y][x][0] = color.getRed();
        pixels[y][x][1] = color.getGreen();
        pixels[y][x][2] = color.getBlue();
      }
    }

    return new Image(256, 256, 255, pixels);
  }

  /**
   * Draws the background grid for the histogram.
   *
   * @param graphics  The graphics object to draw on.
   * @param maxWidth  The width of the histogram.
   * @param maxHeight The height of the histogram.
   */
  private void drawHistogram(Graphics2D graphics, int maxWidth, int maxHeight) {
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, maxWidth, maxHeight);

    graphics.setColor(Color.LIGHT_GRAY);
    for (int i = 0; i < maxWidth; i += 8) {
      graphics.drawLine(i, 0, i, maxHeight);
    }
    for (int i = 0; i < maxHeight; i += 8) {
      graphics.drawLine(0, i, maxWidth, i);
    }
  }

  /**
   * Draws a line chart of the histogram data.
   *
   * @param graphics The graphics object to draw on.
   * @param data     The histogram data to draw.
   * @param scale    The scaling factor for the data.
   * @param color    The color of the line.
   */
  private void drawLineChart(Graphics2D graphics, int[] data, double scale, Color color) {
    graphics.setColor(color);
    for (int i = 1; i < data.length; i++) {
      double value = data[i] * scale;
      double prevValue = data[i - 1] * scale;
      graphics.drawLine(i, (int) (256 - prevValue), i + 1, (int) (256 - value));
    }
  }

  /**
   * Finds the maximum frequency in a histogram.
   *
   * @param frequency The histogram data.
   * @return The maximum frequency.
   */
  private int maxFrequency(int[] frequency) {
    int max = 0;
    for (int freq : frequency) {
      if (freq > max) {
        max = freq;
      }
    }
    return max;
  }

  /**
   * Calculates the histograms of an image.
   *
   * @param image The image to calculate histograms from.
   * @return The histograms of the image.
   */
  protected static int[][] calculateHistograms(ImageInterface image) {
    int[][] histograms = new int[3][256];
    int width = image.getWidth();
    int height = image.getHeight();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = image.getPixel(x, y);
        histograms[0][rgb[0]]++;
        histograms[1][rgb[1]]++;
        histograms[2][rgb[2]]++;
      }
    }

    return histograms;
  }
}
