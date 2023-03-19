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

        replaceParentsChild(newParent, actualNode, leftChild);
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

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(NodeOfTheTree parent, NodeOfTheTree oldChild, NodeOfTheTree newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
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
    public void deleteRBT(NodeOfTheTree actualNode, Data dataToDelete) {

        // Find the node to be deleted
        while (actualNode != null && actualNode.data != dataToDelete) {
            // Traverse the tree to the left or right depending on the key
            if (dataToDelete.compareTo(actualNode.data) < 0) {
                actualNode = actualNode.left;
            } else {
                actualNode = actualNode.right;
            }
        }

        // Node not found?
        if (actualNode == null) {
            return;
        }

        // At this point, "node" is the node to be deleted

        // In this variable, we'll store the node at which we're going to start to fix the R-B
        // properties after deleting a node.
        NodeOfTheTree movedUpNode;
        boolean deletedNodeColor;

        // Node has zero or one child
        if (actualNode.left == null || actualNode.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(actualNode);
            deletedNodeColor = actualNode.color;
        }

        // Node has two children
        else {
            // Find minimum node of right subtree ("inorder successor" of current node)
            NodeOfTheTree inOrderSuccessor = findMinimum(actualNode.right);

            // Copy inorder successor's data to current node (keep its color!)
            actualNode.data = inOrderSuccessor.data;

            // Delete inorder successor just as we would delete a node with 0 or 1 child
            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode);

            // Remove the temporary NIL node
            if (movedUpNode.getClass() == NilNode.class) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }
    }

    private NodeOfTheTree deleteNodeWithZeroOrOneChild(NodeOfTheTree node) {
        // Node has ONLY a left child --> replace by its left child
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left; // moved-up node
        }

        // Node has ONLY a right child --> replace by its right child
        else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right; // moved-up node
        }

        // Node has no children -->
        // * node is red --> just remove it
        // * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)
        else {
            NodeOfTheTree newChild = node.color == BLACK ? new NilNode() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private void fixRedBlackPropertiesAfterDelete(NodeOfTheTree node) {
        // Case 1: Examined node is root, end of recursion
        if (node == root) {
            // Uncomment the following line if you want to enforce black roots (rule 2):
            // node.color = BLACK;
            return;
        }

        NodeOfTheTree sibling = getSibling(node);

        // Case 2: Red sibling
        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
        }

        // Cases 3+4: Black sibling with two black children
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;

            // Case 3: Black sibling with two black children + red parent
            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }

            // Case 4: Black sibling with two black children + black parent
            else {
                fixRedBlackPropertiesAfterDelete(node.parent);
            }
        }

        // Case 5+6: Black sibling with at least one red child
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
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

    private void handleRedSibling(NodeOfTheTree node, NodeOfTheTree sibling) {
        // Recolor...
        sibling.color = BLACK;
        node.parent.color = RED;

        // ... and rotate
        if (node == node.parent.left) {
            leftRotation(node.parent);
        } else {
            rightRotation(node.parent);
        }
    }

    private NodeOfTheTree getSibling(NodeOfTheTree node) {
        NodeOfTheTree parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        } else {
            System.out.println("Couldn't find data in the red-black tree");
            return null;
        }
    }

    private boolean isBlack(NodeOfTheTree node) {
        return node == null || node.color == BLACK;
    }
}
