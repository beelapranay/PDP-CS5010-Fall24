package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to split an image into its RGB components.
 */
public class RGBSplitCommand implements Command {
  private final String imageName;
  private final String redDestName;
  private final String greenDestName;
  private final String blueDestName;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a RGB split command with the given tokens, image processor, image map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   */
  public RGBSplitCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                         ImageModelInterface model) {
    if (tokens.length != 5) {
      throw new IllegalArgumentException("Usage: rgb-split image-name dest-image-name-red " +
              "dest-image-name-green dest-image-name-blue");
    }
    this.imageName = tokens[1];
    this.redDestName = tokens[2];
    this.greenDestName = tokens[3];
    this.blueDestName = tokens[4];
    this.imageProcessor = imageProcessor;
    this.model = model;
  }

  /**
   * Executes the RGB split command.
   *
   * @throws IllegalArgumentException if the image is not found in the image map
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    ImageInterface[] result = imageProcessor.split(image);
    model.addImage(redDestName, result[0]);
    model.addImage(greenDestName, result[1]);
    model.addImage(blueDestName, result[2]);
  }
}
