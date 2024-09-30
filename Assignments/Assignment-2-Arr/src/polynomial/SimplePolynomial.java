package polynomial;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a simple polynomial where terms are stored in an array.
 * Each index in the array corresponds to the power of the polynomial, and the value
 * at that index is the coefficient of that term.
 */
public class SimplePolynomial implements Polynomial {

  private int[] coefficients;
  private String polynomial;

  /**
   * Default constructor that initializes the polynomial to 0.
   */
  public SimplePolynomial() {
    this.polynomial = "0";
    coefficients = new int[1];
  }

  /**
   * Adds another polynomial to this polynomial.
   *
   * @param other The other polynomial to be added.
   * @return A new polynomial representing the sum of this and the other polynomial.
   */
  @Override
  public Polynomial add(Polynomial other) {
    int maxDegree = Math.max(this.getDegree(), other.getDegree());

    SimplePolynomial result = new SimplePolynomial();

    for (int i = 0; i <= maxDegree; i++) {
      result.addTerm(this.getCoefficient(i) + other.getCoefficient(i), i);
    }

    return result;
  }

  /**
   * Multiplies this polynomial with another polynomial.
   *
   * @param other The other polynomial to multiply with.
   * @return A new polynomial representing the product of this and the other polynomial.
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    Polynomial result = new SimplePolynomial();

    for (int i = 0; i <= this.getDegree(); i++) {
      for (int j = 0; j <= other.getDegree(); j++) {
        int power = i + j;
        int coefficient = this.coefficients[i] * other.getCoefficient(j);

        result.addTerm(coefficient, power);
      }
    }

    return result;
  }

  /**
   * Computes the derivative of this polynomial.
   *
   * @return A new polynomial representing the derivative of this polynomial.
   */
  @Override
  public Polynomial derivative() {
    SimplePolynomial result = new SimplePolynomial();

    for (int i = 1; i < coefficients.length; i++) {
      result.addTerm(this.getCoefficient(i) * i, i - 1);
    }

    return result;
  }

  /**
   * Adds a term to this polynomial. If the term with the same power already exists,
   * the coefficients will be added.
   *
   * @param coefficient The coefficient of the term to be added.
   * @param power       The power of the term to be added.
   * @throws IllegalArgumentException If the power is negative.
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException();
    }
    if (power > coefficients.length - 1) {
      coefficients = Arrays.copyOf(coefficients, power + 1);
    }

    if (coefficients[power] == 0) {
      coefficients[power] = coefficient;
    } else {
      coefficients[power] += coefficient;
    }
  }

  /**
   * Returns the degree of the polynomial, which is the highest power of the polynomial.
   *
   * @return The degree of the polynomial.
   */
  @Override
  public int getDegree() {
    int result = 0;

    for (int i = coefficients.length - 1; i >= 0; i--) {
      if (coefficients[i] != 0) {
        result = i;
        break;
      }
    }

    return result;
  }

  /**
   * Evaluates the polynomial for a given value of x.
   *
   * @param x The value at which to evaluate the polynomial.
   * @return The result of evaluating the polynomial at x.
   */
  @Override
  public double evaluate(double x) {
    double result = 0.0;

    for (int i = 0; i < coefficients.length; i++) {
      result += coefficients[i] * Math.pow(x, i);
    }

    return result;
  }

  /**
   * Gets the coefficient of the term with the specified power.
   *
   * @param power The power of the term.
   * @return The coefficient of the term with the specified power, or 0 if the power does not exist.
   */
  @Override
  public int getCoefficient(int power) {
    if (power > coefficients.length - 1) {
      return 0;
    }
    return coefficients[power];
  }

  /**
   * Returns the string representation of the polynomial.
   *
   * @return The string representation of the polynomial.
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    for (int i = coefficients.length - 1; i >= 0; i--) {
      int coefficient = coefficients[i];

      if (coefficient == 0) {
        continue;
      }
      if (result.length() > 0 && coefficient > 0) {
        result.append("+");
      }
      if (i == 0) {
        result.append(coefficient);
      } else {
        result.append(coefficient).append("x^").append(i);
      }
    }

    if (result.length() > 0) {
      this.polynomial = result.toString();
    } else {
      this.polynomial = "0";
    }

    return this.polynomial;
  }

  /**
   * Compares this polynomial to another object for equality.
   *
   * @param other The object to compare to.
   * @return True if the polynomials are equal, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof SimplePolynomial)) {
      return false;
    }

    return Objects.equals(this.polynomial,
            ((SimplePolynomial) other).polynomial);
  }

  /**
   * Returns the hash code of this polynomial.
   *
   * @return The hash code of the polynomial.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.polynomial);
  }
}
