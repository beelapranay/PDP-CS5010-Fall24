package controller.commands;

import java.io.IOException;
import controller.ImageIOUtil;
import model.ImageModelInterface;
import model.image.ImageInterface;

/**
 * Command to save an image to a file path.
 */
public class SaveCommand implements Command {
  private final String imagePath;
  private final String imageName;
  private final ImageModelInterface model;

  /**
   * Constructs a save command with the given tokens and model.
   *
   * @param tokens the tokens that form the command
   * @param model  the image model to apply the command
   */
  public SaveCommand(String[] tokens, ImageModelInterface model) {
    if (tokens.length != 3) {
      throw new IllegalArgumentException("Usage: save image-path image-name");
    }
    this.imagePath = tokens[1];
    this.imageName = tokens[2];
    this.model = model;
  }

  /**
   * Executes the save command.
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    try {
      ImageIOUtil.writeImage(image, imagePath);
    } catch (IOException e) {
      throw new IllegalStateException("Error saving image to " + imagePath + ": "
          + e.getMessage(), e);
    }
  }
}