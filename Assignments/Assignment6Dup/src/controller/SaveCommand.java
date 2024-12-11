package controller;

import java.io.IOException;

/**
 * Command to save an image to a specified file path with a given name.
 */
public class SaveCommand implements Command {
  private final controller.ImageController controller;

  /**
   * Constructs a SaveCommand with the specified image controller.
   *
   * @param controller the image controller to handle the save operation
   */
  public SaveCommand(ImageController controller) {
    this.controller = controller;
  }

  /**
   * Executes the save command.
   *
   * @param tokens the input tokens containing the command, file path, and image name
   * @throws IOException if an error occurs during the save operation
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 3) {
      System.out.println("Error: 'save' requires a file path and image name.");
    } else {
      controller.saveImage(tokens[1], tokens[2]);
    }
  }
}
