package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public class And extends LogicFunction {

    public And(String input) {
        parseInput(input);
    }

    @Override
    public void parseInput(String input) {

        String[] splitInput = input.split("\\.");
        for (String str : splitInput) {
            if (input.startsWith("!")) {
                children.add(new Not(str));
            } else {
                children.add(new Variable(str));
            }
        }
    }

    @Override
    public boolean evaluate(HashMap<String, Boolean> values) {
        for (Expression child: children) {
            if (!child.evaluate(values)){
                return false;
            }
        }
        return true;
    }
}
