import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for {@code SingleBloodPressureRecord}.
 * This class contains unit tests to validate the behavior and
 * logic of the {@code SingleBloodPressureRecord} class.
 */
public class SingleBloodPressureRecordTest {

  /**
   * Tests the constructor when the ID is invalid (null).
   * Expects an {@code IllegalArgumentException} to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidID() {
    new SingleBloodPressureRecord(null, 120, 80);
  }

  /**
   * Tests the constructor when the systolic value is invalid (negative).
   * Expects an {@code IllegalArgumentException} to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidSys() {
    new SingleBloodPressureRecord("123", -120, 80);
  }

  /**
   * Tests the constructor when the diastolic value is invalid (negative).
   * Expects an {@code IllegalArgumentException} to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidDias() {
    new SingleBloodPressureRecord("123", 120, -80);
  }

  /**
   * Tests the constructor with valid inputs.
   * Validates that the ID, systolic, and diastolic readings are correctly set.
   */
  @Test
  public void testConstructorValidInput() {
    SingleBloodPressureRecord record = new SingleBloodPressureRecord("123", 120, 80);
    assertEquals("123", record.getID());
    assertEquals(120, record.getSystolicReading(), 0.01);
    assertEquals(80, record.getDiastolicReading(), 0.01);
  }

  /**
   * Tests that updating the systolic reading with an invalid value
   * (less than diastolic) throws an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateSystolicInvalid() {
    SingleBloodPressureRecord record = new SingleBloodPressureRecord("123", 120, 80);
    record.updateSystolicReading(70); // systolic < diastolic
  }

  /**
   * Tests that updating the systolic reading with a valid value works correctly.
   */
  @Test
  public void testUpdateSystolicValid() {
    SingleBloodPressureRecord record = new SingleBloodPressureRecord("123", 120, 80);
    record.updateSystolicReading(130);
    assertEquals(130, record.getSystolicReading(), 0.01);
  }

  /**
   * Tests that updating the diastolic reading with an invalid value
   * (greater than systolic) throws an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateDiastolicInvalid() {
    SingleBloodPressureRecord record = new SingleBloodPressureRecord("123", 120, 80);
    record.updateDiastolicReading(130); // diastolic > systolic
  }

  /**
   * Tests that updating the diastolic reading with a valid value works correctly.
   */
  @Test
  public void testUpdateDiastolicValid() {
    SingleBloodPressureRecord record = new SingleBloodPressureRecord("123", 120, 80);
    record.updateDiastolicReading(70);
    assertEquals(70, record.getDiastolicReading(), 0.01);
  }

  /**
   * Tests that the equals method returns true when comparing the same object.
   */
  @Test
  public void testEqualsSameObject() {
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("123", 120, 80);
    assertTrue(record1.equals(record1));
  }

  /**
   * Tests that the equals method returns true when comparing two objects with the same values.
   */
  @Test
  public void testEqualsDifferentObjectSameValues() {
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("123", 120, 80);
    SingleBloodPressureRecord record2 = new SingleBloodPressureRecord("123", 120, 80);
    assertTrue(record1.equals(record2));
  }

  /**
   * Tests that the equals method returns false when comparing two objects with different values.
   */
  @Test
  public void testEqualsDifferentObjectDifferentValues() {
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("123", 120, 80);
    SingleBloodPressureRecord record2 = new SingleBloodPressureRecord("123", 130, 90);
    assertFalse(record1.equals(record2));
  }

  /**
   * Tests that two objects with the same values return the same hash code.
   */
  @Test
  public void testHashCodeConsistency() {
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("123", 120, 80);
    SingleBloodPressureRecord record2 = new SingleBloodPressureRecord("123", 120, 80);
    assertEquals(record1.hashCode(), record2.hashCode());
  }

  /**
   * Tests that two objects with different values return different hash codes.
   */
  @Test
  public void testHashCodeDifferentForDifferentObjects() {
    SingleBloodPressureRecord record1 = new SingleBloodPressureRecord("123", 120, 80);
    SingleBloodPressureRecord record2 = new SingleBloodPressureRecord("456", 130, 85);
    assertNotEquals(record1.hashCode(), record2.hashCode());
  }
}
