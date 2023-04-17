package sk.stuba.fiit;// sk.stuba.fiit.ROBDD - Reduced ordered binary decision diagram

import sk.stuba.fiit.MathLogic.Or;
import sk.stuba.fiit.Tables.ReductionTableBDD;
import sk.stuba.fiit.Tables.StoreNodeBDD;

import java.util.HashMap;
import java.util.HashSet;

public class ROBDD {

    private int numberOfVariables;

    // table for storing nodes
    private StoreNodeBDD storeTable;

    // ensure if BDD is reduces --> store only new nodes
    private ReductionTableBDD reductionTable;
    private String bfunction;

    public HashMap<String, Boolean> values = new HashMap<>();
    private Node root;

    public ROBDD(Node root) {
        this.root = root;
    }

    public ROBDD(String bfunction) {
        this.bfunction = bfunction;
    }

    public Node BDD_create(String bfunction, String order) {
        return BDD_create_helper(bfunction, order, 0);
    }

    private Node BDD_create_helper(String bfunction, String order, int variableIndex) {

        Or or = new Or(bfunction);

        this.numberOfVariables = countAndStoreVariables(bfunction);

        if (variableIndex > this.numberOfVariables) {
            boolean value = or.evaluate(values);
            return value ? new Node(1, null, null, null) : new Node(0, null, null, null);
        } else {

        }

        return null;
    }

    private Integer countAndStoreVariables(String bfunction) {
        HashSet<Character> variables = new HashSet<>();
        for (char c : bfunction.toCharArray()) {
            if (Character.isLetter(c)) {
                variables.add(c);
                values.put(c + "", null);
            }
        }
        return variables.size();
    }

    public ROBDD BDD_create_with_best_order(String bfunction) {

        return null;
    }

    public char BDD_use(ROBDD bdd, String inputs) {

        return 'a';
    }

    public Node checkReductionBDD(Node node, StoreNodeBDD storeTable, ReductionTableBDD reductionTable) {

        int indexOfNode;

        if (node.getLow() == node.getHigh()) {
            return node.getLow();
        } else if (reductionTable.check(node)) {
            indexOfNode = reductionTable.search(node);
            return storeTable.search(indexOfNode);
        } else {
            Node newNode = storeTable.insert(node);
            indexOfNode = storeTable.getSize() - 1;
            reductionTable.insert(indexOfNode, newNode);
            return node;
        }
    }

}
