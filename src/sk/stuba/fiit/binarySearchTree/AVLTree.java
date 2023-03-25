// Source:
// https://www.happycoders.eu/algorithms/avl-tree-java/

package sk.stuba.fiit.binarySearchTree;

public class AVLTree extends BinarySearchTree {

    public AVLTree() {
        this.root = null;
    }

    // calculate depth of the binary search tree
    int height(NodeOfTheTree actualNode) {

        if (actualNode == null) {
            return -1;
        }
        return actualNode.height;

//        // if the tree is empty, the depth is 0
//        if (actualNode == null || (actualNode.right == null && actualNode.left == null)) {
//            return 0;
//        } else {
//            int heightLeft = height(actualNode.left);
//            int heightRight = height(actualNode.right);
//
//            if (heightLeft > heightRight) {
//                return (1 + heightLeft);
//            } else {
//                // + 1 because of the current node of the tree
//                return (1 + heightRight);
//            }
//        }
    }

    public int callHeight() {
        int value = height(root);
        System.out.println("height: " + value);
        return value;
    }

    void updateHeight(NodeOfTheTree node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    int balanceFactor(NodeOfTheTree node) {
        return height(node.right) - height(node.left);
    }

    NodeOfTheTree rightRotation(NodeOfTheTree actualNode) {

        if (actualNode.left != null) {

            // new parent
            NodeOfTheTree newParent = actualNode.left;

            // the left child will be changed from the original parent
            actualNode.left = newParent.right;

            if (newParent.right != null) {
                // original parent will be right child of the new parent
                newParent.right = actualNode;
            }

            updateHeight(actualNode);
            updateHeight(newParent);

            return newParent;
        } else {
            return actualNode;
        }
    }

    NodeOfTheTree leftRotation(NodeOfTheTree actualNode) {

        if (actualNode.right != null) {

            // new parent
            NodeOfTheTree newParent = actualNode.right;

            // the right child will be changed from the original parent
            actualNode.right = newParent.left;

            if (newParent.left != null) {
                // original parent will be right child of the new parent
                newParent.left = actualNode;
            }

            updateHeight(actualNode);
            updateHeight(newParent);

            return newParent;
        } else {
            return actualNode;
        }
    }

    NodeOfTheTree rightLeftRotation(NodeOfTheTree actualNode) {

        actualNode.right = rightRotation(actualNode.right);
        return leftRotation(actualNode);
    }

    NodeOfTheTree leftRightRotation(NodeOfTheTree actualNode) {

        actualNode.left = leftRotation(actualNode.left);
        return rightRotation(actualNode);
    }

    NodeOfTheTree rebalance(NodeOfTheTree node) {
        
        if (node == null) {
            return null;
        }
        
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.left) <= 0) {
                node = leftRotation(node);
            } else if (balanceFactor(node.left) > 0) {
                node = leftRightRotation(node);
            }
        } else if (balanceFactor(node) > 1) {
            if (balanceFactor(node.right) >= 0) {
                node = rightRotation(node);
            } else if (balanceFactor(node) < 0) {
                node = rightLeftRotation(node);
            }
        }

        return node;
    }

    NodeOfTheTree insert(NodeOfTheTree actualNode, Data newData) {
        actualNode = super.insert(actualNode, newData);

        if (actualNode.data == newData) {
            return actualNode;
        }

        updateHeight(actualNode);
        return rebalance(actualNode);
    }

    NodeOfTheTree delete(NodeOfTheTree actualNode, Data deleteData) {
        actualNode = super.delete(actualNode, deleteData);

        if (actualNode == null) {
            return null;
        }

        updateHeight(actualNode);
        return rebalance(actualNode);
    }
}
