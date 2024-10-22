import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageController implements ImageControllerInterface {
  protected HashMap<String, RGB[][]> images = new HashMap<>();

  @Override
  public void loadImage(String filePath, String imageName) throws IOException {
    try {
      BufferedImage image = ImageIO.read(new File(filePath));

      int width = image.getWidth();
      int height = image.getHeight();

      RGB[][] pixelArray = new RGB[height][width];

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = image.getRGB(x, y);

          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;

          pixelArray[y][x] = new RGB(red, green, blue);
        }
      }

      images.put(imageName, pixelArray);

      System.out.println("Image " + imageName + " loaded. Current images in HashMap: " + images.keySet());
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }

  @Override
  public BufferedImage arrayToImage(RGB[][] image) {
    int width = image[0].length;
    int height = image.length;

    BufferedImage processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        RGB pixel = image[y][x];

        int rgb = (pixel.red << 16) | (pixel.green << 8) | pixel.blue;

        processedImage.setRGB(x, y, rgb);
      }
    }

    return processedImage;
  }

  private String getFileFormat(String filePath) {
    String[] parts = filePath.split("\\.");

    if (parts.length > 1) {
      return parts[parts.length - 1];
    } else {
      return "";
    }
  }

  @Override
  public void saveImage(String filePath, String imageName) throws IOException {
    try {
      File outputfile = new File(filePath);

      ImageIO.write(arrayToImage(images.get(imageName)),
              getFileFormat(filePath),
              outputfile);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }

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

  @Override
  public void processCommands(String[] tokens) throws IOException {
    ImageModelInterface model = new ImageModel(this);

    switch (tokens[0]) {
      case "load":
        loadImage(tokens[1], tokens[2]);
        break;

      case "save":
        saveImage(tokens[1], tokens[2]);
        break;

      case "red-component":
        model.splitRedChannel(tokens[1], tokens[2]);
        break;

      case "green-component":
        model.splitGreenChannel(tokens[1], tokens[2]);
        break;

      case "blue-component":
        model.splitBlueChannel(tokens[1], tokens[2]);
        break;

      case "value-component":
        model.calculateValue(tokens[1], tokens[2]);
        break;

      case "luma-component":
        model.calculateLuma(tokens[1], tokens[2]);
        break;

      case "intensity-component":
        model.calculateIntensity(tokens[1], tokens[2]);
        break;

      case "horizontal-flip":
        model.horizontalFlipImage(tokens[1], tokens[2]);
        break;

      case "vertical-flip":
        model.verticalFlipImage(tokens[1], tokens[2]);
        break;

      case "brighten":
        int value = Integer.parseInt(tokens[1]);
        model.changeBrightness(tokens[2], tokens[3], value);
        break;

      case "rgb-split":
        model.splitRedChannel(tokens[1], tokens[2]);
        model.splitGreenChannel(tokens[1], tokens[3]);
        model.splitBlueChannel(tokens[1], tokens[4]);
        break;

      case "rgb-combine":
        model.combineGreyscale(tokens[1],
                tokens[2],
                tokens[3],
                tokens[4]);
        break;

      case "blur":
        model.blurImage(tokens[1], tokens[2]);
        break;

      case "sharpen":
        model.sharpenImage(tokens[1], tokens[2]);
        break;

      case "sepia":
        model.applySepiaTone(tokens[1], tokens[2]);
        break;

      default:
        System.out.println("Unknown command: " + tokens[0] + "!");
    }
  }

  public static void main(String[] args) {
    ImageController controller = new ImageController();

    if (args.length > 0) {
      StringBuilder command = new StringBuilder();
      for (String arg : args) {
        command.append(arg).append(" ");
      }

      try {
        controller.commandParser(command.toString().trim());
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    } else {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Enter a command or 'run' followed by a file path:");

      try {
        String command = reader.readLine();
        controller.commandParser(command);
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
