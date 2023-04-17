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
        for (Expression child: children) {
            if(child.evaluate(values)){
                return true;
            }
        }
        return false;
    }

    public static Or replace(String variable, HashMap<String, Boolean> values, Or or, Boolean bool) {

        List<Expression> expr = new ArrayList<>();

        values.put(variable, bool);

        for (Expression child: or.children) {
            if (child.evaluate(values)) {
                expr.add(child);
            }
        }

        for (int i = 0; i < expr.size(); i++) {
            And and = (And) expr.get(i);
            expr.set(i, And.reduce(variable, values, and));
        }

        values.put(variable, null);

        return new Or(expr);
    }
}
