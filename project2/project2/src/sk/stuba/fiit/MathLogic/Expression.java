package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public abstract class Expression {

    protected abstract void parseInput(String str);
    public abstract boolean evaluate(HashMap<String, Boolean> values);
}
