package listadt;

import java.util.function.Function;

/**
 * Represents an immutable list implementation based on {@link ListADTImpl}.
 * This class provides an immutable wrapper over a mutable list, ensuring
 * that any operations that would modify the list return a new list instance
 * instead of changing the current one.
 *
 * @param <T> the type of elements in this list
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  private final ListADTImpl<T> internalList;

  /**
   * Constructs an immutable list with the specified internal list.
   *
   * @param internalList the internal list to wrap as immutable
   */
  private ImmutableListADTImpl(ListADTImpl<T> internalList) {
    this.internalList = internalList;
  }

  /**
   * Constructs an empty immutable list.
   */
  public ImmutableListADTImpl() {
    this.internalList = new ListADTImpl<>();
  }

  /**
   * Returns a new immutable list with the specified element added to the back.
   *
   * @param element the element to add to the back of the list
   * @return a new immutable list containing all previous elements and the new element
   */
  private ImmutableListADTImpl<T> addBack(T element) {
    ListADTImpl<T> newList = new ListADTImpl<>();
    for (int i = 0; i < internalList.getSize(); i++) {
      newList.addBack(internalList.get(i));
    }
    newList.addBack(element);
    return new ImmutableListADTImpl<>(newList);
  }

  /**
   * Returns the size of the list.
   *
   * @return the number of elements in this list
   */
  @Override
  public int getSize() {
    return internalList.getSize();
  }

  /**
   * Retrieves the element at the specified index.
   *
   * @param index the index of the element to retrieve
   * @return the element at the given index
   * @throws IllegalArgumentException if the index is invalid
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    return internalList.get(index);
  }

  /**
   * Applies a mapping function to each element of the list, returning a new
   * immutable list with the mapped elements.
   *
   * @param converter the function to convert elements from type T to type R
   * @param <R>       the type of elements in the resulting list
   * @return a new immutable list with each element converted using the provided function
   */
  @Override
  public <R> ImmutableListADTImpl<R> map(Function<T, R> converter) {
    Builder<R> builder = new Builder<>();
    for (int i = 0; i < internalList.getSize(); i++) {
      builder.addBack(converter.apply(internalList.get(i)));
    }
    return builder.build();
  }

  /**
   * Throws an UnsupportedOperationException as this list is immutable.
   *
   * @return nothing, as this operation is unsupported
   * @throws UnsupportedOperationException always, since this list is immutable
   */
  @Override
  public MutableListADT<T> getMutableList() {
    throw new UnsupportedOperationException("This list is immutable");
  }

  /**
   * Returns a string representation of the list.
   *
   * @return a string representation of the elements in the list
   */
  @Override
  public String toString() {
    return internalList.toString();
  }

  /**
   * Builder class to create instances of {@link ImmutableListADTImpl}.
   * This builder allows adding elements to the back and then building an
   * immutable list with the added elements.
   *
   * @param <T> the type of elements in the list to build
   */
  public static class Builder<T> {
    private final ListADTImpl<T> tempInternalList;

    /**
     * Constructs an empty builder.
     */
    public Builder() {
      this.tempInternalList = new ListADTImpl<>();
    }

    /**
     * Adds an element to the back of the list in the builder.
     *
     * @param element the element to add
     */
    public void addBack(T element) {
      tempInternalList.addBack(element);
    }

    /**
     * Builds and returns an immutable list with the elements added to the builder.
     *
     * @return a new immutable list containing the added elements
     */
    public ImmutableListADTImpl<T> build() {
      return new ImmutableListADTImpl<>(tempInternalList);
    }
  }
}