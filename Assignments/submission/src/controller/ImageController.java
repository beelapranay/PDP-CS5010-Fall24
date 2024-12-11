package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import model.ImageModel;
import model.ImageModelInterface;
import model.RGB;
import view.ImageView;
import view.ImageViewInterface;

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
    ImageViewInterface view = new ImageView(this);
    ImageModelInterface model = new ImageModel(this, view);

    switch (tokens[0]) {
      case "load":
        if (tokens.length < 3) {
          System.out.println("Error: 'load' requires a file path and image name.");
        } else {
          loadImage(tokens[1], tokens[2]);
        }
        break;
      case "save":
        if (tokens.length < 3) {
          System.out.println("Error: 'save' requires a file path and image name.");
        } else {
          saveImage(tokens[1], tokens[2]);
        }
        break;
      case "red-component":
      case "green-component":
      case "blue-component":
      case "value-component":
      case "luma-component":
      case "intensity-component":
        if (tokens.length == 3) {
          model.applyGreyscaleTransformation(tokens[1], tokens[2], tokens[0], 100);
        } else if (tokens.length == 5 && tokens[3].equals("split")) {
          int splitPercentage = Integer.parseInt(tokens[4]);
          model.applyGreyscaleTransformation(tokens[1], tokens[2], tokens[0], splitPercentage);
        } else {
          System.out.println("Error: Invalid arguments for " + tokens[0]);
        }
        break;
      case "horizontal-flip":
      case "vertical-flip":
        if (tokens.length < 3) {
          System.out.println("Error: '" + tokens[0]
                  + "' requires source and destination image names.");
        } else {
          model.flipImage(tokens[1], tokens[2], tokens[0]);
        }
        break;
      case "brighten":
        if (tokens.length < 4) {
          System.out.println("Error: 'brighten' requires a brightness " +
                  "value, source, and destination image names.");
        } else {
          int value = Integer.parseInt(tokens[1]);
          model.changeBrightness(tokens[2], tokens[3], value);
        }
        break;
      case "rgb-split":
        if (tokens.length < 5) {
          System.out.println("Error: 'rgb-split' requires three destination image names.");
        } else {
          model.applyGreyscaleTransformation(tokens[1],
                  tokens[2], "red-component", 100);
          model.applyGreyscaleTransformation(tokens[1],
                  tokens[3], "green-component", 100);
          model.applyGreyscaleTransformation(tokens[1],
                  tokens[4], "blue-component", 100);
        }
        break;
      case "rgb-combine":
        if (tokens.length < 5) {
          System.out.println("Error: 'rgb-combine' requires " +
                  "three source and one destination image names.");
        } else {
          model.combineGreyscale(tokens[1], tokens[2], tokens[3], tokens[4]);
        }
        break;
      case "blur":
      case "sharpen":
        if (tokens.length == 3) {
          model.blurOrSharpen(tokens[1], tokens[2], 100, tokens[0]);
        } else if (tokens.length == 5 && tokens[3].equals("split")) {
          int splitPercentage = Integer.parseInt(tokens[4]);
          model.blurOrSharpen(tokens[1], tokens[2], splitPercentage, tokens[0]);
        } else {
          System.out.println("Error: Invalid arguments for " + tokens[0]);
        }
        break;
      case "sepia":
        if (tokens.length == 3) {
          model.applySepiaTone(tokens[1], tokens[2], 100);
        } else if (tokens.length == 5 && tokens[3].equals("split")) {
          int splitPercentage = Integer.parseInt(tokens[4]);
          model.applySepiaTone(tokens[1], tokens[2], splitPercentage);
        } else {
          System.out.println("Error: Invalid arguments for sepia.");
        }
        break;
      case "compress":
        if (tokens.length < 4) {
          System.out.println("Error: 'compress' requires a " +
                  "compression percentage, source, and destination image names.");
        } else {
          model.compressImage(tokens[1], tokens[2], tokens[3]);
        }
        break;
      case "histogram":
        view.generateAndStoreHistogram(tokens[1], tokens[2]);
        break;
      case "color-correct":
        if (tokens.length == 3) {
          model.colorCorrect(tokens[1], tokens[2], 100);
        } else if (tokens.length == 5 && tokens[3].equals("split")) {
          int splitPercentage = Integer.parseInt(tokens[4]);
          model.colorCorrect(tokens[1], tokens[2], splitPercentage);
        } else {
          System.out.println("Error: Invalid arguments for color-correct.");
        }
        break;
      case "levels-adjust":
        if (tokens.length == 6) {
          int b = Integer.parseInt(tokens[1]);
          int m = Integer.parseInt(tokens[2]);
          int w = Integer.parseInt(tokens[3]);
          model.levelsAdjust(tokens[4], tokens[5], b, m, w, 100);
        } else if (tokens.length == 8 && tokens[6].equals("split")) {
          int b = Integer.parseInt(tokens[1]);
          int m = Integer.parseInt(tokens[2]);
          int w = Integer.parseInt(tokens[3]);
          int splitPercentage = Integer.parseInt(tokens[7]);
          model.levelsAdjust(tokens[4], tokens[5], b, m, w, splitPercentage);
        } else {
          System.out.println("Error: Invalid arguments for levels-adjust.");
        }
        break;

      default:
        System.out.println("Unknown command: " + tokens[0] + "!");
    }
  }
}
