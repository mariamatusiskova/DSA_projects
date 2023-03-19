// Source of studying: https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html

package sk.stuba.fiit.program;

import java.util.Comparator;
import java.util.Objects;

public class Data implements Comparable<Data> {

    public int number;

    // random String
    public String value;

    // key hashed from string
    public int key;

    public Data(String value, int number) {
        this.value = value;
        this.number = number;
        this.key = value.hashCode();
    }

    public int compareTo(Data other){
        if(this.key > other.key) return 1;
        if(this.key == other.key) return 0;
        return -1;
    }
}
