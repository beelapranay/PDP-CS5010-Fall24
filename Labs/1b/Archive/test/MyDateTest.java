import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the MyDate class. This class contains test cases to verify the
 * correctness of the MyDate class including valid and invalid date handling, leap
 * year calculations, and advancing or receding dates.
 */
public class MyDateTest {

  /**
   * Tests if a valid date is correctly represented.
   */
  @Test
  public void isValidDate() {
    MyDate myDate = new MyDate(15, 8, 2023);
    assertEquals("2023-08-15", myDate.toString());
  }

  /**
   * Tests if a valid leap year date (February 29) is correctly represented.
   */
  @Test
  public void isValidLeapYearDate() {
    MyDate myDate = new MyDate(29, 2, 2024);
    assertEquals("2024-02-29", myDate.toString());
  }

  /**
   * Tests if a valid non-leap year February date is correctly represented.
   */
  @Test
  public void isValidNonLeapYearFebruaryDate() {
    MyDate myDate = new MyDate(28, 2, 2023);
    assertEquals("2023-02-28", myDate.toString());
  }

  /**
   * Tests if an invalid non-leap year February date (February 29) throws
   * an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isInvalidNonLeapYearFebruaryDate() {
    MyDate myDate = new MyDate(29, 2, 2023);
  }

  /**
   * Tests if an invalid leap year February date (February 30) throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isInvalidLeapYearFebruaryDate() {
    MyDate myDate = new MyDate(30, 2, 2024);
  }

  /**
   * Tests if an invalid date in a month with 30 days (April 31) throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isInvalidLengthyMonth() {
    MyDate myDate = new MyDate(31, 4, 2023);
  }

  /**
   * Tests if an invalid month (13) throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isInvalidMonth() {
    MyDate myDate = new MyDate(30, 13, 2023);
  }

  /**
   * Tests if advancing the date by 9 days from September 10, 2024, correctly
   * results on September 19, 2024.
   */
  @Test
  public void isValidAdvanceForDays() {
    MyDate myDate = new MyDate(10, 9, 2024);
    myDate.advance(9);
    assertEquals("2024-09-19", myDate.toString());
  }

  /**
   * Tests if advancing the date by 55 days from September 10, 2024, correctly results
   * on November 4, 2024.
   */
  @Test
  public void isValidAdvanceForMonths() {
    MyDate myDate = new MyDate(10, 9, 2024);
    myDate.advance(55);
    assertEquals("2024-11-04", myDate.toString());
  }

  /**
   * Tests if advancing the date by 2024 days from September 10, 2024, correctly results
   * on March 27, 2030.
   */
  @Test
  public void isValidAdvanceForYears() {
    MyDate myDate = new MyDate(10, 9, 2024);
    myDate.advance(2024);
    assertEquals("2030-03-27", myDate.toString());
  }

  /**
   * Tests if advancing the date by 1 day from February 28, 2024, correctly results
   * on February 29, 2024 (leap year).
   */
  @Test
  public void isValidAdvanceForLeapYearFebruary() {
    MyDate myDate = new MyDate(28, 2, 2024);
    myDate.advance(1);
    assertEquals("2024-02-29", myDate.toString());
  }

  /**
   * Tests if receding the date by 20 days from September 10, 2024, correctly results
   * on August 21, 2024.
   */
  @Test
  public void isValidRecedeForDays() {
    MyDate myDate = new MyDate(10, 9, 2024);
    myDate.advance(-20);
    assertEquals("2024-08-21", myDate.toString());
  }

  /**
   * Tests if receding the date by 200 days from September 10, 2024, correctly results
   * on February 23, 2024.
   */
  @Test
  public void isValidRecedeForMonths() {
    MyDate myDate = new MyDate(10, 9, 2024);
    myDate.advance(-200);
    assertEquals("2024-02-23", myDate.toString());
  }

  /**
   * Tests if receding the date by 20240 days from September 10, 2024, correctly results
   * on April 12, 1969.
   */
  @Test
  public void isValidRecedeForYears() {
    MyDate myDate = new MyDate(10, 9, 2024);
    myDate.advance(-20240);
    assertEquals("1969-04-12", myDate.toString());
  }

  /**
   * Tests if receding the date by 1 day from March 1, 2024, correctly results
   * on February 29, 2024 (leap year).
   */
  @Test
  public void isValidRecedeForLeapYearFebruary() {
    MyDate myDate = new MyDate(1, 3, 2024);
    myDate.advance(-1);
    assertEquals("2024-02-29", myDate.toString());
  }

  /**
   * Tests if receding the date by 1 day from March 1, 2023, correctly results
   * on February 28, 2023 (non-leap year).
   */
  @Test
  public void isValidRecedeForNonLeapYearFebruary() {
    MyDate myDate = new MyDate(1, 3, 2023);
    myDate.advance(-1);
    assertEquals("2023-02-28", myDate.toString());
  }
}