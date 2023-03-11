package sk.stuba.fiit.binarySearchTree;

import java.util.Vector;

public class AVLTree {

    BinarySearchTree bst = new BinarySearchTree();

    // calculate depth of the binary search tree
    int height(NodeOfTheTree actualNode) {

        // if the tree is empty, the depth is 0
        if (actualNode == null || (actualNode.right == null && actualNode.left == null)) {
            return 0;
        } else {
            int heightLeft = height(actualNode.left);
            int heightRight = height(actualNode.right);

            if (heightLeft > heightRight) {
                return (1 + heightLeft);
            } else {
                // + 1 because of the current node of the tree
                return (1 + heightRight);
            }
        }
    }

    public int callHeight() {
        int value = height(bst.root);
        System.out.println("height: " + value);
        return value;
    }

    void updateHeight(NodeOfTheTree node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // ###################
    // ### count nodes ###
    // ###################

    int countNodes(NodeOfTheTree actualNode) {

        if (actualNode == null) {
            return 0;
        } else {
            return (1 + countNodes(actualNode.left) + countNodes(actualNode.right));
        }
    }



}
