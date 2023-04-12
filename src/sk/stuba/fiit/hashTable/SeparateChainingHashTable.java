// Source:
// https://sd.blackball.lv/library/Introduction_to_Algorithms_Third_Edition_(2009).pdf
// https://medium.com/omarelgabrys-blog/hash-tables-2fec6870207f
// Adam Gábor, AIS ID 116174, DSA documentation

package sk.stuba.fiit.hashTable;

import java.util.ArrayList;
import java.util.LinkedList;

// linked list at the index in the hash table
public class SeparateChainingHashTable {

    // dynamic array of objects
    public ArrayList<LinkedList<DataHashTable>> buckets = new ArrayList<>();
    public int sentSizeOfTable;
    public int tableSize;
    public int countNodes;

    // default constructor
    public SeparateChainingHashTable(int tableSize) {

        this.sentSizeOfTable = tableSize;
        this.tableSize = tableSize;
        this.countNodes = 0;

        for (int i = 0; i < tableSize; i++) {
            buckets.add(new LinkedList<>());
        }
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

    public void insert(DataHashTable data) {

        // hexadecimal number provides only positive numbers
        int index = (data.key & 0x7fffffff) % (tableSize-1);

        // no duplicity
        for (int i = 0; i < buckets.get(index).size(); i++) {
            if (data.key == buckets.get(index).get(i).key) {
                buckets.get(index).get(i).value = data.value;
                return;
            }
        }

        buckets.get(index).add(data);
        countNodes++;

        if (((float) countNodes) / tableSize > 0.7f) {
            resize(this.tableSize*2);
        }
    }

    void resize(int sizeOfTable) {

        ArrayList<LinkedList<DataHashTable>> newTable = new ArrayList<>();

        // create empty table of new size
        for (int i = 0; i < sizeOfTable; i++) {
            newTable.add(new LinkedList<>());
        }

        ArrayList<LinkedList<DataHashTable>> oldTable = buckets;
        this.buckets = newTable;
        this.tableSize = sizeOfTable;
        this.countNodes = 0;

        for (LinkedList<DataHashTable> bucket : oldTable) {
            for (DataHashTable data : bucket) {
                insert(data);
            }
        }
    }

    public void delete(int key) {

        int index = (key & 0x7fffffff) % (tableSize-1);

        if (buckets.get(index) == null) {
            return;
        }

        for (int i = 0; i < buckets.get(index).size(); i++) {
                if (key == buckets.get(index).get(i).key) {
                    buckets.get(index).removeFirstOccurrence(buckets.get(index).get(i));
                    countNodes--;
                }
        }

        if (((float) countNodes) / tableSize < 0.3f) {
            resize(this.tableSize/2);
        }
    }

    public boolean search(int key) {

        int index = (key & 0x7fffffff) % (tableSize-1);

        if (buckets.get(index) == null) {
            return false;
        }

        for (int i = 0; i < buckets.get(index).size(); i++) {
            DataHashTable currentData = buckets.get(index).get(i);
            if (key == buckets.get(index).get(i).key) {
                return true;
            }
        }

        return false;
    }
}
