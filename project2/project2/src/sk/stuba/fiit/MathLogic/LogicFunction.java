package sk.stuba.fiit.MathLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LogicFunction extends Expression {
    protected List<Expression> children;

    public List<Expression> getChildren() {
        return children;
    }
}
