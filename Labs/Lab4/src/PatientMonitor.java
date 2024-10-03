import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a patient monitor that tracks multiple blood pressure records.
 * It monitors for hypertensive crisis events, specifically when patients have systolic readings
 * greater than 180 or diastolic readings greater than 120. It determines if more than one patient
 * is in a critical state.
 */
public class PatientMonitor implements Monitor {

  private List<BloodPressureRecord> bpRecordList;
  public Set<String> countedIds;

  /**
   * Constructs an empty {@code PatientMonitor} with no blood pressure records.
   */
  public PatientMonitor() {
    this.bpRecordList = new ArrayList<BloodPressureRecord>();
  }

  /**
   * Adds a blood pressure record to the monitor.
   *
   * @param t the blood pressure record to be added
   */
  public void add(BloodPressureRecord t) {
    bpRecordList.add(t);
  }

  /**
   * Removes a blood pressure record from the monitor.
   *
   * @param t the blood pressure record to be removed
   */
  public void remove(BloodPressureRecord t) {
    bpRecordList.remove(t);
  }

  /**
   * Returns the number of blood pressure records currently being monitored.
   *
   * @return the number of records in the monitor
   */
  public int getNumberOfRecords() {
    return bpRecordList.size();
  }

  /**
   * Checks if more than one unique patient is in a hypertensive crisis state.
   * A patient is considered to be in a crisis if their systolic reading exceeds 180 or
   * their diastolic reading exceeds 120.
   *
   * @return {@code true} if more than one patient is in crisis, otherwise {@code false}
   */
  public boolean emergency() {
    countedIds = new HashSet<String>();

    for (BloodPressureRecord t : bpRecordList) {
      if (countedIds.contains(t.getID())) {
        continue;
      } else if (t.getSystolicReading() > 180 || t.getDiastolicReading() > 120) {
        countedIds.add(t.getID());
      }
    }

    return countedIds.size() > 1;
  }
}
