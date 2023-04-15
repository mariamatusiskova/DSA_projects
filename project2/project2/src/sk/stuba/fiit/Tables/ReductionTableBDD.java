package sk.stuba.fiit.Tables;

import sk.stuba.fiit.Node;

import java.util.HashMap;
import java.util.Map;

public class ReductionTableBDD {

    Map<String, Node> table;

    public ReductionTableBDD() {
        // empty
        table = new HashMap<>();
    }

    public boolean check(Node low, Node high, int level) {
        String key = low.getLevel() + "," + high.getLevel() + "," + level;
        return table.containsKey(key);
    }

    public Node search(Node low, Node high, int level) {
        String key = low.getLevel() + "," + high.getLevel() + "," + level;
        return table.get(key);
    }

    public void insert(Node low, Node high, int level, Node node) {
        String key = low.getLevel() + "," + high.getLevel() + "," + level;
        table.put(key, node);
    }
}
