import java.util.ArrayList;
import java.util.List;

/**
 * A decorator for the PillCounter that monitors the number of times pills
 * are added to the counter between resets.
 */
public class PillAddMonitor extends PillCounterDecorator {
  private final List<Integer> addCounts;
  private int currentCount;

  /**
   * Constructs a PillAddMonitor with a given PillCounter delegate.
   *
   * @param delegate the PillCounter instance to decorate
   */
  public PillAddMonitor(PillCounter delegate) {
    super(delegate);
    addCounts = new ArrayList<>();
    currentCount = 0;
  }

  /**
   * Overrides the addPill method to increment the current count
   * while delegating the actual addition to the decorated PillCounter.
   *
   * @param count the number of pills to add
   */
  @Override
  public void addPill(int count) {
    super.addPill(count);
    currentCount++;
  }

  /**
   * Overrides the reset method to store the current count and reset it
   * before delegating the reset operation to the decorated PillCounter.
   */
  @Override
  public void reset() {
    addCounts.add(currentCount);
    currentCount = 0;
    super.reset();
  }

  /**
   * Retrieves a list of all recorded counts of addPill calls between resets.
   *
   * @return a new List containing the add counts
   */
  public List<Integer> getAddCounts() {
    return new ArrayList<>(addCounts);
  }
}
