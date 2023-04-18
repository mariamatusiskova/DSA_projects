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
        boolean allChildrenAreNull = true;
        for (Expression child: children) {
            if (!child.evaluate(values)){
                return false;
            } else if (child.evaluate(values) != null) {
                allChildrenAreNull = false;
            }
        }
        return allChildrenAreNull ? null : true;
    }

    public void reduce(HashMap<String, Boolean> values) {

        List<Expression> newChildren = new ArrayList<>();

        //values.put(variable, bool);

        for (Expression child: children) {
            // reduce true variables
            if (child.evaluate(values) == false || child.evaluate(values) == null) {  // !child.evaluate(values)
                newChildren.add(child);
            }
        }

        //values.put(variable, null);

        children = newChildren;
    }
}
