import org.junit.Test;

import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.Queue;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryTree {
    public Node root;

    /*
    Insertion method:
        - if the new node's value is lower than the current nodes,
        we go to the left child
        - if the new node's value is greater than the current nodes,
        we go to the right child
        - when the current node is null, we've reached a leaf node and
        we can insert the new node in the position

     */

    //Recursive method to do insertion in tree
    private Node addRecursive(Node current, int value) {
        if (current == null)
            return new Node(value);

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            //value already exists
            return current;
        }
        return current;
    }

    //Method to start recursion from the root node
    public void add(int value) {
        root = addRecursive(root, value);
    }

<<<<<<< HEAD
=======
    //Method to create a binary tree
    public BinaryTree createBinaryTree() {
        /*
                     6
                    / \
                   4   8
                  / \ / \
                3   5 7   9
            Visual representation of the tree created by this method
         */
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }

>>>>>>> a5db5b263fdd25a21279606eb350effe346448d1
    //Finding an element using recursion
    public boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value ? containsNodeRecursive(current.left, value) : containsNodeRecursive(current.right, value);
    }

    //Search starting from the root
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    //Simple test to see if the items added to the tree were actually added
    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElems() {
        BinaryTree bt = createBinaryTree();
        assertTrue(bt.containsNode(6));
        assertTrue(bt.containsNode(4));
        assertFalse(bt.containsNode(1));
    }

    //Delete a node from the tree
    /*
    One the node to be deleted is found there are 3 main different cases:
        - a node has no children: simplest case; we just need to replace this
        node with its only child.
        - a node has exactly one child: in the parent node, we replace this node
        with its only child.
        - a node has two children: this is the most complex case b/c it requires
        a tree reorganization
     */
    public Node deleteRecursive(Node current, int value) {
        if (current == null)
            return null;

        if (value == current.value) {
            //Node to delete found
            //case 1;
            if (current.left == null && current.right == null) {
                return null;
            }
            //Case 2: here we're returning a non-null child so it can be assigned to the parent node
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            //Case 3:
            else {
                int smallestValue = findSmallestValue(current.right);
                current.value = smallestValue;
                current.right = deleteRecursive(current.right, smallestValue);
                return current;
            }
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    //Helper Method to handle the case where the node has two children
    public int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    //Method to preform deletion from the root
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    //Test to check if the values was actually deleted
    @Test
    public void checkIfDel() {
        BinaryTree bt = createBinaryTree();

        assertTrue(bt.containsNode(9));
        bt.delete(9);
        assertFalse(bt.containsNode(9));
    }

    //*****************Tree Traversals*****************
    /*
    First: Depth-First Search - a type of traversal that goes deep as much as
    possible in every child before exploring the next sibling
        - There are several ways to preform a depth-first search:
            -in-order, pre-order, and post-order
     */
    //In-order consists of first visiting the left sub-tree, then the root node,
    //and finally the right sub-tree
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    //Check that the in-order is working properly
    @Test
    public void checkInOrder() {
        BinaryTree bt = createBinaryTree();
        bt.traverseInOrder(root);
    }

    //Pre-order traversal visits first the root node, then the left subtree
    //and finally the right subtree
    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    /*
    Post-order traversal visits the left subtree, the right subtree, and the
    root node at the end
     */
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePreOrder(node.right);
            System.out.print(" " + node.value);
        }
    }

    /*
    Breadth-First Search (BFS): another common type of traversal that visits all the
    nodes of a level before going to the next level
        - Also known as level order and visits all the levels of the tree starting
        from the root, and from left to right

        -This implementation uses a queue to hold the nodes from each level in order
        Extract each node from the list, print its values, then add its children to the queue
     */
    public void traverseBreadthFirst() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(" " + node.value);

            //Add all the nodes left and right to the current node being processed
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    //Method to get the depth or height of a binary tree
    public int getMaxDepth(Node root) {
        //if the root is null, we return 0 since its an empty tree
        if (root == null) {
            return 0;
        }

        //Add one to the recursive call while the tree has children nodes
        int maxLeft = getMaxDepth(root.left);
        int maxRight = getMaxDepth(root.right);

        //return the max value between the left and right nodes and add one to account for the root node
        return Math.max(maxLeft, maxRight) + 1;
    }

//    Iterative method to get the depth of a tree
//    public int getMaxDepth(Node root) {
//        //if the root is null, we return 0 since its an empty tree
//        if (root == null) {
//            return 0;
//        }
//        int leftDep = 0;
//        int rightDep = 0;
//        //Node iterators for left and right
//        Node liter = root;
//        Node riter = root;
//        while(liter.left != null) {
//            leftDep++;
//            liter = liter.left;
//        }
//        while (riter.right != null) {
//            rightDep++;
//            riter = riter.right;
//        }
//        //return the max value between the left and right nodes and add one to account for the root node
//        return Math.max(leftDep, rightDep) + 1;
//    }

    //Method to create a binary tree
    public BinaryTree createBinaryTree() {
        /*
                        6
                     /    \
                    4       8
                  /   \   /   \
                3      5 7     9
            Visual representation of the tree created by this method
         */
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }
}
