package sk.stuba.fiit;// sk.stuba.fiit.ROBDD - Reduced ordered binary decision diagram

import sk.stuba.fiit.MathLogic.Expression;
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
    private String order;
    private String bfunction;

    public HashMap<String, Boolean> values = new HashMap<>();
    private Node root;

    public ROBDD(Node root) {
        this.root = root;
    }

    public ROBDD(String bfunction, String order) {

        this.storeTable = new StoreNodeBDD();
        this.reductionTable = new ReductionTableBDD();
        this.order = order;
        this.bfunction = bfunction;
    }

    public Node callBDD_create() {
        return BDD_create(bfunction, "1");
    }

    private Node BDD_create(String bfunction, String order) {

        Expression expr = new Or(bfunction);

        int orderInt = 0;
        try {
            orderInt = Integer.parseInt(order);
        } catch (NumberFormatException e) {
            System.out.println("The string is not a valid integer");
        }

        HashSet<Character> countVariables = new HashSet<>();
        for (char c : bfunction.toCharArray()) {
            if (Character.isLetter(c)) {
                countVariables.add(c);
            }
        }
        this.numberOfVariables = countVariables.size();

        if (orderInt > this.numberOfVariables) {
            boolean value = expr.evaluate(values);
            return value ? new Node(null, null, 1) : new Node(null, null, 0);
        }

        return null;
    }

    public ROBDD BDD_create_with_best_order(String bfunction) {

        return null;
    }

    public char BDD_use(ROBDD bdd, String inputs) {

        return 'a';
    }

    public Node checkReductionBDD(Node low, Node high, int level, StoreNodeBDD storeTable, ReductionTableBDD reductionTable) {

        if (low == high) {
            return low;
        } else if (reductionTable.check(low, high, level)) {
            return reductionTable.search(low, high, level);
        } else {
            Node node = storeTable.insert(low, high, level);
            reductionTable.insert(low, high, level, node);
            return node;
        }
    }

}
