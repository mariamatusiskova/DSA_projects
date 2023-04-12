// Source:
// https://www.geeksforgeeks.org/implementing-hash-table-open-addressing-linear-probing-cpp/
package sk.stuba.fiit.hashTable;

import java.util.ArrayList;

public class OpenAddressingHashTable {

    public ArrayList<DataHashTable> buckets = new ArrayList<>();
    public int tableSize;
    public int sentSizeOfTable;
    public int countNodes;
    public DataHashTable deleted = new DataHashTable(-1, "deleted");

    // default constructor
    public OpenAddressingHashTable(int tableSize) {

        this.sentSizeOfTable = tableSize;
        this.tableSize = tableSize;
        this.countNodes = 0;

        for (int i = 0; i < tableSize; i++) {
            buckets.add(null);
        }
    }

    public void insert(DataHashTable data) {

        if (data == null) {
            return;
        }

        int index = (data.key & 0x7fffffff) % (tableSize-1);

        while (buckets.get(index) != null && buckets.get(index).key != data.key) {
            if (buckets.get(index).key == deleted.key) {
                break;
            }
            index = (index + 1) % (tableSize-1);
        }

        if (buckets.get(index) == null || buckets.get(index).key == deleted.key) {
            countNodes++;
        }
        buckets.set(index, data);

        if (((float)countNodes)/tableSize > 0.7f) {
            resize(this.tableSize*2);
        }
    }

    public void delete(int key) {

        int index = (key & 0x7fffffff) % (tableSize-1);

        while (buckets.get(index) != null) {

            if (buckets.get(index).key == key) {
                buckets.set(index, deleted);
                countNodes--;
            }
            index = (index + 1) % (tableSize-1);
        }

        if (((float)countNodes)/tableSize < 0.3f) {
            resize(this.tableSize/2);
        }
    }

    public boolean search(int key) {

        int index = (key & 0x7fffffff) % (tableSize-1);

        while (buckets.get(index) != null) {

            if (buckets.get(index).key == key) {
                DataHashTable currentData = buckets.get(index);
                return true;
            }
            index = (index + 1) % (tableSize-1);
        }

        return false;
    }

    private void resize(int sizeOfTable) {

        ArrayList<DataHashTable> newBuckets = new ArrayList<>();
        for (int i = 0; i < sizeOfTable; i++) {
            newBuckets.add(null);
        }
        ArrayList<DataHashTable> oldBuckets = buckets;

        this.buckets = newBuckets;
        this.tableSize = sizeOfTable;
        this.countNodes = 0;

        for (DataHashTable data : oldBuckets) {
            insert(data);
        }
    }
}
