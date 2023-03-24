// Source of studying: https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html

package sk.stuba.fiit.program;

public class DataHashTable implements Comparable<DataHashTable> {

    // random String
    public String value;

    // key hashed from string
    public int key;

    public DataHashTable(int key, String value) {
        this.value = value;
        this.key = key;
    }

    public int compareTo(DataHashTable other){
        if(this.key > other.key) return 1;
        if(this.key == other.key) return 0;
        return -1;
    }
}
