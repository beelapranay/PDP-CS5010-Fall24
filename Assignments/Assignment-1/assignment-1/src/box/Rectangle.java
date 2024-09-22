package box;

import java.util.Objects;


/**
 * This class represents a rectangle defined by its
 * top-left corner (x, y) and its dimensions (width, height).
 */
public class Rectangle {
  int x;
  int y;
  int width;
  int height;

  /**
   * Constructs a Rectangle with the specified coordinates and dimensions.
   *
   * @param x      the x-coordinate of the top-left corner of the rectangle
   * @param y      the y-coordinate of the top-left corner of the rectangle
   * @param width  the width of the rectangle
   * @param height the height of the rectangle
   */
  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Compares this rectangle to the specified object.
   * The result is true if and only if the argument is
   * not null and is a Rectangle object that has the
   * same x, y, width, and height as this object.
   *
   * @param o the object to compare this rectangle against
   * @return true if the given object represents a
   * Rectangle equivalent to this rectangle, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rectangle rectangle = (Rectangle) o;
    return x == rectangle.x && y == rectangle.y
            && width == rectangle.width && height == rectangle.height;
  }

  /**
   * Returns a hash code for this rectangle.
   * The hash code is computed using the x, y, width, and height fields.
   *
   * @return a hash code value for this rectangle
   */
  @Override
  public int hashCode() {
    return Objects.hash(x, y, width, height);
  }
}
