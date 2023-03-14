// Source:
// https://www.happycoders.eu/algorithms/red-black-tree-java/
// https://www.javadevjournal.com/data-structure/red-black-tree/
// https://algorithmtutor.com/Data-Structures/Tree/Red-Black-Trees/
// https://www.codesdope.com/course/data-structures-red-black-trees-insertion/
// https://www.codesdope.com/course/data-structures-red-black-trees-deletion/
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

        // store data into node
        NodeOfTheTree addNode = new NodeOfTheTree(newData);

        NodeOfTheTree parent = null;

        while (actualNode != null) {
            parent = actualNode;

            if (actualNode.data.compareTo(addNode.data) > 0) {
                actualNode = actualNode.left;
            } else {
                actualNode = actualNode.right;
            }
        }

        // insert new node
        addNode.color = RED;
        addNode.parent = parent;

        // if added root is root
        if (parent == null) {
            root = addNode;
        } else if (parent.data.compareTo(addNode.data) > 0) {
            parent.left = addNode;
        } else {
            parent.right = addNode;
        }

        addNode.right = null;
        addNode.left = null;

        // fix red and black properties after insert
        NodeOfTheTree uncle;

        while (addNode != null && addNode.parent != null && addNode.parent.color == RED) {

            if (addNode.parent == addNode.parent.parent.left) {
                uncle = addNode.parent.parent.right;
                // parent and uncle are red --> need to recolor
                if (uncle != null && uncle.color == RED) {
                    // parent will be black
                    addNode.parent.color = BLACK;
                    // uncle will be black
                    uncle.color = BLACK;
                    // grandparent will be red
                    addNode.parent.parent.color = RED;
                    // set node as a parent
                    addNode = addNode.parent.parent;
                } else {
                    // triangle, uncle is black, parent is red (an inserted value (left child) will be red --> rotation)
                    if (addNode == addNode.parent.right) {
                        addNode = addNode.parent;
                        leftRotation(addNode);
                    }
                    // line, uncle is black, parent is red (an inserted value (right child) will be red --> rotation)
                    addNode.parent.color = BLACK;
                    addNode.parent.parent.color = RED;
                    rightRotation(addNode.parent.parent);
                }
            } else {
                uncle = addNode.parent.parent.left;
                // parent and uncle are red --> need to recolor
                if (uncle != null && uncle.color == RED) {
                    addNode.parent.color = BLACK;
                    uncle.color = BLACK;
                    addNode.parent.parent.color = RED;
                    addNode = addNode.parent.parent;
                } else {
                    // triangle, uncle is black, parent is red (an inserted value (left child) will be red --> rotation)
                    if (addNode == addNode.parent.left) {
                        addNode = addNode.parent;
                        rightRotation(addNode);
                    }
                    // line, uncle is black, parent is red (an inserted value (right child) will be red --> rotation)
                    addNode.parent.color = BLACK;
                    addNode.parent.parent.color = RED;
                    leftRotation(addNode.parent.parent);
                }
            }

        }
        // the root is always black (rule of RBT)
        root.color = BLACK;
    }

    public void callInsert(Data newData) {
        // sending a root because that's the beginning of the tree
        insertRBT(root, newData);
    }

    // replace the node --> new parent
    void transplant(NodeOfTheTree node, NodeOfTheTree nodeChild) {

        if (node.parent == null) {
            root = nodeChild;
        } else if (node == node.parent.left) {
            node.parent.left = nodeChild;
        } else {
            node.parent.right = nodeChild;
        }

        if (nodeChild != null) {
            nodeChild.parent = node.parent;
        }
    }

    void deleteRBT(NodeOfTheTree actualNode, Data deleteData) {

        NodeOfTheTree deleteNode = null;
        NodeOfTheTree childNode, parentNode;
        boolean deleteColor;

        if (actualNode == null) {
            return;
        }

        while (actualNode != null) {

            if (actualNode.data.compareTo(deleteData) == 0) {
                deleteNode = actualNode;
            }

            if (actualNode.data.compareTo(deleteData) < 0) {
                actualNode = actualNode.right;
            } else {
                actualNode = actualNode.left;
            }
        }

        parentNode = deleteNode;
        deleteColor = parentNode.color;

        // no children or only right child
        if (deleteNode.left == null) {
            childNode = deleteNode.right;
            transplant(deleteNode, deleteNode.right);
            // only left child
        } else if (deleteNode.right == null) {
            childNode = deleteNode.left;
            transplant(deleteNode, deleteNode.left);
            // two children
        } else {
            parentNode = findMinimum(deleteNode.right);
            deleteColor = parentNode.color;
            childNode = parentNode.right;

            // if it is child of deleteNode
            if (parentNode.parent == deleteNode) {
                childNode.parent = parentNode;
            } else {
                transplant(parentNode, parentNode.right);
                parentNode.right = deleteNode.right;
                parentNode.right.parent =  parentNode;
            }

            transplant(deleteNode, parentNode);
            parentNode.left = deleteNode.left;
            parentNode.left.parent = parentNode;
            parentNode.color = deleteNode.color;
        }

        if (deleteColor == BLACK) {

            // delete fixup childNode
            NodeOfTheTree uncle;

            while (childNode != root && childNode.color == BLACK) {

                if (childNode == childNode.parent.left) {
                    uncle = childNode.parent.right;
                    // case 3.1
                    if (uncle.color == RED) {
                        uncle.color = BLACK;
                        childNode.parent.color = RED;
                        leftRotation(childNode.parent);
                        uncle = childNode.parent.right;
                    }

                    // case 3.2
                    if (uncle.left.color == BLACK & uncle.right.color == BLACK) {
                        uncle.color = RED;
                        childNode = childNode.parent;
                    } else {
                        // case 3.3
                        if (uncle.right.color == BLACK) {
                            uncle.left.color = BLACK;
                            uncle.color = RED;
                            rightRotation(uncle);
                            uncle = childNode.parent.right;
                        }
                        // case 3.4
                        uncle.color = childNode.parent.color;
                        childNode.parent.color = BLACK;
                        uncle.right.color = BLACK;
                        leftRotation(childNode.parent);
                        childNode = root;
                    }
                } else {
                    uncle = childNode.parent.left;
                    // case 3.1
                    if (uncle.color == RED) {
                        uncle.color = BLACK;
                        childNode.parent.color = RED;
                        rightRotation(childNode.parent);
                        uncle = childNode.parent.left;
                    }

                    // case 3.2
                    if (uncle.right.color == BLACK & uncle.left.color == BLACK) {
                        uncle.color = RED;
                        childNode = childNode.parent;
                    } else {
                        // case 3.3
                        if (uncle.left.color == BLACK) {
                            uncle.right.color = BLACK;
                            uncle.color = RED;
                            leftRotation(uncle);
                            uncle = childNode.parent.left;
                        }
                        // case 3.4
                        uncle.color = childNode.parent.color;
                        childNode.parent.color = BLACK;
                        uncle.left.color = BLACK;
                        rightRotation(childNode.parent);
                        childNode = root;
                    }
                }
            }

            // case 0
            childNode.color = BLACK;
        }
    }

    public void callDelete(Data deleteData) {
        // sending a root because that's the beginning of the tree
        deleteRBT(root, deleteData);
    }

}
