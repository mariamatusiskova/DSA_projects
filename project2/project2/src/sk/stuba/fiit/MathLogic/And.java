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

        String[] splitInput = input.trim().split("\\s*\\.\\s*");
        for (String str : splitInput) {
            if (str.startsWith("!")) {
                children.add(new Not(str));
            } else {
                children.add(new Variable(str));
            }
        }
    }

    @Override
    public Boolean evaluate(HashMap<String, Boolean> values) {
        boolean allChildrenAreNull = true;

        if (children.isEmpty()) {
            return true;
        }

        for (Expression child: children) {

            Boolean value = child.evaluate(values);

            if (value != null) {
                if (!value) {
                    return false;
                }
                allChildrenAreNull = false;
            }
        }

        return allChildrenAreNull ? null : true;
    }

    public Expression reduce(HashMap<String, Boolean> values) {
        List<Expression> newChildren = new ArrayList<>();

        for (Expression child: children) {

            // reduce true variables
            Boolean value = child.evaluate(values);
            if (value == null || value == false) {
                newChildren.add(child);
            }
        }

        Expression combinedExpression = new And(newChildren);

        return combinedExpression;
    }

    @Override
    public String toString() {
        return String.join(".", children.stream().map(ch -> ch.toString()).toList());
    }
}
