package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.RGB;

/**
 * Default implementation of ImageLoaderAndSaver for loading and saving images in standard formats.
 */
public class DefaultImageLoaderAndSaver extends ImageLoaderAndSaver {
  private final controller.ImageControllerInterface imageController;

  /**
   * Constructor that initializes the image controller.
   *
   * @param imageController the controller to interact with images.
   */
  public DefaultImageLoaderAndSaver(ImageControllerInterface imageController) {
    this.imageController = imageController;
  }

  /**
   * Loads an image from the specified file path and converts it to a 2D array of RGB values.
   *
   * @param filePath the path of the image file
   * @param imageName the name of the image to load
   * @return a 2D array of RGB values representing the image
   * @throws IOException if an error occurs during loading
   */
  @Override
  public RGB[][] loadImage(String filePath, String imageName) throws IOException {
    RGB[][] pixelArray;

    try {
      BufferedImage image = ImageIO.read(new File(filePath));

      int width = image.getWidth();
      int height = image.getHeight();
      pixelArray = new RGB[height][width];

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = image.getRGB(x, y);
          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;
          pixelArray[y][x] = new RGB(red, green, blue);
        }
      }
    } catch (IOException e) {
      throw new IOException(e);
    }

    return pixelArray;
  }

  /**
   * Saves the image with the specified name to the file path.
   *
   * @param filePath the path to save the image to
   * @param imageName the name of the image to save
   * @throws IOException if an error occurs during saving
   */
  public void saveImage(String filePath, String imageName) throws IOException {
    try {
      File outputfile = new File(filePath);
      ImageIO.write(arrayToImage(this.imageController.getImages().get(imageName)),
              getFileFormat(filePath), outputfile);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
