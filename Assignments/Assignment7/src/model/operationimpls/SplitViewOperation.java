package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.AdvancedImageProcessor;
import model.operationinterface.ImageOperation;
import model.operationinterface.ImageProcessor;

/**
 * Operation class for splitting an image into two views and applying an operation to each view.
 * The split position is specified as a percentage of the image width.
 * The operation is applied to the left view, and the original image is displayed on the right.
 */
public class SplitViewOperation implements ImageOperation {
  private final String operation;
  private final int splitPosition;
  private final Object[] params;
  private final ImageProcessor processor;

  /**
   * Constructs a new SplitViewOperation with the specified operation and split position.
   *
   * @param operationName The name of the operation to apply to the left view.
   * @param splitPosition The position to split the image (percentage of width).
   * @param processor     The ImageProcessor instance to use for applying the operation.
   * @param params        Additional parameters for the operation (optional).
   * @throws IllegalArgumentException if the operation name or processor is null,
   *                                  or if the split position is invalid.
   */
  public SplitViewOperation(String operationName, int splitPosition,
                            ImageProcessor processor, Object... params) {

    if (operationName == null || processor == null) {
      throw new IllegalArgumentException("Operation name and processor must not be null.");
    }
    this.operation = operationName;
    this.splitPosition = splitPosition;
    this.processor = processor;
    this.params = params;
  }

  /**
   * Splits the input image into two views and applies the specified operation to the left view.
   * The right view displays the original image.
   *
   * @param images Array containing a single image to split and process
   * @return A new ImageInterface instance representing the split view image
   * @throws IllegalArgumentException if:
   *                                  - images array is null or does not contain exactly one image
   *                                  - input image, operation, or processor is null
   *                                  - split position is invalid
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("SplitViewOperation requires exactly one image.");
    }
    ImageInterface image = images[0];

    if (image == null || operation == null || processor == null) {
      throw new IllegalArgumentException("Image, operation, and processor must not be null.");
    }

    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();

    if (splitPosition < 0 || splitPosition > 100) {
      throw new IllegalArgumentException("Split position must be between 0 and 100.");
    }

    int splitPixel = (int) ((splitPosition / 100.0) * width);

    ImageInterface processedImage = applyOperation(image, operation, processor, params);

    int[][][] newPixels = new int[height][width][3];

    int[][][] originalPixels = image.getPixels();
    int[][][] processedPixels = processedImage.getPixels();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (x < splitPixel) {
          newPixels[y][x][0] = processedPixels[y][x][0];
          newPixels[y][x][1] = processedPixels[y][x][1];
          newPixels[y][x][2] = processedPixels[y][x][2];
        } else {
          newPixels[y][x][0] = originalPixels[y][x][0];
          newPixels[y][x][1] = originalPixels[y][x][1];
          newPixels[y][x][2] = originalPixels[y][x][2];
        }
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Applies the specified operation using the provided ImageProcessor.
   *
   * @param image     The original image.
   * @param operation The name of the operation to apply.
   * @param processor The ImageProcessor instance.
   * @return The processed image after applying the operation.
   */
  private static ImageInterface applyOperation(ImageInterface image, String operation,
                                               ImageProcessor processor, Object... params) {

    AdvancedImageProcessor advancedProcessor = (AdvancedImageProcessor) processor;

    switch (operation.toLowerCase()) {
      case "blur":
        return processor.blur(image);
      case "sharpen":
        return processor.sharpen(image);
      case "sepia":
        return processor.sepia(image);
      case "brighten":
        int increment = Integer.parseInt(params[0].toString());
        return processor.brighten(image, increment);
      case "red-component":
      case "green-component":
      case "blue-component":
      case "luma-component":
      case "intensity-component":
      case "value-component":
        String componentName = operation.split("-")[0];
        return processor.extractComponent(image, componentName);

      case "dither":
        return advancedProcessor.applyDithering(image);

      case "color-correct":
        return advancedProcessor.colorCorrect(image);
      case "levels-adjust":
        if (params == null || params.length != 3) {
          throw new IllegalArgumentException("Levels-adjust requires blackPoint, midPoint, " +
                  "and whitePoint parameters.");
        }
        AdvancedImageProcessor advancedProcessor2 = (AdvancedImageProcessor) processor;
        int blackPoint = (int) params[0];
        int midPoint = (int) params[1];
        int whitePoint = (int) params[2];
        return advancedProcessor2.adjustLevels(image, blackPoint, midPoint, whitePoint);
      default:
        throw new IllegalArgumentException("Unsupported operation for split view: " + operation);
    }
  }

}
