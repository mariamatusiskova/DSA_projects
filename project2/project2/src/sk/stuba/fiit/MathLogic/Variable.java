package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public class Variable extends Expression {

    private String name;

    public Variable(String input) { parseInput(input); }

    @Override
    public void parseInput(String input) {
        this.name = input;
    }

    @Override
    public Boolean evaluate(HashMap<String, Boolean> values) {
        return values.get(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
