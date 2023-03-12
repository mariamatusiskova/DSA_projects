
package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.AVLTree;
import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.NodeOfTheTree;

import java.util.Random;

public class Testing {
    public String generateData() {

        String result = null;

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        Random ran = new Random();

        // make a string from chars of array --> append()
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(ran.nextInt(chars.length())));
        }

        // toString()
        return sb.toString();
    }

    public void createBinarySearchTree() {

        BinarySearchTree bst = new BinarySearchTree();
        Traverse tr = new Traverse();
        AVLTree avl = new AVLTree();

        bst.callInsert(new Data(generateData()));
        Data specific = new Data(generateData());
        bst.callInsert(specific);
        bst.callInsert(new Data(generateData()));
        Data toDelete = new Data(generateData());
        bst.callInsert(toDelete);
        bst.callInsert(new Data(generateData()));
        bst.callInsert(new Data(generateData()));
        bst.callInsert(new Data(generateData()));

        bst.callSearch(specific);

        avl.callHeight();

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        bst.callDelete(toDelete);
        bst.callSearch(toDelete);

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        avl.callHeight();

        System.out.println();
        System.out.println("*************** AVL ***************");
        System.out.println();

        avl.callInsert(new Data(generateData()));
        Data specificAVL = new Data(generateData());
        avl.callInsert(specificAVL);
        avl.callInsert(new Data(generateData()));
        Data toDeleteAVL = new Data(generateData());
        avl.callInsert(toDeleteAVL);
        avl.callInsert(new Data(generateData()));
        avl.callInsert(new Data(generateData()));
        avl.callInsert(new Data(generateData()));

        avl.callSearch(specific);

        avl.callHeight();

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        avl.callDelete(toDeleteAVL);
        avl.callSearch(toDeleteAVL);

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        avl.callHeight();
    }

    public static void main(String[] args) {
        //TODO: Test programme
        Testing tree = new Testing();

        tree.createBinarySearchTree();
    }
}
