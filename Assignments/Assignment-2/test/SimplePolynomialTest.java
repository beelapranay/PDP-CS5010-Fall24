import org.junit.Test;

import polynomial.SimplePolynomial;

import static org.junit.Assert.assertEquals;

public class SimplePolynomialTest {

  @Test
  public void testForConstructor(){
    SimplePolynomial polynomial = new SimplePolynomial();
    assertEquals("0", polynomial.toString());
  }

  @Test
  public void testAdd(){
    SimplePolynomial polynomial = new SimplePolynomial();
    polynomial.addTerm();
  }
}
