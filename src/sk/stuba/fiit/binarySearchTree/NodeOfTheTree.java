package sk.stuba.fiit.binarySearchTree;

import sk.stuba.fiit.program.Data;

// Node for storing values and keeping a reference to each child
public class NodeOfTheTree {

    // BinarySearchTree

    public Data data;
    public NodeOfTheTree right;
    public NodeOfTheTree left;

    // AVL Tree
    public int height;

    // Red-Black Tree
    // we need a parent node to balance the tree because the colours of the tree depend on the parent
    NodeOfTheTree parent;
    boolean color;

    // default constructor
    NodeOfTheTree() {
        this.data = null;
        this.right = this.left = null;
        this.height = 0;
        this.color = true;
    }

    // add a new node
    public NodeOfTheTree(Data data) {
        this.data = data;
        this.right = this.left = null;
        this.height = 0;
        this.color = true;
    }
}
