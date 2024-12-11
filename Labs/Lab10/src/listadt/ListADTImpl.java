package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list.
 * It specifically implements the {@link ListADT} interface.
 * This class provides operations to add, remove, and retrieve elements in the list.
 *
 * @param <T> the type of elements in this list
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  /**
   * Constructs an empty list with a {@link GenericEmptyNode} as the head.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode<>();
  }

  /**
   * Constructs a list with the specified head node.
   *
   * @param head the head node of the list
   */
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Adds an element to the front of the list.
   *
   * @param b the element to add to the front
   */
  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  /**
   * Adds an element to the back of the list.
   *
   * @param b the element to add to the back
   */
  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  /**
   * Adds an element at a specific index in the list.
   *
   * @param index the position where the element should be added
   * @param b     the element to add
   * @throws IllegalArgumentException if the index is invalid
   */
  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  /**
   * Returns the number of elements in the list.
   *
   * @return the size of the list
   */
  @Override
  public int getSize() {
    return head.count();
  }

  /**
   * Removes the first occurrence of the specified element from the list.
   *
   * @param b the element to remove
   */
  @Override
  public void remove(T b) {
    head = head.remove(b);
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
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    }
    throw new IllegalArgumentException("Invalid index");
  }

  /**
   * Applies a mapping function to each element of the list, returning a new list
   * with the mapped elements.
   *
   * @param converter the function to convert elements from type T to type R
   * @param <R>       the type of elements in the resulting list
   * @return a new list with each element converted using the provided function
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl<>(head.map(converter));
  }

  /**
   * Returns a string representation of the list.
   *
   * @return a string representation of the elements in the list
   */
  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}