package controller.commands;


import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.ImageProcessor;

/**
 * Command to perform brighten operation.
 */
public class BrightenCommand implements Command {
  private int increment;
  private String imageName;
  private String destImageName;
  private ImageProcessor imageProcessor;
  private ImageModelInterface model;

  /**
   * Constructs a brighten command with the given parameters.
   *
   * @param tokens         the tokens representing the command
   * @param imageProcessor the image processor to be used
   * @param model          the image model to be used
   */
  public BrightenCommand(String[] tokens, ImageProcessor imageProcessor,
                         ImageModelInterface model) {
    
    if (tokens.length != 4) {
      throw new IllegalArgumentException("Usage: brighten increment image-name dest-image-name");
    }
    try {
      this.increment = Integer.parseInt(tokens[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid increment value: " + tokens[1]);
    }
    this.imageName = tokens[2];
    this.destImageName = tokens[3];
    this.imageProcessor = imageProcessor;
    this.model = model;
  }

  /**
   * Executes the brighten command.
   * Brightens the image with the given increment and adds the result to the model.
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    ImageInterface result = imageProcessor.brighten(image, increment);
    model.addImage(destImageName, result);
  }
}
