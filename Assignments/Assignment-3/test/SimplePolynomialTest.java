import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SimplePolynomial;
import polynomial.SparsePolynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the SimplePolynomial class.
 * It covers various test cases for the methods in SimplePolynomial,
 * including constructor, addTerm, addition, multiplication, derivative,
 * degree, evaluate, coefficient retrieval, and toString method.
 */
public class SimplePolynomialTest {

  /**
   * Tests the constructor to ensure a newly created polynomial is initialized to "0".
   */
  @Test
  public void testForConstructor() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests adding a term with zero coefficient and power.
   * The polynomial should remain as "0".
   */
  @Test
  public void testAddTermZeroValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(0, 0);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests adding positive terms to the polynomial.
   * Verifies that the terms are correctly added and returned in descending order of powers.
   */
  @Test
  public void testAddTermPositiveValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(4, 5);
    polynomial.addTerm(6, 7);
    assertEquals("6x^7+4x^5+2x^3", polynomial.toString());
  }

  /**
   * Tests adding negative terms to the polynomial.
   * Verifies that negative coefficients are correctly represented.
   */
  @Test
  public void testAddTermNegativeValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(-4, 5);
    polynomial.addTerm(-6, 7);
    assertEquals("-6x^7-4x^5-2x^3", polynomial.toString());
  }

  /**
   * Tests that adding a term with a negative power throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTermException() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(4, -5);  // Negative power should throw an exception
  }

  /**
   * Tests adding two polynomials with positive values.
   * Verifies that the polynomials are correctly summed.
   */
  @Test
  public void testAddMethodPositiveValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(6, 7);
    polynomial.addTerm(1, 4);

    otherPolynomial.addTerm(100, 7);
    otherPolynomial.addTerm(7, 10);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("7x^10+106x^7+1x^4+2x^3", result.toString());
  }

  /**
   * Tests adding a polynomial to a zero polynomial.
   * Verifies that the result is the original non-zero polynomial.
   */
  @Test
  public void testAddMethodZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(6, 7);
    polynomial.addTerm(1, 4);

    polynomial.add(otherPolynomial);

    assertEquals("6x^7+1x^4+2x^3", polynomial.toString());
  }

  /**
   * Tests adding polynomials with both positive and negative terms.
   * Verifies that terms with the same powers are properly summed.
   */
  @Test
  public void testAddMethodPositiveAndNegativeValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(6, 7);
    polynomial.addTerm(1, 4);

    otherPolynomial.addTerm(-2, 3);
    otherPolynomial.addTerm(-10, 7);
    otherPolynomial.addTerm(7, 10);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("7x^10-4x^7+1x^4", result.toString());
  }

  /**
   * Tests adding polynomials with both positive and negative terms.
   */
  @Test
  public void testAddMethodUniquePolynomials() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 4);
    polynomial.addTerm(6, 6);
    polynomial.addTerm(1, 7);

    otherPolynomial.addTerm(-2, 2);
    otherPolynomial.addTerm(-10, 5);
    otherPolynomial.addTerm(7, 3);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("1x^7+6x^6-10x^5+2x^4+7x^3-2x^2", result.toString());
  }

  /**
   * Tests adding polynomials where all terms are negative.
   */
  @Test
  public void testAddMethodNegativeValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(-2, 3);
    polynomial.addTerm(-6, 7);
    polynomial.addTerm(-1, 4);
    polynomial.addTerm(-238, 0);

    otherPolynomial.addTerm(-2, 3);
    otherPolynomial.addTerm(-10, 7);
    otherPolynomial.addTerm(-7, 10);
    otherPolynomial.addTerm(-1000, 0);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("-7x^10-16x^7-1x^4-4x^3-1238", result.toString());
  }

  /**
   * Tests multiplying a polynomial by a zero polynomial.
   * The result should be zero.
   */
  @Test
  public void testMultiplyMethodZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(1, 4);
    polynomial.addTerm(20, 0);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals("0", result.toString());
  }

  /**
   * Tests multiplying two polynomials with positive values.
   * Verifies that the result is correctly computed based on the powers and coefficients.
   */
  @Test
  public void testMultiplyMethodPositiveValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(1, 4);
    polynomial.addTerm(20, 0);

    otherPolynomial.addTerm(49, 3);
    otherPolynomial.addTerm(10, 5);
    otherPolynomial.addTerm(17, 4);
    otherPolynomial.addTerm(10, 0);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals("10x^9+37x^8+83x^7+98x^6+200x^5+350x^4+1000x^3+200",
            result.toString());
  }

  /**
   * Tests multiplying two polynomials with both positive and negative values.
   * Verifies that the product is correctly computed.
   */
  @Test
  public void testMultiplyMethodPositiveAndNegativeValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(-6, 7);
    polynomial.addTerm(-1, 4);
    polynomial.addTerm(20, 0);

    otherPolynomial.addTerm(-2, 3);
    otherPolynomial.addTerm(10, 7);
    otherPolynomial.addTerm(-7, 10);
    otherPolynomial.addTerm(10, 0);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals(
            "42x^17-53x^14-14x^13-10x^11-108x^10+142x^7-4x^6-10x^4-20x^3+200",
            result.toString());
  }

  /**
   * Tests multiplying two polynomials where all terms have negative values.
   */
  @Test
  public void testMultiplyMethodNegativeValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(-2, 3);
    polynomial.addTerm(-6, 7);
    polynomial.addTerm(-1, 4);
    polynomial.addTerm(-20, 0);

    otherPolynomial.addTerm(-2, 3);
    otherPolynomial.addTerm(-10, 7);
    otherPolynomial.addTerm(-7, 10);
    otherPolynomial.addTerm(-10, 0);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals(
            "42x^17+67x^14+14x^13+10x^11+172x^10+262x^7+4x^6+10x^4+60x^3+200",
            result.toString());
  }

  /**
   * Tests taking the derivative of a zero polynomial.
   * The result should still be zero.
   */
  @Test
  public void testZeroPolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    Polynomial result = polynomial.derivative();

    assertEquals("0", result.toString());
  }

  /**
   * Tests taking the derivative of a constant polynomial.
   * The derivative of a constant is zero.
   */
  @Test
  public void testConstantDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    Polynomial result = polynomial.derivative();

    assertEquals("0", result.toString());
  }

  /**
   * Tests taking the derivative of a linear polynomial.
   * The derivative of a term like 5x should be 5.
   */
  @Test
  public void testSingleTermLinearDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 1);
    Polynomial result = polynomial.derivative();

    assertEquals("5", result.toString());
  }

  /**
   * Tests taking the derivative of a polynomial with higher degree terms.
   */
  @Test
  public void testSingleTermHighDegreeDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 3);
    Polynomial result = polynomial.derivative();

    assertEquals("12x^2", result.toString());
  }

  /**
   * Tests taking the derivative of a multi-term polynomial.
   */
  @Test
  public void testMultiTermPolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 4);
    polynomial.addTerm(-5, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(-10, 0);

    Polynomial result = polynomial.derivative();

    assertEquals("12x^3-15x^2+2", result.toString());
  }

  /**
   * Tests taking the derivative of a polynomial with negative coefficients.
   */
  @Test
  public void testNegativeCoefficientsDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-3, 4);
    polynomial.addTerm(7, 3);

    Polynomial result = polynomial.derivative();

    assertEquals("-12x^3+21x^2", result.toString());
  }

  /**
   * Tests taking the derivative of a sparse polynomial (some coefficients are zero).
   */
  @Test
  public void testSparsePolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 5);
    polynomial.addTerm(-3, 2);

    Polynomial result = polynomial.derivative();

    assertEquals("20x^4-6x^1", result.toString());
  }

  /**
   * Tests taking the derivative of a high-degree polynomial.
   */
  @Test
  public void testHighDegreePolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(1, 100);

    Polynomial result = polynomial.derivative();

    assertEquals("100x^99", result.toString());
  }

  /**
   * Tests the degree of a zero polynomial, which should be 0.
   */
  @Test
  public void testZeroPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals(0, polynomial.getDegree());
  }

  /**
   * Tests the degree of a constant polynomial, which should also be 0.
   */
  @Test
  public void testConstantPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals(0, polynomial.getDegree());
  }

  /**
   * Tests the degree of a polynomial with a single term.
   * The degree should be the power of the single term.
   */
  @Test
  public void testSingleTermPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    assertEquals(3, polynomial.getDegree());
  }

  /**
   * Tests the degree of a multi-term polynomial.
   * The degree should be the highest power among the terms.
   */
  @Test
  public void testMultiTermPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 4);
    polynomial.addTerm(-5, 2);
    polynomial.addTerm(7, 1);
    assertEquals(4, polynomial.getDegree());
  }

  /**
   * Tests the degree of a polynomial with negative coefficients.
   */
  @Test
  public void testNegativeCoefficientPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-5, 6);
    polynomial.addTerm(2, 3);
    assertEquals(6, polynomial.getDegree());
  }

  /**
   * Tests the degree of a sparse polynomial, where some powers have zero coefficients.
   * The degree should be the highest non-zero power.
   */
  @Test
  public void testSparsePolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 8);
    polynomial.addTerm(0, 5);
    polynomial.addTerm(-3, 2);
    assertEquals(8, polynomial.getDegree());
  }

  /**
   * Tests evaluating a zero polynomial at a given value of x.
   * The result should always be 0.
   */
  @Test
  public void testEvaluateZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals(0.0, polynomial.evaluate(2.0), 0.0001);
  }

  /**
   * Tests evaluating a constant polynomial at a given value of x.
   * The result should be the constant term.
   */
  @Test
  public void testEvaluateConstantPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals(5.0, polynomial.evaluate(3.0), 0.0001);
  }

  /**
   * Tests evaluating a single-term polynomial at a given value of x.
   */
  @Test
  public void testEvaluateSingleTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 2);
    assertEquals(12.0, polynomial.evaluate(2.0), 0.0001);
  }

  /**
   * Tests evaluating a multi-term polynomial at a given value of x.
   */
  @Test
  public void testEvaluateMultiTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(-1, 1);
    polynomial.addTerm(4, 0);
    assertEquals(18.0, polynomial.evaluate(2.0), 0.0001);
  }

  /**
   * Tests evaluating a polynomial with negative coefficients at a given value of x.
   */
  @Test
  public void testEvaluateNegativeCoefficients() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-3, 2);
    polynomial.addTerm(1, 1);
    assertEquals(-10.0, polynomial.evaluate(2.0), 0.0001);
  }

  /**
   * Tests evaluating a polynomial at x = 0.
   * The result should be the constant term.
   */
  @Test
  public void testEvaluateWithZeroX() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(-2, 2);
    polynomial.addTerm(4, 0);
    assertEquals(4.0, polynomial.evaluate(0.0), 0.0001);
  }

  /**
   * Tests evaluating a polynomial at a negative value of x.
   * The result should be the constant term.
   */
  @Test
  public void testEvaluateWithNegativeX() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(6, 3);
    polynomial.addTerm(-5, 2);
    polynomial.addTerm(9, 0);
    assertEquals(-455.0, polynomial.evaluate(-4.0), 0.0001);
  }

  /**
   * Tests retrieving the coefficient of an existing power.
   */
  @Test
  public void testGetCoefficientForExistingPower() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    assertEquals(5, polynomial.getCoefficient(3));
  }

  /**
   * Tests retrieving the coefficient of a non-existing power.
   * The result should be 0.
   */
  @Test
  public void testGetCoefficientForNonExistingPower() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 2);
    assertEquals(0, polynomial.getCoefficient(5));  // Power 5 doesn't exist
  }

  /**
   * Tests retrieving the coefficient of the zero power (constant term).
   */
  @Test
  public void testGetCoefficientForZeroPower() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 0);
    assertEquals(4, polynomial.getCoefficient(0));  // Constant term
  }

  /**
   * Tests retrieving the coefficient for a term with a negative coefficient.
   */
  @Test
  public void testGetCoefficientForNegativeCoefficient() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-7, 2);
    assertEquals(-7, polynomial.getCoefficient(2));  // Negative coefficient
  }

  /**
   * Tests retrieving the coefficient for a term with an explicitly zero coefficient.
   */
  @Test
  public void testGetCoefficientForZeroCoefficient() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(0, 3);
    assertEquals(0, polynomial.getCoefficient(3));  // Explicit zero coefficient
  }

  /**
   * Tests the string representation of a zero polynomial.
   */
  @Test
  public void testToStringZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests the string representation of a constant polynomial.
   */
  @Test
  public void testToStringConstantPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals("5", polynomial.toString());
  }

  /**
   * Tests the string representation of a single-term polynomial.
   */
  @Test
  public void testToStringSingleTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 2);
    assertEquals("3x^2", polynomial.toString());
  }

  /**
   * Tests the string representation of a multi-term polynomial.
   */
  @Test
  public void testToStringMultiTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 3);
    polynomial.addTerm(-5, 2);
    polynomial.addTerm(1, 1);
    polynomial.addTerm(6, 0);
    assertEquals("4x^3-5x^2+1x^1+6", polynomial.toString());
  }

  /**
   * Tests the string representation of a polynomial with negative coefficients.
   */
  @Test
  public void testToStringNegativeCoefficients() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-3, 2);
    polynomial.addTerm(2, 1);
    assertEquals("-3x^2+2x^1", polynomial.toString());
  }

  /**
   * Tests the string representation of a polynomial with zero coefficients for some terms.
   */
  @Test
  public void testToStringWithZeroCoefficients() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(0, 2);
    polynomial.addTerm(-7, 1);
    assertEquals("5x^3-7x^1", polynomial.toString());
  }

  /**
   * Tests the equality method for two equal polynomials.
   */
  @Test
  public void testEqualsMethod() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(-2, 2);

    SimplePolynomial polynomial2 = new SimplePolynomial();
    polynomial2.addTerm(5, 3);
    polynomial2.addTerm(-2, 2);

    boolean result = polynomial.equals(polynomial2);
    assertTrue(result);
  }

  /**
   * Tests if a SimplePolynomial and a SparsePolynomial with the same terms are considered equal.
   */
  @Test
  public void testSimpleAndSparsePolynomialsAreEqual() {
    Polynomial poly1 = new SimplePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(4, 3);
    poly1.addTerm(2, 1);

    poly2.addTerm(4, 3);
    poly2.addTerm(2, 1);

    assertTrue(poly1.equals(poly2));
  }

  /**
   * Tests if a SimplePolynomial and a SparsePolynomial with different terms are not equal.
   */
  @Test
  public void testSimpleAndSparsePolynomialsDifferentTerms() {
    Polynomial poly1 = new SimplePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(4, 3);
    poly1.addTerm(2, 1);

    poly2.addTerm(4, 3);
    poly2.addTerm(5, 1);

    assertFalse(poly1.equals(poly2));
  }

  /**
   * Tests if two polynomials of different types are not equal.
   */
  @Test
  public void testPolynomialsDifferentTypes() {
    Polynomial poly1 = new SimplePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(5, 2);
    poly2.addTerm(5, 2);

    assertTrue(poly1.equals(poly2));
  }
}