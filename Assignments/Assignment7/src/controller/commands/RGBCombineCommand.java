package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to combine RGB component images into a single image.
 */
public class RGBCombineCommand implements Command {
  private final String destImageName;
  private final String redImageName;
  private final String greenImageName;
  private final String blueImageName;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a RGB combine command with the given tokens, image processor, image map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   */
  public RGBCombineCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                           ImageModelInterface model) {
    if (tokens.length != 5) {
      throw new IllegalArgumentException("Usage: rgb-combine dest-image-name " +
              "red-image-name green-image-name blue-image-name");
    }
    this.destImageName = tokens[1];
    this.redImageName = tokens[2];
    this.greenImageName = tokens[3];
    this.blueImageName = tokens[4];
    this.imageProcessor = imageProcessor;
    this.model = model;
  }

  /**
   * Executes the RGB combine command.
   *
   * @throws IllegalArgumentException if one or more component images are not found
   */
  @Override
  public void execute() {
    ImageInterface redImage = model.getImage(redImageName);
    ImageInterface greenImage = model.getImage(greenImageName);
    ImageInterface blueImage = model.getImage(blueImageName);

    if (redImage == null || greenImage == null || blueImage == null) {
      throw new IllegalArgumentException("One or more component images not found.");
    }

    ImageInterface result = imageProcessor.combine(redImage, greenImage, blueImage);
    model.addImage(destImageName, result);
  }
}
