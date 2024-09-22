/**
 * The Money interface represents money in dollars and cents.
 * It provides methods to add monetary values and retrieve the
 * decimal representation of a monetary value.
 */
public interface Money {
  /**
   * Adds the value of another Money object to this Money object.
   * This method calculates the dollars and cents from the provided Money object
   * and adds them to this object's dollars and cents. It handles the case where
   * cents exceed 100, converting them to dollars.
   *
   * @param other The Money object to be added to this Money object.
   * @return This Money object after addition.
   */
  Money add(Money other);

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
  Money add(int dollars, int cents) throws IllegalArgumentException;

  /**
   * Returns the decimal value of this Money object.
   * The value is calculated as dollars + (cents / 100.0).
   *
   * @return The decimal representation of this Money object.
   */
  double getDecimalValue();
}
