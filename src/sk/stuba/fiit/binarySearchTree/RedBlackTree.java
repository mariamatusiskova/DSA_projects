// Source:
// https://www.happycoders.eu/algorithms/red-black-tree-java/

package sk.stuba.fiit.binarySearchTree;
import sk.stuba.fiit.program.Data;

public class RedBlackTree extends BinarySearchTree {

    private final boolean RED = false;
    private final boolean BLACK = true;

    public RedBlackTree() {
        this.root = null;
    }

    private void rightRotation(NodeOfTheTree actualNode) {

        NodeOfTheTree newParent = actualNode.parent;
        NodeOfTheTree leftChild = actualNode.left;

        actualNode.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = actualNode;
        }

        leftChild.right = actualNode;
        actualNode.parent = leftChild;

        transplant(newParent, actualNode, leftChild);
    }

    private void leftRotation(NodeOfTheTree node) {

        NodeOfTheTree parent = node.parent;
        NodeOfTheTree rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        transplant(parent, node, rightChild);
    }

    public void callInsert(Data newData) {
        // sending a root because that's the beginning of the tree
        insertRBT(root, newData);
    }

    public void insertRBT(NodeOfTheTree actualNode, Data newData) {

        // store data into node
        NodeOfTheTree newNode = new NodeOfTheTree(newData);
        NodeOfTheTree parent = null;

        // if tree is not empty
        while (actualNode != null) {
            parent = actualNode;
            if (actualNode.data.compareTo(newData) > 0) {
                actualNode = actualNode.left;
            } else if (actualNode.data.compareTo(newData) < 0) {
                actualNode = actualNode.right;
            } else {
               return;
            }
        }

        newNode.color = RED;

        // if added node is a root
        if (parent == null) {
            root = newNode;
        } else if (parent.data.compareTo(newNode.data) > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        insertFixup(newNode);
    }

    // recursive method
    private void insertFixup(NodeOfTheTree newNode) {

        NodeOfTheTree parent = newNode.parent;

        // check if parent is null, pointer is in the end of the tree
        if (parent == null) {
            // newNode is a root
            newNode.color = BLACK;
            return;
        }

        // case when parent is already black
        if (parent.color == BLACK) {
            return;
        }

        // case when parent is red:
        NodeOfTheTree grandparent = parent.parent;

        // if grandparent is null then again parent is a root
        if (grandparent == null) {
            parent.color = BLACK;
            return;
        }

        // check if uncle is black or nor
        NodeOfTheTree uncle = null;

        // grandparent.left = uncle
        if (grandparent.left == parent) {
            uncle = grandparent.right;
        // grandparent.left = uncle
        } else if (grandparent.right == parent) {
            uncle = grandparent.left;
        }

        // case if uncle is red
        if (uncle != null && uncle.color == RED) {

            /* before:                  after:
                      G = B                         G = R
                       / \                           / \
                  U = R   P = R                  U = B  P = B
             */

            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;

            // grandparent is red, but we don't know if it is a root or not and the rule is, that root have to be black
            insertFixup(grandparent);
        /*
            condition:
              G
             / \
            P
         */
        } else if (parent == grandparent.left) {

            /*
                condition:
                  G
                 / \
                P
               / \
                  NewNode
             */
            if (newNode == parent.right) {

                leftRotation(parent);
                parent = newNode;
                 /*
                     after:
                              G
                             / \
                        NewNode
                           / \
                          P
                */
            }

            /*
                case:
                          G
                         / \
                    NewNode
                       / \
                     P
             */

            rightRotation(grandparent);
              /*
                 after:
                        NewNode
                         / \
                        G   P
                */

            parent.color = BLACK;
            grandparent.color = RED;
        /*
            condition:
              G
             / \
                P
         */
        } else {
            // symmetrical
            if (newNode == parent.left) {
                rightRotation(parent);

                parent = newNode;
            }

            leftRotation(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    public void callDelete(Data deleteData) {
        // sending a root because that's the beginning of the tree
        deleteRBT(root, deleteData);
    }
    public void deleteRBT(NodeOfTheTree actualNode, Data deleteData) {

        NodeOfTheTree deleteNode;
        boolean deleteColor;

        // search node to delete
        while (actualNode != null && actualNode.data.compareTo(deleteData) == 0) {

            if (actualNode.data.compareTo(deleteData) > 0) {
                actualNode = actualNode.left;
            } else {
                actualNode = actualNode.right;
            }
        }

        // not found
        if (actualNode == null) {
            return;
        }

        // no children or only right child
        if (actualNode.left == null || actualNode.right == null) {

            if (actualNode.left != null) {
                transplant(actualNode.parent, actualNode, actualNode.left);
                deleteNode = actualNode.left;
            } else if (actualNode.right != null) {
                transplant(actualNode.parent, actualNode, actualNode.right);
                deleteNode = actualNode.right;
            // no children, two options: 1) actualNode is red, then remove it; 2) actualNode is black, replace it with NIL node, then fix RBT
            } else {
                NodeOfTheTree alternativeNode = actualNode.color == BLACK ? new NilNode() : null;
                transplant(actualNode.parent, actualNode, alternativeNode);
                deleteNode = alternativeNode;
            }

            deleteColor = actualNode.color;
        // two children
        } else {

            NodeOfTheTree successorNode = findMinimum(actualNode.right);
            actualNode.data = successorNode.data;

            // if it is child of deleteNode
            if (successorNode.left != null) {
                transplant(actualNode.parent, successorNode, successorNode.left);
                deleteNode = successorNode.left;
            } else if (successorNode.right != null) {
                transplant(successorNode.parent, successorNode, successorNode.right);
                deleteNode = successorNode.right;
            // no children, two options: 1) actualNode is red, then remove it; 2) actualNode is black, replace it with NIL node, then fix RBT
            } else {
                NodeOfTheTree newChild = successorNode.color == BLACK ? new NilNode() : null;
                transplant(successorNode.parent, successorNode, newChild);
                deleteNode = newChild;
            }

            deleteColor = successorNode.color;
        }

        if (deleteColor == BLACK) {
            deleteFixup(deleteNode);

            // remove NIL node
            if (deleteNode.getClass() == NilNode.class) {
                transplant(deleteNode.parent, deleteNode, null);
            }
        }
    }

    // replace the node --> new parent
    private void transplant(NodeOfTheTree parent, NodeOfTheTree nodeChild, NodeOfTheTree newChild) {

        if (parent == null) {
            root = newChild;
        } else if (parent.left == nodeChild) {
            parent.left = newChild;
        } else if (parent.right == nodeChild) {
            parent.right = newChild;
        } else {
            return;
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    // recursive method
    private void deleteFixup(NodeOfTheTree deleteNode) {

        NodeOfTheTree uncle;

        if (deleteNode == root) {
            deleteNode.color = BLACK;
            return;
        }

        if (deleteNode == deleteNode.parent.left) {
            uncle = deleteNode.parent.right;
        } else if (deleteNode == deleteNode.parent.right) {
            uncle = deleteNode.parent.left;
        } else {
            uncle = null;
        }

        if (uncle == null) {
            return;
        }

        // uncle is red, needs to be black
        if (uncle.color == RED) {

            uncle.color = BLACK;
            deleteNode.parent.color = RED;

            // fix RBT
            if (deleteNode == deleteNode.parent.left) {
                leftRotation(deleteNode.parent);
            } else {
                rightRotation(deleteNode.parent);
            }

            if (deleteNode == deleteNode.parent.left) {
                uncle = deleteNode.parent.right;
            } else if (deleteNode == deleteNode.parent.right) {
                uncle = deleteNode.parent.left;
            } else {
                uncle = null;
            }
        }

        // Cases 3+4: Black uncle with two black children
        if (isBlack(uncle.left) && isBlack(uncle.right)) {
            uncle.color = RED;

            // Case 3: Black uncle with two black children + red parent
            if (deleteNode.parent.color == RED) {
                deleteNode.parent.color = BLACK;
            }

            // Case 4: Black uncle with two black children + black parent
            else {
                deleteFixup(deleteNode.parent);
            }
        }

        // Case 5+6: Black uncle with at least one red child
        else {
            handleBlackSiblingWithAtLeastOneRedChild(deleteNode, uncle);
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(NodeOfTheTree node, NodeOfTheTree sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        // Case 5: Black sibling with at least one red child + "outer nephew" is black
        // --> Recolor sibling and its child, and rotate around sibling
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = BLACK;
            sibling.color = RED;
            rightRotation(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color = RED;
            leftRotation(sibling);
            sibling = node.parent.left;
        }

        // Fall-through to case 6...

        // Case 6: Black sibling with at least one red child + "outer nephew" is red
        // --> Recolor sibling + parent + sibling's child, and rotate around parent
        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            leftRotation(node.parent);
        } else {
            sibling.left.color = BLACK;
            rightRotation(node.parent);
        }
    }

    private boolean isBlack(NodeOfTheTree node) {
        return node == null || node.color == BLACK;
    }
}
