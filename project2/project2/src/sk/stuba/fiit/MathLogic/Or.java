package sk.stuba.fiit.MathLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Or extends LogicFunction {

    public Or(String input) {
        parseInput(input);
    }

    public Or(List<Expression> children) {
        this.children = children;
    }

    @Override
    public void parseInput(String input) {

        String[] splitInput = input.trim().split("\\s*\\+\\s*");
        for (String str : splitInput) {
            children.add(new And(str));
        }
    }

    @Override
    public Boolean evaluate(HashMap<String, Boolean> values) {

        boolean allChildrenAreNull = true;

        if (children.isEmpty()) {
            return false;
        }

        for (Expression child: children) {
            Boolean value = child.evaluate(values);
            if (value != null) {
                if (value) {
                    return true;
                }
                allChildrenAreNull = false;
            }
        }

        return allChildrenAreNull ? null : false;
    }

    @Override
    public String toString() {
        return String.join("+", children.stream().map(ch -> ch.toString()).toList());
    }

    public List<Expression> replace(String variable, HashMap<String, Boolean> values, Boolean bool) {

        List<Expression> newChildren = new ArrayList<>();

        values.put(variable, bool);

        for (Expression child: children) {
            Boolean value = child.evaluate(values);
            if (value == null || value) {
                newChildren.add(child);
            }
        }

        for (int i = 0; i < newChildren.size(); i++) {
            And and = (And) newChildren.get(i);
            newChildren.set(i, and.reduce(values));
        }

        values.put(variable, null);
        return newChildren;
    }

}
