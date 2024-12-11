package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to downscale an image to specified dimensions.
 */
public class DownscaleCommand implements Command {
  private final String[] tokens;
  private final AdvancedImageProcessor processor;
  private final ImageModelInterface model;

  /**
   * Constructs a downscale command with the given parameters.
   *
   * @param tokens    the tokens that form the command
   * @param processor the image processor to apply the command
   * @param model     the image model to apply the command
   */
  public DownscaleCommand(String[] tokens, AdvancedImageProcessor processor,
                          ImageModelInterface model) {
    this.tokens = tokens;
    this.processor = processor;
    this.model = model;
  }

  /**
   * Executes the downscale command to downscale an image to the specified dimensions.
   */
  @Override
  public void execute() {
    if (tokens.length != 5) {
      throw new IllegalArgumentException("Usage: " +
              "downscale targetWidth targetHeight sourceImage destImage");
    }
    try {
      int targetWidth = Integer.parseInt(tokens[1]);
      int targetHeight = Integer.parseInt(tokens[2]);
      String sourceImageName = tokens[3];
      String destImageName = tokens[4];

      ImageInterface sourceImage = model.getImage(sourceImageName);
      if (sourceImage == null) {
        throw new IllegalArgumentException("Source image not found: " + sourceImageName);
      }

      ImageInterface downscaledImage = processor.downscale(sourceImage, targetWidth, targetHeight);
      model.addImage(destImageName, downscaledImage);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid dimensions. " +
              "Please enter positive integers for width and height.");
    }
  }
}