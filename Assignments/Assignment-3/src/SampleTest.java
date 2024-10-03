import org.junit.Test;
import polynomial.Polynomial;
import polynomial.SimplePolynomial;

import static org.junit.Assert.assertEquals;

public class SampleTest {

    @Test
    public void sampleTest() {
        int degree_a = 20000000;
        int degree_b = 30000000;
        Polynomial a = new SimplePolynomial();
        Polynomial b = new SimplePolynomial();

        a.addTerm(1,degree_a);
        a.addTerm(1,0);

        b.addTerm(2,degree_b);
        b.addTerm(1,0);

        Polynomial c = a.multiply(b);

        assertEquals("Coefficient does not match",2,c.getCoefficient(degree_a+degree_b));
        assertEquals("Coefficient does not match",2,c.getCoefficient(degree_b));
        assertEquals("Coefficient does not match",1,c.getCoefficient(degree_a));
        assertEquals("Coefficient does not match",1,c.getCoefficient(0));


    }
}
