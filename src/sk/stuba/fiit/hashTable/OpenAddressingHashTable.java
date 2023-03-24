// Source:
// https://www.geeksforgeeks.org/implementing-hash-table-open-addressing-linear-probing-cpp/
package sk.stuba.fiit.hashTable;

import sk.stuba.fiit.program.DataHashTable;

import java.util.ArrayList;

public class OpenAddressingHashTable {

    public ArrayList<Data> buckets = new ArrayList<DataHashTable>();
    public int tableSize;
    public int countNodes;
    public Data deleted = new Data("deleted", 0);



    // default constructor
    public OpenAddressingHashTable(int tableSize) {

        this.tableSize = tableSize;
        this.countNodes = 0;

        for (int i = 0; i < tableSize; i++) {
            buckets.add(null);
        }
    }

    public void insert(Data data) {

        int index = (data.key & 0x7fffffff) % (tableSize-1);
        while (buckets.get(index) != null && buckets.get(index).compareTo(data) != 0) {
            if (buckets.get(index) == deleted) {
                break;
            }
            index = (index + 1) % (tableSize-1);
        }

        if (buckets.get(index) == null || buckets.get(index) == deleted) {
            countNodes++;
        }
        buckets.set(index, data);
    }

    public void delete(Data data) {

        int index = (data.key & 0x7fffffff) % (tableSize-1);
        while () {

        }

    }

    private void resize() {

    }
}
