package controller;

import model.ImageModelInterface;
import model.image.ImageInterface;
import model.operationimpls.AdvancedImageProcessorImpl;
import model.operationinterface.AdvancedImageProcessor;
import view.GUIView;
import view.View;


/**
 * The controller for the GUI mode of the application.
 * It handles user interactions from the GUI and coordinates with the model and view.
 */
public class GUIController implements Features {
  private final ImageModelInterface model;
  private final CommandExecutor commandExecutor;
  private final AdvancedImageProcessor imageProcessor;
  private GUIView view;

  /**
   * Constructs a GUIController with the given model.
   *
   * @param model the image processing model
   */
  public GUIController(ImageModelInterface model) {
    this.model = model;
    this.commandExecutor = new CommandExecutor(model);
    this.imageProcessor = new AdvancedImageProcessorImpl();
  }

  /**
   * Sets the view for the controller.
   *
   * @param view the view to set
   */
  public void setView(View view) {
    if (view instanceof GUIView) {
      this.view = (GUIView) view;
    } else {
      throw new IllegalArgumentException("Invalid view type. Expected GUIView.");
    }
  }


  /**
   * Loads an image from the given file path and assigns it the given name.
   *
   * @param filePath  the file path of the image
   * @param imageName the name to assign to the loaded image
   */
  @Override
  public void loadImage(String filePath, String imageName) {
    try {
      commandExecutor.executeCommand("load " + filePath + " " + imageName);
    } catch (Exception e) {
      view.displayMessage("Error loading image: " + e.getMessage());
    }
  }

  /**
   * Saves the image with the given name to the specified file path.
   *
   * @param filePath  the file path to save the image to
   * @param imageName the name of the image to save
   */
  @Override
  public void saveImage(String filePath, String imageName) {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    commandExecutor.executeCommand("save " + filePath + " " + imageName);
  }

  /**
   * Gets the image with the given name.
   *
   * @param imageName the name of the image to retrieve
   * @return the image with the given name
   */
  @Override
  public ImageInterface getImage(String imageName) {
    return model.getImage(imageName);
  }

  /**
   * Generates a histogram image for the given image.
   *
   * @param imageName the name of the image to generate the histogram for
   * @return the histogram image
   */
  @Override
  public ImageInterface generateHistogramImage(String imageName) {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      view.displayMessage("Image not found: " + imageName);
      return null;
    }
    return imageProcessor.generateHistogram(image);
  }

  /**
   * Applies the red component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyRedComponent(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("red-component " + imageName + " " + destImageName);
      view.displayMessage("red component extracted");
    } catch (Exception e) {
      view.displayMessage("Error extracting red component: " + e.getMessage());
    }
  }

  /**
   * Applies the green component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyGreenComponent(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("green-component " + imageName + " " + destImageName);
      view.displayMessage("green component extracted");
    } catch (Exception e) {
      view.displayMessage("Error extracting green component: " + e.getMessage());
    }
  }

  /**
   * Applies the blue component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyBlueComponent(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("blue-component " + imageName + " " + destImageName);
      view.displayMessage("blue component extracted");
    } catch (Exception e) {
      view.displayMessage("Error extracting blue component: " + e.getMessage());
    }
  }

  /**
   * Applies the luma component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyLumaComponent(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("luma-component " + imageName + " " + destImageName);
      view.displayMessage("luma component extracted");
    } catch (Exception e) {
      view.displayMessage("Error extracting luma component: " + e.getMessage());
    }
  }

  /**
   * Applies the luma component operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyIntensityComponent(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("intensity-component " + imageName + " " + destImageName);
      view.displayMessage("intensity component extracted");
    } catch (Exception e) {
      view.displayMessage("Error extracting intensity component: " + e.getMessage());
    }
  }

  /**
   * Flips the image horizontally to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyHorizontalFlip(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("horizontal-flip " + imageName + " " + destImageName);
      view.displayMessage("Horizontal flip applied");
    } catch (Exception e) {
      view.displayMessage("Error flipping image: " + e.getMessage());
    }
  }

  /**
   * Flips the image vertically to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyVerticalFlip(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("vertical-flip " + imageName + " " + destImageName);
      view.displayMessage("Vertical flip applied");
    } catch (Exception e) {
      view.displayMessage("Error flipping image: " + e.getMessage());
    }
  }

  /**
   * Applies a sepia tone to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applySepia(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("sepia " + imageName + " " + destImageName);
      view.displayMessage("Sepia tone applied");
    } catch (Exception e) {
      view.displayMessage("Error applying sepia: " + e.getMessage());
    }
  }

  /**
   * Applies a sharpen filter to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applySharpen(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("sharpen " + imageName + " " + destImageName);
      view.displayMessage("Sharpen operation applied");
    } catch (Exception e) {
      view.displayMessage("Error sharpening image: " + e.getMessage());
    }
  }

  /**
   * Applies a blur filter to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyBlur(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("blur " + imageName + " " + destImageName);
      view.displayMessage("Blur operation applied");
    } catch (Exception e) {
      view.displayMessage("Error blurring image: " + e.getMessage());
    }

  }

  /**
   * Applies a brighten operation to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param increment     the increment value for brightening
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyBrighten(String imageName, int increment, String destImageName) {
    try {
      commandExecutor.executeCommand("brighten " + increment + " "
          + imageName + " " + destImageName);
      view.displayMessage("Image brightened");
    } catch (Exception e) {
      view.displayMessage("Error brightening image: " + e.getMessage());
    }

  }

  /**
   * Compresses the image with the given name using the specified compression percentage.
   *
   * @param imageName             the name of the image to compress
   * @param compressionPercentage the compression percentage to apply
   * @param destImageName         the name to assign to the resulting image
   */
  @Override
  public void applyCompress(String imageName, double compressionPercentage, String destImageName) {
    try {
      commandExecutor.executeCommand("compress " + compressionPercentage + " " + imageName +
          " " + destImageName);
      view.displayMessage("Image compressed");
    } catch (Exception e) {
      view.displayMessage("Error compressing image: " + e.getMessage());
    }

  }

  /**
   * Adjusts levels of the image with the given name using the specified parameters.
   *
   * @param imageName     the name of the image to adjust
   * @param blackPoint    the black point value
   * @param midPoint      the mid point value
   * @param whitePoint    the white point value
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyLevelsAdjust(String imageName, int blackPoint, int midPoint, int whitePoint,
                                String destImageName) {
    try {
      commandExecutor.executeCommand("levels-adjust " + blackPoint + " " + midPoint
          + " " + whitePoint + " " + imageName + " " + destImageName);
      view.displayMessage("Levels adjusted");
    } catch (Exception e) {
      view.displayMessage("Error adjusting levels: " + e.getMessage());
    }
  }

  /**
   * Applies color correction to the image with the given name.
   *
   * @param imageName     the name of the image to process
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyColorCorrect(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("color-correct " + imageName + " " + destImageName);
      view.displayMessage("Color correction applied");
    } catch (Exception e) {
      view.displayMessage("Error color correcting image: " + e.getMessage());
    }
  }


  /**
   * Downscales the image with the given name to the specified width and height.
   *
   * @param imageName     the name of the image to downscale
   * @param targetWidth   the target width
   * @param targetHeight  the target height
   * @param destImageName the name to assign to the resulting image
   */
  @Override
  public void applyDownscale(String imageName, int targetWidth, int targetHeight,
                             String destImageName) {
    try {
      commandExecutor.executeCommand("downscale " + targetWidth + " " + targetHeight
              + " " + imageName + " " + destImageName);
    } catch (Exception e) {
      view.displayMessage("Error downscaling image: " + e.getMessage());
    }
  }


  /**
   * Displays details of the image with the given name.
   *
   * @param imageName the name of the image
   */
  @Override
  public void showImageDetails(String imageName) {
    ImageInterface image = model.getImage(imageName);
    if (image == null) {
      view.displayMessage("Image not found: " + imageName);
      return;
    }
    String details =
        "Width: " + image.getWidth() + "\n"
            + "Height: " + image.getHeight() + "\n"
            + "Max Value: " + image.getMaxValue();
    view.displayMessage(details);
  }

  /**
   * Combines two images into a split view based on the split percentage and operation parameters.
   *
   * @param originalImage   The original image.
   * @param splitPercentage The percentage to split the images.
   * @param operationName   The name of the operation.
   * @param params          Additional parameters for the operation.
   * @return The combined split view image.
   */
  @Override
  public ImageInterface getSplitViewImage(ImageInterface originalImage,
                                          int splitPercentage, String operationName,
                                          Object... params) {
    try {
      return imageProcessor.splitView(originalImage, operationName, splitPercentage, params);
    } catch (Exception e) {
      view.displayMessage("Error creating split view image: " + e.getMessage());
      return null;
    }
  }

  /**
   * Removes an image from the model by name.
   *
   * @param imageName The name of the image to remove.
   */
  @Override
  public void removeImage(String imageName) {
    model.removeImage(imageName);
  }

  @Override
  public void applyDithering(String imageName, String destImageName) {
    try {
      commandExecutor.executeCommand("dither " + imageName + " " + destImageName);
      view.displayMessage("Dither applied");
    } catch (Exception e) {
      view.displayMessage("Error applying dithering: " + e.getMessage());
    }
  }

}