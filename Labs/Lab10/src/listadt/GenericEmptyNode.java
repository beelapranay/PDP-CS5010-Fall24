package listadt;

import java.util.function.Function;

/**
 * Represents an empty node in the generic list implementation.
 * This is the base case for a recursive list structure, where the list has no elements.
 *
 * @param <T> the type of elements in the list
 */
public class GenericEmptyNode<T> implements GenericListADTNode<T> {

  /**
   * Returns the count of elements in this empty node, which is always 0.
   *
   * @return 0, as this node is empty
   */
  @Override
  public int count() {
    return 0;
  }

  /**
   * Adds an element to the front of this empty node, creating a new non-empty node.
   *
   * @param object the object to be added
   * @return a new GenericElementNode with the specified object as its element
   */
  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode<>(object, this);
  }

  /**
   * Adds an element to the back of this empty node, which is the same as adding to the front.
   *
   * @param object the object to be added
   * @return a new GenericElementNode with the specified object as its element
   */
  @Override
  public GenericListADTNode<T> addBack(T object) {
    return addFront(object);
  }

  /**
   * Adds an element at the specified index in this empty node.
   * Only index 0 is valid, as there are no elements in this node.
   *
   * @param index  the position where the element should be added
   * @param object the object to be added
   * @return a new GenericElementNode with the specified object if index is 0
   * @throws IllegalArgumentException if the index is not 0
   */
  @Override
  public GenericListADTNode<T> add(int index, T object) throws IllegalArgumentException {
    if (index == 0) {
      return addFront(object);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  /**
   * Removes an element from this empty node, which has no effect as the node is empty.
   *
   * @param object the object to remove (ignored in this case)
   * @return this node, as it remains empty
   */
  @Override
  public GenericListADTNode<T> remove(T object) {
    return this;
  }

  /**
   * Retrieves the element at the specified index.
   *
   * @param index the index of the element to retrieve
   * @return nothing, as this node is empty
   * @throws IllegalArgumentException always, since there are no elements to retrieve
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  /**
   * Applies a mapping function to each element in this empty node.
   *
   * @param converter the function to convert elements from type T to type R
   * @param <R>       the type of elements in the resulting list
   * @return a new empty node of type R, as this node is empty
   */
  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    return new GenericEmptyNode<>();
  }

  /**
   * Returns a string representation of this empty node, which is an empty string.
   *
   * @return an empty string, as this node has no elements
   */
  @Override
  public String toString() {
    return "";
  }
}