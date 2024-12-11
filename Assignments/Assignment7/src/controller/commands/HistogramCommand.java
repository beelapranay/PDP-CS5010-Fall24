package controller.commands;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;

/**
 * Command to generate histogram image.
 */
public class HistogramCommand implements Command {
  private final String imageName;
  private final String destImageName;
  private final AdvancedImageProcessor imageProcessor;
  private final ImageModelInterface model;

  /**
   * Constructs a histogram command with the given tokens, image processor, image map, and view.
   *
   * @param tokens         the tokens that form the command
   * @param imageProcessor the image processor to apply the command
   * @param model          the map of images to apply the command
   * @throws IllegalArgumentException if the number of tokens is incorrect
   */
  public HistogramCommand(String[] tokens, AdvancedImageProcessor imageProcessor,
                          ImageModelInterface model) {
    if (tokens.length != 3) {
      throw new IllegalArgumentException("Usage: histogram image-name dest-image-name");
    }
    this.imageName = tokens[1];
    this.destImageName = tokens[2];
    this.imageProcessor = imageProcessor;
    this.model = model;
  }

  /**
   * Executes the histogram command.
   *
   * @throws IllegalArgumentException if the image is not found in the image map
   */
  @Override
  public void execute() {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    ImageInterface histogramImage = imageProcessor.generateHistogram(image);
    model.addImage(destImageName, histogramImage);
  }
}
