import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SimplePolynomial;
import polynomial.SparsePolynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the SparsePolynomial class, covering operations such as
 * adding terms, multiplying polynomials, taking derivatives, and evaluating polynomials.
 * Also tests equality between SparsePolynomial and SimplePolynomial.
 */
public class SparsePolynomialTest {

  /**
   * Tests the constructor to ensure a newly created polynomial is initialized to "0".
   */
  @Test
  public void testForConstructor() {
    Polynomial polynomial = new SparsePolynomial();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests adding a term with zero coefficient and power.
   * The polynomial should remain as "0".
   */
  @Test
  public void testAddTermZeroValues() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(0, 0);
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests adding positive terms to the polynomial.
   * Verifies that the terms are correctly added and returned in descending order of powers.
   */
  @Test
  public void testAddTermPositiveValues() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(20000, 3000);
    polynomial.addTerm(4000000, 50000);
    polynomial.addTerm(6, 700000);
    assertEquals("6x^700000+4000000x^50000+20000x^3000", polynomial.toString());
  }

  /**
   * Tests adding negative terms to the polynomial.
   * Verifies that negative coefficients are correctly represented.
   */
  @Test
  public void testAddTermNegativeValues() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(-20000, 300000);
    polynomial.addTerm(-400, 5000);
    polynomial.addTerm(-6, 70000000);
    assertEquals("-6x^70000000-20000x^300000-400x^5000", polynomial.toString());
  }

  /**
   * Tests adding negative terms to the polynomial.
   * Verifies that negative coefficients are correctly represented.
   */
  @Test
  public void testAddTermLargeValues() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(-2, 30000000);
    polynomial.addTerm(-6, 7);
    assertEquals("-2x^30000000-6x^7", polynomial.toString());
  }

  /**
   * Tests that adding a term with a negative power throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddTermException() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(4, -5888);
  }

  /**
   * Tests adding two polynomials with positive values.
   * Verifies that the polynomials are correctly summed.
   */
  @Test
  public void testAddMethodUniquePowers() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(278, 3988);
    polynomial.addTerm(65, 7775);
    polynomial.addTerm(1, 4276);

    otherPolynomial.addTerm(100, 9874);
    otherPolynomial.addTerm(7, 10);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("100x^9874+65x^7775+1x^4276+278x^3988+7x^10", result.toString());
  }

  /**
   * Tests adding two polynomials with positive values.
   * Verifies that the polynomials are correctly summed.
   */
  @Test
  public void testAddMethodSimilarPowers() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(265, 3099);
    polynomial.addTerm(67, 70);
    polynomial.addTerm(1, 4);

    otherPolynomial.addTerm(6544, 3099);
    otherPolynomial.addTerm(100, 70);
    otherPolynomial.addTerm(7, 4);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("6809x^3099+167x^70+8x^4", result.toString());
  }

  /**
   * Tests adding a polynomial to a zero polynomial.
   * Verifies that the result is the original non-zero polynomial.
   */
  @Test
  public void testAddMethodZeroPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(2, 3755);
    polynomial.addTerm(6, 754);
    polynomial.addTerm(1, 465757);

    polynomial.add(otherPolynomial);

    assertEquals("1x^465757+2x^3755+6x^754", polynomial.toString());
  }

  /**
   * Tests adding polynomials with both positive and negative terms.
   * Verifies that terms with the same powers are properly summed.
   */
  @Test
  public void testAddMethodDifferentCoefficients() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(2556, 3667);
    polynomial.addTerm(66, 746456);
    polynomial.addTerm(16454, 44445);

    otherPolynomial.addTerm(-245646, 364);
    otherPolynomial.addTerm(-10654, 746456);
    otherPolynomial.addTerm(754, 1055);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("-10588x^746456+16454x^44445+2556x^3667+754x^1055-245646x^364",
            result.toString());
  }

  /**
   * Tests adding polynomials where all terms are negative.
   */
  @Test
  public void testAddMethodNegativeCoefficients() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(-2, 3666);
    polynomial.addTerm(-6, 74545);
    polynomial.addTerm(-1, 4004);
    polynomial.addTerm(-238, 545);

    otherPolynomial.addTerm(-2, 354);
    otherPolynomial.addTerm(-10, 7546);
    otherPolynomial.addTerm(-7, 10000);
    otherPolynomial.addTerm(-1000, 545);

    Polynomial result = polynomial.add(otherPolynomial);

    assertEquals("-6x^74545-7x^10000-10x^7546-1x^4004-2x^3666-1238x^545-2x^354",
            result.toString());
  }

  /**
   * Tests multiplying polynomials with large positive and negative values.
   */
  @Test
  public void testMultiplyMethodUniquePowers() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(3, 50);
    polynomial.addTerm(-5, 100);
    polynomial.addTerm(6, 65);

    otherPolynomial.addTerm(2, 41);
    otherPolynomial.addTerm(-4, 82);
    otherPolynomial.addTerm(1, 20);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals("20x^182-24x^147-10x^141-12x^132-5x^120+12x^106+6x^91+6x^85+3x^70",
            result.toString());
  }

  /**
   * Tests multiplying polynomials where all terms are negative.
   */
  @Test
  public void testMultiplyMethodNegativeCoefficients() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(-7, 103);
    polynomial.addTerm(-3, 21);
    polynomial.addTerm(-9, 3);

    otherPolynomial.addTerm(-4, 45);
    otherPolynomial.addTerm(-6, 59);
    otherPolynomial.addTerm(-8, 600);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals("56x^703+24x^621+72x^603+42x^162+28x^148+18x^80+12x^66+54x^62+36x^48",
            result.toString());
  }

  /**
   * Tests multiplying polynomials where one has large values and the other has small values.
   */
  @Test
  public void testMultiplyMethodMixedValues() {
    Polynomial polynomial = new SparsePolynomial();
    Polynomial otherPolynomial = new SparsePolynomial();

    polynomial.addTerm(4, 32);
    polynomial.addTerm(-3, 64);
    polynomial.addTerm(6, 1);

    otherPolynomial.addTerm(1, 5);
    otherPolynomial.addTerm(-2, 101);
    otherPolynomial.addTerm(1, 41);

    Polynomial result = polynomial.multiply(otherPolynomial);

    assertEquals("6x^165-8x^133-3x^105-12x^102+4x^73-3x^69+6x^42+4x^37+6x^6",
            result.toString());
  }

  /**
   * Tests the derivative of a polynomial with large positive exponents.
   */
  @Test
  public void testDerivativeWithLargeExponents() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5, 10000);
    polynomial.addTerm(3, 5000);
    polynomial.addTerm(2, 2000);

    Polynomial derivative = polynomial.derivative();

    assertEquals("50000x^9999+15000x^4999+4000x^1999", derivative.toString());
  }

  /**
   * Tests the derivative of a polynomial with large negative coefficients.
   */
  @Test
  public void testDerivativeWithNegativeCoefficients() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(-6, 8000);
    polynomial.addTerm(-4, 4000);
    polynomial.addTerm(-2, 1000);

    Polynomial derivative = polynomial.derivative();

    assertEquals("-48000x^7999-16000x^3999-2000x^999", derivative.toString());
  }

  /**
   * Tests the derivative of a polynomial with both large positive and negative coefficients.
   */
  @Test
  public void testDerivativeWithMixedCoefficients() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(7, 12000);
    polynomial.addTerm(-5, 6000);
    polynomial.addTerm(3, 3000);

    Polynomial derivative = polynomial.derivative();

    assertEquals("84000x^11999-30000x^5999+9000x^2999", derivative.toString());
  }

  /**
   * Tests the derivative of a polynomial where one of the terms has power zero (constant term).
   */
  @Test
  public void testDerivativeWithConstantTerm() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(8, 5000);
    polynomial.addTerm(4, 3000);
    polynomial.addTerm(10, 0);

    Polynomial derivative = polynomial.derivative();

    assertEquals("40000x^4999+12000x^2999", derivative.toString());
  }

  /**
   * Tests the derivative of a polynomial where all terms are large exponents and coefficients.
   */
  @Test
  public void testDerivativeWithAllLargeValues() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(10, 100000);
    polynomial.addTerm(15, 50000);
    polynomial.addTerm(20, 25000);

    Polynomial derivative = polynomial.derivative();

    assertEquals("1000000x^99999+750000x^49999+500000x^24999", derivative.toString());
  }

  /**
   * Tests the derivative of a polynomial with a single term.
   */
  @Test
  public void testDerivativeSingleTerm() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(3, 10000);

    Polynomial derivative = polynomial.derivative();

    assertEquals("30000x^9999", derivative.toString());
  }

  /**
   * Tests the derivative of a polynomial with only a constant term.
   */
  @Test
  public void testDerivativeWithOnlyConstantTerm() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(10, 0);

    Polynomial derivative = polynomial.derivative();

    assertEquals("0", derivative.toString());
  }

  /**
   * Tests the derivative of a zero polynomial.
   */
  @Test
  public void testDerivativeOfZeroPolynomial() {
    Polynomial polynomial = new SparsePolynomial();

    Polynomial derivative = polynomial.derivative();

    assertEquals("0", derivative.toString());
  }

  /**
   * Tests evaluating a zero polynomial at a given value of x.
   * The result should always be 0.
   */
  @Test
  public void testEvaluateZeroPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    assertEquals(0.0, polynomial.evaluate(2.0), 0.0001);
  }

  /**
   * Tests evaluating a constant polynomial at a given value of x.
   * The result should be the constant term.
   */
  @Test
  public void testEvaluateConstantPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals(5.0, polynomial.evaluate(3.0), 0.0001);
  }

  /**
   * Tests evaluating a multi-term polynomial at a given value of x.
   */
  @Test
  public void testEvaluateMultiTermPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(-1, 5);
    polynomial.addTerm(-2, 8);
    assertEquals(-528.0, polynomial.evaluate(2.0), 0.0001);
  }

  /**
   * Tests evaluating a polynomial at x = 0.
   * The result should be the constant term.
   */
  @Test
  public void testEvaluateWithZeroX() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(-2, 2);
    polynomial.addTerm(4, 0);
    assertEquals(4.0, polynomial.evaluate(0.0), 0.0001);
  }

  /**
   * Tests evaluating a polynomial at x = 0.
   * The result should be the constant term.
   */
  @Test
  public void testEvaluateWithNegativeX() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(-2, 2);
    polynomial.addTerm(4, 0);
    assertEquals(-401.72, polynomial.evaluate(-4.2), 0.0001);
  }

  /**
   * Tests if two SparsePolynomials are equal.
   */
  @Test
  public void testSparsePolynomialsAreEqual() {
    Polynomial poly1 = new SparsePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(5, 10000);
    poly1.addTerm(3, 5000);

    poly2.addTerm(5, 10000);
    poly2.addTerm(3, 5000);

    assertTrue(poly1.equals(poly2));
  }

  /**
   * Tests if two SparsePolynomials with different powers are not equal.
   */
  @Test
  public void testSparsePolynomialsDifferentPowers() {
    Polynomial poly1 = new SparsePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(5, 10000);
    poly1.addTerm(3, 5000);

    poly2.addTerm(5, 10000);
    poly2.addTerm(3, 4000);

    assertFalse(poly1.equals(poly2));
  }

  /**
   * Tests if two SparsePolynomials with the same power but different coefficients are not equal.
   */
  @Test
  public void testSparsePolynomialsDifferentCoefficients() {
    Polynomial poly1 = new SparsePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(5, 10000);
    poly1.addTerm(3, 5000);

    poly2.addTerm(5, 10000);
    poly2.addTerm(2, 5000);

    assertFalse(poly2.equals(poly1));
  }

  /**
   * Tests the string representation of a zero polynomial.
   */
  @Test
  public void testToStringZeroPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    assertEquals("0", polynomial.toString());
  }

  /**
   * Tests the string representation of a constant polynomial.
   */
  @Test
  public void testToStringConstantPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals("5", polynomial.toString());
  }

  /**
   * Tests the string representation of a single-term polynomial.
   */
  @Test
  public void testToStringSingleTermPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(3432, 2568);
    assertEquals("3432x^2568", polynomial.toString());
  }

  /**
   * Tests the string representation of a multi-term polynomial.
   */
  @Test
  public void testToStringMultiTermPolynomial() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(4, 35749);
    polynomial.addTerm(-5, 2453);
    polynomial.addTerm(1, 15353);
    polynomial.addTerm(6, 7575);
    assertEquals("4x^35749+1x^15353+6x^7575-5x^2453", polynomial.toString());
  }

  /**
   * Tests the string representation of a polynomial with zero coefficients for some terms.
   */
  @Test
  public void testToStringWithZeroCoefficients() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5, 3435);
    polynomial.addTerm(0, 2);
    polynomial.addTerm(-7, 1343445);
    assertEquals("-7x^1343445+5x^3435", polynomial.toString());
  }

  /**
   * Tests retrieving the coefficient of an existing power.
   */
  @Test
  public void testGetCoefficientForExistingPower() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(5754, 33453);
    assertEquals(5754, polynomial.getCoefficient(33453));
  }

  /**
   * Tests retrieving the coefficient of a non-existing power.
   * The result should be 0.
   */
  @Test
  public void testGetCoefficientForNonExistingPower() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(335, 2535);
    assertEquals(0, polynomial.getCoefficient(5));
  }

  /**
   * Tests retrieving the coefficient of the zero power (constant term).
   */
  @Test
  public void testGetCoefficientForZeroPower() {
    Polynomial polynomial = new SparsePolynomial();
    polynomial.addTerm(45343, 0);
    assertEquals(45343, polynomial.getCoefficient(0));  // Constant term
  }

  /**
   * Tests that two polynomials of the same type (SparsePolynomial)
   * with the same constant term (degree 0) have the same hashCode.
   */
  @Test
  public void testHashCodeSameTypeZeroDegrees() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(43, 0);

    Polynomial polynomial2 = new SparsePolynomial();
    polynomial2.addTerm(43, 0);

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of different types (SparsePolynomial and SimplePolynomial)
   * with the same constant term (degree 0) have the same hashCode.
   */
  @Test
  public void testHashCodeDifferentTypeZeroPowers() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(43, 0);

    Polynomial polynomial2 = new SimplePolynomial();
    polynomial2.addTerm(43, 0);

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of the same type (SparsePolynomial)
   * where one has a term with a zero coefficient
   * have the same hashCode when the zero coefficient term is ignored.
   */
  @Test
  public void testHashCodeSameTypeMissingCoefficients() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(0, 12);
    polynomial1.addTerm(25, 8);
    polynomial1.addTerm(7, 15);

    Polynomial polynomial2 = new SparsePolynomial();
    polynomial2.addTerm(25, 8);
    polynomial2.addTerm(7, 15);

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of different types (SparsePolynomial and SimplePolynomial)
   * where one has a term with a zero coefficient have the same hashCode
   * when the zero coefficient term is ignored.
   */
  @Test
  public void testHashCodeDifferentTypeMissingCoefficients() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(90, 5);
    polynomial1.addTerm(32, 4);
    polynomial1.addTerm(0, 4);

    Polynomial polynomial2 = new SimplePolynomial();
    polynomial2.addTerm(90, 5);
    polynomial2.addTerm(32, 4);

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two zero polynomials (SparsePolynomial with no terms) have the same hashCode.
   */
  @Test
  public void testHashCodeForZeroPolynomial() {
    Polynomial polynomial1 = new SparsePolynomial();
    Polynomial polynomial2 = new SparsePolynomial();

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of the same type (SparsePolynomial) with identical terms
   * have the same hashCode.
   */
  @Test
  public void testHashCodeSameTypePolynomials() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(673, 2689);
    polynomial1.addTerm(4750, 153);
    polynomial1.addTerm(248, 99);

    Polynomial polynomial2 = new SparsePolynomial();
    polynomial2.addTerm(673, 2689);
    polynomial2.addTerm(4750, 153);
    polynomial2.addTerm(248, 99);

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of different types (SparsePolynomial and SimplePolynomial)
   * with identical terms have the same hashCode.
   */
  @Test
  public void testHashCodeDifferentTypePolynomials() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(15, 30000);
    polynomial1.addTerm(-8, 2);
    polynomial1.addTerm(10, 1);


    Polynomial polynomial2 = new SimplePolynomial();
    polynomial2.addTerm(15, 30000);
    polynomial2.addTerm(-8, 2);
    polynomial2.addTerm(10, 1);

    assertEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of the same type (SparsePolynomial)
   * with different powers have different hashCodes.
   */
  @Test
  public void testHashCodeSameTypeDifferentPowers() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(9, 4);

    Polynomial polynomial2 = new SparsePolynomial();
    polynomial2.addTerm(10, 3);

    assertNotEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

  /**
   * Tests that two polynomials of different types (SparsePolynomial and SimplePolynomial)
   * with different powers have different hashCodes.
   */
  @Test
  public void testHashCodeDifferentTypeDifferentPowers() {
    Polynomial polynomial1 = new SparsePolynomial();
    polynomial1.addTerm(9, 4);

    Polynomial polynomial2 = new SimplePolynomial();
    polynomial2.addTerm(10, 3);

    assertNotEquals(polynomial1.hashCode(), polynomial2.hashCode());
  }

}
