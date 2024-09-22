/**
 * The SimpleMoney class represents a monetary value in dollars and cents.
 * It implements the Money interface and provides functionality to add
 * monetary amounts and retrieve the value in decimal form.
 */
public class SimpleMoney implements Money {

  private int dollars;
  private int cents;

  /**
   * Constructs a SimpleMoney object with the specified dollars and cents.
   * If the cents value is 100 or more, it is converted into dollars.
   *
   * @param dollars the dollar portion of the money. Must be non-negative.
   * @param cents   the cent portion of the money. Must be non-negative.
   * @throws IllegalArgumentException if either dollars or cents are negative.
   */
  public SimpleMoney(int dollars, int cents) throws IllegalArgumentException {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException();
    } else {
      this.dollars = dollars;
      this.cents = cents;

      if (this.cents >= 100) {
        cents /= 100;

        this.dollars += cents;
        this.cents -= cents * 100;
      }
    }
  }

  /**
   * Returns a string representation of the monetary value in the format "dollars.cents".
   *
   * @return a string formatted as "dollars.cents", with two digits for cents.
   */
  @Override
  public String toString() {
    return String.format("$%d.%02d", dollars, cents);
  }

  /**
   * Adds the value of another Money object to this Money object.
   * This method calculates the dollars and cents from the provided Money object
   * and adds them to this object's dollars and cents. It handles the case where
   * cents exceed 100, converting them to dollars.
   *
   * @param other The Money object to be added to this Money object.
   * @return This Money object after addition.
   */
  @Override
  public Money add(Money other) {
    double money = other.getDecimalValue();

    int otherDollars = (int) money;
    int otherCents = (int) Math.round((money - otherDollars) * 100);

    this.dollars += otherDollars;
    this.cents += otherCents;

    if (this.cents >= 100) {
      this.dollars += this.cents / 100;
      this.cents = this.cents % 100;
    }

    return this;
  }

  /**
   * Adds the specified number of dollars and cents to this Money object.
   * This method ensures that the cents value is valid (less than 100)
   * and throws an IllegalArgumentException if negative values are provided.
   *
   * @param dollars The amount of dollars to be added.
   * @param cents   The amount of cents to be added.
   * @return This Money object after the addition.
   * @throws IllegalArgumentException If either the dollars or cents are negative.
   */
  @Override
  public Money add(int dollars, int cents) throws IllegalArgumentException {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException();
    } else if (cents >= 100) {
      int extraDollars = cents / 100;
      dollars += extraDollars;
      cents -= extraDollars * 100;
    }
    this.dollars += dollars;
    this.cents += cents;

    if (this.cents >= 100) {
      this.dollars += this.cents / 100;
      this.cents -= (this.cents / 100) * 100;
    }

    return this;
  }

  /**
   * Returns the decimal value of this Money object.
   * The value is calculated as dollars + (cents / 100.0).
   *
   * @return The decimal representation of this Money object.
   */
  @Override
  public double getDecimalValue() {
    return this.dollars + (this.cents / 100.0);
  }
}
