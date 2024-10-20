package solution;

import java.util.function.Consumer;

/**
 * This class represents a data-containing node of the binary search tree.
 * It mutates on all relevant operations.
 */
public class BSTElementNode<T extends Comparable<T>> implements BSTNode<T> {
  private BSTNode<T> left;
  private BSTNode<T> right;
  private T data;

  /**
   * Constructs a new element node with given data, left, and right subtrees.
   *
   * @param data  the data for this node
   * @param left  the left subtree
   * @param right the right subtree
   */
  public BSTElementNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Inserts data into the tree and returns the updated tree.
   *
   * @param data the data to insert
   * @return the updated tree with the inserted data
   */
  @Override
  public BSTNode<T> insert(T data) {
    if (data.compareTo(this.data) < 0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data) > 0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  /**
   * Finds the minimum value in the tree.
   *
   * @return the minimum value
   */
  @Override
  public T minimum() {
    T minimum;

    try {
      minimum = this.left.minimum();
    } catch (NothingThereException e) {
      minimum = this.data;
    }
    return minimum;
  }

  /**
   * Finds the maximum value in the tree.
   *
   * @return the maximum value
   */
  @Override
  public T maximum() {
    T maximum;

    try {
      maximum = this.right.maximum();
    } catch (NothingThereException e) {
      maximum = this.data;
    }

    return maximum;
  }

  /**
   * Checks if the tree contains the specified data.
   *
   * @param data the data to search for
   * @return true if the tree contains the data, false otherwise
   */
  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult == 0) {
      return true;
    } else if (compareResult < 0) {
      return this.left.contains(data);
    } else {
      return this.right.contains(data);
    }
  }

  /**
   * Returns a string representation of the tree in-order.
   *
   * @return a string representing the tree
   */
  @Override
  public String toString() {
    String left;
    String right;
    String middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();

    if (left.length() > 0) {
      left = left + " ";
    }
    if (right.length() > 0) {
      right = " " + right;
    }
    return left + middle + right;
  }

  /**
   * Traverses the tree in pre-order and applies the given consumer to each node.
   *
   * @param consumer the action to apply to each node
   */
  @Override
  public void preorder(Consumer<T> consumer) {
    consumer.accept(this.data);

    if (this.left != null) {
      this.left.preorder(consumer);
    }

    if (this.right != null) {
      this.right.preorder(consumer);
    }
  }

  /**
   * Traverses the tree in post-order and applies the given consumer to each node.
   *
   * @param consumer the action to apply to each node
   */
  @Override
  public void postorder(Consumer<T> consumer) {
    if (this.left != null) {
      this.left.postorder(consumer);
    }

    if (this.right != null) {
      this.right.postorder(consumer);
    }

    consumer.accept(this.data);
  }

  /**
   * Creates a deep copy of the tree.
   *
   * @return a new tree that is a copy of this tree
   */
  @Override
  public BSTNode<T> copy() {
    return new BSTElementNode<>(
            this.data,
            this.left.copy(),
            this.right.copy()
    );
  }

  /**
   * Compares this element node with another node using double dispatch.
   *
   * @param other the other node to compare
   * @return true if both trees are identical, false otherwise
   */
  @Override
  public boolean same(BSTNode<T> other) {
    return other.sameElement(this);
  }

  /**
   * Compares this element node with another element node.
   *
   * @param otherNode the other element node to compare
   * @return true if both element nodes and their subtrees are identical
   */
  @Override
  public boolean sameElement(BSTElementNode<T> otherNode) {
    if (!this.data.equals(otherNode.data)) {
      return false;
    }

    return this.left.same(otherNode.left) && this.right.same(otherNode.right);
  }

  /**
   * Compares this element node with an empty node, always returning false.
   *
   * @param otherNode the empty node to compare
   * @return false, as an element node cannot be the same as an empty node
   */
  @Override
  public boolean sameEmpty(BSTEmptyNode<T> otherNode) {
    return false;
  }
}
