public class And extends LogicFunction {


    public And(String str) {

    }

    @Override
    protected void parseInput() {

    }

    @Override
    public boolean evaluate() {
        for (Expression child: children) {
            if (!child.evaluate()){
                return false;
            }
        }
        return true;
    }
}
