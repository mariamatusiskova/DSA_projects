// Source of studying: https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html

package sk.stuba.fiit.program;

public class Data {

    // unique for each object
    int value;

    // random String
    String data;

    // key hashed from string
    int key;

    // # variables for hashing #
    private char storeChars[];
    private int firstIndexOfStorage;
    private int numOfCharsInString;

    Data(int value) {
        this.value = value;
    }

    public int hashCodeBST() {

        int h = 0;

        int copyOfFirstIdxOfStorage = firstIndexOfStorage;
        char copyOfStoreChars[] = storeChars;
        int length = numOfCharsInString;

        for (int i = 0; i < length; i++) {
            h = 31 * h + copyOfStoreChars[copyOfFirstIdxOfStorage++];
        }

        return h;
    }
}
