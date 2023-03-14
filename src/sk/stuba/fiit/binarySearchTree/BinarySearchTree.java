/* sources from which I have studied:
 * # binary search tree:
 * https://sd.blackball.lv/library/Introduction_to_Algorithms_Third_Edition_(2009).pdf
 * https://www.geeksforgeeks.org/deletion-in-binary-search-tree/
 * https://www.baeldung.com/java-binary-tree
 * https://www.youtube.com/watch?v=ytfEFJAhMgo
 * # implementing AVL tree to BSt
 * https://www.educative.io/answers/how-to-convert-any-binary-search-tree-to-an-avl-tree
 */

package sk.stuba.fiit.binarySearchTree;

import sk.stuba.fiit.program.Data;

public class BinarySearchTree {

    public static NodeOfTheTree root;

    // default constructor
    public BinarySearchTree() {
        this.root = null;
    }

    // recursive method for checking values of nodes to insert a new value in the right place
    NodeOfTheTree insert(NodeOfTheTree actualNode, Data newData) {
        // if the current node is last, so we'll insert a new value in that position (like a new leaf)
        // if tree is empty
        if (actualNode == null) {
            return new NodeOfTheTree(newData);
        }

        // no duplicates
        if (actualNode.data.compareTo(newData) > 0) {
                actualNode.left = insert(actualNode.left, newData);
        } else if (actualNode.data.compareTo(newData) < 0) {
                actualNode.right = insert(actualNode.right, newData);
        } else {
            return actualNode;
        }

        return actualNode;
    }

    // method for calling recursive method insert
    public void callInsert(Data newData) {
        // sending a root because that's the beginning of the tree
        root = insert(root, newData);
    }

    boolean search(NodeOfTheTree actualNode, Data searchValue) {

        if (actualNode == null) {
            return false;
        }

        if (actualNode.data.compareTo(searchValue) == 0) { //equal
            return true;
        } else if (actualNode.data.compareTo(searchValue) > 0) {
                return search(actualNode.left, searchValue);
        } else if (actualNode.data.compareTo(searchValue) < 0) {
                return search(actualNode.right, searchValue);
        } else {
            return false;
        }
    }

    // method for calling recursive method search
    public boolean callSearch(Data searchData) {
        // sending a root because that's the beginning of the tree
        boolean searchedValue = search(root, searchData);
        System.out.println("expected value: " + searchedValue + " | " + searchData.value);
        return searchedValue;
    }

    NodeOfTheTree delete(NodeOfTheTree actualNode, Data deleteData) {

        // no child
        if (actualNode == null) {
            return null;
        }

        if (actualNode.data.compareTo(deleteData) == 0 || actualNode.left == null && actualNode.right == null) {
            actualNode = null;
        } else if (actualNode.data.compareTo(deleteData) > 0) {
            actualNode.left = delete(actualNode.left, deleteData);
        } else if (actualNode.data.compareTo(deleteData) < 0) {
            actualNode.right = delete(actualNode.right, deleteData);
        } else {

            // one child
            if (actualNode.left == null)
                return actualNode.right;
            else if (actualNode.right == null)
                return actualNode.left;

            // two children
            NodeOfTheTree copyNode = findMinimum(actualNode.right);

            actualNode.data = copyNode.data;

            actualNode.right = delete(actualNode.right, actualNode.data);
        }

        return actualNode;
    }

    NodeOfTheTree findMinimum(NodeOfTheTree actualNode) {

        while (actualNode.left != null) {
            actualNode = actualNode.left;
        }

        return actualNode;
    }

    public void callDelete(Data deleteData) {
        // sending a root because that's the beginning of the tree
        root = delete(root, deleteData);
    }

}
