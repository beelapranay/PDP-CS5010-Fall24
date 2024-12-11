package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to perform blur operation, with optional split view or masking.
 */
public class BlurCommand implements Command {
  private final String imageName;
  private String destImageName;
  private String maskImageName;
  private boolean isSplit;
  private int splitPercentage;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a blur command with the given parameters.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the image model to apply the command
   */
  public BlurCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                     ImageModelInterface model) {

    if (tokens.length < 3 || tokens.length > 5) {
      throw new IllegalArgumentException("Usage: blur sourceImage [maskImage] destImage [split p]");
    }

    this.imageProcessor = imageProcessor;
    this.model = model;

    this.imageName = tokens[1];
    this.maskImageName = null;
    this.destImageName = null;
    this.isSplit = false;

    parseArguments(tokens);
  }

  /**
   * Parses the arguments to determine if a mask image or split view is specified.
   *
   * @param tokens the command tokens
   */
  private void parseArguments(String[] tokens) {
    if (tokens.length == 3) {
      this.destImageName = tokens[2];
      return;
    }

    if (tokens.length == 4) {
      if (tokens[2].equalsIgnoreCase("split")) {
        throw new IllegalArgumentException("Invalid syntax. " +
                "Expected 'split' keyword at position 3.");
      } else {
        this.maskImageName = tokens[2];
        this.destImageName = tokens[3];
      }
      return;
    }

    if (tokens.length == 5) {
      if (tokens[3].equalsIgnoreCase("split")) {
        this.destImageName = tokens[2];
        this.isSplit = true;
        parseSplit(tokens[3], tokens[4]);
      } else {
        throw new IllegalArgumentException("Invalid syntax or " +
                "cannot use both mask image and split view.");
      }
      return;
    }

    throw new IllegalArgumentException("Invalid command format.");
  }

  /**
   * Parses the split view option from the given tokens.
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
   * Executes the blur command on the given image.
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }

    ImageInterface result;

    if (maskImageName != null) {
      // Masking operation
      ImageInterface maskImage = model.getImage(maskImageName);
      if (maskImage == null) {
        throw new IllegalArgumentException("Mask image not found: " + maskImageName);
      }
      result = imageProcessor.applyMask(image, maskImage, "blur");
    } else if (isSplit) {
      result = imageProcessor.splitView(image, "blur", splitPercentage);
    } else {
      result = imageProcessor.blur(image);
    }

    model.addImage(destImageName, result);
  }
}
