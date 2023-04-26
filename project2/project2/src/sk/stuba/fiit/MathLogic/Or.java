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

        String[] splitInput = input.split("\\+");
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
            if (child.evaluate(values) != null) {
                if (child.evaluate(values)) {
                    return true;
                }
                allChildrenAreNull = false;
            }
        }

        return allChildrenAreNull ? null : false;
    }

    public void replace(String variable, HashMap<String, Boolean> values, Boolean bool) {

        List<Expression> newChildren = new ArrayList<>();

        values.put(variable, bool);

        for (Expression child: children) {
            if (child.evaluate(values) == null || child.evaluate(values)) {
                newChildren.add(child);
            }
        }

        for (int i = 0; i < newChildren.size(); i++) {
            And and = (And) newChildren.get(i);
            and.reduce(values);
        }

        values.put(variable, null);
        children = newChildren;
    }

}
