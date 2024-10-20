package solution;

import java.util.function.Consumer;

/**
 * This node represents an empty node in the binary search tree (i.e. the
 * leaves)
 */
public class BSTEmptyNode<T extends Comparable<T>> implements BSTNode<T> {

  /**
   * Inserts a new element into the tree, converting the empty node into an element node.
   *
   * @param data the data to insert
   * @return a new BSTElementNode containing the inserted data
   */
  @Override
  public BSTNode<T> insert(T data) {
    return new BSTElementNode<>(data, new BSTEmptyNode<>(), new BSTEmptyNode<>());
  }

  /**
   * Throws an exception because an empty node has no minimum value.
   *
   * @return nothing, always throws exception
   * @throws NothingThereException if the tree is empty
   */
  @Override
  public T minimum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  /**
   * Throws an exception because an empty node has no maximum value.
   *
   * @return nothing, always throws exception
   * @throws NothingThereException if the tree is empty
   */
  @Override
  public T maximum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  /**
   * Returns false because an empty node cannot contain any data.
   *
   * @param data the data to search for
   * @return false
   */
  @Override
  public boolean contains(T data) {
    return false;
  }

  /**
   * Returns an empty string because an empty node has no data.
   *
   * @return an empty string
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Does nothing because an empty node has no data for preorder traversal.
   *
   * @param consumer the action to perform for each node
   */
  @Override
  public void preorder(Consumer<T> consumer) {
    // Does nothing because an empty node has no data for preorder traversal.
  }

  /**
   * Does nothing because an empty node has no data for postorder traversal.
   *
   * @param consumer the action to perform for each node
   */
  @Override
  public void postorder(Consumer<T> consumer) {
    // Does nothing because an empty node has no data for postorder traversal.
  }

  /**
   * Returns a new instance of BSTEmptyNode, as copying an empty node results in another empty node.
   *
   * @return a new BSTEmptyNode
   */
  @Override
  public BSTNode<T> copy() {
    return new BSTEmptyNode<>();
  }

  /**
   * Compares this empty node with another node, using double dispatch.
   *
   * @param other the other node to compare
   * @return the result of comparing the other node with this empty node
   */
  @Override
  public boolean same(BSTNode<T> other) {
    return other.sameEmpty(this);
  }

  /**
   * Returns false because an element node cannot be the same as an empty node.
   *
   * @param otherNode the element node to compare
   * @return false
   */
  @Override
  public boolean sameElement(BSTElementNode<T> otherNode) {
    return false;
  }

  /**
   * Returns true because two empty nodes are considered identical.
   *
   * @param otherNode the empty node to compare
   * @return true
   */
  @Override
  public boolean sameEmpty(BSTEmptyNode<T> otherNode) {
    return true;
  }

}
