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

public class BinarySearchTree {

    public NodeOfTheTree root;

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

        while (actualNode != null) {
            if (actualNode.data.key == searchValue.key) {
                return true;
            } else if (actualNode.data.key > searchValue.key) {
                actualNode = actualNode.left;
            } else if (actualNode.data.key < searchValue.key) {
                actualNode = actualNode.right;
            }
        }
        return false;
    }

    // method for calling recursive method search
    public void callSearch(Data searchData) {
        // sending a root because that's the beginning of the tree
        search(root, searchData);
    }

    NodeOfTheTree delete(NodeOfTheTree actualNode, Data deleteData) {
        if (actualNode == null) {
            return null;
        }

        if(deleteData.compareTo(actualNode.data) < 0){
            actualNode.left = delete(actualNode.left, deleteData);
        }
        else if(deleteData.compareTo(actualNode.data) > 0){
            actualNode.right = delete(actualNode.right, deleteData);
        }
        else{
            //one child or no child
            if(actualNode.left == null){
                return actualNode.right;
            }
            //one child
            else if(actualNode.right == null){
                return actualNode.left;
            }

            //has two children
            actualNode = findMinimum(actualNode.right);

            actualNode.right = delete(actualNode.right, deleteData);
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