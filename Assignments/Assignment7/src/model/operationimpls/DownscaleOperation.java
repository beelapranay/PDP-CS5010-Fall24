package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

/**
 * Operation to downscale an image to specified width and height using bilinear interpolation.
 */
public class DownscaleOperation implements ImageOperation {
  private final int targetWidth;
  private final int targetHeight;

  /**
   * Constructs a DownscaleOperation with the specified target dimensions.
   *
   * @param targetWidth  The target width.
   * @param targetHeight The target height.
   */
  public DownscaleOperation(int targetWidth, int targetHeight) {
    if (targetWidth <= 0 || targetHeight <= 0) {
      throw new IllegalArgumentException("Target width and height must be positive integers.");
    }
    this.targetWidth = targetWidth;
    this.targetHeight = targetHeight;
  }

  /**
   * Downscale the input image to the target width and height using bilinear interpolation.
   *
   * @param images The input image to downscale.
   * @return The downscaled image.
   * @throws IllegalArgumentException if the input is invalid.
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1 || images[0] == null) {
      throw new IllegalArgumentException("DownscaleOperation requires exactly one image.");
    }
    ImageInterface image = images[0];

    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();

    if (targetWidth >= originalWidth || targetHeight >= originalHeight) {
      throw new IllegalArgumentException("Target width and height must be less than the original.");
    }

    int maxValue = image.getMaxValue();
    int[][][] originalPixels = image.getPixels();

    int[][][] newPixels = new int[targetHeight][targetWidth][3];

    for (int y = 0; y < targetHeight; y++) {
      for (int x = 0; x < targetWidth; x++) {
        double origY = y * ((double) (originalHeight - 1) / (targetHeight - 1));
        double origX = x * ((double) (originalWidth - 1) / (targetWidth - 1));

        int floorY = (int) Math.floor(origY);
        int floorX = (int) Math.floor(origX);
        int ceilY = Math.min(floorY + 1, originalHeight - 1);
        int ceilX = Math.min(floorX + 1, originalWidth - 1);

        double verticalRatio = origY - floorY;
        double horizontalRatio = origX - floorX;

        int[] topLeftPixel = originalPixels[floorY][floorX];
        int[] bottomLeftPixel = originalPixels[ceilY][floorX];
        int[] topRightPixel = originalPixels[floorY][ceilX];
        int[] bottomRightPixel = originalPixels[ceilY][ceilX];

        int[] newPixel = new int[3];

        for (int i = 0; i < 3; i++) {
          double top = topLeftPixel[i] * (1 - horizontalRatio) + topRightPixel[i] * horizontalRatio;
          double bottom = bottomLeftPixel[i] * (1 - horizontalRatio)
                  + bottomRightPixel[i] * horizontalRatio;
          newPixel[i] = (int) Math.round(top * (1 - verticalRatio) + bottom * verticalRatio);
        }

        newPixels[y][x] = newPixel;
      }
    }

    return new Image(targetWidth, targetHeight, maxValue, newPixels);
  }
}