package model.operationimpls;

import model.image.Image;
import model.image.ImageInterface;
import model.operationinterface.ImageOperation;

/**
 * An operation that adjusts the levels of an image.
 * This operation takes three points as input: black, mid, and white.
 * The black point is the value that will be mapped to 0.
 * The mid point is the value that will be mapped to the maximum value divided by 2.
 * The white point is the value that will be mapped to the maximum value.
 * The operation uses a quadratic function to adjust the levels of the image.
 */
public class AdjustLevelsOperation implements ImageOperation {
  private final int blackPoint;
  private final int midPoint;
  private final int whitePoint;

  /**
   * Constructs an AdjustLevelsOperation with the specified black, mid, and white points.
   *
   * @param blackPoint The black point value.
   * @param midPoint   The mid-tone point value.
   * @param whitePoint The white point value.
   */
  public AdjustLevelsOperation(int blackPoint, int midPoint, int whitePoint) {
    this.blackPoint = blackPoint;
    this.midPoint = midPoint;
    this.whitePoint = whitePoint;
  }

  /**
   * Adjusts the levels of the input image based on the black, mid, and white points.
   * The black point is the value that will be mapped to 0.
   * The mid point is the value that will be mapped to the maximum value divided by 2.
   * The white point is the value that will be mapped to the maximum value.
   *
   * @param images The input image to adjust.
   * @return A new image with the adjusted levels.
   * @throws IllegalArgumentException if the input image is null or invalid
   */
  @Override
  public ImageInterface execute(ImageInterface... images) {
    if (images == null || images.length != 1) {
      throw new IllegalArgumentException("AdjustLevelsOperation requires exactly one image.");
    }
    ImageInterface image = images[0];

    int maxValue = image.getMaxValue();

    if (blackPoint < 0
            || blackPoint >= midPoint || midPoint >= whitePoint || whitePoint > maxValue) {
      throw new IllegalArgumentException("Invalid black, mid, and white points.");
    }

    double[] coefficients = computeQuadraticCoefficients(blackPoint, midPoint,
        whitePoint, maxValue);

    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] newPixels = new int[height][width][3];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] rgb = image.getPixel(x, y);
        int[] newRgb = new int[3];
        for (int c = 0; c < 3; c++) {
          int value = rgb[c];
          double adjustedValue =
                  coefficients[0] * value * value + coefficients[1] * value + coefficients[2];

          newRgb[c] = clamp((int) Math.round(adjustedValue), 0, maxValue);
        }
        newPixels[y][x] = newRgb;
      }
    }

    return new Image(width, height, maxValue, newPixels);
  }

  /**
   * Computes the coefficients of the quadratic function that maps the black, mid, and white points
   * to 0, maxValue/2, and maxValue respectively.
   *
   * @param b        The black point value.
   * @param m        The mid-tone point value.
   * @param w        The white point value.
   * @param maxValue The maximum value of the image.
   * @return The coefficients of the quadratic function.
   */
  private static double[] computeQuadraticCoefficients(int b, int m, int w, int maxValue) {
    double blackPoint = b;
    double midPoint = m;
    double whitePoint = w;

    double blackLevel = 0;
    double midLevel = maxValue / 2.0;
    double whiteLevel = maxValue;

    double denominatorBlackMid = (blackPoint - midPoint) * (blackPoint - whitePoint);
    double denominatorMidWhite = (midPoint - blackPoint) * (midPoint - whitePoint);
    double denominatorWhiteBlack = (whitePoint - blackPoint) * (whitePoint - midPoint);

    double a_black = blackLevel / denominatorBlackMid;
    double a_mid = midLevel / denominatorMidWhite;
    double a_white = whiteLevel / denominatorWhiteBlack;

    double a = a_black + a_mid + a_white;
    double b_coeff = -(a_black * (midPoint + whitePoint) + a_mid * (blackPoint + whitePoint)
            + a_white * (blackPoint + midPoint));
    double c = a_black * midPoint * whitePoint + a_mid * blackPoint * whitePoint + a_white
            * blackPoint * midPoint;

    return new double[]{a, b_coeff, c};
  }

  /**
   * Clamps a value to the specified range.
   *
   * @param value The value to clamp.
   * @param min   The minimum value.
   * @param max   The maximum value.
   * @return The clamped value.
   */
  private static int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(max, value));
  }


}
