package model.image;

/**
 * A class that represents an image.
 * This class contains the width, height, maximum value, and pixels of an image.
 * The pixels are stored in a 3D array where the first dimension represents the height,
 * the second dimension represents the width, and the third dimension represents the channels (RGB).
 */
public class Image implements ImageInterface {
  private final int width;
  private final int height;
  private final int maxValue;
  private final int[][][] pixels;

  /**
   * Creates a new image with the specified width, height, maximum value, and pixels.
   *
   * @param width    The width of the image.
   * @param height   The height of the image.
   * @param maxValue The maximum value of the image.
   * @param pixels   The pixels of the image.
   */
  public Image(int width, int height, int maxValue, int[][][] pixels) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixels = pixels;
  }

  /**
   * Gets the pixel at the specified coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The RGB values of the pixel.
   */
  @Override
  public int[] getPixel(int x, int y) {
    return pixels[y][x];
  }

  /**
   * Gets the width of the image.
   *
   * @return The width of the image.
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the image.
   *
   * @return The height of the image.
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Gets the maximum value of the image.
   *
   * @return The maximum value of the image.
   */
  @Override
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Gets the pixels of the image.
   *
   * @return The pixels of the image.
   */
  @Override
  public int[][][] getPixels() {
    return pixels;
  }

  /**
   * Clones the image.
   *
   * @return A new image with the same width, height, maximum value,
   *         and pixels as the original image.
   */
  @Override
  public ImageInterface clone() {
    int[][][] pixelsCopy = new int[height][width][3];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        pixelsCopy[y][x] = pixels[y][x].clone();
      }
    }
    return new Image(width, height, maxValue, pixelsCopy);
  }
}