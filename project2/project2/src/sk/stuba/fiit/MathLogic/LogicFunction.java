package sk.stuba.fiit.MathLogic;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicFunction extends Expression {
    protected List<Expression> children = new ArrayList<>();

    public List<Expression> getChildren() {
        return children;
    }
}
