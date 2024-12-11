package listadt;

/**
 * Represents an immutable list of elements.
 * This interface extends {@link CommonListADT} and ensures that the list cannot be modified.
 * Provides a method to obtain a mutable version of the list if needed.
 *
 * @param <T> the type of elements in this list
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {

  /**
   * Returns a mutable version of this list.
   * Any changes made to the returned list will not affect the immutable list.
   *
   * @return a mutable version of this list
   */
  MutableListADT<T> getMutableList();
}