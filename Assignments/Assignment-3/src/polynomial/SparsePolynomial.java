package polynomial;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a sparse polynomial and provides operations such as
 * addition, multiplication, and derivative. It uses a LinkedList to store
 * polynomial terms.
 */
public class SparsePolynomial implements Polynomial {

  private String polynomial;
  private List<Terms> polynomialTerms;

  /**
   * Constructor to initialize an empty sparse polynomial.
   * The polynomial is initialized to "0".
   */
  public SparsePolynomial() {
    this.polynomial = "0";
    this.polynomialTerms = new LinkedList<Terms>();
  }

  protected List<Terms> getPolynomialTerms() {
    return this.polynomialTerms;
  }

  /**
   * Adds the given polynomial to this polynomial.
   *
   * @param other the polynomial to add
   * @return the resulting polynomial after addition
   */
  @Override
  public Polynomial add(Polynomial other) {
    if (other.getDegree() == 0 && other.getCoefficient(0) == 0) {
      return this;
    }

    Polynomial result = new SparsePolynomial();

    for (Terms term : this.polynomialTerms) {
      result.addTerm(term.getCoefficient(), term.getPower());
    }

    for (int i = 0; i <= other.getDegree(); i++) {
      if (other.getCoefficient(i) != 0) {
        result.addTerm(other.getCoefficient(i), i);
      }
    }

    return result;
  }

  /**
   * Multiplies the given polynomial with this polynomial.
   *
   * @param other the polynomial to multiply with
   * @return the resulting polynomial after multiplication
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    Polynomial sparsePolynomialResult = new SparsePolynomial();

    if (other instanceof SparsePolynomial) {
      for (Terms terms1 : ((SparsePolynomial) other).getPolynomialTerms()) {
        int power1 = terms1.getPower();
        int coefficient1 = terms1.getCoefficient();

        for (Terms terms2 : this.polynomialTerms) {
          int power2 = terms2.getPower();
          int coefficient2 = terms2.getCoefficient();

          int newPower = power1 + power2;
          int newCoefficient = coefficient1 * coefficient2;

          sparsePolynomialResult.addTerm(newCoefficient, newPower);
        }
      }
    } else {
      for (int i = other.getDegree(); i >= 0; i--) {
        int coefficient1 = other.getCoefficient(i);

        for (Terms terms : this.polynomialTerms) {
          int power2 = terms.getPower();
          int coefficient2 = terms.getCoefficient();

          int newPower = i + power2;
          int newCoefficient = coefficient1 * coefficient2;

          sparsePolynomialResult.addTerm(newCoefficient, newPower);
        }
      }
    }

    return sparsePolynomialResult;
  }

  /**
   * Computes the derivative of this polynomial.
   *
   * @return the resulting polynomial after computing the derivative
   */
  @Override
  public Polynomial derivative() {
    Polynomial sparsePolynomialDerivative = new SparsePolynomial();

    for (Terms term : this.polynomialTerms) {
      if (term.getPower() != 0) {
        int newPower = term.getPower() - 1;
        int newCoefficient = term.getPower() * term.getCoefficient();

        sparsePolynomialDerivative.addTerm(newCoefficient, newPower);
      }
    }

    return sparsePolynomialDerivative;
  }

  /**
   * Adds a term to this polynomial with the given coefficient and power.
   *
   * @param coefficient the coefficient of the term
   * @param power       the power of the term
   * @throws IllegalArgumentException if the power is negative
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException();
    }

    if (coefficient == 0) {
      return;
    }

    Terms newTerm = new Terms(coefficient, power);

    if (polynomialTerms.isEmpty()) {
      polynomialTerms.add(newTerm);
      return;
    }

    for (int i = 0; i < polynomialTerms.size(); i++) {
      Terms currentTerm = polynomialTerms.get(i);

      if (currentTerm.getPower() == power) {
        int newCoefficient = currentTerm.getCoefficient() + coefficient;
        if (newCoefficient == 0) {
          polynomialTerms.remove(i);
        } else {
          currentTerm.setCoefficient(currentTerm.getCoefficient() + coefficient);
        }

        return;
      } else if (currentTerm.getPower() < power) {
        polynomialTerms.add(i, newTerm);
        return;
      }
    }

    polynomialTerms.add(newTerm);
  }

  /**
   * Returns the degree of the polynomial (i.e., the highest power).
   *
   * @return the degree of the polynomial
   */
  @Override
  public int getDegree() {
    if (polynomialTerms.isEmpty()) {
      return 0;
    }

    return polynomialTerms.get(0).getPower();
  }

  /**
   * Evaluates the polynomial for a given value of x.
   *
   * @param x the value at which to evaluate the polynomial
   * @return the result of evaluating the polynomial at x
   */
  @Override
  public double evaluate(double x) {
    double result = 0.0;

    for (Terms term : polynomialTerms) {
      int power = term.getPower();
      int coefficient = term.getCoefficient();

      if (power == 0) {
        result += coefficient;
      } else {
        result += coefficient * Math.pow(x, power);
      }
    }

    return result;
  }

  /**
   * Returns the coefficient of the term with the specified power.
   *
   * @param power the power of the term whose coefficient is to be returned
   * @return the coefficient of the term with the given power, or 0 if no such term exists
   */
  @Override
  public int getCoefficient(int power) {
    for (Terms term : polynomialTerms) {
      int powerOfTerm = term.getPower();

      if (powerOfTerm == power) {
        return term.getCoefficient();
      }
    }

    return 0;
  }

  /**
   * Returns a string representation of the polynomial.
   *
   * @return a string representing the polynomial
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    for (Terms polynomialTerm : polynomialTerms) {
      int coefficient = polynomialTerm.getCoefficient();
      int power = polynomialTerm.getPower();

      if (coefficient == 0) {
        continue;
      }
      if (result.length() > 0 && coefficient > 0) {
        result.append("+");
      }
      if (power == 0) {
        result.append(coefficient);
      } else {
        result.append(coefficient).append("x^").append(power);
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
   * Compares this polynomial with another polynomial for equality.
   *
   * @param other the polynomial to compare with
   * @return true if the polynomials are equal, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Polynomial)) {
      return false;
    }

    if (((Polynomial) other).getDegree() != this.getDegree()) {
      return false;
    }

    if (other instanceof SparsePolynomial) {
      if (((SparsePolynomial) other).getPolynomialTerms().size()
              != this.getPolynomialTerms().size()) {
        return false;
      }

      int i = 0;
      for (Terms term : ((SparsePolynomial) other).getPolynomialTerms()) {

        if (term.getPower() != this.polynomialTerms.get(i).getPower()
                || term.getCoefficient() != this.polynomialTerms.get(i).getCoefficient()) {
          return false;
        }

        i++;
      }
      return true;
    }

    for (int i = 0; i <= this.getDegree(); i++) {
      if (this.getCoefficient(i) != ((Polynomial) other).getCoefficient(i)) {
        return false;
      }
    }

    return true;
  }


  /**
   * Returns a hash code value for the polynomial.
   *
   * @return the hash code value of the polynomial
   */
  @Override
  public int hashCode() {
    final int prime = 37;
    int hash = 1;

    int maxPower = this.getDegree();
    for (int power = 0; power <= maxPower; power++) {
      int coefficient = this.getCoefficient(power);
      if (coefficient != 0) {
        hash = prime * hash + coefficient;
        hash = prime * hash + power;
      }
    }

    return hash;
  }
}
