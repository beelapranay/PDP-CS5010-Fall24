import java.util.Objects;

/**
 * The {@code SingleBloodPressureRecord} class implements
 * the {@code BloodPressureRecord} interface and
 * represents a single blood pressure record with an ID, systolic reading, and diastolic reading.
 * The class provides methods to retrieve, update, and compare these records.
 */
public class SingleBloodPressureRecord implements BloodPressureRecord {
  private String id;
  private double sys;
  private double dias;

  /**
   * Constructs a {@code SingleBloodPressureRecord} with the specified ID, systolic,
   * and diastolic readings.
   *
   * @param id    the unique ID of the blood pressure record
   * @param sys   the systolic reading of the blood pressure
   * @param dias  the diastolic reading of the blood pressure
   * @throws IllegalArgumentException if the ID is null, empty, or if the systolic or
   *                                  diastolic readings are negative
   */
  public SingleBloodPressureRecord(String id, double sys, double dias)
          throws IllegalArgumentException {
    if (id == null || id.isEmpty() || sys < 0 || dias < 0) {
      throw new IllegalArgumentException();
    } else {
      this.id = id;
      this.sys = sys;
      this.dias = dias;
    }
  }

  /**
   * Retrieves the ID of the blood pressure record.
   *
   * @return the ID of the record
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * Retrieves the systolic reading of the blood pressure.
   *
   * @return the systolic reading
   */
  @Override
  public double getSystolicReading() {
    return this.sys;
  }

  /**
   * Retrieves the diastolic reading of the blood pressure.
   *
   * @return the diastolic reading
   */
  @Override
  public double getDiastolicReading() {
    return this.dias;
  }

  /**
   * Updates the systolic reading of the blood pressure.
   * The systolic reading must not be less than the diastolic reading.
   *
   * @param sys the new systolic reading
   * @throws IllegalArgumentException if the systolic reading is less than the current
   *                                  diastolic reading
   */
  @Override
  public void updateSystolicReading(double sys) throws IllegalArgumentException {
    if (sys < this.getDiastolicReading()) {
      throw new IllegalArgumentException();
    } else {
      this.sys = sys;
    }
  }

  /**
   * Updates the diastolic reading of the blood pressure.
   * The diastolic reading must not be greater than the systolic reading.
   *
   * @param dias the new diastolic reading
   * @throws IllegalArgumentException if the diastolic reading is greater than the current
   *                                  systolic reading
   */
  @Override
  public void updateDiastolicReading(double dias) throws IllegalArgumentException {
    if (dias > this.getSystolicReading()) {
      throw new IllegalArgumentException();
    } else {
      this.dias = dias;
    }
  }

  //  @Override
  //  public boolean equals(Object o) {
  //    if(o == this) {
  //      return true;
  //    }
  //
  //    if(!(o instanceof SingleBloodPressureRecord)) {
  //      return false;
  //    }
  //
  //    SingleBloodPressureRecord other = (SingleBloodPressureRecord) o;
  //
  //    return other.getID().equals(this.id)
  //            && this.getSystolicReading()-other.getDiastolicReading() < 1
  //            && this.getDiastolicReading()-other.getDiastolicReading() < 1;
  //  }


  /**
   * Compares this {@code SingleBloodPressureRecord} with another object for equality.
   * Two records are considered equal if they have the same ID, and their systolic
   * and diastolic readings
   * are approximately equal (rounded to the nearest integer).
   *
   * @param o the object to be compared with this record
   * @return {@code true} if the specified object is equal to this record; {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof SingleBloodPressureRecord)) {
      return false;
    }

    SingleBloodPressureRecord other = (SingleBloodPressureRecord) o;

    return other.getID().equals(this.id)
            && Math.round(this.getSystolicReading()) == Math.round(other.getSystolicReading())
            && Math.round(this.getDiastolicReading()) == Math.round(other.getDiastolicReading());
  }

  /**
   * Returns the hash code value for this {@code SingleBloodPressureRecord}.
   * The hash code is based on the ID, rounded systolic reading, and rounded diastolic reading.
   *
   * @return the hash code value of this record
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, Math.round(this.sys), Math.round(this.dias));
  }
}
