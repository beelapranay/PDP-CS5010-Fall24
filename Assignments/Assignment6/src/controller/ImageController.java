package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.ImageModel;
import model.ImageModelInterface;
import model.RGB;
import view.ImageView;
import view.ImageViewInterface;

/**
 * ImageController handles loading, saving, and processing image commands.
 */
public class ImageController implements ImageControllerInterface {
  protected Map<String, RGB[][]> images = new HashMap<>();

  private final GetAndRegisterCommands getAndRegisterCommands;

  /**
   * Constructs an ImageController and initializes the command registry, model, and view.
   * Registers commands for image operations such as loading, saving, flipping, grayscale
   * transformations, color correction, histogram generation, compression, and more.
   */
  public ImageController() {
    this.getAndRegisterCommands = new CommandRegistry();
    ImageViewInterface view = new ImageView(this);
    ImageModelInterface model = new ImageModel(this, view);

    getAndRegisterCommands.register("load", new LoadCommand(this));
    getAndRegisterCommands.register("save", new SaveCommand(this));

    getAndRegisterCommands.register("red-component", new controller.GreyscaleCommand(model));
    getAndRegisterCommands.register("green-component", new controller.GreyscaleCommand(model));
    getAndRegisterCommands.register("blue-component", new controller.GreyscaleCommand(model));
    getAndRegisterCommands.register("value-component", new controller.GreyscaleCommand(model));
    getAndRegisterCommands.register("luma-component", new controller.GreyscaleCommand(model));
    getAndRegisterCommands.register("intensity-component",
            new GreyscaleCommand(model));

    getAndRegisterCommands.register("horizontal-flip", new controller.FlipCommand(model));
    getAndRegisterCommands.register("vertical-flip", new FlipCommand(model));

    getAndRegisterCommands.register("sepia", new SepiaCommand(model));

    getAndRegisterCommands.register("brighten", new BrightenCommand(model));

    getAndRegisterCommands.register("blur", new controller.BlurAndSharpenCommand(model));
    getAndRegisterCommands.register("sharpen", new BlurAndSharpenCommand(model));

    getAndRegisterCommands.register("compress", new CompressCommand(model));

    getAndRegisterCommands.register("rgb-split", new RGBSplitCommand(model));

    getAndRegisterCommands.register("rgb-combine", new RGBCombineCommand(model));

    getAndRegisterCommands.register("histogram", new HistogramCommand(view));

    getAndRegisterCommands.register("color-correct", new ColorCorrectCommand(model));

    getAndRegisterCommands.register("levels-adjust", new LevelsAdjustCommand(model));

    getAndRegisterCommands.register("downscale", new DownscaleCommand(model));
  }

  /**
   * Loads the image from the specified file path and associates it with a name.
   *
   * @param filePath the path of the image file
   * @param imageName the name to assign to the loaded image
   * @throws IOException if an error occurs during loading
   */
  @Override
  public void loadImage(String filePath, String imageName) throws IOException {
    controller.ImageLoaderAndSaver imageLoader = controller.ImageLoaderFactory
            .getImageLoader(filePath, this);
    RGB[][] pixelArray = imageLoader.loadImage(filePath, imageName);
    images.put(imageName, pixelArray);
  }

  /**
   * Saves the image to the specified file path.
   *
   * @param filePath the path to save the image
   * @param imageName the name of the image to save
   * @throws IOException if an error occurs during saving
   */
  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    ImageLoaderAndSaver imageLoader = ImageLoaderFactory
            .getImageLoader(filePath, this);
    imageLoader.saveImage(filePath, imageName);
  }

  /**
   * Retrieves all loaded images, mapped by their names.
   *
   * @return a HashMap of image names and corresponding RGB arrays
   */
  public Map<String, RGB[][]> getImages() {
    return this.images;
  }

  /**
   * Parses and processes a single command or runs a series of commands from a file.
   *
   * @param command the command to be parsed and executed
   * @throws IOException if an error occurs during processing
   */
  @Override
  public void commandParser(String command) throws IOException {
    String[] tokens = command.split("\\s+");

    if (tokens[0].equals("run")) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(tokens[1]));
        String line;

        while ((line = reader.readLine()) != null) {
          if (!line.trim().startsWith("#") && !line.trim().isEmpty()) {
            String[] fileTokens = line.split("\\s+");
            processCommands(fileTokens);
          }
        }

        reader.close();
      } catch (IOException e) {
        throw new IOException(e.getMessage());
      }
    } else {
      processCommands(tokens);
    }
  }

  /**
   * Processes a given array of command tokens.
   *
   * @param tokens an array of command strings
   */
  @Override
  public void processCommands(String[] tokens) {
    Command command = getAndRegisterCommands.get(tokens[0]);
    if (command != null) {
      try {
        command.execute(tokens);
      } catch (IOException e) {
        System.out.println("Error executing command: " + e.getMessage());
      }
    } else {
      System.out.println("Unknown command: " + tokens[0] + "!");
    }
  }
}
