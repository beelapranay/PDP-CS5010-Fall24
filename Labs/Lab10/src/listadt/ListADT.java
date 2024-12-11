package listadt;

/**
 * This interface represents a generic list.
 *
 * @param <T> the type of elements in this list
 */
public interface ListADT<T> extends CommonListADT<T> {
  /**
   * Add an object to the front of this list.
   *
   * @param b the object to be added to the front of this list
   */
  void addFront(T b);

  /**
   * Add an object to the back of this list (so it is the last object in the
   * list.
   *
   * @param b the object to be added to teh back of this list
   */
  void addBack(T b);

  /**
   * Add an object to this list so that it occupies the provided index. Index
   * begins with 0.
   *
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  void add(int index, T b);


  /**
   * Remove the first instance of this object from this list.
   *
   * @param b the object to be removed
   */
  void remove(T b);
}
