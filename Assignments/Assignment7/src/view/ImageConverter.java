package view;

import model.image.ImageInterface;

import java.awt.image.BufferedImage;

/**
 * Utility class to convert ImageInterface to BufferedImage.
 */
public class ImageConverter {
  /**
   * Converts an ImageInterface to a BufferedImage.
   *
   * @param image the image to convert
   * @return the BufferedImage
   */
  public static BufferedImage toBufferedImage(ImageInterface image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    int[][][] pixels = image.getPixels();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = pixels[y][x];
        int r = rgb[0];
        int g = rgb[1];
        int b = rgb[2];
        int color = (r << 16) | (g << 8) | b;
        bufferedImage.setRGB(x, y, color);
      }
    }
    return bufferedImage;
  }
}
