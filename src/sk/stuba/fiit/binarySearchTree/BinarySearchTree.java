/* sources from which I have studied:
 * # binary search tree:
 * https://sd.blackball.lv/library/Introduction_to_Algorithms_Third_Edition_(2009).pdf
 * https://www.geeksforgeeks.org/deletion-in-binary-search-tree/
 * https://www.baeldung.com/java-binary-tree
 * https://www.youtube.com/watch?v=ytfEFJAhMgo
 * # implementing AVL tree to BSt
 * https://www.youtube.com/watch?v=n917xdxKSmo
 * https://www.youtube.com/watch?v=jDM6_TnYIqE
 */

package sk.stuba.fiit.binarySearchTree;

import java.util.Vector;

public class BinarySearchTree {

    NodeOfTheTree root;

    // default constructor
    public BinarySearchTree() {
        this.root = null;
    }

    // ******** 𓍊𓋼𓍊𓋼𓍊 ********

    // ##############
    // ### insert ###
    // ##############

    // TODO: debug
    // recursive method for checking values of nodes to insert a new value in the right place
    NodeOfTheTree insert(NodeOfTheTree actualNode, int newValue) {

        // if the current node is last, so we'll insert a new value in that position (like a new leaf)
        // if tree is empty
        if (actualNode == null) {
            return new NodeOfTheTree(newValue);
        }

        // no duplicates
        if (actualNode.value == newValue) {
            return null;
        } else if (actualNode.value > newValue) {
            if (actualNode.left == null) {
                actualNode.left = new NodeOfTheTree(newValue);
            } else {
                actualNode.left = insert(actualNode.left, newValue);
            }
        } else if (actualNode.value < newValue) {
            if (actualNode.right == null) {
                actualNode.right = new NodeOfTheTree(newValue);
            } else {
                actualNode.right = insert(actualNode.right, newValue);
            }
        } else {
            return actualNode;
        }

        // AVL Tree
        // calculate depth
        actualNode.depth = (Math.max(depth(actualNode.left), depth(actualNode.right)) + 1);
        // find balance o the tree
        int middleRoot = getAVLRoot(actualNode);

        // rotations of AVL
        if ((middleRoot > 1) && (actualNode.value < actualNode.left.value)) {
            return rightRotation(actualNode);
        } else if ((middleRoot < -1) && (actualNode.value > actualNode.right.value)) {
            return leftRotation(actualNode);
        } else if ((middleRoot > 1) && (actualNode.value > actualNode.left.value)) {
            return leftRightRotation(actualNode);
        } else if ((middleRoot < -1) && (actualNode.value < actualNode.right.value)) {
            return rightLeftRotation(actualNode);
        }

        // recalculate depth
        actualNode.depth = (Math.max(depth(actualNode.left), depth(actualNode.right)) + 1);

        // TODO: ????
        // update root pointer
        if (actualNode.left != null && actualNode.depthLeft > actualNode.depthRight) {
            actualNode = rightRotation(actualNode);
        } else if (actualNode.right != null && actualNode.depthRight > actualNode.depthLeft) {
            actualNode = leftRotation(actualNode);
        }

        return actualNode;
    }

    // method for calling recursive method insert
    public void callInsert(int newValue) {
        // sending a root because that's the beginning of the tree
        root = insert(root, newValue);
    }

    // ##############
    // ### search ###
    // ##############

    boolean search(NodeOfTheTree actualNode, int searchValue) {

        if (actualNode == null) {
            return false;
        }

        if (actualNode.value == searchValue) {
            return true;
        } else if (actualNode.value > searchValue) {
                return search(actualNode.left, searchValue);
        } else if (actualNode.value < searchValue) {
                return search(actualNode.right, searchValue);
        } else {
            return false;
        }
    }

    // method for calling recursive method search
    public boolean callSearch(int searchValue) {
        // sending a root because that's the beginning of the tree
        boolean searchedValue = search(root, searchValue);
        System.out.println("expected value: " + searchedValue);
        return searchedValue;
    }

    // ##############
    // ### delete ###
    // ##############

    NodeOfTheTree delete(NodeOfTheTree actualNode, int deleteValue) {

        // no child
        if (actualNode == null) {
            return null;
        }

//        if (actualNode.value == deleteValue) {
//            return null;
//        } else
        if (actualNode.value > deleteValue) {
            if (actualNode.left == null) {
                return null;
            } else {
                actualNode.left = delete(actualNode.left, deleteValue);
            }
        } else if (actualNode.value < deleteValue) {
            if (actualNode.right == null) {
                return null;
            } else {
                actualNode.right = delete(actualNode.right, deleteValue);
            }
        } else {

            // one child
            if (actualNode.left == null)
                return actualNode.right;
            else if (actualNode.right == null)
                return actualNode.left;

            // two children
            actualNode.value = findMinimum(actualNode.right);

            actualNode.right = delete(actualNode.right, actualNode.value);
        }

        return actualNode;
    }

    int findMinimum(NodeOfTheTree actualNode) {

        while (actualNode.left != null) {
            actualNode = actualNode.left;
        }

        return actualNode.value;
    }

    public void callDelete(int deleteValue) {
        // sending a root because that's the beginning of the tree
        root = delete(root, deleteValue);
    }

    // ####################
    // ### transverses ###
    // ##################

    // #### inorder tree walk --> visiting left subtree, then the root, at the end the right subtree
    // recursive method
    void inorder(NodeOfTheTree actualNode) {

        if (actualNode != null) {

            inorder(actualNode.left);
            System.out.println("inorder: " + actualNode.value);
            inorder(actualNode.right);
        }
    }

    void callInorder() {
        inorder(root);
    }

    // ### preorder tree walk --> visiting root, then the left subtree, at the end the right subtree
    // recursive method
    void preorder(NodeOfTheTree actualNode) {

        if (actualNode != null) {

            System.out.println("preorder: " + actualNode.value);
            preorder(actualNode.left);
            preorder(actualNode.right);
        }
    }

    void callPreorder() {
        preorder(root);
    }

    // ### postorder tree walk --> visiting the left subtree, then the right subtree, the root node at the end
    // recursive method
    void postorder(NodeOfTheTree actualNode) {

        if (actualNode != null) {

            postorder(actualNode.left);
            postorder(actualNode.right);
            System.out.println("postorder: " + actualNode.value);
        }
    }

    void callPostorder() {
        postorder(root);
    }

    // #############
    // ### depth ###
    // #############

    // calculate depth of the binary search tree
    int depth(NodeOfTheTree actualNode) {

        // if the tree is empty, the depth is 0
        if (actualNode == null || (actualNode.right == null && actualNode.left == null)) {
            return 0;
        } else {
            actualNode.depthLeft = depth(actualNode.left);
            actualNode.depthRight = depth(actualNode.right);

            if (actualNode.depthLeft > actualNode.depthRight) {
                actualNode.depth = actualNode.depthLeft = (1 + actualNode.depthLeft);
                return actualNode.depthLeft;
            } else {
                // + 1 because of the current node of the tree
                actualNode.depth = actualNode.depthRight = (1 + actualNode.depthRight);
                return actualNode.depthRight;
            }
        }
    }

    int callDepth() {
        int value = depth(root);
        System.out.println("depth: " + value);
        return value;
    }

    // ******** 𓍝 (AVL) ********
    /* efficient way convert BST to AVL by storing values into an array
     * The program picks from the array a middle value and divides values to the left and right subtrees.
     */

    // ##########################################
    // ### find value in the middle as a root ###
    // ##########################################

    int getAVLRoot(NodeOfTheTree actualNode) {
        if (actualNode == null) {
            return 0;
        }

        return (depth(actualNode.left) - depth(actualNode.right));
    }

    // #################
    // ### rotations ###
    // #################

    NodeOfTheTree leftRotation(NodeOfTheTree actualNode) {

        NodeOfTheTree newRoot;

        if (actualNode == null) {
            return null;
        }

        if (actualNode.depthRight != 0 || actualNode.depthLeft != 0) {

            if (actualNode.depthRight > actualNode.depthLeft) {
                // the newRoot will be a right child of actualNode
                newRoot = actualNode.right;
                // the right child of actualNode will be the left child
                actualNode.right = newRoot.left;
                // the left child of newRoot is ActualNode
                newRoot.left = actualNode;

                // update heights
                actualNode.depth = (Math.max(depth(actualNode.left), depth(actualNode.right)) + 1);
                newRoot.depth = (Math.max(depth(newRoot.left), depth(newRoot.right)) + 1);

                return newRoot;
            }
        }

        return null;
    }

    NodeOfTheTree rightRotation(NodeOfTheTree actualNode) {

        NodeOfTheTree newRoot;

        if (actualNode == null) {
            return null;
        }

        if (actualNode.depthRight != 0 || actualNode.depthLeft != 0) {

            if (actualNode.depthRight < actualNode.depthLeft) {
                // the newRoot will be a left child of actualNode
                newRoot = actualNode.left;
                // the left child of actualNode will be the right child
                actualNode.left = newRoot.right;
                // the right child of newRoot is ActualNode
                newRoot.right = actualNode;

                // update heights
                actualNode.depth = (Math.max(depth(actualNode.left), depth(actualNode.right)) + 1);
                newRoot.depth = (Math.max(depth(newRoot.left), depth(newRoot.right)) + 1);

                return newRoot;
            }
        }

        return null;
    }

    NodeOfTheTree leftRightRotation(NodeOfTheTree actualNode) {

        NodeOfTheTree newNode;

        if (actualNode == null) {
            return null;
        }

        newNode = actualNode;
        depth(newNode.left);

        if (actualNode.depthRight != 0 || actualNode.depthLeft != 0) {

            if ((newNode.depthRight < newNode.depthLeft) && (actualNode.depthRight > actualNode.depthLeft)) {
                actualNode.left = leftRotation(actualNode.left);
                return rightRotation(newNode);
            }
        }

        return null;
    }

    NodeOfTheTree rightLeftRotation(NodeOfTheTree actualNode) {

        NodeOfTheTree newNode;

        if (actualNode == null) {
            return null;
        }

        newNode = actualNode;
        depth(newNode.right);

        if (actualNode.depthRight != 0 || actualNode.depthLeft != 0) {

            if ((newNode.depthRight > newNode.depthLeft) && (actualNode.depthRight < actualNode.depthLeft)) {
                actualNode.right = leftRotation(actualNode.right);
                return leftRotation(newNode);
            }
        }

        return null;
    }

    // ######***********************************************#######
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

    void callCountNodes() {
        countNodes(root);
    }

    // overriding and overloading method to AVL needs
    void inorder(NodeOfTheTree actualNode, Vector<Integer> nodeValues) {

        if (actualNode != null) {

            inorder(actualNode.left, nodeValues);
            nodeValues.add(actualNode.value);
            inorder(actualNode.right, nodeValues);
        }
    }

    // ##############################
    // ### put nodes to the array ###
    // ##############################

    // using vector class --> growable array/dynamic array of objects
    void putNodesToAVL(NodeOfTheTree actualNode, Vector<Integer> nodeValues) {

        // store node values to the array (using in-order method)
       inorder(actualNode, nodeValues);
       return;
    }

    // #########################
    // ### building AVL tree ###
    // #########################

    // recursive method
    NodeOfTheTree buildAVLTree(Vector<Integer> nodeValues) {

        // if empty :(
        if (nodeValues.size() == 0) {
            return null;
        }

        // find a new root
        NodeOfTheTree findMiddleValue = new NodeOfTheTree(nodeValues.get(nodeValues.size()/2));

        // making new subtrees

        Vector<Integer> leftSubtree = null;
        Vector<Integer> rightSubtree = null;

        for (int i = 0 ; i < nodeValues.size()/2 ; i++) {
            leftSubtree.add(nodeValues.get(i));
        }
        findMiddleValue.left  = buildAVLTree(leftSubtree);

        for (int j = ((nodeValues.size()/2) + 1); j < nodeValues.size() ; j++) {
            rightSubtree.add(nodeValues.get(j));
        }
        findMiddleValue.right  = buildAVLTree(rightSubtree);

        return findMiddleValue;
    }

    // ####################################
    // ### finally transform BST to AVL ###
    // ####################################

    // TODO: use this
    NodeOfTheTree transformBST_To_AVL(NodeOfTheTree rootAVL) {

        putNodesToAVL(rootAVL, null);
        rootAVL = buildAVLTree(null);

        return rootAVL;

    }
    // ********* End of AVL *********

    // ####################
    // ### display BST ###
    // ##################

    // TODO: Should this stay???
    public BinarySearchTree createBinarySearchTree() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.callInsert(1);
        bst.callInsert(8);
        bst.callInsert(5);
        bst.callInsert(7);
        bst.callInsert(2);
        bst.callInsert(9);
        bst.callInsert(4);

        bst.callSearch(8);

        bst.callInorder();
        bst.callPreorder();
        bst.callPostorder();

        bst.callDelete(8);
        bst.callSearch(8);

        bst.callInorder();
        bst.callPreorder();
        bst.callPostorder();

        bst.callDepth();

        return bst;
    }

    // ################################
    // ### main --> testing program ###
    // ################################

    public static void main(String[] args) {
        //TODO: Test programme
        BinarySearchTree tree = new BinarySearchTree();

        tree.createBinarySearchTree();
    }
}
