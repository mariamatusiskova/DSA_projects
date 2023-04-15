package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public class Not extends Expression {

    private Expression child;

    public Not(String input) {
        parseInput(input);
    }

    @Override
    public void parseInput(String input) {
        // substring from index to end, it won't contain '!'
        setChild(new Variable(input.substring(1)));
    }

    @Override
    public boolean evaluate(HashMap<String, Boolean> values) {
        return !child.evaluate(values);
    }

    public Expression getChild() {
        return this.child;
    }

    public void setChild(Expression child) {
        this.child = child;
    }

}
