package sk.stuba.fiit.binarySearchTree;

// Node for storing values and keeping a reference to each child
public class NodeOfTheTree {

    public int value;
    NodeOfTheTree right, left;

    // AVL
    int balanceFactor;

    // default constructor
    NodeOfTheTree() {
        this.value = 0;
        this.right = this.left = null;
    }

    // add a new node
    public NodeOfTheTree(int value) {
        this.value = value;
        this.right = this.left = null;
    }
}
