// SOURCE:
// https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java

package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.NodeOfTheTree;

public class Traverse {

    BinarySearchTree bst = new BinarySearchTree();

    // #### inorder tree walk --> visiting left subtree, then the root, at the end the right subtree
    // recursive method
    void inorder(NodeOfTheTree actualNode, int space) {

        if (actualNode != null) {

            inorder(actualNode.left, space + 1);

            if (space != 0) {
                for (int i = 0; i < space; i++) {
                    System.out.print("\t\t\t");
                }
                System.out.println("|-------------- " + actualNode.data.value);
            } else {
                // root
                System.out.println("INORDER ROOT: " + actualNode.data.value);
            }

            inorder(actualNode.right, space + 1);
        }
    }

    public void callInorder() {
        System.out.println();
        inorder(bst.root, 0);
        System.out.println();
        System.out.println();
    }

    // ### preorder tree walk --> visiting root, then the left subtree, at the end the right subtree
    // recursive method
    void preorder(NodeOfTheTree actualNode, int space) {

        if (actualNode != null) {

            if (space != 0) {
                for (int i = 0; i < space; i++) {
                    System.out.print("\t\t\t");
                }
                System.out.println("|-------------- " + actualNode.data.value);
            } else {
                // root
                System.out.println("PREORDER ROOT: " + actualNode.data.value);
            }

            preorder(actualNode.left, space + 1);
            preorder(actualNode.right, space + 1);
        }
    }

    public void callPreorder() {
        System.out.println();
        preorder(bst.root, 0);
        System.out.println();
        System.out.println();
    }

    // ### postorder tree walk --> visiting the left subtree, then the right subtree, the root node at the end
    // recursive method
    void postorder(NodeOfTheTree actualNode, int space) {

        if (actualNode != null) {

            postorder(actualNode.left, space + 1);
            postorder(actualNode.right, space + 1);

            if (space != 0) {
                for (int i = 0; i < space; i++) {
                    System.out.print("\t\t\t");
                }
                System.out.println("|-------------- " + actualNode.data.value);
            } else {
                // root
                System.out.println("POSTORDER ROOT: " + actualNode.data.value);
            }
        }
    }

    public void callPostorder() {
        System.out.println();
        postorder(bst.root, 0);
        System.out.println();
        System.out.println();
    }
}
