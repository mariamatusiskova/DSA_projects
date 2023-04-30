package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public class Variable extends Expression {

    private String name;
    private Boolean lastNodeValue = null;

    public Variable(String input) { parseInput(input); }

    public Variable(boolean lastNodeValue) {
        this.lastNodeValue = lastNodeValue;
    }

    @Override
    public void parseInput(String input) {
        this.name = input;
    }

    @Override
    public Boolean evaluate(HashMap<String, Boolean> values) {

        if (lastNodeValue != null) {
            return lastNodeValue;
        }

        return values.get(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
