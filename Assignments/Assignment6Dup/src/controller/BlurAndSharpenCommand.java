package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Represents a command to apply blur or sharpen operations on an image.
 */
public class BlurAndSharpenCommand implements Command {

  private final ImageModelInterface model;

  /**
   * Constructs a BlurAndSharpenCommand with the specified image model.
   *
   * @param model the image model to execute the blur or sharpen operation
   */
  public BlurAndSharpenCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the blur or sharpen operation based on the given tokens.
   * The tokens should specify the operation type, source image name,
   * destination image name, and optionally a split percentage for partial manipulation.
   *
   * @param tokens an array of strings representing the command arguments
   * @throws IOException if an I/O error occurs during the execution
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length == 3) {
      model.blurOrSharpen(tokens[1], tokens[2], 100, tokens[0]);
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      try {
        int splitPercentage = Integer.parseInt(tokens[4]);

        if (splitPercentage < 0 || splitPercentage > 100) {
          throw new IllegalArgumentException("Split percentage must be between 0 and 100.");
        }

        model.blurOrSharpen(tokens[1], tokens[2], splitPercentage, tokens[0]);
      } catch (NumberFormatException e) {
        System.out.println("Error: Split percentage must be a valid integer.");
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      }
    } else {
      System.out.println("Error: Invalid arguments for " + tokens[0]);
    }
  }
}
