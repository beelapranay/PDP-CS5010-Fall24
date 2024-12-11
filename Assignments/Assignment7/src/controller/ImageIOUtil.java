package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.image.Image;
import model.image.ImageInterface;

import javax.imageio.ImageIO;

/**
 * A utility class that provides methods to read and write images.
 * This class supports reading and writing images in PPM, PNG, and JPEG formats.
 * It uses Java ImageIO to write images in PNG and JPEG formats.
 */
public class ImageIOUtil {

  /**
   * Reads an image from the specified path.
   * The method reads the image file based on the file extension.
   * Supported formats include PPM, PNG, and JPEG.
   *
   * @param path The path to the image file.
   * @return The image object containing the pixel data.
   * @throws IOException If an error occurs while reading the image.
   */
  public static ImageInterface readImage(String path) throws IOException {
    String extension = getFileExtension(path);
    switch (extension.toLowerCase()) {
      case "ppm":
        return readPPM(path);
      case "png":
      case "jpg":
      case "jpeg":
        return readWithImageIO(path);
      default:
        throw new IOException("Unsupported file format: " + extension);
    }
  }

  /**
   * This method reads images in PNG and JPEG formats.
   * It converts the image to a custom Image object.
   * The image is read as a BufferedImage and the pixel data is extracted.
   * The pixel data is stored in a 3D array where the first dimension represents the height,
   * the second dimension represents the width, and the third dimension represents the RGB channels.
   *
   * @param path The path to the image file.
   * @return The image object containing the pixel data.
   * @throws IOException If an error occurs while reading the image.
   */
  private static ImageInterface readWithImageIO(String path) throws IOException {
    try {
      BufferedImage bufferedImage = ImageIO.read(new File(path));
      int width = bufferedImage.getWidth();
      int height = bufferedImage.getHeight();
      int[][][] pixels = new int[height][width][3];

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int argb = bufferedImage.getRGB(x, y);
          int red = (argb >> 16) & 0xFF;
          int green = (argb >> 8) & 0xFF;
          int blue = argb & 0xFF;
          pixels[y][x] = new int[]{red, green, blue};
        }
      }
      return new Image(width, height, 255, pixels);
    } catch (Exception e) {
      throw new IOException("Failed to read image: " + path, e);
    }
  }

  /**
   * Writes an image to the specified path.
   * The image is written in the specified format based on the file extension.
   * Supported formats include PPM, PNG, and JPEG.
   * It uses Java ImageIO to write images in PNG and JPEG formats.
   *
   * @param image The image object to be written.
   * @param path  The path to write the image file.
   * @throws IOException If an error occurs while writing the image.
   */
  public static void writeImage(ImageInterface image, String path) throws IOException {
    String extension = getFileExtension(path);
    switch (extension.toLowerCase()) {
      case "ppm":
        writePPM(image, path);
        break;
      case "png":
      case "jpg":
      case "jpeg":
        writeWithImageIO(image, path, extension);
        break;
      default:
        throw new IOException("Unsupported file format: " + extension);
    }
  }

  /**
   * Writes an image using Java ImageIO.
   * This method writes images in PNG and JPEG formats.
   * It converts the image to a BufferedImage and sets the pixel data.
   * The pixel data is extracted from the custom Image object and set in the BufferedImage.
   *
   * @param image  The image object containing the pixel data.
   * @param path   The path to write the image file.
   * @param format The format of the image file (PNG or JPEG).
   * @throws IOException If an error occurs while writing the image.
   */
  private static void writeWithImageIO(ImageInterface image, String path, String format)
          throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = image.getPixels()[y][x];
        int rgbInt = ((rgb[0] & 0xFF) << 16) | ((rgb[1] & 0xFF) << 8) | (rgb[2] & 0xFF);
        bufferedImage.setRGB(x, y, rgbInt);
      }
    }


    File outputFile = new File(path);
    if (!ImageIO.write(bufferedImage, format, outputFile)) {
      throw new IOException("Could not write image in the specified format: " + format);
    }
  }

  /**
   * Reads an image in PPM format.
   * The method reads the image file and extracts the pixel data.
   * The pixel data is stored in a 3D array where the first dimension represents the height,
   * the second dimension represents the width, and the third dimension represents the RGB channels.
   *
   * @param filename The path to the PPM file.
   * @return The image object containing the pixel data.
   * @throws IOException If an error occurs while reading the image.
   */
  public static ImageInterface readPPM(String filename) throws IOException {
    StringBuilder builder = new StringBuilder();
    try (Scanner fileScanner = new Scanner(new FileInputStream(filename))) {
      while (fileScanner.hasNextLine()) {
        String s = fileScanner.nextLine();
        if (!s.startsWith("#")) {
          builder.append(s).append(System.lineSeparator());
        }
      }
    } catch (FileNotFoundException e) {
      throw new IOException("File not found: " + filename, e);
    }

    try (Scanner sc = new Scanner(builder.toString())) {
      String token = sc.next();
      if (!token.equals("P3")) {
        throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();
      int[][][] pixels = new int[height][width][3];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          pixels[i][j] = new int[]{r, g, b};
        }
      }
      return new Image(width, height, maxValue, pixels);
    }
  }

  /**
   * Writes an image in PPM format.
   * The method writes the image file in PPM format based on the pixel data.
   * The pixel data is extracted from the custom Image object and written to the file.
   *
   * @param image    The image object containing the pixel data.
   * @param filename The path to write the PPM file.
   * @throws IOException If an error occurs while writing the image.
   */
  public static void writePPM(ImageInterface image, String filename) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
      writer.println("P3");
      writer.println(image.getWidth() + " " + image.getHeight());
      writer.println(image.getMaxValue());
      int[][][] pixels = image.getPixels();
      for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
          int[] rgb = pixels[y][x];
          writer.println(rgb[0]);
          writer.println(rgb[1]);
          writer.println(rgb[2]);
        }
      }
    }
  }

  /**
   * Gets the file extension from the filename.
   * The method extracts the file extension from the filename.
   *
   * @param filename The filename to extract the extension from.
   * @return The file extension.
   */
  private static String getFileExtension(String filename) {
    int index = filename.lastIndexOf('.');
    if (index > 0) {
      return filename.substring(index + 1);
    }
    return "";
  }
}