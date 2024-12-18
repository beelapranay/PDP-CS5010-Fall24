import org.junit.Test;

import polynomial.SimplePolynomial;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the SimplePolymial class.
 */
public class SimplePolynomialTest {

  @Test
  public void testForConstructor(){
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals("0", polynomial.toString());
  }

  @Test
  public void testAddTermZeroValues(){
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(0, 0);

    assertEquals("0", polynomial.toString());
  }

  @Test
  public void testAddTermPositiveValues(){
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(4, 5);
    polynomial.addTerm(6, 7);

    assertEquals("6x^7+4x^5+2x^3", polynomial.toString());
  }

  @Test
  public void testAddTermNegativeValues(){
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-2, 3);
    polynomial.addTerm(-4, 5);
    polynomial.addTerm(-6, 7);

    assertEquals("-6x^7-4x^5-2x^3", polynomial.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddTermException(){
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(4, -5);
    polynomial.addTerm(6, 7);
  }

  @Test
  public void testAddMethodPositiveValues() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(6, 7);
    polynomial.addTerm(1, 4);

    otherPolynomial.addTerm(100, 7);
    otherPolynomial.addTerm(7, 10);

    polynomial.add(otherPolynomial);

    assertEquals("7x^10+106x^7+1x^4+2x^3", polynomial.toString());
  }

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

    polynomial.add(otherPolynomial);

    assertEquals("7x^10-4x^7+1x^4", polynomial.toString());
  }

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

    polynomial.add(otherPolynomial);

    assertEquals("-7x^10-16x^7-1x^4-4x^3-1238", polynomial.toString());
  }

  @Test
  public void testMultiplyMethodZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    SimplePolynomial otherPolynomial = new SimplePolynomial();

    polynomial.addTerm(2, 3);
    polynomial.addTerm(1, 4);
    polynomial.addTerm(20, 0);

    polynomial.multiply(otherPolynomial);

    assertEquals("0",
            polynomial.toString());
  }

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

    polynomial.multiply(otherPolynomial);

    assertEquals("10x^9+37x^8+83x^7+98x^6+200x^5+350x^4+1000x^3+200",
            polynomial.toString());
  }

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

    polynomial.multiply(otherPolynomial);

    assertEquals("42x^17-53x^14-14x^13-10x^11-108x^10+142x^7-4x^6-10x^4-20x^3+200",
            polynomial.toString());
  }

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

    polynomial.multiply(otherPolynomial);

    assertEquals("42x^17+67x^14+14x^13+10x^11+172x^10+262x^7+4x^6+10x^4+60x^3+200",
            polynomial.toString());
  }

  @Test
  public void testZeroPolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();

    polynomial.derivative();

    assertEquals("0", polynomial.toString());
  }

  @Test
  public void testConstantDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);

    polynomial.derivative();

    assertEquals("0", polynomial.toString());
  }

  @Test
  public void testSingleTermLinearDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 1);

    polynomial.derivative();

    assertEquals("5", polynomial.toString());
  }

  @Test
  public void testSingleTermHighDegreeDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 3);
    polynomial.derivative();
    assertEquals("12x^2", polynomial.toString());
  }

  @Test
  public void testMultiTermPolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 4);
    polynomial.addTerm(-5, 3);
    polynomial.addTerm(2, 1);
    polynomial.addTerm(-10, 0);
    polynomial.derivative();
    assertEquals("12x^3-15x^2+2", polynomial.toString());
  }

  @Test
  public void testNegativeCoefficientsDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-3, 4);
    polynomial.addTerm(7, 3);
    polynomial.derivative();
    assertEquals("-12x^3+21x^2", polynomial.toString());
  }

  @Test
  public void testSparsePolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 5);
    polynomial.addTerm(-3, 2);
    polynomial.derivative();
    assertEquals("20x^4-6x", polynomial.toString());
  }

  @Test
  public void testZeroCoefficientsDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(0, 2);
    polynomial.addTerm(-2, 1);
    polynomial.derivative();
    assertEquals("15x^2-2", polynomial.toString());
  }

  @Test
  public void testHighDegreePolynomialDerivative() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(1, 100);
    polynomial.derivative();
    assertEquals("100x^99", polynomial.toString());
  }

  @Test
  public void testZeroPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals(-1, polynomial.getDegree());
  }

  @Test
  public void testConstantPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals(0, polynomial.getDegree());
  }

  @Test
  public void testSingleTermPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    assertEquals(3, polynomial.getDegree());
  }

  @Test
  public void testMultiTermPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 4);
    polynomial.addTerm(-5, 2);
    polynomial.addTerm(7, 1);
    assertEquals(4, polynomial.getDegree());
  }

  @Test
  public void testNegativeCoefficientPolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-5, 6);
    polynomial.addTerm(2, 3);
    assertEquals(6, polynomial.getDegree());
  }

  @Test
  public void testSparsePolynomialDegree() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 8);
    polynomial.addTerm(0, 5);
    polynomial.addTerm(-3, 2);
    assertEquals(8, polynomial.getDegree());
  }

  @Test
  public void testEvaluateZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals(0.0, polynomial.evaluate(2.0), 0.0001);
  }

  @Test
  public void testEvaluateConstantPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals(5.0, polynomial.evaluate(3.0), 0.0001);
  }

  @Test
  public void testEvaluateSingleTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 2);
    assertEquals(12.0, polynomial.evaluate(2.0), 0.0001);
  }

  @Test
  public void testEvaluateMultiTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(2, 3);
    polynomial.addTerm(-1, 1);
    polynomial.addTerm(4, 0);
    assertEquals(18.0, polynomial.evaluate(2.0), 0.0001);
  }

  @Test
  public void testEvaluateNegativeCoefficients() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-3, 2);
    polynomial.addTerm(1, 1);
    assertEquals(-10.0, polynomial.evaluate(2.0), 0.0001);
  }

  @Test
  public void testEvaluateWithZeroX() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(-2, 2);
    polynomial.addTerm(4, 0);
    assertEquals(4.0, polynomial.evaluate(0.0), 0.0001);
  }

  @Test
  public void testGetCoefficientForExistingPower() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    assertEquals(5, polynomial.getCoefficient(3));
  }

  @Test
  public void testGetCoefficientForNonExistingPower() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 2);
    assertEquals(0, polynomial.getCoefficient(5));  // Power 5 doesn't exist
  }

  @Test
  public void testGetCoefficientForZeroPower() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 0);
    assertEquals(4, polynomial.getCoefficient(0));  // Constant term
  }

  @Test
  public void testGetCoefficientForNegativeCoefficient() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-7, 2);
    assertEquals(-7, polynomial.getCoefficient(2));  // Negative coefficient
  }

  @Test
  public void testGetCoefficientForZeroCoefficient() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(0, 3);
    assertEquals(0, polynomial.getCoefficient(3));  // Explicit zero coefficient
  }

  @Test
  public void testToStringZeroPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals("0", polynomial.toString());
  }

  @Test
  public void testToStringConstantPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 0);
    assertEquals("5", polynomial.toString());
  }

  @Test
  public void testToStringSingleTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(3, 2);
    assertEquals("3x^2", polynomial.toString());
  }

  @Test
  public void testToStringMultiTermPolynomial() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(4, 3);
    polynomial.addTerm(-5, 2);
    polynomial.addTerm(1, 1);
    polynomial.addTerm(6, 0);
    assertEquals("4x^3-5x^2+1x+6", polynomial.toString());
  }

  @Test
  public void testToStringNegativeCoefficients() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(-3, 2);
    polynomial.addTerm(2, 1);
    assertEquals("-3x^2+2x", polynomial.toString());
  }

  @Test
  public void testToStringWithZeroCoefficients() {
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm(5, 3);
    polynomial.addTerm(0, 2);
    polynomial.addTerm(-7, 1);
    assertEquals("5x^3-7x", polynomial.toString());
  }

}
