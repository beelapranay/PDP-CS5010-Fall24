/**
 * A decorator for the PillCounter that batches pill additions to reduce
 * the number of calls to the delegate's addPill method.
 */
public class PillBatchAddCounter extends PillCounterDecorator {
  private int pendingPills;

  /**
   * Constructs a PillBatchAddCounter with a given PillCounter delegate.
   *
   * @param delegate the PillCounter instance to decorate
   */
  public PillBatchAddCounter(PillCounter delegate) {
    super(delegate);
    pendingPills = 0;
  }

  /**
   * Overrides the addPill method to accumulate the count of pills
   * instead of immediately forwarding the call to the delegate.
   *
   * @param count the number of pills to add
   */
  @Override
  public void addPill(int count) {
    pendingPills += count;
  }

  /**
   * Overrides the getPillCount method to ensure that any pending pills
   * are added to the delegate before retrieving the current pill count.
   *
   * @return the total count of pills, including pending additions
   */
  @Override
  public int getPillCount() {
    if (pendingPills > 0) {
      delegate.addPill(pendingPills);
      pendingPills = 0;
    }
    return super.getPillCount();
  }

  /**
   * Overrides the reset method to finalize any pending additions
   * before delegating the reset operation to the decorated PillCounter.
   */
  @Override
  public void reset() {
    if (pendingPills > 0) {
      delegate.addPill(pendingPills);
      pendingPills = 0;
    }
    super.reset();
  }
}
