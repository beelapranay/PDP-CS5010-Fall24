import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a patient monitor. It monitor several blood pressure records,
 * specifically to see how many of them are going into hypertensive crisis.
 */

public class PatientMonitor {
  private List<BloodPressureRecord> bpRecordList;

  public PatientMonitor() {
    this.bpRecordList = new ArrayList<BloodPressureRecord>();
  }

  public void add(BloodPressureRecord t) {
    bpRecordList.add(t);
  }

  public void remove(BloodPressureRecord t) {
    bpRecordList.remove(t);
  }

  public int getNumberOfRecords() {
    return bpRecordList.size();
  }

  public boolean emergency() {
    int count = 0;
    for (BloodPressureRecord t: bpRecordList) {
      if ((t.getSystolicReading()>180) || (t.getDiastolicReading()>120)) {
        count +=1;
      }

    }
    return count>1;
  }

}
