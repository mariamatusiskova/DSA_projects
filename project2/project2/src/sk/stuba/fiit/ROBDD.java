package sk.stuba.fiit;// sk.stuba.fiit.ROBDD - Reduced ordered binary decision diagram

import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.MathLogic.Or;
import sk.stuba.fiit.MathLogic.Variable;
import sk.stuba.fiit.Tables.ReductionTableBDD;
import sk.stuba.fiit.Tables.StoreNodeBDD;

import java.util.HashMap;
import java.util.HashSet;

public class ROBDD {

    HashSet<Character> variables = new HashSet<>();
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
        this.storeTable = new StoreNodeBDD(new Node(numberOfVariables-1, null, null, null));
        this.reductionTable = new ReductionTableBDD();
    }

    // BUILD
    public Node BDD_create(String bfunction, String order) {
        root = BDD_create_helper(new Or(bfunction), order, 0);
        return root;
    }

    private Node BDD_create_helper(Or or, String order, int variableIndex) {

        if (variableIndex > this.numberOfVariables-1) {
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
        for (char c : bfunction.toCharArray()) {
            if (Character.isLetter(c)) {
                variables.add(c);
                values.put(c + "", null);
            }
        }
        return variables.size();
    }

    private Node checkReductionBDD(Node node, StoreNodeBDD storeTable, ReductionTableBDD reductionTable) {

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

    // ORDER
    public Node BDD_create_with_best_order(String bfunction) {

        // linear ordering

        if (variables.isEmpty()) {
            // TODO
        }

        String order = stringifyVariables();

        // looking for best BDD
        int finalNodes = Integer.MAX_VALUE;
        ROBDD finalBDD = null;

        for (int i = 0; i < order.length(); i++) {

            order = order.substring(1) + order.substring(0, 1);
            System.out.println(order);

            ROBDD currentBdd = new ROBDD(bfunction);
            currentBdd.BDD_create(bfunction, order);

            int countNodes = currentBdd.reductionTable.countNodes();
            if (countNodes < finalNodes) {
                finalNodes = countNodes;
                finalBDD = currentBdd;
            }
        }

        return finalBDD.root;
    }

    private String stringifyVariables() {
        // put hashset of variables into a string
        StringBuilder sb = new StringBuilder();

        for (char c : variables) {
            sb.append(c);
        }

        return sb.toString();
    }

    private String generateOrder() {
        return null;
    }

    // USE
    public char BDD_use(ROBDD bdd, String inputs) {

        return 'a';
    }

}
