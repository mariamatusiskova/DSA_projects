package sk.stuba.fiit.binarySearchTree;

// Node for storing values and keeping a reference to each child
public class NodeOfTheTree {

    public int value;
    NodeOfTheTree right, left;

    // AVL attribute
    int depthRight, depthLeft, depth;

    // default constructor
    NodeOfTheTree() {
        this.value = 0;
        this.right = this.left = null;
        this.depthRight = this.depthLeft = this.depth = 0;
    }

    // add a new node
    public NodeOfTheTree(int value) {
        this.value = value;
        this.right = this.left = null;
    }
}
