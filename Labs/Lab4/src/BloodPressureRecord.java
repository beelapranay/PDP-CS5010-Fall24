/**
 * This interface represents a blood pressure record, defining methods to retrieve
 * and update blood pressure readings. It includes methods for accessing and modifying
 * both systolic and diastolic readings and the unique ID of the record.
 */
public interface BloodPressureRecord {

  /**
   * Returns the unique ID of the blood pressure record.
   *
   * @return the unique ID of the record
   */
  String getID();

  /**
   * Returns the systolic blood pressure reading.
   *
   * @return the systolic reading as a double
   */
  double getSystolicReading();

  /**
   * Returns the diastolic blood pressure reading.
   *
   * @return the diastolic reading as a double
   */
  double getDiastolicReading();

  /**
   * Updates the systolic blood pressure reading.
   * Throws an {@link IllegalArgumentException} if the systolic reading is invalid,
   * for example, if it is lower than the current diastolic reading.
   *
   * @param sys the new systolic reading to update
   * @throws IllegalArgumentException if the systolic reading is less than the diastolic reading
   */
  void updateSystolicReading(double sys) throws IllegalArgumentException;

  /**
   * Updates the diastolic blood pressure reading.
   * Throws an {@link IllegalArgumentException} if the diastolic reading is invalid,
   * for example, if it is higher than the current systolic reading.
   *
   * @param dias the new diastolic reading to update
   * @throws IllegalArgumentException if the diastolic reading is greater than the systolic reading
   */
  void updateDiastolicReading(double dias) throws IllegalArgumentException;

}
