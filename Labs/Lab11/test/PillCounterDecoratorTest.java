import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test class for PillCounterDecorator.
 */
public class PillCounterDecoratorTest {

  /**
   * Test that the decorator delegates the addPill call correctly.
   */
  @Test
  public void testAddPill() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(5);
    assertEquals(5, decorator.getPillCount());
  }

  /**
   * Test that multiple addPill calls are correctly delegated.
   */
  @Test
  public void testMultipleAddPill() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(3);
    decorator.addPill(2);
    assertEquals(5, decorator.getPillCount());
  }

  /**
   * Test that the decorator delegates the removePill call correctly.
   */
  @Test
  public void testRemovePill() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(5);
    decorator.removePill();
    assertEquals(4, decorator.getPillCount());
  }

  /**
   * Test that multiple removePill calls work as expected.
   */
  @Test
  public void testMultipleRemovePill() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(5);
    decorator.removePill();
    decorator.removePill();
    assertEquals(3, decorator.getPillCount());
  }

  /**
   * Test that removePill does not go below 0.
   */
  @Test
  public void testRemovePillBelowZero() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.removePill();
    assertEquals(0, decorator.getPillCount());
  }

  /**
   * Test that the decorator correctly resets the pill count.
   */
  @Test
  public void testReset() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(10);
    decorator.reset();
    assertEquals(0, decorator.getPillCount());
  }

  /**
   * Test a sequence of add, remove, and reset operations.
   */
  @Test
  public void testSequenceOperations() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(10);
    decorator.removePill();
    decorator.addPill(5);
    decorator.reset();
    decorator.addPill(3);
    assertEquals(3, decorator.getPillCount());
  }

  /**
   * Test that the pill count is delegated correctly after multiple resets.
   */
  @Test
  public void testMultipleResets() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(5);
    decorator.reset();
    decorator.addPill(3);
    decorator.reset();
    decorator.addPill(7);
    assertEquals(7, decorator.getPillCount());
  }

  /**
   * Test a large number of pill additions and removals to ensure stability.
   */
  @Test
  public void testLargeNumberOperations() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    for (int i = 0; i < 1000; i++) {
      decorator.addPill(1);
    }
    for (int i = 0; i < 500; i++) {
      decorator.removePill();
    }
    assertEquals(500, decorator.getPillCount());
  }

  /**
   * Test that a decorated counter behaves the same as the base counter.
   */
  @Test
  public void testDecoratorBehaviorConsistency() {
    PillCounter baseCounter = new LoggingPillCounter();
    PillCounter decorator = new PillCounterDecorator(baseCounter);

    decorator.addPill(10);
    decorator.removePill();
    decorator.reset();

    assertEquals(baseCounter.getPillCount(), decorator.getPillCount());
  }
}
