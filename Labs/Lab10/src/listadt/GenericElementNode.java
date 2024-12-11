package listadt;

import java.util.function.Function;

/**
 * Represents a non-empty node in a generic list.
 * This node contains an element and a reference to the rest of the list.
 *
 * @param <T> the type of element in this node
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  private final T object;
  private GenericListADTNode<T> rest;

  /**
   * Constructs a GenericElementNode with the specified object and the rest of the list.
   *
   * @param p    the object contained in this node
   * @param rest the rest of the list
   */
  public GenericElementNode(T p, GenericListADTNode<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  /**
   * Counts the number of elements in this node and the rest of the list.
   *
   * @return the size of the list starting from this node
   */
  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  /**
   * Adds an element to the front of the list and returns the new node.
   *
   * @param object the object to be added at the front
   * @return a new node containing the new element at the front
   */
  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode<>(object, this);
  }

  /**
   * Adds an element to the back of the list.
   *
   * @param object the object to be added at the back
   * @return this node, after updating the rest of the list
   */
  @Override
  public GenericListADTNode<T> addBack(T object) {
    this.rest = this.rest.addBack(object);
    return this;
  }

  /**
   * Adds an element at a specific index in the list.
   *
   * @param index  the position where the element should be added
   * @param object the object to be added
   * @return this node, after updating the rest of the list
   * @throws IllegalArgumentException if the index is invalid
   */
  @Override
  public GenericListADTNode<T> add(int index, T object) {
    if (index == 0) {
      return addFront(object);
    } else {
      this.rest = this.rest.add(index - 1, object);
      return this;
    }
  }

  /**
   * Removes the first occurrence of the specified object from the list.
   *
   * @param object the object to be removed
   * @return the updated list after removing the object
   */
  @Override
  public GenericListADTNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  /**
   * Retrieves the element at the specified index.
   *
   * @param index the index of the element to retrieve
   * @return the object at the specified index
   * @throws IllegalArgumentException if the index is invalid
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.object;
    }
    return this.rest.get(index - 1);
  }

  /**
   * Maps each element in this list to a new type using the specified converter function.
   *
   * @param converter the function to convert elements from type T to type R
   * @param <R>       the type of elements in the resulting list
   * @return a new list with elements of type R after applying the converter
   */
  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    return new GenericElementNode<>(
            converter.apply(this.object),
            this.rest.map(converter));
  }

  /**
   * Returns a string representation of the list starting from this node.
   *
   * @return a string representation of the list
   */
  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (!rest.isEmpty()) {
      return objstring + " " + rest;
    } else {
      return objstring;
    }
  }
}