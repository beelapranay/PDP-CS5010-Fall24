package controller;

import java.io.IOException;
import java.util.HashMap;
import model.RGB;

/**
 * Interface for an image controller that handles loading, saving, and processing images.
 */
public interface ImageControllerInterface {

  /**
   * Loads an image from the specified file path and assigns it a name.
   *
   * @param filePath the path of the image file
   * @param imageName the name to associate with the loaded image
   * @throws IOException if an error occurs during loading
   */
  void loadImage(String filePath, String imageName) throws IOException;

  /**
   * Saves the specified image to the provided file path.
   *
   * @param filePath the path to save the image to
   * @param imageName the name of the image to save
   * @throws IOException if an error occurs during saving
   */
  void saveImage(String filePath, String imageName) throws IOException;

  /**
   * Retrieves all loaded images, mapped by their assigned names.
   *
   * @return a HashMap containing image names and their corresponding RGB array representations
   */
  HashMap<String, RGB[][]> getImages();

  /**
   * Parses and processes a single command string.
   *
   * @param command the command to be parsed and processed
   * @throws IOException if an error occurs while processing the command
   */
  void commandParser(String command) throws IOException;

  /**
   * Processes an array of command tokens.
   *
   * @param tokens an array of command strings
   * @throws IOException if an error occurs while processing the commands
   */
  void processCommands(String[] tokens) throws IOException;
}
