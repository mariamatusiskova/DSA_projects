package sk.stuba.fiit.MathLogic;

import java.util.HashMap;

public class Or extends LogicFunction {

    public Or(String input) {
        parseInput(input);
    }

    @Override
    public void parseInput(String input) {

        String[] splitInput = input.split("\\+");
        for (String str : splitInput) {
            children.add(new And(str));
        }
    }

    @Override
    public boolean evaluate(HashMap<String, Boolean> values) {
        for (Expression child: children) {
            if(child.evaluate(values)){
                return true;
            }
        }
        return false;
    }
}
