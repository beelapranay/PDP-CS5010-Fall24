/**
 * This interface defines the operations for monitoring blood pressure records.
 * It allows adding, removing, checking the number of records, and determining
 * if an emergency situation exists based on the records.
 */
public interface Monitor {

  /**
   * Adds a new blood pressure record to the monitor.
   *
   * @param bpr the {@link BloodPressureRecord} to add
   */
  void add(BloodPressureRecord bpr);

  /**
   * Removes a blood pressure record from the monitor.
   *
   * @param bpr the {@link BloodPressureRecord} to remove
   */
  void remove(BloodPressureRecord bpr);

  /**
   * Returns the number of blood pressure records currently being monitored.
   *
   * @return the number of records in the monitor
   */
  int getNumberOfRecords();

  /**
   * Determines if an emergency situation exists. An emergency is defined
   * as having more than one critical blood pressure reading.
   *
   * @return {@code true} if there are multiple critical blood pressure records,
   *         otherwise {@code false}
   */
  boolean emergency();
}