// Source of studying: https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html

package sk.stuba.fiit.program;

import java.util.Comparator;
import java.util.Objects;

public class Data implements Comparable<Data> {

    // random String
    public String value;

    // key hashed from string
    public int key;

    public Data(String value) {
        this.value = value;
        this.key = value.hashCode();
    }


    // https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html
//    public int hashCodeBST() {
//
//        int h = 0;
//
//        int firstIdxOfStorage = value != null && value.length() > 0 ? value.charAt(0) : 0;
//        char storeChars[] = value.toCharArray();
//        int length = value.length();
//
//        for (int i = 0; i < length; i++) {
//            h = 31 * h + storeChars[firstIdxOfStorage++];
//        }
//
//        return h;
//    }

    public int compareTo(Data other){
        if(this.key > other.key) return 1;
        if(this.key == other.key) return 0;
        return -1;
    }
}
