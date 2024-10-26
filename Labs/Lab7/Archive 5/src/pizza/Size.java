package pizza;

/**
 * This enumerated type represents the size of a single pizza.
 * It also keeps the associated base cost of a pizza in this size.
 */
public enum Size {
  Small(3.0),  Medium(5.0), Large(7.0);

  private final double baseCost;

  public double getBaseCost() {
    return this.baseCost;
  }

  Size(double bc) {
    this.baseCost = bc;
  }
}
