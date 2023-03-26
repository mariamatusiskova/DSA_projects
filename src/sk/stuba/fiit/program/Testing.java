
package sk.stuba.fiit.program;

import sk.stuba.fiit.binarySearchTree.AVLTree;
import sk.stuba.fiit.binarySearchTree.BinarySearchTree;
import sk.stuba.fiit.binarySearchTree.Data;
import sk.stuba.fiit.binarySearchTree.RedBlackTree;
import sk.stuba.fiit.hashTable.DataHashTable;
import sk.stuba.fiit.hashTable.OpenAddressingHashTable;
import sk.stuba.fiit.hashTable.SeparateChainingHashTable;
//import sk.stuba.fiit.binarySearchTree.SplayTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public List<Data> generateData() {
        List<Data> data = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            data.add(new Data(generateRandomString(), generateRandomNumber()));
        }
        return data;
    }

    public List<DataHashTable> generateDataHash() {
        List<DataHashTable> data = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            data.add(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }
        return data;
    }

    // ######### Binary search tree, Adelson-Velsky Landis Tree, Red-Black Tree --> testing #########
    public void testTreeInsertDeleteSearchIndividually(BinarySearchTree tree, int numberOfNodes, List<Data> data) {

        final int LOOP = data.size();

        for (int i = 0; i < numberOfNodes; i++) {
            tree.callInsert(new Data(generateRandomString(), generateRandomNumber()));
        }

        // insert testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            tree.callInsert(data.get(i));
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to insert " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds ");
        System.out.println("Average time of insert element: " + duration/LOOP + " nanoseconds");
        System.out.println();



        // search testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            tree.callSearch(data.get(i));
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to search " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of search element: " + duration/LOOP + " nanoseconds");
        System.out.println();

        // delete testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            tree.callDelete(data.get(i));
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to delete " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of delete element: " + duration/LOOP + " nanoseconds");
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
       System.out.println("Time taken to tree with " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
       System.out.println();
   }

    // ######### Separate Chaining, Linear Probing  --> testing #########
    public void testSeparateChainingHashTableInsertDeleteSearchIndividually(SeparateChainingHashTable table, int numberOfNodes, List<DataHashTable> datahash) {

        final int LOOP = datahash.size();

        for (int i = 0; i < (table.sentSizeOfTable-1); i++) {
            table.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        // insert testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            table.insert(datahash.get(i));
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("Time taken to insert " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of insert element: " + duration/LOOP + " nanoseconds");
        System.out.println();

        // search testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            table.search(datahash.get(i).key);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to search " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of search element: " + duration/LOOP + " nanoseconds");
        System.out.println();

        // delete testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            table.delete(datahash.get(i).key);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to delete " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of delete element: " + duration/LOOP + " nanoseconds");
        System.out.println();

    }

    public void testOpenAddressingHashTableInsertDeleteSearchIndividually(OpenAddressingHashTable table, int numberOfNodes, List<DataHashTable> datahash) {

        final int LOOP = datahash.size();

        for (int i = 0; i < (table.sentSizeOfTable-1); i++) {
            table.insert(new DataHashTable(generateRandomNumber(), generateRandomString()));
        }

        // insert testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            table.insert(datahash.get(i));
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to insert " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of insert element: " + duration/LOOP + " nanoseconds");
        System.out.println();

        // search testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            table.search(datahash.get(i).key);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to search " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of search element: " + duration/LOOP + " nanoseconds");
        System.out.println();

        // delete testing
        startTime = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            table.delete(datahash.get(i).key);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Time taken to delete " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
        System.out.println("Average time of delete element: " + duration/LOOP + " nanoseconds");
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
        System.out.println("Time taken to separate chaining hash table with " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
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
        System.out.println("Time taken to linear probing hash table one " + 1 + " element from " + (numberOfNodes+1) + ": " + duration + " nanoseconds");
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
        //tr.callPreorder(bst);
        //tr.callPostorder(bst);

        bst.callDelete(specificBST);
        bst.callSearch(specificBST);

        tr.callInorder(bst);
        // tr.callPreorder(bst);
        //tr.callPostorder(bst);

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
       // tr.callPreorder(avl);
       // tr.callPostorder(avl);

        avl.callDelete(specificAVL);
        avl.callSearch(specificAVL);

        tr.callInorder(avl);
       // tr.callPreorder(avl);
       // tr.callPostorder(avl);

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
       // tr.callPreorder(rbt);
       // tr.callPostorder(rbt);

        rbt.callDelete(specificRBT);
        rbt.callSearch(specificRBT);

        tr.callInorder(rbt);
      //  tr.callPreorder(rbt);
      //  tr.callPostorder(rbt);

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

    public static void main(String[] args) {

        Testing test = new Testing();

        // data
        List<Data> data = test.generateData();
        List<DataHashTable> datahash = test.generateDataHash();

        // size
        int numberOfNodes_1000 = 1000;
        int numberOfNodes_10000 = 10000;
        int numberOfNodes_100000 = 100000;
        int numberOfNodes_1000000 = 1000000;
        int numberOfNodes_10000000 = 10000000;

        // trees
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avlTree = new AVLTree();
        RedBlackTree redBlackTree = new RedBlackTree();

//        System.out.println("*************** BST - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(bst, numberOfNodes_1000, data);
//        System.out.println("*************** AVL - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(avlTree, numberOfNodes_1000, data);
//        System.out.println("*************** RBT - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(redBlackTree, numberOfNodes_1000, data);
//
//        System.out.println("*************** BST - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(bst, numberOfNodes_10000, data);
//        System.out.println("*************** AVL - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(avlTree, numberOfNodes_10000, data);
//        System.out.println("*************** RBT - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(redBlackTree, numberOfNodes_10000, data);
//
//        System.out.println("*************** BST - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(bst, numberOfNodes_100000, data);
//        System.out.println("*************** AVL - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(avlTree, numberOfNodes_100000, data);
//        System.out.println("*************** RBT - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(redBlackTree, numberOfNodes_100000, data);
//
//        System.out.println("*************** BST - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(bst, numberOfNodes_1000000, data);
//        System.out.println("*************** AVL - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(avlTree, numberOfNodes_1000000, data);
//        System.out.println("*************** RBT - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(redBlackTree, numberOfNodes_1000000, data);
//
//        System.out.println("*************** BST - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(bst, numberOfNodes_10000000, data);
//        System.out.println("*************** AVL - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(avlTree, numberOfNodes_10000000, data);
//        System.out.println("*************** RBT - testing methods individually ***************");
//        test.testTreeInsertDeleteSearchIndividually(redBlackTree, numberOfNodes_10000000, data);

//        System.out.println("*************** BST - testing the entire tree ***************");
//        test.testWholeTree(bst, numberOfNodes_1000);
//        System.out.println("*************** AVL - testing the entire tree ***************");
//        test.testWholeTree(avlTree, numberOfNodes_1000);
//        System.out.println("*************** RBT = testing the entire tree ***************");
//        test.testWholeTree(redBlackTree, numberOfNodes_1000);

//        System.out.println("*************** BST - testing the entire tree ***************");
//        test.testWholeTree(bst, numberOfNodes_10000);
//        System.out.println("*************** AVL - testing the entire tree ***************");
//        test.testWholeTree(avlTree, numberOfNodes_10000);
//        System.out.println("*************** RBT = testing the entire tree ***************");
//        test.testWholeTree(redBlackTree, numberOfNodes_10000);
//
//        System.out.println("*************** BST - testing the entire tree ***************");
//        test.testWholeTree(bst, numberOfNodes_100000);
//        System.out.println("*************** AVL - testing the entire tree ***************");
//        test.testWholeTree(avlTree, numberOfNodes_100000);
//        System.out.println("*************** RBT = testing the entire tree ***************");
//        test.testWholeTree(redBlackTree, numberOfNodes_100000);
//
//        System.out.println("*************** BST - testing the entire tree ***************");
//        test.testWholeTree(bst, numberOfNodes_1000000);
//        System.out.println("*************** AVL - testing the entire tree ***************");
//        test.testWholeTree(avlTree, numberOfNodes_1000000);
//        System.out.println("*************** RBT = testing the entire tree ***************");
//        test.testWholeTree(redBlackTree, numberOfNodes_1000000);
////
//        System.out.println("*************** BST - testing the entire tree ***************");
//        test.testWholeTree(bst, numberOfNodes_10000000);
//        System.out.println("*************** AVL - testing the entire tree ***************");
//        test.testWholeTree(avlTree, numberOfNodes_10000000);
//        System.out.println("*************** RBT = testing the entire tree ***************");
//        test.testWholeTree(redBlackTree, numberOfNodes_10000000);

      //  test.testIfTreesAreWorkingCorrect();

        // hash tables
        SeparateChainingHashTable separateChainingHashTable_1000 = new SeparateChainingHashTable(numberOfNodes_1000);
        OpenAddressingHashTable openAddressingHashTable_1000 = new OpenAddressingHashTable(numberOfNodes_1000);

        SeparateChainingHashTable separateChainingHashTable_10000 = new SeparateChainingHashTable(numberOfNodes_10000);
        OpenAddressingHashTable openAddressingHashTable_10000 = new OpenAddressingHashTable(numberOfNodes_10000);

        SeparateChainingHashTable separateChainingHashTable_100000 = new SeparateChainingHashTable(numberOfNodes_100000);
        OpenAddressingHashTable openAddressingHashTable_100000 = new OpenAddressingHashTable(numberOfNodes_100000);

        SeparateChainingHashTable separateChainingHashTable_1000000 = new SeparateChainingHashTable(numberOfNodes_1000000);
        OpenAddressingHashTable openAddressingHashTable_1000000 = new OpenAddressingHashTable(numberOfNodes_1000000);

        SeparateChainingHashTable separateChainingHashTable_10000000 = new SeparateChainingHashTable(numberOfNodes_10000000);
        OpenAddressingHashTable openAddressingHashTable_10000000 = new OpenAddressingHashTable(numberOfNodes_10000000);


//        System.out.println("*************** Separate Chaining - testing methods individually ***************");
//        test.testSeparateChainingHashTableInsertDeleteSearchIndividually(separateChainingHashTable_1000, numberOfNodes_1000, datahash);
//        test.testSeparateChainingHashTableInsertDeleteSearchIndividually(separateChainingHashTable_10000, numberOfNodes_10000, datahash);
//        test.testSeparateChainingHashTableInsertDeleteSearchIndividually(separateChainingHashTable_100000, numberOfNodes_100000, datahash);
//        test.testSeparateChainingHashTableInsertDeleteSearchIndividually(separateChainingHashTable_1000000, numberOfNodes_1000000, datahash);
//        test.testSeparateChainingHashTableInsertDeleteSearchIndividually(separateChainingHashTable_10000000, numberOfNodes_10000000, datahash);
//
//        System.out.println("*************** Linear Probing - testing methods individually ***************");
//        test.testOpenAddressingHashTableInsertDeleteSearchIndividually(openAddressingHashTable_1000, numberOfNodes_1000, datahash);
//        test.testOpenAddressingHashTableInsertDeleteSearchIndividually(openAddressingHashTable_10000, numberOfNodes_10000, datahash);
//        test.testOpenAddressingHashTableInsertDeleteSearchIndividually(openAddressingHashTable_100000, numberOfNodes_100000, datahash);
//        test.testOpenAddressingHashTableInsertDeleteSearchIndividually(openAddressingHashTable_1000000, numberOfNodes_1000000, datahash);
//        test.testOpenAddressingHashTableInsertDeleteSearchIndividually(openAddressingHashTable_10000000, numberOfNodes_10000000, datahash);

//        System.out.println("*************** Separate Chaining - testing the entire hash table ***************");
//        test.testWholeSeparateChaining(separateChainingHashTable_1000, numberOfNodes_1000);
//        test.testWholeSeparateChaining(separateChainingHashTable_10000, numberOfNodes_10000);
//        test.testWholeSeparateChaining(separateChainingHashTable_100000, numberOfNodes_100000);
//        test.testWholeSeparateChaining(separateChainingHashTable_1000000, numberOfNodes_1000000);
//        test.testWholeSeparateChaining(separateChainingHashTable_10000000, numberOfNodes_10000000);

        System.out.println("*************** Linear Probing - testing the entire hash table ***************");
        test.testWholeLinearProbing(openAddressingHashTable_1000, numberOfNodes_1000);
//        test.testWholeLinearProbing(openAddressingHashTable_10000, numberOfNodes_10000);
//        test.testWholeLinearProbing(openAddressingHashTable_100000, numberOfNodes_100000);
//        test.testWholeLinearProbing(openAddressingHashTable_1000000, numberOfNodes_1000000);
//        test.testWholeLinearProbing(openAddressingHashTable_10000000, numberOfNodes_10000000);

       // test.testIfHashTablesAreWorkingCorrect();
    }
}
