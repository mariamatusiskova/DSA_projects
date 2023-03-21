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

// linked list at the index in the hash table
public class SeparateChainingHashTable {

    public class HashNode {

        public Data data;
        public HashNode next;

        public HashNode(Data data, HashNode nextNode) {
            this.data = data;
            this.next = nextNode;
        }
    }

    public HashNode buckets[];
    public int tableSize;
    public int countNodes;

    // default constructor
    public SeparateChainingHashTable(int tableSize) {

        this.tableSize = tableSize;
        this.buckets = new HashNode[tableSize];
        this.countNodes = 0;
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

    public SeparateChainingHashTable insert(SeparateChainingHashTable hashTable, Data data) {

        // hexadecimal number provides only positive numbers
        int index = (data.key & 0x7fffffff) % tableSize;

//        if (data == null) {
//            delete(hashTable, data);
//            return hashTable;
//        }

        for (HashNode node = buckets[index]; node != null && node.next != null; node = node.next) {
            if (data.key == node.data.key) {
                node.next.data = node.data;
                return hashTable;
            }
        }

        if (countNodes / tableSize > 0.7f) {
            hashTable = hashTable.resize(this.tableSize*2);
        }

        buckets[index] = new HashNode(data, buckets[index]);
        countNodes++;

        return hashTable;
    }

    SeparateChainingHashTable resize(int sizeOfTable) {
        // in constructor
        SeparateChainingHashTable newHashTable = new SeparateChainingHashTable(sizeOfTable);

        for (int i = 0; i < tableSize; i++) {
            for (HashNode node = buckets[i]; node!= null; node = node.next) {
                newHashTable = newHashTable.insert(newHashTable ,node.data);
            }
        }

        return  newHashTable;

    }

    public SeparateChainingHashTable delete(SeparateChainingHashTable hashTable, Data data) {

        int index = (data.key & 0x7fffffff) % tableSize;
        buckets[index] = deleteRecursive(buckets[index], data);

        if (countNodes / tableSize < 0.3f) {
            hashTable = hashTable.resize(this.tableSize/2);
        }

        return hashTable;
    }

    HashNode deleteRecursive(HashNode node, Data data) {

        if (node == null) {
            return null;
        }

        if (data.key == node.data.key) {
            countNodes--;
            return node.next;
        }

        node.next = deleteRecursive(node.next, data);
        return node;
    }

    public boolean search(Data data) {

        int index = (data.key & 0x7fffffff) % tableSize;

        if (buckets[index] == null) {
            System.out.println("searched value: " + data.key + "|" + data.value + " --> false");
            return false;
        }

        for (HashNode node = buckets[index]; node != null; node = node.next) {
            if (data.key == node.data.key) {
                System.out.println("searched value: " + data.key + "|" + data.value + " --> true");
                return true;
            }
        }

        System.out.println("searched value: " + data.key + "|" + data.value + " --> false");
        return false;
    }
}
