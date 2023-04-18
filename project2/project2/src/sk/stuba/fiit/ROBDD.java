package sk.stuba.fiit;// sk.stuba.fiit.ROBDD - Reduced ordered binary decision diagram

import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.MathLogic.Or;
import sk.stuba.fiit.MathLogic.Variable;
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
        this.numberOfVariables = countAndStoreVariables(bfunction);
        this.storeTable = new StoreNodeBDD(numberOfVariables, new Node(numberOfVariables-1, null, null, null));
        this.reductionTable = new ReductionTableBDD();
    }

    public Node BDD_create(String bfunction, String order) {
        return BDD_create_helper(new Or(bfunction), order, 0);
    }

    private Node BDD_create_helper(Or or, String order, int variableIndex) {

//        Or.replace(, values, or, false);
//        Or.replace(order, values, or, true);


        if (variableIndex > this.numberOfVariables) {
            boolean value = or.evaluate(values);
            return new Node(0, null, null, new Variable(value + ""));
        } else {

            Or orZero = new Or(or.getChildren());
            orZero.replace(order.charAt(variableIndex) + "", values, false);

            Or orOne = new Or(or.getChildren());
            orOne.replace(order.charAt(variableIndex) + "", values, true);

            Node nodeZero = BDD_create_helper(orZero, order, variableIndex + 1);
            Node nodeOne = BDD_create_helper(orOne, order, variableIndex + 1);

            return checkReductionBDD(new Node(variableIndex, nodeZero, nodeOne, or), storeTable, reductionTable);
        }
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
