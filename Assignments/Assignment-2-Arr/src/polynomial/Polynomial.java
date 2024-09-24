package polynomial;

/**
 * This interface represents all the operations offered by a polynomial. A
 * polynomial is defined here as a function of one variable. The polynomial is a
 * weighted sum of terms (the weights, called coefficients are whole numbers).
 */

public interface Polynomial {
  /**
   * Add this polynomial with another and return the result as another
   * polynomial. All implementations must ensure that neither of the two operand
   * polynomials are changed as a result of this operation.
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   */
  Polynomial add(Polynomial other);

  /**
   * Multiply this polynomial to another and return the result as another
   * polynomial. All implementations must ensure that neither of the two operand
   * polynomials are changed as a result of this operation.
   *
   * @param other the other polynomial to be multiplied
   * @return the resulting polynomial
   */
  Polynomial multiply(Polynomial other);


  /**
   * Compute and return the first derivative of this polynomial. All implementations
   * must ensure that the calling polynomial is not changed as a result of this
   * operation.
   *
   * @return the polynomial that is the first derivative of this polynomial
   */
  Polynomial derivative();

  /**
   * Add a term to this polynomial with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Get the degree of this polynomial. The degree of a polynomial is defined as the
   * highest power in the polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  int getDegree();

  /**
   * Return a string representation of this polynomial. The string is made of
   * terms separated by a space. The string has the following characteristics:
   * <ol>
   * <li>The terms are arranged in the decreasing order of their power.</li>
   * <li>Each power appears at most once in the string.</li>
   * <li>The string contains only terms with non-zero coefficients.</li>
   * <li>Each term is of the form ax^b where a is the coefficient and b
   * is the power of that term. The term a is signed, i.e. it contains + or -
   * before the value.</li>
   * <li>If the polynomial starts with a term with a positive coefficient,
   * it does not have + in front of it.</li>
   * <li>An empty polynomial returns the string "0"</li>
   * </li>
   * </ol>
   *
   * @return a string formatted according to the above specifications
   */
  String toString();

  /**
   * Evaluate the value of this polynomial at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  double evaluate(double x);

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  int getCoefficient(int power);
}
