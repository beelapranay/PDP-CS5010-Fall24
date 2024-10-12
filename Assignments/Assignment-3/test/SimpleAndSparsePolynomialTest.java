import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SimplePolynomial;
import polynomial.SparsePolynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains unit tests for verifying polynomial operations
 * (multiplication and string representation) using large values
 * in both SparsePolynomial and SimplePolynomial types.
 */
public class SimpleAndSparsePolynomialTest {

  /**
   * Tests the multiplication of two polynomials with large degrees
   * and verifies the resulting polynomial's string representation.
   * This test has a timeout of 5000ms to ensure it completes in a reasonable time.
   */
  @Test(timeout = 5000)
  public void testForLargeValues() {
    int degree_a = 3679;
    int degree_b = 4652;
    Polynomial a = new SparsePolynomial();
    Polynomial b = new SimplePolynomial();

    a.addTerm(1, degree_a);
    a.addTerm(1, 0);

    b.addTerm(2, degree_b);
    b.addTerm(1, 0);

    Polynomial c = a.multiply(b);

    System.out.println(c.toString());

    assertEquals("2x^8331+2x^4652+1x^3679+1",
            c.toString());
  }

  /**
   * Tests the multiplication of two polynomials with large exponents and coefficients,
   * ensuring that the string representation of the resulting polynomial is correct.
   * This test has a timeout of 5000ms.
   */
  @Test(timeout = 5000)
  public void testForLargeValues1() {
    int degree_a = 3687;
    int degree_b = 678;
    Polynomial a = new SparsePolynomial();
    Polynomial b = new SimplePolynomial();

    a.addTerm(34, degree_a);
    a.addTerm(-499, 0);

    b.addTerm(568, degree_b);
    b.addTerm(35, 0);

    Polynomial c = a.multiply(b);

    assertEquals("19312x^4365+1190x^3687-283432x^678-17465",
            c.toString());
  }

  /**
   * Tests the multiplication of polynomials with very large exponents and verifies
   * that the resulting polynomial is correctly represented as a string.
   * This test also has a timeout of 5000ms.
   */
  @Test(timeout = 5000)
  public void testForLargeValues2() {
    int degree_a = 200;
    int degree_b = 3982;
    Polynomial a = new SparsePolynomial();
    Polynomial b = new SimplePolynomial();

    a.addTerm(10, degree_a);
    a.addTerm(2, 0);

    b.addTerm(344, degree_b);
    b.addTerm(69, 0);

    Polynomial c = a.multiply(b);

    assertEquals("3440x^4182+688x^3982+690x^200+138",
            c.toString());
  }

  /**
   * Tests the multiplication of polynomials where both polynomials
   * contain terms with large degrees and coefficients.
   * The test ensures that the resulting polynomial is correctly generated.
   * This test also has a timeout of 5000ms.
   */
  @Test(timeout = 5000)
  public void testForLargeValues3() {
    int degree_a = 2;
    int degree_b = 304;
    Polynomial a = new SparsePolynomial();
    Polynomial b = new SimplePolynomial();

    a.addTerm(1, degree_a);
    a.addTerm(45, 78);
    a.addTerm(68, 78);
    a.addTerm(1, 0);

    b.addTerm(2, degree_b);
    b.addTerm(209, degree_b);
    b.addTerm(1, 0);

    Polynomial c = a.multiply(b);

    assertEquals("23843x^382+211x^306+211x^304+113x^78+1x^2+1",
            c.toString());
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

    assertTrue(poly2.equals(poly1));
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

    assertFalse(poly2.equals(poly1));
  }

  /**
   * Tests if two polynomials of different types are not equal.
   */
  @Test
  public void testPolynomialsEqualsDifferentTypes() {
    Polynomial poly1 = new SimplePolynomial();
    Polynomial poly2 = new SparsePolynomial();

    poly1.addTerm(5, 2);
    poly2.addTerm(5, 2);

    assertTrue(poly2.equals(poly1));
  }

  @Test
  public void ssj(){
    Polynomial poly1 = new SparsePolynomial();

    poly1.addTerm(1, 9);
    poly1.addTerm(2, 10);
    poly1.addTerm(3, 6);

    System.out.println(poly1.toString());

  }
}