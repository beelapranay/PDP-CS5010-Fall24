package controller;

import java.io.IOException;
import model.ImageModelInterface;

/**
 * Command class to execute the downscaling of an image to a specified width and height.
 */
public class DownscaleCommand implements Command {
  private final ImageModelInterface model;

  /**
   * Constructs a DownscaleCommand with the specified model.
   *
   * @param model the image model interface used to perform the downscaling
   */
  public DownscaleCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the downscaling operation with the provided parameters.
   *
   * @param tokens an array containing the command name, target width, target height,
   *               source image name, and destination image name
   * @throws IOException if an I/O error occurs during command execution
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 5) {
      System.out.println("Error: 'downscale' requires target width, target height, "
              + "source image name, and destination image name.");
      return;
    }
    try {
      int targetWidth = Integer.parseInt(tokens[1]);
      int targetHeight = Integer.parseInt(tokens[2]);
      String sourceImageName = tokens[3];
      String destinationImageName = tokens[4];
      model.downscaleImage(sourceImageName, destinationImageName, targetWidth, targetHeight);
    } catch (NumberFormatException e) {
      System.out.println("Error: Width and height must be valid integers.");
    }
  }
}