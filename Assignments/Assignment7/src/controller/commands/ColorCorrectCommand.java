package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to perform color correction, with optional split view.
 */
public class ColorCorrectCommand implements Command {
  private final String imageName;
  private final String destImageName;
  private final boolean isSplit;
  private int splitPercentage;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a color correction command with the given tokens, processor, image map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   */
  public ColorCorrectCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                             ImageModelInterface model) {
    if (tokens.length != 3 && tokens.length != 5) {
      throw new IllegalArgumentException("Usage: color-correct image-name dest-image-name " +
          "[split p]");
    }
    this.imageName = tokens[1];
    this.destImageName = tokens[2];
    this.imageProcessor = imageProcessor;
    this.model = model;

    this.isSplit = (tokens.length == 5);
    if (this.isSplit) {
      parseSplit(tokens[3], tokens[4]);
    }
  }

  /**
   * Parses the split view option from the given tokens.
   * The split view option is expected to be in the form "split p" where p is the percentage.
   *
   * @param splitKeyword the keyword for the split view option
   * @param percentage   the percentage for the split view
   */
  private void parseSplit(String splitKeyword, String percentage) {
    if (!splitKeyword.equalsIgnoreCase("split")) {
      throw new IllegalArgumentException("Invalid syntax. Expected 'split' keyword.");
    }
    try {
      this.splitPercentage = Integer.parseInt(percentage);
      if (splitPercentage < 0 || splitPercentage > 100) {
        throw new IllegalArgumentException("Split percentage must be between 0 and 100.");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Split percentage must be an integer.");
    }
  }

  /**
   * Executes the color correction command.
   * Applies color correction to the image and adds the result to the model.
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }

    ImageInterface result;
    if (isSplit) {
      result = imageProcessor.splitView(image, "color-correct", splitPercentage);
    } else {
      result = imageProcessor.colorCorrect(image);
    }

    model.addImage(destImageName, result);
  }
}
