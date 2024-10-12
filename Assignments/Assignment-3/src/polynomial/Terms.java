package polynomial;

/**
 * This class represents a term in a polynomial.
 * Each term consists of a coefficient and a power (exponent).
 */
public class Terms {
  private int power;
  private int coefficient;

  /**
   * Constructs a new term with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term
   * @param power       the exponent (power) of the term
   */
  public Terms(int coefficient, int power) {
    this.power = power;
    this.coefficient = coefficient;
  }

  /**
   * Sets the coefficient of the term.
   *
   * @param coefficient the new coefficient of the term
   */
  public void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * Returns the power (exponent) of the term.
   *
   * @return the power of the term
   */
  public int getPower() {
    return this.power;
  }

  /**
   * Returns the coefficient of the term.
   *
   * @return the coefficient of the term
   */
  public int getCoefficient() {
    return this.coefficient;
  }
}
