package sk.stuba.fiit.Tables;

import sk.stuba.fiit.Node;

import java.util.HashMap;
import java.util.Map;

public class StoreNodeBDD {

    Map<String, Node> table;

    public StoreNodeBDD() {
        table = new HashMap<>();
        // initialization of table with terminal nodes 0 and 1
        // string, node --> low, high, level
        table.put("0,0,0", new Node(null, null, -1));
        table.put("0,0,1", new Node(null, null, -1));
    }

    public Node insert(Node low, Node high, int level) {
        String key = low.getLevel() + "," + high.getLevel() + "," + level;
        if (table.containsKey(key)) {
            return table.get(key);
        } else {
            Node newNode = new Node(low, high, level);
            table.put(key, newNode);
            return newNode;
        }
    }

    public Node search(Node low, Node high, int level) {
        String key = low.getLevel() + "," + high.getLevel() + "," + level;
        return table.get(key);
    }
}
