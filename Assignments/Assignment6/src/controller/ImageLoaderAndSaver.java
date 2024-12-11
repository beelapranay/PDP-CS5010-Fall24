package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import model.RGB;

/**
 * Abstract class for loading and saving images, providing common utilities.
 */
public abstract class ImageLoaderAndSaver {

  /**
   * Loads an image from the specified file path.
   *
   * @param filePath the path of the image file
   * @param imageName the name of the image to load
   * @return a 2D array of RGB values representing the image
   * @throws IOException if an error occurs during loading
   */
  public abstract RGB[][] loadImage(String filePath, String imageName) throws IOException;

  /**
   * Saves the image with the specified name to the file path.
   *
   * @param filePath the path to save the image to
   * @param imageName the name of the image to save
   * @throws IOException if an error occurs during saving
   */
  public abstract void saveImage(String filePath, String imageName) throws IOException;

  /**
   * Extracts the file format from the file path.
   *
   * @param filePath the path of the file
   * @return the file format extension as a string
   */
  protected String getFileFormat(String filePath) {
    String[] parts = filePath.split("\\.");
    if (parts.length > 1) {
      return parts[parts.length - 1];
    } else {
      return "";
    }
  }

  /**
   * Converts a 2D RGB array to a BufferedImage.
   *
   * @param image the 2D array of RGB values
   * @return a BufferedImage representation of the RGB array
   */
  protected BufferedImage arrayToImage(RGB[][] image) {
    int width = image[0].length;
    int height = image.length;
    BufferedImage processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = image[y][x];
        int rgb = (pixel.red << 16) | (pixel.green << 8) | pixel.blue;
        processedImage.setRGB(x, y, rgb);
      }
    }

    return processedImage;
  }
}
