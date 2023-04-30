package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public abstract class Expression {

    protected abstract void parseInput(String str);
    public abstract Boolean evaluate(HashMap<String, Boolean> values);

    public abstract String toString();

    public boolean equals(Expression expression) {
        return this.toString().equals(expression.toString());
    }
}
