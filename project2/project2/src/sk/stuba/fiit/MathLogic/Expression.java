package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public abstract class Expression {

    protected HashMap<String, Boolean> values;

    protected abstract void parseInput(String str);
    public abstract Boolean evaluate();

    public abstract String toString();

    public boolean equals(Expression expression) {
        return this.toString().equals(expression.toString());
    }

    public HashMap<String, Boolean> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Boolean> values) {
        this.values = values;
    }
}
