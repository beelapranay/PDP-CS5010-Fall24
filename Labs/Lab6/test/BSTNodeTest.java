
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import solution.BSTEmptyNode;
import solution.BSTNode;
import solution.NothingThereException;

/**
 * Test class for verifying the functionality of a binary search tree (BSTNode) implementation.
 * Includes tests for insertion, traversal, minimum/maximum value retrieval,
 * and tree structure checks.
 */
public class BSTNodeTest {

  /**
   * Test to verify if the tree correctly inserts multiple random values and
   * that the in-order traversal (toString) matches the expected sorted output.
   */
  @Test
  public void testInsertions() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    Set<Integer> expected = new TreeSet<>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      root = root.insert(e);
    }

    StringBuilder sb = new StringBuilder();
    for (Integer e : expected) {
      sb.append(e + " ");
    }
    String output = sb.toString();
    output = output.substring(0, output.length() - 1);

    assertEquals(output, root.toString());
  }

  /**
   * Test to verify that the minimum and maximum values in the tree match
   * the expected minimum and maximum from a set of random values.
   */
  @Test
  public void testMinMax() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    TreeSet<Integer> expected = new TreeSet<>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      root = root.insert(e);
    }

    assertEquals(expected.first(), root.minimum());
    assertEquals(expected.last(), root.maximum());
  }

  /**
   * Test to ensure that calling minimum() on an empty tree throws a NothingThereException.
   */
  @Test(expected = NothingThereException.class)
  public void testMinWhenEmpty() {
    new BSTEmptyNode<Integer>().minimum();
  }

  /**
   * Test to ensure that calling maximum() on an empty tree throws a NothingThereException.
   */
  @Test(expected = NothingThereException.class)
  public void testMaxWhenEmpty() {
    new BSTEmptyNode<Integer>().maximum();
  }

  /**
   * Test to verify that the contains() method correctly identifies whether a value is present
   * in the tree after inserting random values.
   */
  @Test
  public void testContains() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    List<Integer> expected = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      root = root.insert(e);
    }

    for (int i = -1000; i <= 1000; i++) {
      assertEquals(expected.contains(i), root.contains(i));
    }
  }

  /**
   * Test pre-order traversal of a tree and verify the traversal order.
   * Ensures that the pre-order traversal returns nodes in the correct order.
   */
  @Test
  public void testPreorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);

    List<Integer> actualList = new ArrayList<Integer>();

    root.preorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);
  }

  /**
   * Test pre-order traversal on a single-node tree.
   */
  @Test
  public void testPreorderSingleNode() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);

    List<Integer> expectedList = Arrays.asList(10);
    List<Integer> actualList = new ArrayList<>();

    root.preorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test pre-order traversal on a left-skewed tree (all nodes are added to the left).
   */
  @Test
  public void testPreorderLeftSkewedTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(1);

    List<Integer> expectedList = Arrays.asList(10, 5, 1);
    List<Integer> actualList = new ArrayList<>();

    root.preorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test pre-order traversal on a right-skewed tree (all nodes are added to the right).
   */
  @Test
  public void testPreorderRightSkewedTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(20);

    List<Integer> expectedList = Arrays.asList(10, 15, 20);
    List<Integer> actualList = new ArrayList<>();

    root.preorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test pre-order traversal on a complete binary tree (balanced tree).
   */
  @Test
  public void testPreorderCompleteTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(15);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(12);
    root = root.insert(18);

    List<Integer> expectedList = Arrays.asList(10, 5, 2, 7, 15, 12, 18);
    List<Integer> actualList = new ArrayList<>();

    root.preorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test pre-order traversal on an empty tree.
   */
  @Test
  public void testPreorderEmptyTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    List<Integer> expectedList = Collections.emptyList(); // No elements expected
    List<Integer> actualList = new ArrayList<>();

    root.preorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /////

  /**
   * Test post-order traversal on a tree with multiple nodes.
   */
  @Test
  public void testPostorder() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<>();

    root.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);
  }

  /**
   * Test post-order traversal on a single-node tree.
   */
  @Test
  public void testPostorderSingleNode() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);

    List<Integer> expectedList = Arrays.asList(10);
    List<Integer> actualList = new ArrayList<>();

    root.postorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test post-order traversal on a left-skewed tree.
   */
  @Test
  public void testPostorderLeftSkewedTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(1);

    List<Integer> expectedList = Arrays.asList(1, 5, 10);
    List<Integer> actualList = new ArrayList<>();

    root.postorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test post-order traversal on a right-skewed tree.
   */
  @Test
  public void testPostorderRightSkewedTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(20);

    List<Integer> expectedList = Arrays.asList(20, 15, 10);
    List<Integer> actualList = new ArrayList<>();

    root.postorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test post-order traversal on a complete binary tree.
   */
  @Test
  public void testPostorderCompleteTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(15);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(12);
    root = root.insert(18);

    List<Integer> expectedList = Arrays.asList(2, 7, 5, 12, 18, 15, 10);
    List<Integer> actualList = new ArrayList<>();

    root.postorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test post-order traversal on an empty tree.
   */
  @Test
  public void testPostorderEmptyTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    List<Integer> expectedList = Collections.emptyList(); // No elements expected
    List<Integer> actualList = new ArrayList<>();

    root.postorder(actualList::add);

    assertEquals(expectedList, actualList);
  }

  /**
   * Test copying a non-trivial tree and verifying the copied tree's post-order traversal.
   */
  @Test
  public void testCopy() {
    BSTNode<Integer> root = new BSTEmptyNode<>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<>();

    BSTNode<Integer> copy = root.copy();

    copy.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);
  }

  /**
   * Test copying an empty tree and verifying the copied tree is also empty.
   */
  @Test
  public void testCopyEmptyTree() {
    BSTNode<Integer> emptyTree = new BSTEmptyNode<>();
    BSTNode<Integer> copy = emptyTree.copy();

    List<Integer> actualList = new ArrayList<>();
    copy.postorder(i -> actualList.add(i));

    assertTrue(actualList.isEmpty());
  }

  /**
   * Test copying a single-node tree and verifying the post-order traversal of the copy.
   */
  @Test
  public void testCopySingleNodeTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);

    BSTNode<Integer> copy = root.copy();

    List<Integer> actualList = new ArrayList<>();
    copy.postorder(i -> actualList.add(i));

    assertEquals(Arrays.asList(10), actualList);
  }

  /**
   * Test copying a tree with nodes only in the left subtree and verifying
   * the post-order traversal of the copy.
   */
  @Test
  public void testCopyLeftSubtreeTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(3);
    root = root.insert(1);

    BSTNode<Integer> copy = root.copy();

    List<Integer> actualList = new ArrayList<>();
    copy.postorder(i -> actualList.add(i));

    assertEquals(Arrays.asList(1, 3, 5, 10), actualList);
  }

  /**
   * Test copying a tree with nodes only in the right subtree and verifying
   * the post-order traversal of the copy.
   */
  @Test
  public void testCopyRightSubtreeTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(20);
    root = root.insert(25);

    BSTNode<Integer> copy = root.copy();

    List<Integer> actualList = new ArrayList<>();
    copy.postorder(i -> actualList.add(i));

    assertEquals(Arrays.asList(25, 20, 15, 10), actualList);
  }

  /**
   * Test copying a balanced tree and verifying the post-order traversal of the copy.
   */
  @Test
  public void testCopyBalancedTree() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(15);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(12);
    root = root.insert(18);

    BSTNode<Integer> copy = root.copy();

    List<Integer> actualList = new ArrayList<>();
    copy.postorder(i -> actualList.add(i));

    assertEquals(Arrays.asList(2, 7, 5, 12, 18, 15, 10), actualList);
  }

  /**
   * Test structural integrity by ensuring the copied tree is a
   * distinct object from the original tree.
   */
  @Test
  public void testCopyStructuralIntegrity() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(15);

    BSTNode<Integer> copy = root.copy();

    assertNotSame(root, copy);
  }

  /**
   * Test modifying the original tree after copying and ensuring the copy remains unchanged.
   */
  @Test
  public void testModifyOriginalTreeAfterCopy() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(15);

    BSTNode<Integer> copy = root.copy();

    // Modify original tree
    root = root.insert(12);

    List<Integer> actualList = new ArrayList<>();
    copy.postorder(i -> actualList.add(i));

    assertEquals(Arrays.asList(5, 15, 10), actualList);
  }

  /**
   * Test to check the sameness of two identical trees created separately
   * and to verify that a copied tree is identical to the original tree.
   */
  @Test
  public void testSameness() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    BSTNode<Integer> anotherRoot = new BSTEmptyNode<Integer>();

    root = root.insert(10);
    root = root.insert(15);
    root = root.insert(5);
    root = root.insert(1);
    root = root.insert(2);
    root = root.insert(7);
    root = root.insert(6);
    root = root.insert(8);
    root = root.insert(20);
    root = root.insert(19);

    anotherRoot = anotherRoot.insert(10);
    anotherRoot = anotherRoot.insert(15);
    anotherRoot = anotherRoot.insert(5);
    anotherRoot = anotherRoot.insert(1);
    anotherRoot = anotherRoot.insert(2);
    anotherRoot = anotherRoot.insert(7);
    anotherRoot = anotherRoot.insert(6);
    anotherRoot = anotherRoot.insert(8);
    anotherRoot = anotherRoot.insert(20);
    anotherRoot = anotherRoot.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<Integer>();

    BSTNode<Integer> copy = root.copy();

    assertTrue(root.same(anotherRoot));
    assertTrue(root.same(copy));
  }

  /**
   * Test to check if trees with different structures are not considered the same.
   */
  @Test
  public void testDifferentStructure() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    root1 = root1.insert(10);
    root1 = root1.insert(5);
    root1 = root1.insert(1);

    root2 = root2.insert(10);
    root2 = root2.insert(15);
    root2 = root2.insert(20);

    assertFalse(root1.same(root2));
  }

  /**
   * Test to check if trees with the same structure but different values
   * are not considered the same.
   */
  @Test
  public void testDifferentValues() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    root1 = root1.insert(10);
    root1 = root1.insert(5);
    root1 = root1.insert(15);

    root2 = root2.insert(10);
    root2 = root2.insert(5);
    root2 = root2.insert(20);

    assertFalse(root1.same(root2));
  }

  /**
   * Test to ensure that modifying a tree after copying results in different trees.
   */
  @Test
  public void testSameTreeAfterModifications() {
    BSTNode<Integer> root = new BSTEmptyNode<>();
    root = root.insert(10);
    root = root.insert(5);
    root = root.insert(15);

    BSTNode<Integer> copyRoot = root.copy();

    BSTNode<Integer> modifiedRoot = root.insert(12);

    assertFalse(copyRoot.same(modifiedRoot));
  }

  /**
   * Test to verify that two empty trees are considered the same.
   */
  @Test
  public void testEmptyTrees() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    assertTrue(root1.same(root2));
  }

  /**
   * Test to check if two single-node trees with the same value are considered the same.
   */
  @Test
  public void testSingleNodeTrees() {
    BSTNode<Integer> root1 = new BSTEmptyNode<>();
    BSTNode<Integer> root2 = new BSTEmptyNode<>();

    root1 = root1.insert(10);
    root2 = root2.insert(10);

    assertTrue(root1.same(root2));
  }
}