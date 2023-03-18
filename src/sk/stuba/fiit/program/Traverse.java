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

//            if (space != 0) {
//                for (int i = 0; i < space; i++) {
//                    System.out.print("\t\t\t");
//                }
//                System.out.println("|-------------- " + actualNode.data.value + " | " + actualNode.data.number);
//            } else {
//                // root
//                System.out.println("INORDER ROOT: " + actualNode.data.value + " | " + actualNode.data.number);
//            }

            System.out.println(actualNode.data.value + " | " + actualNode.data.number);

            inorder(actualNode.right, space + 1);
        }
    }

    public void callInorder(BinarySearchTree tree) {
        System.out.println();
        inorder(tree.root, 0);
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
                System.out.println("|-------------- " + actualNode.data.value + " | " + actualNode.data.number);
            } else {
                // root
                System.out.println("PREORDER ROOT: " + actualNode.data.value + " | " + actualNode.data.number);
            }

            preorder(actualNode.left, space + 1);
            preorder(actualNode.right, space + 1);
        }
    }

    public void callPreorder(BinarySearchTree tree) {
        System.out.println();
        preorder(tree.root, 0);
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
                    System.out.print("\t\t\t\t");
                }
                System.out.println("|-------------- " + actualNode.data.value + " | " + actualNode.data.number);
            } else {
                // root
                System.out.println("POSTORDER ROOT: " + actualNode.data.value + " | " + actualNode.data.number);
            }
        }
    }

    public void callPostorder(BinarySearchTree tree) {
        System.out.println();
        postorder(tree.root, 0);
        System.out.println();
        System.out.println();
    }
}
