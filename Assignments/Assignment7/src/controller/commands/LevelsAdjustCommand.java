package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to perform levels adjustment, with optional split view.
 */
public class LevelsAdjustCommand implements Command {
  private final int blackPoint;
  private final int midPoint;
  private final int whitePoint;
  private final String imageName;
  private final String destImageName;
  private final boolean isSplit;
  private int splitPercentage;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a levels adjustment command with the given tokens, processor, image map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   */
  public LevelsAdjustCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                             ImageModelInterface model) {
    if (tokens.length != 6 && tokens.length != 8) {
      throw new IllegalArgumentException("Usage: " +
              "levels-adjust b m w image-name dest-image-name [split p]");
    }
    try {
      this.blackPoint = Integer.parseInt(tokens[1]);
      this.midPoint = Integer.parseInt(tokens[2]);
      this.whitePoint = Integer.parseInt(tokens[3]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid level values.");
    }
    if (blackPoint < 0 || blackPoint >= midPoint || midPoint >= whitePoint || whitePoint > 255) {
      throw new IllegalArgumentException("Invalid black, mid, and white points.");
    }
    this.imageName = tokens[4];
    this.destImageName = tokens[5];
    this.imageProcessor = imageProcessor;
    this.model = model;

    this.isSplit = (tokens.length == 8);
    if (this.isSplit) {
      parseSplit(tokens[6], tokens[7]);
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
   * Executes the levels adjustment command.
   *
   * @throws IllegalArgumentException if the image is not found in the image map
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }

    ImageInterface result;
    if (isSplit) {
      result = imageProcessor.splitView(image, "levels-adjust", splitPercentage,
              blackPoint, midPoint, whitePoint);
    } else {
      result = imageProcessor.adjustLevels(image, blackPoint, midPoint, whitePoint);
    }

    model.addImage(destImageName, result);
  }
}
