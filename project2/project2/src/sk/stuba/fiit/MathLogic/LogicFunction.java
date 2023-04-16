package sk.stuba.fiit.MathLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class LogicFunction extends Expression {
    List<Expression> children;

    public abstract Expression replace(String variable, Boolean bool);
}
