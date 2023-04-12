public class Node {

    // false --> 0
    private Node low;

    // true --> 1
    private Node high;
    private int variable;

    public Node(Node low, Node high, int variable) {

        this.low = low;
        this.high = high;
        this.variable = variable;
    }

    public Node getLow() {
        return low;
    }

    public Node getHigh() {
        return high;
    }

    public int getVariable() {
        return variable;
    }
}
