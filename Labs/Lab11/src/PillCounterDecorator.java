/**
 * A decorator for the PillCounter interface that allows additional behavior
 * to be dynamically added to a PillCounter instance. This class delegates all
 * calls to the wrapped PillCounter instance.
 */
public class PillCounterDecorator implements PillCounter {
  /**
   * The wrapped PillCounter instance to which method calls are delegated.
   */
  protected PillCounter delegate;

  /**
   * Constructs a PillCounterDecorator with a given PillCounter delegate.
   *
   * @param delegate the PillCounter instance to decorate
   */
  public PillCounterDecorator(PillCounter delegate) {
    this.delegate = delegate;
  }

  /**
   * Delegates the addition of pills to the wrapped PillCounter instance.
   *
   * @param count the number of pills to add
   */
  @Override
  public void addPill(int count) {
    delegate.addPill(count);
  }

  /**
   * Delegates the removal of a pill to the wrapped PillCounter instance.
   */
  @Override
  public void removePill() {
    delegate.removePill();
  }

  /**
   * Delegates the reset operation to the wrapped PillCounter instance.
   */
  @Override
  public void reset() {
    delegate.reset();
  }

  /**
   * Delegates the retrieval of the current pill count to the wrapped
   * PillCounter instance.
   *
   * @return the total count of pills tracked by the wrapped PillCounter
   */
  @Override
  public int getPillCount() {
    return delegate.getPillCount();
  }
}
