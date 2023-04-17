package sk.stuba.fiit.MathLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class And extends LogicFunction {

    public And(String input) {
        parseInput(input);
    }

    public And(List<Expression> children) {
        this.children = children;
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
    public Boolean evaluate(HashMap<String, Boolean> values) {
        for (Expression child: children) {
            if (!child.evaluate(values)){
                return false;
            }
        }
        return true;
    }

    public static And reduce(String variable, HashMap<String, Boolean> values, And and) {

        List<Expression> expr = new ArrayList<>();

        for (Expression child: and.children) {
            if (child.evaluate(values)) {
                expr.add(child);
            }
        }

        return new And(expr);
    }
}
