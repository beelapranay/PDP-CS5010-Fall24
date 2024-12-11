package listadt;

import java.util.function.Function;

/**
 * This generic interface represents all the operations to be supported by a
 * list of objects of type T.
 */
public interface GenericListADTNode<T> {
  /**
   * Return the number of objects in this list.
   *
   * @return the size of this list
   */
  int count();

  /**
   * Add the given object to the front of this list and return this modified
   * list.
   *
   * @param b the object to be added
   * @return the resulting list
   */
  GenericListADTNode<T> addFront(T b);

  /**
   * Add the given object to the back of this list and return this modified list.
   *
   * @param b the object to be added
   * @return the resulting list
   */
  GenericListADTNode<T> addBack(T b);

  /**
   * A method that adds the given object at the given index in this list.
   * If index is 0, it means this object should be added to the front of this list.
   *
   * @param index the position to be occupied by this object, starting at 0
   * @param b     the object to be added
   * @return the resulting list
   * @throws IllegalArgumentException if an invalid index is passed
   */
  GenericListADTNode<T> add(int index, T b) throws IllegalArgumentException;

  /**
   * Remove the first instance of this object from the list.
   *
   * @param b the object to be removed
   * @return the resulting list
   */
  GenericListADTNode<T> remove(T b);

  /**
   * Get the object at the specified index, with 0 meaning the first object in
   * this list.
   *
   * @param index the specified index
   * @return the object at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  T get(int index) throws IllegalArgumentException;

  /**
   * A general map higher order function on nodes. This returns a list of
   * identical structure, but each data item of type T converted into R using
   * the provided converter method.
   *
   * @param converter the function needed to convert T into R
   * @param <R>       the type of the data in the returned list
   * @return the head of a list that is structurally identical to this list,
   *         but contains data of type R
   */
  <R> GenericListADTNode<R> map(Function<T, R> converter);
}
