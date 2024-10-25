package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import model.ImageModel;
import model.ImageModelInterface;
import model.RGB;

/**
 * ImageController handles loading, saving, and processing image commands.
 */
public class ImageController implements ImageControllerInterface {
  protected HashMap<String, RGB[][]> images = new HashMap<>();

  /**
   * Loads the image from the specified file path and associates it with a name.
   *
   * @param filePath the path of the image file
   * @param imageName the name to assign to the loaded image
   * @throws IOException if an error occurs during loading
   */
  @Override
  public void loadImage(String filePath, String imageName) throws IOException {
    ImageLoaderAndSaver imageLoader = ImageLoaderFactory
            .getImageLoader(filePath, this);
    RGB[][] pixelArray = imageLoader.loadImage(filePath, imageName);
    images.put(imageName, pixelArray);
  }

  /**
   * Saves the image to the specified file path.
   *
   * @param filePath the path to save the image
   * @param imageName the name of the image to save
   * @throws IOException if an error occurs during saving
   */
  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    ImageLoaderAndSaver imageLoader = ImageLoaderFactory
            .getImageLoader(filePath, this);
    imageLoader.saveImage(filePath, imageName);
  }

  /**
   * Retrieves all loaded images, mapped by their names.
   *
   * @return a HashMap of image names and corresponding RGB arrays
   */
  public HashMap<String, RGB[][]> getImages() {
    return this.images;
  }

  /**
   * Parses and processes a single command or runs a series of commands from a file.
   *
   * @param command the command to be parsed and executed
   * @throws IOException if an error occurs during processing
   */
  @Override
  public void commandParser(String command) throws IOException {
    String[] tokens = command.split("\\s+");

    if (tokens[0].equals("run")) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(tokens[1]));
        String line;

        while ((line = reader.readLine()) != null) {
          if (!line.trim().startsWith("#") && !line.trim().isEmpty()) {
            String[] fileTokens = line.split("\\s+");
            processCommands(fileTokens);
          }
        }

        reader.close();
      } catch (IOException e) {
        throw new IOException(e.getMessage());
      }
    } else {
      processCommands(tokens);
    }
  }

  /**
   * Processes a given array of command tokens.
   *
   * @param tokens an array of command strings
   * @throws IOException if an error occurs during processing
   */
  @Override
  public void processCommands(String[] tokens) throws IOException {
    ImageModelInterface model = new ImageModel(this);

    switch (tokens[0]) {
      case "load":
        loadImage(tokens[1], tokens[2]);
        break;
      case "save":
        saveImage(tokens[1], tokens[2]);
        break;
      case "red-component":
        model.splitRedChannel(tokens[1], tokens[2]);
        break;
      case "green-component":
        model.splitGreenChannel(tokens[1], tokens[2]);
        break;
      case "blue-component":
        model.splitBlueChannel(tokens[1], tokens[2]);
        break;
      case "value-component":
        model.calculateValue(tokens[1], tokens[2]);
        break;
      case "luma-component":
        model.calculateLuma(tokens[1], tokens[2]);
        break;
      case "intensity-component":
        model.calculateIntensity(tokens[1], tokens[2]);
        break;
      case "horizontal-flip":
        model.horizontalFlipImage(tokens[1], tokens[2]);
        break;
      case "vertical-flip":
        model.verticalFlipImage(tokens[1], tokens[2]);
        break;
      case "brighten":
        int value = Integer.parseInt(tokens[1]);
        model.changeBrightness(tokens[2], tokens[3], value);
        break;
      case "rgb-split":
        model.splitRedChannel(tokens[1], tokens[2]);
        model.splitGreenChannel(tokens[1], tokens[3]);
        model.splitBlueChannel(tokens[1], tokens[4]);
        break;
      case "rgb-combine":
        model.combineGreyscale(tokens[1], tokens[2], tokens[3], tokens[4]);
        break;
      case "blur":
        model.blurImage(tokens[1], tokens[2]);
        break;
      case "sharpen":
        model.sharpenImage(tokens[1], tokens[2]);
        break;
      case "sepia":
        model.applySepiaTone(tokens[1], tokens[2]);
        break;
      default:
        System.out.println("Unknown command: " + tokens[0] + "!");
    }
  }
}
