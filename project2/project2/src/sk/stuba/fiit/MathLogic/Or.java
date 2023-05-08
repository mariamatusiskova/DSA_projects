package sk.stuba.fiit.MathLogic;

import java.util.*;

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
    public Boolean evaluate() {

        boolean allChildrenAreNull = true;

        if (children.isEmpty()) {
            return false;
        }

        for (Expression child: children) {
            child.setValues(values);
            Boolean value = child.evaluate();
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

    public List<Expression> replace() {

        List<Expression> newChildren = new ArrayList<>();

        for (Expression child: children) {
            child.setValues(values);
            Boolean value = child.evaluate();
            if (value == null || value) {
                newChildren.add(child);
            }
        }

        reduceAnds(newChildren);
        return newChildren;
    }

    private void reduceAnds(List<Expression> ands){
        for (int i = 0; i < ands.size(); i++) {
            And and = (And) ands.get(i);
            ands.set(i, and.reduce());
        }
    }
}
