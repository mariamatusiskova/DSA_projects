package sk.stuba.fiit.Tables;

import sk.stuba.fiit.Node;

import java.util.HashMap;
import java.util.Map;

public class ReductionTableBDD {

    Map<Node, Integer> table;

    public ReductionTableBDD() {
        table = new HashMap<>();
    }

    public boolean check(Node node) {
        Node key = node;
        return table.containsKey(key);
    }

    public Integer search(Node node) {
        Node key = node;
        return table.get(key);
    }

    public void insert(int indexOfNode, Node node) {
        Node key = node;
        table.put(key, indexOfNode);
    }

    public int countNodes() {
        return table.size();
    }
}
