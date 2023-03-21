// Source:
// https://sd.blackball.lv/library/Introduction_to_Algorithms_Third_Edition_(2009).pdf
// https://everythingcomputerscience.com/books/schoolboek-data_structures_and_algorithms_in_java.pdf (page 577)
// "Algorithms, Part I" course on Coursera --> Separate Chaining (Princeton University)
// https://phpfog.com/how-to-create-a-hash-table-in-java-chaining-example/
// https://opendatastructures.org/ods-java/5_1_ChainedHashTable_Hashin.html
// https://www.sanfoundry.com/java-program-implement-hash-tables-chaining-list-heads/
// https://www.cs.utexas.edu/~slaberge/problem_pages/hash_table_resize/HashTableResizeSolution.pdf
// https://medium.com/omarelgabrys-blog/hash-tables-2fec6870207f

package sk.stuba.fiit.hashTable;

import sk.stuba.fiit.program.Data;

import java.util.ArrayList;
import java.util.LinkedList;

// linked list at the index in the hash table
public class SeparateChainingHashTable {

    // dynamic array of objects
    public ArrayList<LinkedList<Data>> buckets = new ArrayList<LinkedList<Data>>();
    public int tableSize;
    public int countNodes;

    // default constructor
    public SeparateChainingHashTable(int tableSize) {

        this.tableSize = tableSize;
        this.countNodes = 0;

        for (int i = 0; i < tableSize; i++) {
            buckets.add(new LinkedList<Data>());
        }
       // this.tableSize = MAX_SIZE;
    }

//    private int hashNode(Data data) {
//        int hash = 0;
//        for (int i = 0; i < data.value.length(); i++) {
//            hash = 31 * hash + data.value.charAt(i);
//        }
//        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&& : " + hash);
//        // hexadecimal number provides only positive numbers
//        return (hash & 0x7fffffff) % SIZE;
//    }

    public void insert(Data data) {

        // hexadecimal number provides only positive numbers
        int index = (data.key & 0x7fffffff) % tableSize;

        for (int i = 0; i < buckets.get(index).size(); i++) {
            if (data.value.equals(buckets.get(index).get(i).value)) {
                buckets.get(index).get(i).value = data.value;
                return;
            }
        }

        buckets.get(index).add(data);
        countNodes++;

        if (countNodes / tableSize > 0.7f) {
            resize(this.tableSize*2);
        }
    }

    void resize(int sizeOfTable) {

        ArrayList<LinkedList<Data>> newTable = new ArrayList<LinkedList<Data>>();

        // create empty table of new size
        for (int i = 0; i < sizeOfTable; i++) {
            newTable.add(new LinkedList<Data>());
        }

        for (LinkedList<Data> bucket : buckets) {
            for (Data data : bucket) {
                int updateIndex = (data.key & 0x7fffffff) % sizeOfTable;
                newTable.get(updateIndex).add(data);
            }
        }

        // update after resize
        this.tableSize = sizeOfTable;
        this.buckets = newTable;
    }

    public void delete(Data data) {

        int index = (data.key & 0x7fffffff) % tableSize;

        if (buckets.get(index) == null) {
            return;
        }

        for (int i = 0; i < buckets.get(index).size(); i++) {
                if (data.value.equals(buckets.get(index).get(i).value)) {
                    buckets.get(index).removeFirstOccurrence(buckets.get(index).get(i));
                    countNodes--;
                    return;
                }
        }

        if (countNodes / tableSize < 0.3f) {
            resize(this.tableSize/2);
        }

        return;
    }

    public boolean search(Data data) {

        int index = (data.key & 0x7fffffff) % tableSize;

        if (buckets.get(index) == null) {
            System.out.println("searched value: " + data.value + " | " + data.key + " --> false");
            return false;
        }

        for (int i = 0; i < buckets.get(index).size(); i++) {
            if (data.value.equals(buckets.get(index).get(i).value)) {
                System.out.println("searched value: " + data.value + " | " + data.key +" --> true");
                return true;
            }
        }

        System.out.println("searched value: " + data.value + " | " + data.key + " --> false");
        return false;
    }
}
