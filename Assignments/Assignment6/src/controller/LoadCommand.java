package controller;

import java.io.IOException;

/**
 * Command to load an image into the application.
 */
public class LoadCommand implements Command {
  private final controller.ImageController controller;

  /**
   * Constructs a LoadCommand with the specified controller.
   *
   * @param controller the image controller to manage the image loading
   */
  public LoadCommand(ImageController controller) {
    this.controller = controller;
  }

  /**
   * Executes the load command.
   *
   * @param tokens the input tokens containing the command, file path, and image name
   * @throws IOException if an error occurs during image loading
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 3) {
      System.out.println("Error: 'load' requires a file path and image name.");
    } else {
      controller.loadImage(tokens[1], tokens[2]);
    }
  }
}
