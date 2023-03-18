//// https://iq.opengenus.org/splay-tree/
//
//package sk.stuba.fiit.binarySearchTree;
//
//import sk.stuba.fiit.program.Data;
//
//public class SplayTree extends BinarySearchTree {
//
//    public SplayTree() {
//        this.root = null;
//    }
//
//    // right rotation
//    void zigRotation(NodeOfTheTree actualNode) {
//
//        NodeOfTheTree rotateNode = actualNode.left;
//        actualNode.left = rotateNode.right;
//
//        // check if right child id not null
//        if (rotateNode.right != null) {
//            // actualNode as a right child of parent
//            rotateNode.right.parent = actualNode;
//        }
//
//        rotateNode.parent = actualNode.parent;
//
//        if (actualNode.parent == null) {
//            root = rotateNode;
//        } else if (actualNode == actualNode.parent.right) {
//            actualNode.parent.right = rotateNode;
//        } else {
//            actualNode.parent.left = rotateNode;
//        }
//
//        rotateNode.right = actualNode;
//        actualNode.parent = rotateNode;
//    }
//
//    // left rotation
//    void zagRotation(NodeOfTheTree actualNode) {
//
//        NodeOfTheTree rotateNode = actualNode.right;
//        actualNode.right = rotateNode.left;
//
//        if (rotateNode.left != null) {
//            rotateNode.left.parent = actualNode;
//        }
//
//        rotateNode.parent = actualNode.parent;
//
//        if (actualNode.parent == null) {
//            root = rotateNode;
//        } else if (actualNode == actualNode.parent.left) {
//            actualNode.parent.left = rotateNode;
//        } else {
//            actualNode.parent.right = rotateNode;
//        }
//
//        rotateNode.left = actualNode;
//        actualNode.parent = rotateNode;
//    }
//
//    void insertSplay(NodeOfTheTree actualNode, Data insertData) {
//
//        // store data in the node
//        NodeOfTheTree insertNode = new NodeOfTheTree(insertData);
//
//        NodeOfTheTree helpNode = null;
//
//        // only if node is not empty
//        while(actualNode != null) {
//
//            helpNode = actualNode;
//            // check if insertNode is less than actualNode
//            if (actualNode.data.compareTo(insertNode.data) > 0) {
//                actualNode = actualNode.left;
//            } else {
//                actualNode = actualNode.right;
//            }
//        }
//
//        // update insertNode pointer
//        insertNode.parent = helpNode;
//        // check if helpNode tree is empty, if yes make insertNode as a root
//        if (helpNode == null) {
//            root = insertNode;
//        } else if (helpNode.data.compareTo(insertNode.data) > 0) {
//            helpNode.left = insertNode;
//        } else {
//            helpNode.right = insertNode;
//        }
//
//        splay(insertNode);
//    }
//
//    public void callInsert(Data newData) {
//        // sending a root because that's the beginning of the tree
//        insertSplay(root, newData);
//    }
//
//    void splay(NodeOfTheTree insertNode) {
//        // only if insertNode is not root node
//        while (insertNode.parent != null) {
//            // condition for zig and zag rotations
//            if (insertNode.parent.parent == null) {
//
//                // zig condition
//                if (insertNode == insertNode.parent.left) {
//                    zigRotation(insertNode.parent);
//                // zag rotation
//                } else {
//                    zagRotation(insertNode.parent);
//                }
//            // condition for zig-zig rotation
//            } else if (insertNode == insertNode.parent.left && insertNode == insertNode.parent.parent.left) {
//                // rotate grandparent of insertNode to right
//                zigRotation(insertNode.parent.parent);
//                zigRotation(insertNode.parent);
//            // condition for zag-zag rotation
//            } else if (insertNode == insertNode.parent.right && insertNode.parent == insertNode.parent.parent.right) {
//                // rotate grandparent of insertNode to left
//                zagRotation(insertNode.parent.parent);
//                zagRotation(insertNode.parent);
//            // condition for zag-zig rotation
//            } else if (insertNode == insertNode.parent.right && insertNode.parent == insertNode.parent.parent.left) {
//                // rotate parent to left
//                zagRotation(insertNode.parent);
//                // rotate updated insert to right
//                zigRotation(insertNode.parent);
//            // condition for zig-zag rotation
//            } else {
//                // rotate parent to right
//                zigRotation(insertNode.parent);
//                // rotate updated insert to left
//                zagRotation(insertNode.parent);
//            }
//        }
//    }
//
//    // we are searching for maximum, because then we just call method splay, find minimum is more complicated
//    NodeOfTheTree findMaximum(NodeOfTheTree node) {
//
//        while (node.right != null) {
//            node = node.right;
//        }
//
//        return  node;
//    }
//
//    NodeOfTheTree connectSubtrees(NodeOfTheTree leftSubtree, NodeOfTheTree rightSubtree) {
//
//        // if left subtree is empty
//        if (leftSubtree == null) {
//            return rightSubtree;
//        }
//
//        // if right subtree is empty
//        if (rightSubtree == null) {
//            NodeOfTheTree helpNode = findMaximum(leftSubtree);
//            splay(helpNode);
//            return helpNode;
//        }
//
//        NodeOfTheTree helpNode = findMaximum(leftSubtree);
//        splay(helpNode);
//
//        helpNode.right = rightSubtree;
//        // making parent of right child as helpNode
//        rightSubtree.parent = helpNode;
//        return helpNode;
//    }
//
//    void deleteSplay(NodeOfTheTree actualNode, Data deleteData) {
//
//        NodeOfTheTree helpNode = null;
//        NodeOfTheTree rightSubtree = null;
//        NodeOfTheTree leftSubtree = null;
//
//        // searching helpNode to be deleted
//        while (actualNode != null) {
//
//            if (actualNode.data.compareTo(deleteData) == 0) {
//                helpNode = actualNode;
//            }
//
//            if (actualNode.data.compareTo(deleteData) <= 0) {
//                actualNode = actualNode.right;
//            } else {
//                actualNode = actualNode.left;
//            }
//        }
//
//        // if the node is not found, call splay to recently accessed node and return
//        if (helpNode == null) {
//            splay(helpNode);
//            return;
//        }
//
//        // split operation, after rotation helpNode is a root node
//        splay(helpNode);
//
//        if (helpNode.right != null) {
//            rightSubtree = helpNode.right;
//            // unlink helpNode from its right child for right subtree
//            rightSubtree.parent = null;
//        } else {
//            rightSubtree = null;
//        }
//
//        // unlink helpNode from its right child for left subtree
//        leftSubtree = helpNode;
//        leftSubtree.right = null;
//        helpNode = null;
//
//        // check if left subtree is empty or not
//        if (leftSubtree.left != null) {
//            // unlink parent node
//            leftSubtree.left.parent = null;
//        }
//
//        root = connectSubtrees(leftSubtree.left, rightSubtree);
//        leftSubtree = null;
//    }
//
//    public void callDelete(Data deleteData) {
//        // sending a root because that's the beginning of the tree
//        deleteSplay(root, deleteData);
//    }
//
//}
