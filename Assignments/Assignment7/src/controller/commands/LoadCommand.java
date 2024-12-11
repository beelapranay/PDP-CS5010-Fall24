package controller.commands;

import java.io.IOException;

import controller.ImageIOUtil;
import model.ImageModelInterface;
import model.image.ImageInterface;

/**
 * Command to load an image from a file path.
 */
public class LoadCommand implements Command {
  private final String imagePath;
  private final String imageName;
  private final ImageModelInterface model;

  /**
   * Constructs a load command with the given tokens and model.
   *
   * @param tokens the tokens that form the command
   * @param model  the image model to apply the command
   */
  public LoadCommand(String[] tokens, ImageModelInterface model) {
    if (tokens.length != 3) {
      throw new IllegalArgumentException("Usage: load image-path image-name");
    }
    this.imagePath = tokens[1];
    this.imageName = tokens[2];
    this.model = model;
  }

  /**
   * Executes the load command.
   */
  @Override
  public void execute() {
    try {
      ImageInterface image = ImageIOUtil.readImage(imagePath);
      model.addImage(imageName, image);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load image from "
              + imagePath + ": " + e.getMessage(), e);
    }
  }
}