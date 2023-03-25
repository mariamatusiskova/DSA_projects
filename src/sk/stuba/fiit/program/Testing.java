
package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.AVLTree;
import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.Data;
import sk.stuba.fiit.binarySearchTree.RedBlackTree;
import sk.stuba.fiit.hashTable.DataHashTable;
import sk.stuba.fiit.hashTable.OpenAddressingHashTable;
import sk.stuba.fiit.hashTable.SeparateChainingHashTable;
//import sk.stuba.fiit.binarySearchTree.SplayTree;

import java.util.LinkedList;
import java.util.Random;

public class Testing {

    long startTime, endTime, duration;
    Data specific = new Data(generateRandomString(), generateRandomNumber());
    DataHashTable specificHashTable = new DataHashTable(generateRandomNumber(), generateRandomString());


    public String generateRandomString() {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        Random ran = new Random();

        // make a string from chars of array --> append()
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            sb.append(chars.charAt(ran.nextInt(chars.length())));
        }

        // toString()
        return sb.toString();
    }

    int generateRandomNumber() {

       return 1 + (int)(Math.random() * 1000);
    }

    // ######### Binary search tree, Adelson-Velsky Landis Tree, Red-Black Tree --> testing #########
    public void testTreeInsertDeleteSearchIndividually(BinarySearchTree tree, int numberOfNodes) {

        for (int i = 0; i < numberOfNodes; i++) {
            tree.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        }

        // insert testing
        startTime = System.nanoTime();
        tree.callInsert(specific);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to insert " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

        // search testing
        startTime = System.nanoTime();
        tree.callSearch(specific);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to search " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

        // delete testing
        startTime = System.nanoTime();
        tree.callDelete(specific);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to delete " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();
    }

   public void testWholeTree(BinarySearchTree tree, int numberOfNodes) {

       for (int i = 0; i < numberOfNodes; i++) {
           tree.callInsert(new Data(generateRandomString(), generateRandomNumber()));
       }

       startTime = System.nanoTime();

       tree.callInsert(specific);
       tree.callSearch(specific);
       tree.callDelete(specific);

       endTime = System.nanoTime();
       duration = endTime - startTime;
       System.out.println("Time taken to tree with " + 1 + " element: " + duration + " nanoseconds");
       System.out.println();
   }

    // ######### Separate Chaining, Linear Probing  --> testing #########
    public void testSeparateChainingHashTableInsertDeleteSearchIndividually(SeparateChainingHashTable table, int numberOfNodes) {

        for (int i = 0; i < (table.sentSizeOfTable-1); i++) {
            table.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        // insert testing
        startTime = System.nanoTime();
        table.insert(specificHashTable);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to insert " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

        // search testing
        startTime = System.nanoTime();
        table.search(specificHashTable.key);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to search " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

        // delete testing
        startTime = System.nanoTime();
        table.delete(specificHashTable.key);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to delete " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

    }

    public void testOpenAddressingHashTableInsertDeleteSearchIndividually(OpenAddressingHashTable table, int numberOfNodes) {

        for (int i = 0; i < (table.sentSizeOfTable-1); i++) {
            table.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        // insert testing
        startTime = System.nanoTime();
        table.insert(specificHashTable);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to insert " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

        // search testing
        startTime = System.nanoTime();
        table.search(specificHashTable.key);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to search " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();

        // delete testing
        startTime = System.nanoTime();
        table.delete(specificHashTable.key);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to delete " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();
    }

    public void testWholeSeparateChaining(SeparateChainingHashTable table, int numberOfNodes) {

        for (int i = 0; i < (table.sentSizeOfTable-1); i++) {
            table.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        startTime = System.nanoTime();

        table.insert(specificHashTable);
        table.search(specificHashTable.key);
        table.delete(specificHashTable.key);

        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to separate chaining hash table with " + 1 + " element: " + duration + " nanoseconds");
        System.out.println();
    }

    public void testWholeLinearProbing(OpenAddressingHashTable table, int numberOfNodes) {

        for (int i = 0; i < (table.sentSizeOfTable-1); i++) {
            table.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        startTime = System.nanoTime();

        table.insert(specificHashTable);
        table.search(specificHashTable.key);
        table.delete(specificHashTable.key);

        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to linear probing hash table one" + 1 + " element : " + duration + " nanoseconds");
        System.out.println();
    }

    public void testIfTreesAreWorkingCorrect() {

        Traverse tr = new Traverse();

        System.out.println();
        System.out.println("*************** BST ***************");
        System.out.println();

        BinarySearchTree bst = new BinarySearchTree();

        bst.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        Data specificBST = new Data(generateRandomString(), generateRandomNumber());
        bst.callInsert(specific);
        bst.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        for (int i = 0; i < 3; i++) {
            bst.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        }

        bst.callSearch(specificBST);

        tr.callInorder(bst);
        tr.callPreorder(bst);
        tr.callPostorder(bst);

        bst.callDelete(specificBST);
        bst.callSearch(specificBST);

        tr.callInorder(bst);
        tr.callPreorder(bst);
        tr.callPostorder(bst);

        AVLTree avl = new AVLTree();

        System.out.println();
        System.out.println("*************** AVL ***************");
        System.out.println();

        avl.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        Data specificAVL = new Data(generateRandomString(), generateRandomNumber());
        avl.callInsert(specificAVL);
        avl.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        avl.callInsert(specificAVL);
        avl.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        avl.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        avl.callInsert(new Data(generateRandomString(), generateRandomNumber()));

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

        rbt.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        Data specificRBT = new Data(generateRandomString(), generateRandomNumber());
        rbt.callInsert(specificRBT);
        rbt.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        rbt.callInsert(specificRBT);
        rbt.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        rbt.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        rbt.callInsert(new Data(generateRandomString(), generateRandomNumber()));

        rbt.callSearch(specificRBT);

        tr.callInorder(rbt);
        tr.callPreorder(rbt);
        tr.callPostorder(rbt);

        rbt.callDelete(specificRBT);
        rbt.callSearch(specificRBT);

        tr.callInorder(rbt);
        tr.callPreorder(rbt);
        tr.callPostorder(rbt);

    }

    public void testIfHashTablesAreWorkingCorrect() {

        System.out.println();
        System.out.println("*************** Separate Chaining ***************");
        System.out.println();

        SeparateChainingHashTable chaining = new SeparateChainingHashTable(10);

        for (int i = 0; i < (chaining.sentSizeOfTable-1); i++) {
            chaining.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        DataHashTable separateChaining = new DataHashTable(generateRandomNumber(), generateRandomString());
        chaining.insert(separateChaining);
        chaining.insert(separateChaining);


        int j = 0;
        System.out.println();
        for (LinkedList<DataHashTable> bucket : chaining.buckets) {
            System.out.print("Bucket " + j + ":  ");
            for (DataHashTable dataOfTable : bucket) {
                System.out.print(dataOfTable.value + " | " + dataOfTable.key + " | " + " ");
            }
            System.out.println();
            j++;
        }

        System.out.println();

        chaining.search(separateChaining.key);

        chaining.delete(separateChaining.key);

        chaining.search(separateChaining.key);

        int k = 0;
        System.out.println();
        for (LinkedList<DataHashTable> bucket : chaining.buckets) {
            System.out.print("Bucket " + k + ":  ");
            for (DataHashTable dataOfTable : bucket) {
                System.out.print(dataOfTable.value + " | " + dataOfTable.key);
            }
            System.out.println();
            k++;
        }

        System.out.println();
        System.out.println("*************** Open Addressing ***************");
        System.out.println();

        OpenAddressingHashTable openAddressingHashTable = new OpenAddressingHashTable(10);


        for (int i = 0; i < (openAddressingHashTable.sentSizeOfTable-1); i++) {
            openAddressingHashTable.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        DataHashTable linearProbing = new DataHashTable(generateRandomNumber(), generateRandomString());
        openAddressingHashTable.insert(linearProbing);
        openAddressingHashTable.insert(linearProbing);

        int m = 0;
        for (DataHashTable dataOfTable : openAddressingHashTable.buckets) {
            System.out.print("Bucket " + m + ":  ");
            if(dataOfTable == null){
                System.out.print("null");
            } else{
                System.out.print(dataOfTable.value + " | " + dataOfTable.key + " | " + " ");
            }
            System.out.println();
            m++;
        }

        System.out.println();

        openAddressingHashTable.search(linearProbing.key);

        openAddressingHashTable.delete(linearProbing.key);

        openAddressingHashTable.search(linearProbing.key);

        int n = 0;
        for (DataHashTable dataOfTable : openAddressingHashTable.buckets) {
            System.out.print("Bucket " + n + ":  ");
            if(dataOfTable == null){
                System.out.print("null");
            } else {
                System.out.print(dataOfTable.value + " | " + dataOfTable.key + " | " + " ");
            }
            System.out.println();
            n++;
        }

    }

    // 100, 1 000, 10 000, 100 000, 1 000 000, 10 000 000

    public static void main(String[] args) {

        Testing test = new Testing();

        // trees
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avlTree = new AVLTree();
        RedBlackTree redBlackTree = new RedBlackTree();

        // individually

        System.out.println("*************** BST ***************");
        test.testTreeInsertDeleteSearchIndividually(bst, 100);
        System.out.println("*************** AVL ***************");
        test.testTreeInsertDeleteSearchIndividually(avlTree, 100);
        System.out.println("*************** RBT ***************");
        test.testTreeInsertDeleteSearchIndividually(redBlackTree, 100);

        System.out.println("*************** BST ***************");
        test.testTreeInsertDeleteSearchIndividually(bst, 1000);
        System.out.println("*************** AVL ***************");
        test.testTreeInsertDeleteSearchIndividually(avlTree, 1000);
        System.out.println("*************** RBT ***************");
        test.testTreeInsertDeleteSearchIndividually(redBlackTree, 1000);

        System.out.println("*************** BST ***************");
        test.testTreeInsertDeleteSearchIndividually(bst, 10000);
        System.out.println("*************** AVL ***************");
        test.testTreeInsertDeleteSearchIndividually(avlTree, 10000);
        System.out.println("*************** RBT ***************");
        test.testTreeInsertDeleteSearchIndividually(redBlackTree, 10000);

        System.out.println("*************** BST ***************");
        test.testTreeInsertDeleteSearchIndividually(bst, 100000);
        System.out.println("*************** AVL ***************");
        test.testTreeInsertDeleteSearchIndividually(avlTree, 100000);
        System.out.println("*************** RBT ***************");
        test.testTreeInsertDeleteSearchIndividually(redBlackTree, 100000);

        System.out.println("*************** BST ***************");
        test.testTreeInsertDeleteSearchIndividually(bst, 1000000);
        System.out.println("*************** AVL ***************");
        test.testTreeInsertDeleteSearchIndividually(avlTree, 1000000);
        System.out.println("*************** RBT ***************");
        test.testTreeInsertDeleteSearchIndividually(redBlackTree, 1000000);

        System.out.println("*************** BST ***************");
        test.testTreeInsertDeleteSearchIndividually(bst, 10000000);
        System.out.println("*************** AVL ***************");
        test.testTreeInsertDeleteSearchIndividually(avlTree, 10000000);
        System.out.println("*************** RBT ***************");
        test.testTreeInsertDeleteSearchIndividually(redBlackTree, 10000000);

        // whole tree

        System.out.println("*************** BST ***************");
        test.testWholeTree(bst, 100);
        System.out.println("*************** AVL ***************");
        test.testWholeTree(avlTree, 100);
        System.out.println("*************** RBT ***************");
        test.testWholeTree(redBlackTree, 100);

        test.testIfTreesAreWorkingCorrect();

        // hash tables
        SeparateChainingHashTable separateChainingHashTable100 = new SeparateChainingHashTable(100);
        OpenAddressingHashTable openAddressingHashTable = new OpenAddressingHashTable(100);

    }
}
