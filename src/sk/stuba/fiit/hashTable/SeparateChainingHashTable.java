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

    public class HashNode {

        public Data data;
        public HashNode next;

        public HashNode(Data data) {
            this.data = data;
            this.next = null;
        }
    }

    private HashNode buckets[];
    private int tableSize;
    private int countNodes;

    // default constructor
    SeparateChainingHashTable(int tableSize) {

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

    // main
    void callInsert(Data data) {
        SeparateChainingHashTable table = new SeparateChainingHashTable(2);
        table = insert(table, data);
    }

    void callDelete(Data data) {
        SeparateChainingHashTable table = new SeparateChainingHashTable(2);
        table = delete(table, data);
    }

    SeparateChainingHashTable insert(SeparateChainingHashTable hashTable, Data data) {

        // hexadecimal number provides only positive numbers
        int index = (data.key & 0x7fffffff) % tableSize;

        for (HashNode node = buckets[index]; node != null; node = node.next) {
            if (data.compareTo(node.data) == 0) {
                node.next.data = node.data;
                return hashTable;
            }
        }

        if (buckets[index] == null) {
            buckets[index] = new HashNode(data);
            countNodes++;
        }

        if (countNodes / tableSize > 0.7f) {
            hashTable = hashTable.rezise(this.tableSize*2);
        }

        return hashTable;
    }

    SeparateChainingHashTable rezise(int sizeOfTable) {
        // in constructor
        SeparateChainingHashTable newHashTable = new SeparateChainingHashTable(sizeOfTable);

        for (int i = 0; i < tableSize; i++) {
            for (HashNode node = buckets[i]; node!= null; node = node.next) {
                newHashTable = newHashTable.insert(newHashTable ,node.data);
            }
        }

        return  newHashTable;

    }

    SeparateChainingHashTable delete(SeparateChainingHashTable hashTable, Data data) {

        int index = (data.key & 0x7fffffff) % tableSize;
        buckets[index] = deleteRecursive(buckets[index], data);

        if (countNodes / tableSize < 0.3f) {
            hashTable = hashTable.rezise(this.tableSize/2);
        }

        return hashTable;
    }

    HashNode deleteRecursive(HashNode node, Data data) {

        if (node == null) {
            node = null;
        }

        if (data.compareTo(node.data) == 0) {
            node = node.next;
            countNodes--;
        }

        node.next = deleteRecursive(node.next, data);
        return node;
    }

    boolean search(Data data) {

        int index = (data.key & 0x7fffffff) % tableSize;

        if (buckets[index] == null) {
            return false;
        }

        for (HashNode node = buckets[index]; node != null; node = node.next) {
            if (data.compareTo(node.data) == 0) {
                System.out.println("searched value: " + data.key + "|" + data.value + ", found value: " + node.data.key + "|" + node.data.value);
                return true;
            }
        }

        return false;
    }


//       Data searchData = hashTable.get(index).data;
//
//       int index = hash(key, table->size); // determine which bucket to search
//       Node* node = table->buckets[index];
//       while (node != NULL) {
//           if (node->key == key) {
//               return &(node->value); // return a pointer to the value if the key is found
//           }
//           node = node->next;
//       }
//       return NULL; // return
    // }

//   void resizeHashTable() {
//        // idk
//        HashNode[] oldHashTable = hashTable;
//        hashTable = new HashNode[MAX_SIZE]
//   }


//        int index = (hashNode(data));
//        if (hashTable[index] == null) {
//            hashTable[index] = new HashNode(data);
//         } else {
//            HashNode entry = hashTable[index];
//            while (entry.next != null && entry.data.compareTo(data) != 0)
//                entry = entry.next;
//            if (entry.data.compareTo(data) == 0) {
//                entry.data = data;
//            } else {
//                entry.next = new HashNode(data);
//            }
//        }
//        countNodes++;
//    }
}
