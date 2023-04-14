public class Node {

    // false --> 0
    private Node low;

    // true --> 1
    private Node high;

    private int level;
    private int index;

    public Node() {}

    public Node(Node low, Node high, int level) {

        this.low = low;
        this.high = high;
        this.level = level;
    }

    public Node getLow() {
        return low;
    }

    public Node getHigh() {
        return high;
    }

    public int getLevel() {
        return level;
    }

    public int getIndex() {
        return index;
    }
}
