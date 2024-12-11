/**
 * This interface represents a counter for pills. Objects that
 * implement this interface are used in software that interface
 * with machines that fill pills into bottles.
 */
public interface PillCounter {

  /**
   * Add the specific number of pills to this counter. This method
   * is general enough to work with machines with different pill-filling
   * capacities.
   *
   * @param count the number of pills to add
   */
  void addPill(int count);

  /**
   * Remove a pill from this counter. This method is called in case
   * a malfunction in the hardware is detected, and it dispenses too
   * many pills. Only one pill may be removed at a time.
   */
  void removePill();

  /**
   * Reset the counter to 0.
   */
  void reset();

  /**
   * Return how many pills have been counted so far.
   *
   * @return the total count of pills currently tracked by the counter
   */
  int getPillCount();
}
