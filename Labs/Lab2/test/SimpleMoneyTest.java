import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for SimpleMoney.
 */
public class SimpleMoneyTest {
  /**
   * Test for valid inputs.
   */
  @Test
  public void testValidInputs() {
    SimpleMoney money = new SimpleMoney(10, 99);
    assertEquals("$10.99", money.toString());
  }

  /**
   * Test for invalid dollar input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDollarInput() {
    SimpleMoney money = new SimpleMoney(-10, 99);
  }

  /**
   * Test for invalid cents input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCentsInput() {
    SimpleMoney money = new SimpleMoney(10, -99);
  }

  /**
   * Test for invalid cents and dollars inputs.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCurrencyInput() {
    SimpleMoney money = new SimpleMoney(-10, -99);
  }

  /**
   * Test for zero dollars input.
   */
  @Test
  public void testZeroDollarsCentsInput() {
    SimpleMoney money = new SimpleMoney(0, 100);
    assertEquals("$1.00", money.toString());
  }

  /**
   * Test for cents value equal to 100.
   */
  @Test
  public void testCentsValueHundred() {
    SimpleMoney money = new SimpleMoney(10, 100);
    assertEquals("$11.00", money.toString());
  }

  /**
   * Test for cents value greater than 100.
   */
  @Test
  public void testCentsOverflow() {
    SimpleMoney money = new SimpleMoney(5, 150);
    assertEquals("$6.50", money.toString());
  }

  /**
   * Test for checking the add method by passing the object.
   */
  @Test
  public void testAddMoneyObject() {
    SimpleMoney money1 = new SimpleMoney(5, 10);
    SimpleMoney money2 = new SimpleMoney(95, 27);
    money1.add(money2);
    assertEquals("$100.37", money1.toString());
  }

  /**
   * Test for checking the add method by passing the object
   * by overflowing the cents value.
   */
  @Test
  public void testAddMoneyObjectCentsOverflow() {
    SimpleMoney money1 = new SimpleMoney(0, 99);
    SimpleMoney money2 = new SimpleMoney(100, 100);
    money1.add(money2);
    assertEquals("$101.99", money1.toString());
  }

  /**
   * Test for checking the add method by passing values of dollars and cents.
   */
  @Test
  public void testAddMoneyMethod() {
    SimpleMoney money1 = new SimpleMoney(5, 10);
    money1.add(1, 50);
    assertEquals("$6.60", money1.toString());
  }

  /**
   * Test for checking the add method by passing values of dollars and cents
   * by overflowing the value of cents.
   */
  @Test
  public void testAddCentsOverflow() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    money1.add(1, 201);
    assertEquals("$13.51", money1.toString());
  }

  @Test
  public void testAddCentsHigherOverflow() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    money1.add(1, 291);
    assertEquals("$14.41", money1.toString());
  }

  /**
   * Test for checking the add object method for invalid value of dollars.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMoneyForNegativeDollars() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    money1.add(-1, 50);
  }

  /**
   * Test for checking the add object method for invalid value of cents.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddMoneyForNegativeCents() {
    SimpleMoney money1 = new SimpleMoney(10, 50);
    money1.add(1, -50);
  }

  /**
   * Test for checking the getDecimalValue function.
   */
  @Test
  public void testGetDecimalValue() {
    SimpleMoney money = new SimpleMoney(22, 79);
    assertEquals(22.79, money.getDecimalValue(), 0.001);

    SimpleMoney money2 = new SimpleMoney(0, 99);
    assertEquals(0.99, money2.getDecimalValue(), 0.001);

    SimpleMoney money3 = new SimpleMoney(0, 100);
    assertEquals(1.00, money3.getDecimalValue(), 0.001);
  }

  /**
   * Test for checking the toString function.
   */
  @Test
  public void testToString() {
    SimpleMoney money = new SimpleMoney(1, 105);
    assertEquals("$2.05", money.toString());
  }
}