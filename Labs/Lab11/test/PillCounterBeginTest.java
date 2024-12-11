import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *Represents the system utilizing the pill counter, simulated in a test but
 * typically integrated into a larger system managing conveyor belts for pill bottling.
 */
public class PillCounterBeginTest {

  /**
   * Represents a high-volume client of the pill counter, simulating a conveyor belt
   * bottling large quantities of pill bottles with varying capacities.
   */
  @Test
  public void usage() {
    PillCounter counter = new LoggingPillCounter();
    boolean result = conveyorBelt(counter);
    assertTrue(result);
  }

  private boolean conveyorBelt(PillCounter counter) {
    for (int bottle = 0; bottle < 100; bottle += 1) {
      for (int pill = 0; pill < 100; pill += 1) {
        counter.addPill(1);
      }
      assertEquals(100, counter.getPillCount());
      counter.reset();
    }

    for (int bottle = 0; bottle < 1000; bottle += 1) {
      for (int pill = 0; pill < 20; pill += 4) {
        counter.addPill(4);
      }
      assertEquals(20, counter.getPillCount());
      counter.reset();
    }

    for (int bottle = 0; bottle < 500; bottle += 1) {
      for (int pill = 0; pill < 200; pill += 2) {
        counter.addPill(2);
      }
      assertEquals(200, counter.getPillCount());
      counter.reset();
    }
    return true;
  }
}