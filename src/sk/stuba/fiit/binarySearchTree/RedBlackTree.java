// Source:
// https://www.happycoders.eu/algorithms/red-black-tree-java/
// https://www.javadevjournal.com/data-structure/red-black-tree/
// https://algorithmtutor.com/Data-Structures/Tree/Red-Black-Trees/
// https://www.youtube.com/watch?v=5IBxA-bZZH8

package sk.stuba.fiit.binarySearchTree;

import sk.stuba.fiit.program.Data;

public class RedBlackTree extends BinarySearchTree{

    public RedBlackTree() {
        this.root = null;
    }

    private final boolean RED = false;
    private final boolean BLACK = true;

    void rightRotation(NodeOfTheTree actualNode) {

        if (actualNode == null || actualNode.left == null) {
            return;
        }

        // new parent of the tree
        NodeOfTheTree newParent = actualNode.left;

        // the left child will be changed from the original parent
        actualNode.left = newParent.right;

        if (newParent.right != null) {
            newParent.right.parent = actualNode;
        }

        newParent.parent = actualNode.parent;

        // update parent references
        if (actualNode.parent == null) {
            root = newParent;
        } else if (actualNode == actualNode.parent.left) {
            actualNode.parent.left = newParent;
        } else {
            actualNode.parent.right = newParent;
        }

        newParent.right = actualNode;
        actualNode.parent = newParent;
    }

    void leftRotation(NodeOfTheTree actualNode) {

        if (actualNode == null || actualNode.right == null) {
            return;
        }

        // new parent
        NodeOfTheTree newParent = actualNode.right;

        // the right child will be changed from the original parent
        actualNode.right = newParent.left;

        if (newParent.left != null) {
            newParent.left.parent = actualNode;
        }

        newParent.parent = actualNode.parent;

        // update parent references
        if (actualNode.parent == null) {
            root = newParent;
        } else if (actualNode == actualNode.parent.left) {
            actualNode.parent.left = newParent;
        } else {
            actualNode.parent.right = newParent;
        }

        newParent.left = actualNode;
        actualNode.parent = newParent;
    }

    void insertRBT(NodeOfTheTree actualNode, Data newData) {

        NodeOfTheTree parent = null;

        while (actualNode != null) {
            parent = actualNode;

            if (actualNode.data.compareTo(newData) > 0) {
                actualNode = actualNode.left;
            } else {
                actualNode = actualNode.right;
            }
        }

        // insert new node
        NodeOfTheTree addNode = new NodeOfTheTree(newData);
        addNode.color = RED;
        addNode.parent = parent;

        if (parent == null) {
            root = addNode;
        } else if (parent.data.compareTo(addNode.data) > 0) {
            parent.left = addNode;
        } else {
            parent.right = addNode;
        }

        addNode.parent = parent;

        NodeOfTheTree uncle;

        while (actualNode != null && actualNode.parent.color == RED) {

            if (actualNode.parent == actualNode.parent.parent.left) {
                uncle = actualNode.parent.parent.right;
                // parent and uncle are red --> need to recolor
                if (uncle != null && uncle.color == RED) {
                    // parent will be black
                    actualNode.parent.color = BLACK;
                    // uncle will be black
                    uncle.color = BLACK;
                    // grandparent will be red
                    actualNode.parent.parent.color = RED;
                    // set node as a parent
                    actualNode = actualNode.parent.parent;
                } else {
                    // triangle, uncle is black, parent is red (an inserted value (left child) will be red --> rotation)
                    if (actualNode == actualNode.parent.right) {
                        actualNode = actualNode.parent;
                        leftRotation(actualNode);
                    }
                    // line, uncle is black, parent is red (an inserted value (right child) will be red --> rotation)
                    actualNode.parent.color = BLACK;
                    actualNode.parent.parent.color = RED;
                    rightRotation(actualNode.parent.parent);
                }
            } else {
                uncle = actualNode.parent.parent.left;
                // parent and uncle are red --> need to recolor
                if (uncle != null && uncle.color == RED) {
                    actualNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    actualNode.parent.parent.color = RED;
                    actualNode = actualNode.parent.parent;
                } else {
                    // triangle, uncle is black, parent is red (an inserted value (left child) will be red --> rotation)
                    if (actualNode == actualNode.parent.left) {
                        actualNode = actualNode.parent;
                        rightRotation(actualNode);
                    }
                    // line, uncle is black, parent is red (an inserted value (right child) will be red --> rotation)
                    actualNode.parent.color = BLACK;
                    actualNode.parent.parent.color = RED;
                    leftRotation(actualNode.parent.parent);
                }
            }

            if (actualNode == root || actualNode.parent == null) {
                break;
            }

        }
        // the root is always black (rule of RBT)
        root.color = BLACK;
    }

    public void callInsert(Data newData) {
        // sending a root because that's the beginning of the tree
        insertRBT(root, newData);
    }

     void deleteRBT(NodeOfTheTree actualNode, Data deleteData) {

         NodeOfTheTree uncle;

         while (actualNode != root && actualNode.color == BLACK) {
            if (actualNode == actualNode.parent.left) {
                uncle = actualNode.parent.right;
                // case 3.1
                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    actualNode.parent.color = RED;
                    leftRotation(actualNode.parent);
                    uncle = actualNode.parent.right;
                }

                // case 3.2
                if (uncle.left.color == BLACK & uncle.right.color == BLACK) {
                    uncle.color = RED;
                    actualNode = actualNode.parent;
                } else {
                    // case 3.3
                    if (uncle.right.color == BLACK) {
                        uncle.left.color = BLACK;
                        uncle.color = RED;
                        rightRotation(uncle);
                        uncle = actualNode.parent.right;
                    }
                    // case 3.4
                    uncle.color = actualNode.parent.color;
                    actualNode.parent.color = BLACK;
                    uncle.right.color = BLACK;
                    leftRotation(actualNode.parent);
                    actualNode = root;
                }
            } else {
                uncle = actualNode.parent.left;
                // case 3.1
                if (uncle.color == RED) {
                    uncle.color = BLACK;
                    actualNode.parent.color = RED;
                    rightRotation(actualNode.parent);
                    uncle = actualNode.parent.left;
                }

                // case 3.2
                if (uncle.right.color == BLACK & uncle.left.color == BLACK) {
                    uncle.color = RED;
                    actualNode = actualNode.parent;
                } else {
                    // case 3.3
                    if (uncle.left.color == BLACK) {
                        uncle.right.color = BLACK;
                        uncle.color = RED;
                        leftRotation(uncle);
                        uncle = actualNode.parent.left;
                    }
                    // case 3.4
                    uncle.color = actualNode.parent.color;
                    actualNode.parent.color = BLACK;
                    uncle.left.color = BLACK;
                    rightRotation(actualNode.parent);
                    actualNode = root;
                }
            }
         }

         // case 0
         actualNode.color = BLACK;
     }

    public void callDelete(Data deleteData) {
        // sending a root because that's the beginning of the tree
        deleteRBT(root, deleteData);
    }

}
