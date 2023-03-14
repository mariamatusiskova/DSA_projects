
package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.AVLTree;
import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.NodeOfTheTree;
import sk.stuba.fiit.binarySearchTree.RedBlackTree;

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

    int generateRandomNumber() {

       return 1 + (int)(Math.random() * 1000);
    }

    public void createBinarySearchTree() {

        BinarySearchTree bst = new BinarySearchTree();
        Traverse tr = new Traverse();

        bst.callInsert(new Data(generateData(), generateRandomNumber()));
        Data specific = new Data(generateData(), generateRandomNumber());
        bst.callInsert(specific);
        bst.callInsert(new Data(generateData(), generateRandomNumber()));
        Data toDelete = new Data(generateData(), generateRandomNumber());
        bst.callInsert(toDelete);
        bst.callInsert(new Data(generateData(), generateRandomNumber()));
        bst.callInsert(new Data(generateData(), generateRandomNumber()));
        bst.callInsert(new Data(generateData(), generateRandomNumber()));

        bst.callSearch(specific);

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        bst.callDelete(toDelete);
        bst.callSearch(toDelete);

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        AVLTree avl = new AVLTree();

        System.out.println();
        System.out.println("*************** AVL ***************");
        System.out.println();

        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        Data specificAVL = new Data(generateData(), generateRandomNumber());
        avl.callInsert(specificAVL);
        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        Data toDeleteAVL = new Data(generateData(), generateRandomNumber());
        avl.callInsert(toDeleteAVL);
        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        avl.callInsert(new Data(generateData(), generateRandomNumber()));

        avl.callSearch(specificAVL);

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

        RedBlackTree rbt = new RedBlackTree();

        System.out.println();
        System.out.println("*************** RBT ***************");
        System.out.println();

        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        Data specificRBT = new Data(generateData(), generateRandomNumber());
        rbt.callInsert(specificRBT);
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        Data toDeleteRBT = new Data(generateData(), generateRandomNumber());
        rbt.callInsert(toDeleteRBT);
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));

        rbt.callSearch(specificRBT);

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();

        rbt.callDelete(toDeleteRBT);
        rbt.callSearch(toDeleteRBT);

        tr.callInorder();
        tr.callPreorder();
        tr.callPostorder();
    }

    public static void main(String[] args) {
        //TODO: Test programme
        Testing tree = new Testing();

        tree.createBinarySearchTree();
    }
}
