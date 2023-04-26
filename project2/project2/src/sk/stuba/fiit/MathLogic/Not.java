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
    public Boolean evaluate(HashMap<String, Boolean> values) {

        Boolean value = child.evaluate(values);

        if (value != null) {
            return !value;
        }

        return null;
    }

    public Expression getChild() {
        return this.child;
    }

    public void setChild(Expression child) {
        this.child = child;
    }

}
