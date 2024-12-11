package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a mutable list.
 * It implements the {@link MutableListADT} interface by adapting
 * {@link ListADTImpl} to support mutable operations.
 *
 * @param <T> the type of elements in this list
 */
public class MutableListADTImpl<T> implements MutableListADT<T> {
  private final ListADTImpl<T> internalList;

  /**
   * Constructs an empty mutable list.
   */
  public MutableListADTImpl() {
    this.internalList = new ListADTImpl<>();
  }

  /**
   * Returns an immutable version of this list.
   * Any changes made to this mutable list will not affect the returned immutable list.
   *
   * @return an immutable version of this list
   */
  @Override
  public ImmutableListADT<T> getImmutableList() {
    ImmutableListADTImpl.Builder<T> builder = new ImmutableListADTImpl.Builder<>();
    for (int i = 0; i < internalList.getSize(); i++) {
      builder.addBack(internalList.get(i));
    }
    return builder.build();
  }

  /**
   * Adds an element to the front of the list.
   *
   * @param element the element to add to the front
   */
  @Override
  public void addFront(T element) {
    internalList.addFront(element);
  }

  /**
   * Adds an element to the back of the list.
   *
   * @param element the element to add to the back
   */
  @Override
  public void addBack(T element) {
    internalList.addBack(element);
  }

  /**
   * Adds an element at the specified index in the list.
   *
   * @param index   the position where the element should be added
   * @param element the element to add
   * @throws IllegalArgumentException if the index is invalid
   */
  @Override
  public void add(int index, T element) {
    internalList.add(index, element);
  }

  /**
   * Removes the first occurrence of the specified element from the list.
   *
   * @param element the element to remove
   */
  @Override
  public void remove(T element) {
    internalList.remove(element);
  }

  /**
   * Applies a mapping function to each element of the list, returning a new mutable list
   * with the mapped elements.
   *
   * @param converter the function to convert elements from type T to type R
   * @param <R>       the type of elements in the resulting list
   * @return a new mutable list with each element converted using the provided function
   */
  @Override
  public <R> MutableListADT<R> map(Function<T, R> converter) {
    ListADTImpl<R> mappedList = (ListADTImpl<R>) internalList.map(converter);
    return new MutableListADTImpl<>(mappedList);
  }

  /**
   * Returns the number of elements in the list.
   *
   * @return the size of the list
   */
  @Override
  public int getSize() {
    return internalList.getSize();
  }

  /**
   * Retrieves the element at the specified index in the list.
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
   * Constructs a new mutable list with the specified internal list.
   *
   * @param internalList the internal list to initialize this mutable list with
   */
  private <R> MutableListADTImpl(ListADTImpl<R> internalList) {
    this.internalList = (ListADTImpl<T>) internalList;
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
}