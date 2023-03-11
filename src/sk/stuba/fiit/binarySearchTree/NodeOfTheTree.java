package sk.stuba.fiit.binarySearchTree;

import sk.stuba.fiit.program.Data;

// Node for storing values and keeping a reference to each child
public class NodeOfTheTree {

    public Data data;
    public NodeOfTheTree right;
    public NodeOfTheTree left;

    // AVL
    public static int height;

    // default constructor
    NodeOfTheTree() {
        this.data = null;
        this.right = this.left = null;
        this.height = 0;
    }

    // add a new node
    public NodeOfTheTree(Data data) {
        this.data = data;
        this.right = this.left = null;
        this.height = 0;
    }
}
