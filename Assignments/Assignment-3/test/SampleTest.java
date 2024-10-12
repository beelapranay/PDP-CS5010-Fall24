import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SimplePolynomial;
import polynomial.SparsePolynomial;

import static org.junit.Assert.assertEquals;

/**
 * This class contains sample unit tests for the Polynomial class,
 * demonstrating multiplication and addition operations on both
 * SimplePolynomial and SparsePolynomial types.
 */
public class SampleTest {

  /**
   * A sample test to verify the multiplication of two polynomials,
   * one of type SparsePolynomial and one of type SimplePolynomial.
   * The test verifies that the coefficients of the resulting polynomial
   * match the expected values after multiplication.
   */
  @Test
  public void sampleTest() {
    int degree_a = 20000000;
    int degree_b = 30000000;
    Polynomial a = new SparsePolynomial();
    Polynomial b = new SimplePolynomial();

    a.addTerm(1, degree_a);
    a.addTerm(1, 0);

    b.addTerm(2, degree_b);
    b.addTerm(1, 0);

    Polynomial c = a.multiply(b);

    assertEquals("Coefficient does not match", 2, c.getCoefficient(degree_a + degree_b));
    assertEquals("Coefficient does not match", 2, c.getCoefficient(degree_b));
    assertEquals("Coefficient does not match", 1, c.getCoefficient(degree_a));
    assertEquals("Coefficient does not match", 1, c.getCoefficient(0));
  }
}
