package sk.stuba.fiit;

import sk.stuba.fiit.MathLogic.Expression;

import java.util.HashMap;
import java.util.Objects;

public class Node {

    // false --> 0
    private Node low;
    // true --> 1
    private Node high;
    int variableIndex;
    private Expression expression;
    public String order;

    // testing
    public double reductionPercentage;

    public Node() {}

    public Node(int variableIndex, Node low, Node high, Expression expression) {

        this.low = low;
        this.high = high;
        this.variableIndex = variableIndex;
        this.expression = expression;
    }

    public Node getLow() {
        return low;
    }

    public void setLow(Node low) {
        this.low = low;
    }

    public void setHigh(Node high) {
        this.high = high;
    }

    public Node getHigh() {
        return high;
    }

    public int getVariableIndex() {
        return variableIndex;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;

        return variableIndex == node.variableIndex
                && Objects.equals(low, node.low)
                && Objects.equals(high, node.high)
                && expression.equals(node.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(low, high, variableIndex);
    }
}
