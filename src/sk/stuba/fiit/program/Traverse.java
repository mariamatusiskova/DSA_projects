package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.NodeOfTheTree;

public class Traverse {

    BinarySearchTree bst = new BinarySearchTree();

    // #### inorder tree walk --> visiting left subtree, then the root, at the end the right subtree
    // recursive method
    void inorder(NodeOfTheTree actualNode) {

        if (actualNode != null) {

            inorder(actualNode.left);
            System.out.println("inorder: " + actualNode.data.value);
            inorder(actualNode.right);
        }
    }

    public void callInorder() {
        inorder(bst.root);
    }

    // ### preorder tree walk --> visiting root, then the left subtree, at the end the right subtree
    // recursive method
    void preorder(NodeOfTheTree actualNode) {

        if (actualNode != null) {

            System.out.println("preorder: " + actualNode.data.value);
            preorder(actualNode.left);
            preorder(actualNode.right);
        }
    }

    public void callPreorder() {
        preorder(bst.root);
    }

    // ### postorder tree walk --> visiting the left subtree, then the right subtree, the root node at the end
    // recursive method
    void postorder(NodeOfTheTree actualNode) {

        if (actualNode != null) {

            postorder(actualNode.left);
            postorder(actualNode.right);
            System.out.println("postorder: " + actualNode.data.value);
        }
    }

    public void callPostorder() {
        postorder(bst.root);
    }
}
