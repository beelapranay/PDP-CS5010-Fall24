package controller;

/**
 * Factory class for creating ImageLoaderAndSaver instances based on the file extension.
 */
public class ImageLoaderFactory {

  /**
   * Creates an appropriate ImageLoaderAndSaver instance based on the file
   * extension of the given file path.
   *
   * @param filePath        the path of the image file
   * @param imageController the image controller to be used by the loader and saver
   * @return an instance of ImageLoaderAndSaver that can handle the specified file type
   * @throws UnsupportedOperationException if the file type is unsupported
   */
  public static ImageLoaderAndSaver getImageLoader(
          String filePath, ImageControllerInterface imageController)
          throws UnsupportedOperationException {
    if (filePath.endsWith(".ppm")) {
      return new PPMImageLoaderAndSaver(imageController);
    } else if (filePath.endsWith(".jpg") || filePath.endsWith(".png")) {
      return new DefaultImageLoaderAndSaver(imageController);
    } else {
      throw new UnsupportedOperationException();
    }
  }
}
