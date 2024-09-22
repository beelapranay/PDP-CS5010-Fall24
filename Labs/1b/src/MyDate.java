/**
 * MyDate class represents a date with day, month, and year, and provides a function advance to
 * or move back when number of days is given.
 */
public class MyDate {

  private int day;
  private int month;
  private int year;

  /**
   * Checks if a given year is a leap year.
   *
   * @param year The year to check.
   * @return true if the year is a leap year, false otherwise.
   */
  private boolean checkLeap(int year) {
    return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
  }

  /**
   * Checks if a given month has 31 days.
   *
   * @param month The month to check.
   * @return true is the month has 31 days, false otherwise.
   */
  private boolean checkForLengthyMonths(int month) {
    return month == 1 || month == 3 || month == 5 || month == 7
            || month == 8 || month == 10 || month == 12;
  }

  /**
   * Checks and validates if the given day, month, and year can form a valid date.
   *
   * @param day   The day to check.
   * @param month The month to check.
   * @param year  The year to check.
   * @return true if the date is valid, false otherwise.
   */
  private boolean isValidDate(int day, int month, int year) {

    boolean isValid = year >= 0 && month > 0 && month <= 12 && day > 0 && day <= 31;

    if (checkLeap(year) && month == 2) {
      if (day > 29) {
        isValid = false;
      }
    }

    if (!checkLeap(year) && month == 2) {
      if (day > 28) {
        isValid = false;
      }
    }

    if (!checkForLengthyMonths(month) && day > 30) {
      isValid = false;
    }

    return isValid;
  }

  /**
   * The constructor which will construct the object for MyDate if the given values of day, month,
   * year form a valid date.
   * If they do not form a valid date, an IllegalArgumentException is thrown.
   *
   * @param day   The day of the month (1-31).
   * @param month The month of the year (1-12).
   * @param year  The year.
   * @throws IllegalArgumentException if the date is invalid.
   */
  public MyDate(int day, int month, int year) throws IllegalArgumentException {
    if (!isValidDate(day, month, year)) {
      throw new IllegalArgumentException();
    } else {
      this.day = day;
      this.month = month;
      this.year = year;
    }
  }

  /**
   * Returns the number of days in a month, given the month and year as parameters to the function.
   *
   * @param month The month of the year (1-12).
   * @param year  The year.
   * @return The number of days in a specified month.
   */
  private int getNumberOfDaysInAMonth(int month, int year) {
    int numberOfDaysInAMonth;

    if (checkForLengthyMonths(month)) {
      numberOfDaysInAMonth = 31;
    } else if (!checkForLengthyMonths(month) && month != 2) {
      numberOfDaysInAMonth = 30;
    } else if (month == 2 && !checkLeap(year)) {
      numberOfDaysInAMonth = 28;
    } else {
      numberOfDaysInAMonth = 29;
    }

    return numberOfDaysInAMonth;
  }


  /**
   * Advances the given date by the number of days passed as a parameter to the function.
   * The value of the days parameter can be either positive (forward in time) or
   * negative (backward in time).
   *
   * @param days The number of days to advance (positive or negative).
   */
  public void advance(int days) {
    day += days;
    int daysInMonth = getNumberOfDaysInAMonth(month, year);

    while (day > daysInMonth) {
      day -= daysInMonth;
      month++;
      if (month > 12) {
        year++;
        month = 1;
      }

      daysInMonth = getNumberOfDaysInAMonth(month, year);
    }

    while (day <= 0) {
      month--;
      if (month < 1) {
        year--;
        month = 12;
      }

      daysInMonth = getNumberOfDaysInAMonth(month, year);
      day += daysInMonth;
    }
  }

  /**
   * Returns a string representation of the date in the format YYYY-MM-DD with the formatting zeros.
   *
   * @return The formatter date string.
   */
  @Override
  public String toString() {

    int day = this.day;
    int month = this.month;
    int year = this.year;

    String dayString = String.valueOf(day);
    String monthString = String.valueOf(month);
    String yearString = String.valueOf(year);

    if (day < 10) {
      dayString = String.format("%02d", day);
    }

    if (month < 10) {
      monthString = String.format("%02d", month);
    }

    if (year < 1000) {
      yearString = String.format("%04d", year);
    }

    return yearString + "-" + monthString + "-" + dayString;
  }
}
