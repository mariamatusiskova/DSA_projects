package sk.stuba.fiit.Tables;

import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.Node;

import java.util.HashMap;
import java.util.Map;

public class StoreNodeBDD {

    Map<Integer, Node> table;

    // TODO: Expression???
    public StoreNodeBDD(int numberOfVariables) {
        table = new HashMap<>();
        // initialization of table with terminal nodes 0 and 1
        // string, node --> low, high, level
        table.put(0, new Node(numberOfVariables, null, null, null));
        table.put(1, new Node(numberOfVariables, null, null, null));
    }

    public Node insert(int variableIndex, Node low, Node high, Expression expression) {

        Integer key = table.size();

        if (table.containsKey(key)) {
            return table.get(key);
        } else {
            Node newNode = new Node(variableIndex, low, high, expression);
            table.put(key, newNode);
            return newNode;
        }
    }

    public Node search(int indexOfNode) {
        return table.get(indexOfNode);
    }
}
