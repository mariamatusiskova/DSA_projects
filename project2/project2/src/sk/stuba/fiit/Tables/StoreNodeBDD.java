package sk.stuba.fiit.Tables;

import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.Node;

import java.util.HashMap;
import java.util.Map;

public class StoreNodeBDD {

    Map<Integer, Node> table;

    // TODO: Expression???
    public StoreNodeBDD(int numberOfVariables, Node node) {
        table = new HashMap<>();
        // initialization of table with terminal nodes 0 and 1
        // string, node --> low, high, level
        table.put(0, node);
        table.put(1, node);
    }

    public Node insert(Node node) {

        Integer key = table.size();

        if (table.containsKey(key)) {
            return table.get(key);
        } else {
            Node newNode = node;
            table.put(key, newNode);
            return newNode;
        }
    }

    public int getSize() {
        return table.size();
    };

    public Node search(int indexOfNode) {
        return table.get(indexOfNode);
    }
}
