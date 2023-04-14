public class Not extends Expression {

    private Expression child;

    @Override
    public boolean evaluate() {
        return !child.evaluate();
    }

    public Expression getChild() {
        return this.child;
    }

    public void setChild(Expression child) {
        this.child = child;
    }

}
