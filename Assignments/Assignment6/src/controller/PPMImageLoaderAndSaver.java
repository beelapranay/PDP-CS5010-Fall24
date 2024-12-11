package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.RGB;

/**
 * PPM image loader and saver that supports loading and saving PPM format images.
 */
public class PPMImageLoaderAndSaver extends ImageLoaderAndSaver {
  private final controller.ImageControllerInterface imageController;

  /**
   * Constructor that initializes the image controller.
   *
   * @param imageController the controller to interact with images
   */
  public PPMImageLoaderAndSaver(ImageControllerInterface imageController) {
    this.imageController = imageController;
  }

  /**
   * Loads a PPM image from the specified file path and converts it to a 2D array of RGB values.
   *
   * @param filePath  the path of the PPM image file
   * @param imageName the name to associate with the loaded image
   * @return a 2D array of RGB values representing the image
   * @throws IOException if an error occurs during loading
   */
  public RGB[][] loadImage(String filePath, String imageName) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(filePath);
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IOException("Invalid PPM file format.");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();

    sc.nextInt();

    RGB[][] pixelArray = new RGB[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelArray[i][j] = new RGB(r, g, b);
      }
    }

    return pixelArray;
  }

  /**
   * Saves the image as a PPM file at the specified file path.
   *
   * @param filePath  the path to save the image to
   * @param imageName the name of the image to save
   * @throws IOException if an error occurs during saving
   */
  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    RGB[][] pixelArray = this.imageController.getImages().get(imageName);

    try {
      int width = pixelArray[0].length;
      int height = pixelArray.length;

      FileWriter writer = new FileWriter(filePath);

      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n");

      for (RGB[] rgbs : pixelArray) {
        for (int x = 0; x < width; x++) {
          RGB pixel = rgbs[x];
          writer.write(pixel.red + " " + pixel.green + " " + pixel.blue + " ");
        }
        writer.write("\n");
      }

      writer.close();
    } catch (IOException e) {
      throw new IOException("Error saving the PPM image.");
    }
  }
}
