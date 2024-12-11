package controller;

import java.io.IOException;

import model.ImageModelInterface;

/**
 * Command for compressing an image using a specified compression percentage.
 */
public class CompressCommand implements Command {

  private final ImageModelInterface model;

  /**
   * Constructs a CompressCommand with the given model.
   *
   * @param model the image model to apply the command on
   */
  public CompressCommand(ImageModelInterface model) {
    this.model = model;
  }

  /**
   * Executes the compress command by applying a compression percentage to the specified image.
   *
   * @param tokens an array of arguments for the compress command, including:
   *               - compression percentage (integer between 0 and 100)
   *               - source image name
   *               - destination image name
   * @throws IOException if an error occurs during execution
   */
  @Override
  public void execute(String[] tokens) throws IOException {
    if (tokens.length < 4) {
      System.out.println("Error: 'compress' requires a compression percentage, source, "
              + "and destination image names.");
    } else {
      try {
        int compressionPercentage = Integer.parseInt(tokens[1]);

        if (compressionPercentage < 0 || compressionPercentage > 100) {
          throw new IllegalArgumentException("Compression percentage must be between 0 and 100.");
        }

        model.compressImage(tokens[1], tokens[2], tokens[3]);
      } catch (NumberFormatException e) {
        System.out.println("Error: Compression percentage must be a valid integer.");
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}
