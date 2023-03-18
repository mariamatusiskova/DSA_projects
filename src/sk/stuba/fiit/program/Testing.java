
package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.AVLTree;
import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.RedBlackTree;
//import sk.stuba.fiit.binarySearchTree.SplayTree;

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
        for (int i = 0; i < 3; i++) {
            bst.callInsert(new Data(generateData(), generateRandomNumber()));
        }

        bst.callSearch(specific);

        tr.callInorder(bst);
        tr.callPreorder(bst);
        tr.callPostorder(bst);

        bst.callDelete(specific);
        bst.callSearch(specific);

        tr.callInorder(bst);
        tr.callPreorder(bst);
        tr.callPostorder(bst);

        AVLTree avl = new AVLTree();

        System.out.println();
        System.out.println("*************** AVL ***************");
        System.out.println();

        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        Data specificAVL = new Data(generateData(), generateRandomNumber());
        avl.callInsert(specificAVL);
        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        avl.callInsert(specificAVL);
        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        avl.callInsert(new Data(generateData(), generateRandomNumber()));
        avl.callInsert(new Data(generateData(), generateRandomNumber()));

        avl.callSearch(specificAVL);

        avl.callHeight();

        tr.callInorder(avl);
        tr.callPreorder(avl);
        tr.callPostorder(avl);

        avl.callDelete(specificAVL);
        avl.callSearch(specificAVL);

        tr.callInorder(avl);
        tr.callPreorder(avl);
        tr.callPostorder(avl);

        avl.callHeight();

        RedBlackTree rbt = new RedBlackTree();

        System.out.println();
        System.out.println("*************** RBT ***************");
        System.out.println();

        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        Data specificRBT = new Data(generateData(), generateRandomNumber());
        rbt.callInsert(specificRBT);
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        rbt.callInsert(specificRBT);
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));
        rbt.callInsert(new Data(generateData(), generateRandomNumber()));

        rbt.callSearch(specificRBT);

         tr.callInorder(rbt);
//        tr.callPreorder(rbt);
//        tr.callPostorder(rbt);

        rbt.callDelete(specificRBT);
        rbt.callSearch(specificRBT);

          tr.callInorder(rbt);
//        tr.callPreorder(rbt);
//        tr.callPostorder(rbt);

//        SplayTree st = new SplayTree();

//        System.out.println();
//        System.out.println("*************** SplayTree ***************");
//        System.out.println();
//
//        st.callInsert(new Data(generateData(), generateRandomNumber()));
//        Data specificST = new Data(generateData(), generateRandomNumber());
//        st.callInsert(specificST);
//        st.callInsert(new Data(generateData(), generateRandomNumber()));
//        st.callInsert(specificST);
//        st.callInsert(new Data(generateData(), generateRandomNumber()));
//        st.callInsert(new Data(generateData(), generateRandomNumber()));
//        st.callInsert(new Data(generateData(), generateRandomNumber()));
//
//        st.callSearch(specificST);
//
//        tr.callInorder(st);
//        tr.callPreorder(st);
//        tr.callPostorder(st);
//
//        st.callDelete(specificST);
//        st.callSearch(specificST);
//
//        tr.callInorder(st);
//        tr.callPreorder(st);
//        tr.callPostorder(st);
    }

    public static void main(String[] args) {
        //TODO: Test programme
        Testing tree = new Testing();

        tree.createBinarySearchTree();
    }
}
