package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to perform flip operation.
 */
public class FlipCommand implements Command {
  private final boolean isHorizontal;
  private final String imageName;
  private final String destImageName;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a flip command with the given tokens, flip direction, processor, map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param isHorizontal   true if the flip is horizontal, false if vertical
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   * @throws IllegalArgumentException if the number of tokens is incorrect
   */
  public FlipCommand(String[] tokens, boolean isHorizontal, AdvancedImageProcessor imageProcessor,
                     ImageModelInterface model) {
    if (tokens.length != 3) {
      throw new IllegalArgumentException("Usage: " +
              "[horizontal-flip|vertical-flip] image-name dest-image-name");
    }
    this.isHorizontal = isHorizontal;
    this.imageName = tokens[1];
    this.destImageName = tokens[2];
    this.imageProcessor = imageProcessor;
    this.model = model;
  }

  /**
   * Executes the flip command.
   *
   * @throws IllegalArgumentException if the image is not found in the image map
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    ImageInterface result = isHorizontal
            ? imageProcessor.flipHorizontal(image)
            : imageProcessor.flipVertical(image);
    model.addImage(destImageName, result);
  }
}
