package view;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import controller.ImageController;
import model.RGB;

/**
 * ImageView class provides the graphical interface for displaying and processing
 * image histograms, grid overlays, and image transformations.
 */
public class ImageView extends JPanel implements ImageViewInterface {
  private final ImageController imageController;
  private Map<String, int[]> histogramData;

  /**
   * Constructs an ImageView with a specified ImageController.
   *
   * @param imageController the controller to manage images.
   */
  public ImageView(ImageController imageController) {
    this.imageController = imageController;
    this.histogramData = new HashMap<>();
    setPreferredSize(new Dimension(1000, 1000));
  }

  /**
   * Generates and stores a histogram for the specified image, and saves the
   * histogram image to the controller under the specified destination name.
   *
   * @param imageName            the name of the source image.
   * @param destinationImageName the name under which to save the histogram image.
   * @throws NoSuchElementException if the source image is not found.
   */
  public void generateAndStoreHistogram(String imageName, String destinationImageName) {
    if (!imageController.getImages().containsKey(imageName)) {
      throw new NoSuchElementException("Image not found: " + imageName);
    }

    RGB[][] pixelArray = imageController.getImages().get(imageName);

    this.histogramData = calculateHistogramData(pixelArray);

    BufferedImage histogramImage = new BufferedImage(256, 256,
            BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = histogramImage.createGraphics();

    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, 256, 256);

    drawHistogram(g2d);
    g2d.dispose();

    imageController.getImages().put(destinationImageName, convertToRGBArray(histogramImage));
  }

  /**
   * Paints the component with grid lines and color histograms if available.
   *
   * @param g the Graphics object to protect.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    drawGrid(g);

    if (histogramData != null) {
      drawChannelHistogram(g, "Red", Color.RED);
      drawChannelHistogram(g, "Green", Color.GREEN);
      drawChannelHistogram(g, "Blue", Color.BLUE);
    }
  }

  /**
   * Draws a grid on the panel.
   *
   * @param g the Graphics object to draw with.
   */
  private void drawGrid(Graphics g) {
    g.setColor(Color.LIGHT_GRAY);

    for (int x = 0; x <= 256; x += 16) {
      int xPos = (int) (x * 1000.0 / 256);
      g.drawLine(xPos, 0, xPos, 256);
    }

    for (int y = 0; y <= 256; y += 80) {
      int yPos = 256 - y;
      g.drawLine(0, yPos, 1000, yPos);
    }
  }

  /**
   * Draws a histogram for a specified color channel.
   *
   * @param g       the Graphics object to draw with.
   * @param channel the color channel to draw (e.g., "Red", "Green", "Blue").
   * @param color   the color to use for drawing the histogram.
   */
  private void drawChannelHistogram(Graphics g, String channel, Color color) {
    int width = 255;
    int height = 255;
    g.setColor(Color.LIGHT_GRAY);
    for (int i = 0; i <= width; i += 20) {
      g.drawLine(i, 0, i, height);
    }
    for (int i = 0; i <= height; i += 20) {
      g.drawLine(0, i, width, i);
    }

    int[] data = histogramData.get(channel);
    g.setColor(color);

    int maxCount = 0;
    for (int count : data) {
      if (count > maxCount) {
        maxCount = count;
      }
    }

    double scale = maxCount > 0 ? 256.0 / maxCount : 1;

    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke(1.5f));

    for (int i = 0; i < data.length - 1; i++) {
      int y1 = 256 - (int) (data[i] * scale);
      int x2 = i + 1;
      int y2 = 256 - (int) (data[i + 1] * scale);
      g2d.drawLine(i, y1, x2, y2);
    }
  }

  /**
   * Draws the histograms for red, green, and blue channels on the given Graphics object.
   *
   * @param g the Graphics object to draw with.
   */
  private void drawHistogram(Graphics g) {
    drawChannelHistogram(g, "Red", Color.RED);
    drawChannelHistogram(g, "Green", Color.GREEN);
    drawChannelHistogram(g, "Blue", Color.BLUE);
  }

  /**
   * Calculates the histogram data for an RGB pixel array, generating frequency counts
   * for each color channel.
   *
   * @param pixelArray the RGB pixel array.
   * @return a Map containing histogram data for red, green, and blue channels.
   */
  public Map<String, int[]> calculateHistogramData(RGB[][] pixelArray) {
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];

    for (RGB[] row : pixelArray) {
      for (RGB pixel : row) {
        redHistogram[pixel.red]++;
        greenHistogram[pixel.green]++;
        blueHistogram[pixel.blue]++;
      }
    }

    Map<String, int[]> histogramData = new HashMap<>();
    histogramData.put("Red", redHistogram);
    histogramData.put("Green", greenHistogram);
    histogramData.put("Blue", blueHistogram);

    return histogramData;
  }

  /**
   * Converts a BufferedImage to a 2D RGB array.
   *
   * @param image the BufferedImage to convert.
   * @return a 2D array of RGB values.
   */
  private RGB[][] convertToRGBArray(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    RGB[][] rgbArray = new RGB[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        rgbArray[y][x] = new RGB(red, green, blue);
      }
    }
    return rgbArray;
  }
}