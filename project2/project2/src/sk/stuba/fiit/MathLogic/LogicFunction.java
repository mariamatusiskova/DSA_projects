package sk.stuba.fiit.MathLogic;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicFunction extends Expression {
    List<Expression> children;

    public LogicFunction() {
        children = new ArrayList<>();
    }
}
