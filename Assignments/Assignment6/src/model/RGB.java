package model;

/**
 * Class representing an RGB color with red, green, and blue components.
 * This class implements the RGBInterface as a marker interface.
 */
public class RGB implements RGBInterface {

  /**
   * The red component of the RGB color.
   */
  public int red;

  /**
   * The green component of the RGB color.
   */
  public int green;

  /**
   * The blue component of the RGB color.
   */
  public int blue;

  /**
   * Constructs an RGB object with the specified red, green, and blue values.
   *
   * @param red   the red component of the color
   * @param green the green component of the color
   * @param blue  the blue component of the color
   */
  public RGB(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
}
