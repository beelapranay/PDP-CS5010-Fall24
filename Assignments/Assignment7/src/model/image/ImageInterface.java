package model.image;

/**
 * An interface that defines the behavior of an image.
 * It includes methods to get pixel values, dimensions, maximum values,
 * and perform operations like setting a pixel or cloning the image.
 */
public interface ImageInterface {

  /**
   * Gets the pixel at the specified coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The RGB values of the pixel.
   */
  int[] getPixel(int x, int y);

  /**
   * Gets the width of the image.
   *
   * @return The width of the image.
   */
  int getWidth();

  /**
   * Gets the height of the image.
   *
   * @return The height of the image.
   */
  int getHeight();

  /**
   * Gets the maximum value of the image.
   *
   * @return The maximum value of the image.
   */
  int getMaxValue();

  /**
   * Gets the pixels of the image.
   *
   * @return The pixels of the image.
   */
  int[][][] getPixels();

  /**
   * Clones the image.
   *
   * @return A new image with the same width, height, max value,and pixels as the original image.
   */
  ImageInterface clone();
}
