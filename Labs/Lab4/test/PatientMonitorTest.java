import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains unit tests for the {@link PatientMonitor} class.
 * It verifies the correct behavior of adding/removing records and detecting emergencies.
 */
public class PatientMonitorTest {

  private PatientMonitor monitor;
  private BloodPressureRecord record1;
  private BloodPressureRecord record2;
  private BloodPressureRecord record3;

  /**
   * Sets up the initial conditions for each test. Creates a new {@code PatientMonitor} instance
   * and a few {@code BloodPressureRecord} objects.
   */
  @Before
  public void setup() {
    monitor = new PatientMonitor();
    record1 = new SingleBloodPressureRecord("123", 200, 300);
    record2 = new SingleBloodPressureRecord("456", 190, 130);
    record3 = new SingleBloodPressureRecord("789", 175, 115);
  }

  /**
   * Tests that records are added correctly and the count reflects the correct number.
   */
  @Test
  public void testAddRecords() {
    monitor.add(record1);
    monitor.add(record2);
    assertEquals(2, monitor.getNumberOfRecords());
  }

  /**
   * Tests that a record can be removed successfully from the monitor.
   */
  @Test
  public void testRemoveRecord() {
    monitor.add(record1);
    monitor.add(record2);
    monitor.remove(record1);
    assertEquals(1, monitor.getNumberOfRecords());
  }

  /**
   * Tests that the monitor starts empty with zero records.
   */
  @Test
  public void testEmptyMonitor() {
    assertEquals(0, monitor.getNumberOfRecords());
  }

  /**
   * Tests that the emergency condition is not triggered when only one critical record is added.
   */
  @Test
  public void testEmergencyNotTriggered() {
    monitor.add(record1);
    monitor.add(record3);
    assertFalse(monitor.emergency());
  }

  /**
   * Tests that the emergency condition is triggered when more than one critical record is added.
   */
  @Test
  public void testEmergencyTriggered() {
    monitor.add(record1);
    monitor.add(record2);
    monitor.add(record3);
    assertTrue(monitor.emergency());
  }

  /**
   * Tests that the emergency condition is not triggered when multiple records have the same ID.
   */
  @Test
  public void testEmergencyWithSameID() {
    BloodPressureRecord record4 = new SingleBloodPressureRecord("123", 200, 130);
    monitor.add(record1);
    monitor.add(record4);
    assertFalse(monitor.emergency());
  }

  /**
   * Tests that the emergency condition is only triggered once per unique ID,
   * even if multiple critical records are added for the same patient.
   */
  @Test
  public void testEmergencyOnlyOncePerID() {
    monitor.add(record1);
    BloodPressureRecord criticalRecord1
            = new SingleBloodPressureRecord("123", 200, 130);
    BloodPressureRecord criticalRecord2
            = new SingleBloodPressureRecord("456", 190, 130);
    monitor.add(criticalRecord1);
    monitor.add(criticalRecord2);
    assertTrue(monitor.emergency());
  }

}
