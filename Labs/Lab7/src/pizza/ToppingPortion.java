package pizza;

/**
 * This enumerated type represents the portion of a single topping
 * on a single pizza. Associated with each value is a string
 * descriptor and a cost multiplier.
 */
public enum ToppingPortion {
  Full("Both Sides",1),
  LeftHalf("Left half only",0.5),
  RightHalf("Right half only",0.5);

  private final String descriptor;
  private final double costMultiplier;

  public String toString() {
    return this.descriptor;
  }

  public double getCostMultiplier() {
    return this.costMultiplier;
  }

  ToppingPortion(String d, double costMultiplier) {
    this.descriptor = d;
    this.costMultiplier = costMultiplier;
  }
}
