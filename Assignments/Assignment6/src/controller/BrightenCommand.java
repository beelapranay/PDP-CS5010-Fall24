package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Represents a command to adjust the brightness of an image.
 */
public class BrightenCommand implements Command {

  private final ImageModelInterface model;

  /**
   * Constructs a BrightenCommand with the specified image model.
   *
   * @param model the image model to execute the brightness adjustment
   */
  public BrightenCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the brightness adjustment operation based on the given tokens.
   * The tokens should specify the brightness value, source image name,
   * and destination image name.
   *
   * @param tokens an array of strings representing the command arguments
   * @throws IOException if an I/O error occurs during the execution
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 4) {
      System.out.println("Error: 'brighten' requires a brightness value, source, "
              + "and destination image names.");
    } else {
      try {
        int brightnessValue = Integer.parseInt(tokens[1]);

        String sourceImageName = tokens[2];
        String destinationImageName = tokens[3];

        model.changeBrightness(sourceImageName, destinationImageName, brightnessValue);
      } catch (NumberFormatException e) {
        System.out.println("Error: Brightness value must be a valid integer.");
      }
    }
  }
}
