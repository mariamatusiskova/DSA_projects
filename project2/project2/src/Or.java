import java.util.List;

public class Or extends LogicFunction {

    public Or(String input) {
        this.input = input;
    }

    @Override
    protected void parseInput() {
        String[] splitInput = input.split("\\+");
        for (String str : splitInput) {
            children.add(new And(str));
        }
    }

    @Override
    public boolean evaluate() {
        for (Expression child: children) {
            if(child.evaluate()){
                return true;
            }
        }
        return false;
    }
}
