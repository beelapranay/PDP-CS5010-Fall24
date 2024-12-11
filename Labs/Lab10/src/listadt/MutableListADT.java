package listadt;

/**
 * Represents a mutable list of elements.
 * This interface extends {@link ListADT} and provides a method to obtain
 * an immutable version of the list.
 *
 * @param <T> the type of elements in this list
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * Returns an immutable version of this list.
   * Any changes made to the original mutable list will not affect the immutable list.
   *
   * @return an immutable version of this list
   */
  ImmutableListADT<T> getImmutableList();
}