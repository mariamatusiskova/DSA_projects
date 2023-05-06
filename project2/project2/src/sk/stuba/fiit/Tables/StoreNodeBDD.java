package sk.stuba.fiit.Tables;

import sk.stuba.fiit.LeafNode;
import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.Node;

import java.util.HashMap;
import java.util.Map;

public class StoreNodeBDD {

    Map<Integer, Node> table;

    public StoreNodeBDD() {
        table = new HashMap<>();
        // initialization of table with terminal nodes 0 and 1
        table.put(0,  new LeafNode(false));
        table.put(1, new LeafNode(true));
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
