package box;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This Class implements the methods in the BoxSet Interface.
 * It manages a set of rectangles, allowing for operations such as adding and subtracting
 * rectangles and retrieving the current set of rectangles in the SimpleBoxSet.
 */
public class SimpleBoxSet implements BoxSet {

  private ArrayList<Rectangle> rectangles = new ArrayList<>();
  private ArrayList<Integer> rectanglesToRemove = new ArrayList<>();

  /**
   * Adds a rectangle to this set. If the new rectangle overlaps with any existing rectangles
   * in the set, the overlapping portions are split accordingly, ensuring no overlap remains.
   *
   * @param x      the x-coordinate of the rectangle to be added
   * @param y      the y-coordinate of the rectangle to be added
   * @param width  the width of the rectangle to be added
   * @param height the height of the rectangle to be added
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException();
    }

    Rectangle newRectangle = new Rectangle(x, y, width, height);

    for (int i = 0; i < rectangles.size(); i++) {
      if (isOverlapping(i, newRectangle)) {
        ArrayList<Integer> overlapArea = findIntersectionExtremes(i, newRectangle);
        splitRectangles(i, overlapArea.get(0), overlapArea.get(1),
                overlapArea.get(2), overlapArea.get(3));
      }
    }

    for (int i = rectanglesToRemove.size() - 1; i > -1; i--) {
      int removeIndex = rectanglesToRemove.get(i);
      rectangles.remove(removeIndex);
    }

    rectanglesToRemove = new ArrayList<>();
    rectangles.add(newRectangle);
  }

  /**
   * Subtracts the given rectangle from this set.
   * If the new rectangle overlaps with any existing rectangles,
   * the overlapping portions of the existing rectangles are split and removed accordingly.
   *
   * @param x      the x-coordinate of the rectangle to be subtracted
   * @param y      the y-coordinate of the rectangle to be subtracted
   * @param width  the width of the rectangle to be subtracted
   * @param height the height of the rectangle to be subtracted
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException();
    }

    Rectangle newRectangle = new Rectangle(x, y, width, height);

    for (int i = 0; i < rectangles.size(); i++) {
      if (isOverlapping(i, newRectangle)) {
        ArrayList<Integer> overlapArea = findIntersectionExtremes(i, newRectangle);
        splitRectangles(i, overlapArea.get(0), overlapArea.get(1),
                overlapArea.get(2), overlapArea.get(3));
      }
    }

    for (int i = rectanglesToRemove.size() - 1; i > -1; i--) {
      int removeIndex = rectanglesToRemove.get(i);
      rectangles.remove(removeIndex);
    }

    rectanglesToRemove = new ArrayList<>();
  }

  /**
   * Returns the current set of rectangles in this SimpleBoxSet as a 2D array.
   * Each rectangle is represented by four values: x, y, width, and height.
   *
   * @return a 2D array containing the current set of rectangles
   */
  @Override
  public int[][] getBoxes() {
    int[][] boxesArray = new int[rectangles.size()][4];

    for (int i = 0; i < rectangles.size(); i++) {
      Rectangle currentRectangle = rectangles.get(i);
      boxesArray[i][0] = currentRectangle.x;  // x
      boxesArray[i][1] = currentRectangle.y;  // y
      boxesArray[i][2] = currentRectangle.width;  // width
      boxesArray[i][3] = currentRectangle.height;  // height
    }
    return boxesArray;
  }

  /**
   * Returns the number of rectangles currently in this SimpleBoxSet.
   *
   * @return the number of rectangles in this set
   */
  @Override
  public int size() {
    return rectangles.size();
  }

  /**
   * Checks if the rectangle at the specified index overlaps with the new rectangle.
   *
   * @param i            the index of the existing rectangle in the set
   * @param newRectangle the new rectangle to check for overlap
   * @return true if the rectangles overlap, false otherwise
   */
  private boolean isOverlapping(int i, Rectangle newRectangle) {
    int existingX = rectangles.get(i).x;
    int existingY = rectangles.get(i).y;
    int existingWidth = rectangles.get(i).width;
    int existingHeight = rectangles.get(i).height;

    int newX = newRectangle.x;
    int newY = newRectangle.y;
    int newWidth = newRectangle.width;
    int newHeight = newRectangle.height;

    boolean xOverlap = (existingX < newX + newWidth) && (newX < existingX + existingWidth);
    boolean yOverlap = (existingY < newY + newHeight) && (newY < existingY + existingHeight);

    return xOverlap && yOverlap;
  }

  /**
   * Finds the extremes of the intersection (overlap area) between
   * the rectangle at the specified index
   * and the new rectangle.
   *
   * @param i            the index of the existing rectangle in the set
   * @param newRectangle the new rectangle to check for overlap extremes
   * @return a list containing the x-left, y-left, x-right, and y-right of the overlap area
   */
  private ArrayList<Integer> findIntersectionExtremes(int i, Rectangle newRectangle) {
    int existingX = rectangles.get(i).x;
    int existingY = rectangles.get(i).y;
    int existingWidth = rectangles.get(i).width;
    int existingHeight = rectangles.get(i).height;

    int newX = newRectangle.x;
    int newY = newRectangle.y;
    int newWidth = newRectangle.width;
    int newHeight = newRectangle.height;

    int xOverlap = Math.max(existingX, newX);
    int yOverlap = Math.max(existingY, newY);
    int xEndOverlap = Math.min(existingX + existingWidth, newX + newWidth);
    int yEndOverlap = Math.min(existingY + existingHeight, newY + newHeight);

    return new ArrayList<>(Arrays.asList(xOverlap, yOverlap, xEndOverlap, yEndOverlap));
  }

  /**
   * Splits the existing rectangle at the specified index into smaller non-overlapping rectangles
   * based on the overlap area with the new rectangle.
   *
   * @param i      the index of the existing rectangle in the set
   * @param xLeft  the leftmost x-coordinate of the overlap area
   * @param yLeft  the uppermost y-coordinate of the overlap area
   * @param xRight the rightmost x-coordinate of the overlap area
   * @param yRight the lowermost y-coordinate of the overlap area
   */
  private void splitRectangles(int i, int xLeft, int yLeft, int xRight, int yRight) {
    int rectangleX = rectangles.get(i).x;
    int rectangleY = rectangles.get(i).y;
    int rectangleWidth;
    int rectangleHeight;

    rectangleWidth = xLeft - rectangleX;
    if (rectangleWidth > 0) {
      rectangleHeight = rectangles.get(i).height;
      rectangles.add(new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight));
    } else {
      rectangleHeight = yLeft - rectangleY;
      if (rectangleHeight > 0) {
        rectangleWidth = xRight - rectangleX;
        rectangles.add(new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight));
      }
    }

    if (rectangles.get(i).x + rectangles.get(i).width == xRight && rectangles.get(i).x != xLeft) {
      rectangleHeight = yLeft - rectangleY;
      if (rectangleHeight > 0) {
        rectangles.add(new Rectangle(xLeft, rectangleY, xRight - xLeft, rectangleHeight));
      }
    } else if (rectangles.get(i).x + rectangles.get(i).width > xRight) {
      if (rectangles.get(i).x < xLeft && yLeft - rectangleY > 0) {
        rectangles.add(new Rectangle(xLeft, rectangleY,
                xRight - xLeft, yLeft - rectangleY));
      }
      rectangles.add(new Rectangle(xRight, rectangleY, rectangles.get(i).x
              + rectangles.get(i).width - xRight, rectangles.get(i).height));
    }

    if (xLeft == rectangleX) {
      if (rectangles.get(i).y + rectangles.get(i).height > yRight) {
        rectangles.add(new Rectangle(xLeft, yRight, xRight - xLeft,
                rectangles.get(i).y + rectangles.get(i).height - yRight));
      }
    } else if (xLeft > rectangleX && xRight < rectangles.get(i).x + rectangles.get(i).width) {
      rectangles.add(new Rectangle(xLeft, yRight, xRight - xLeft,
              rectangles.get(i).y + rectangles.get(i).height - yRight));
    } else if (xLeft > rectangleX && xRight == rectangles.get(i).x + rectangles.get(i).width) {
      rectangles.add(new Rectangle(xLeft, yRight, xRight - xLeft,
              rectangles.get(i).y + rectangles.get(i).height - yRight));
    }

    rectanglesToRemove.add(i);
  }
}
