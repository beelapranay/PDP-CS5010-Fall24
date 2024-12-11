package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to perform compress operation.
 */
public class CompressCommand implements Command {
  private final double percentage;
  private final String imageName;
  private final String destImageName;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a compress command with the given tokens, image processor, image map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   * @throws IllegalArgumentException if number of tokens is incorrect or the percentage is invalid
   */
  public CompressCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                         ImageModelInterface model) {
    if (tokens.length != 4) {
      throw new IllegalArgumentException("Usage: compress percentage image-name dest-image-name");
    }
    try {
      this.percentage = Double.parseDouble(tokens[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid percentage value: " + tokens[1]);
    }
    if (percentage <= 0 || percentage > 100) {
      throw new IllegalArgumentException("Percentage must be between 0 and 100.");
    }
    this.imageName = tokens[2];
    this.destImageName = tokens[3];
    this.imageProcessor = imageProcessor;
    this.model = model;
  }

  /**
   * Executes the compress command.
   *
   * @throws IllegalArgumentException if the image is not found in the image map
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    ImageInterface result = imageProcessor.compress(image, percentage);
    model.addImage(destImageName, result);
  }
}
