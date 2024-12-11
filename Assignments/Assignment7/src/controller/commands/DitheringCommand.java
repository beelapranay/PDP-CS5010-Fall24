package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to perform dithering on an image, with optional split preview.
 */
public class DitheringCommand implements Command {
  private final String imageName;
  private final String destImageName;
  private boolean isSplit;
  private int splitPercentage;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a dithering command with the given tokens, processor, and image model.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   */
  public DitheringCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                          ImageModelInterface model) {
    if (tokens.length < 3 || tokens.length > 5) {
      throw new IllegalArgumentException("Usage: dither image-name dest-image-name split p");
    }

    this.imageName = tokens[1];
    this.destImageName = tokens[2];
    this.imageProcessor = imageProcessor;
    this.model = model;
    this.isSplit = false;

    if (tokens.length == 5) {
      parseSplit(tokens[3], tokens[4]);
    }
  }

  /**
   * Parses the split preview option from the given tokens.
   *
   * @param splitKeyword the keyword for the split preview option
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
      this.isSplit = true;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Split percentage must be an integer.");
    }
  }

  /**
   * Executes the dithering command.
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
      result = imageProcessor.splitView(image, "dither", splitPercentage);
    } else {
      result = imageProcessor.applyDithering(image);
    }

    model.addImage(destImageName, result);
  }
}