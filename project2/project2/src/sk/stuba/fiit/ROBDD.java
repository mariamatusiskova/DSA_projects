// Source: https://www3.cs.stonybrook.edu/~cram/cse505/Fall20/Resources/bdd97.pdf

package sk.stuba.fiit;// sk.stuba.fiit.ROBDD - Reduced ordered binary decision diagram

import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.MathLogic.Or;
import sk.stuba.fiit.Tables.ReductionTableBDD;
import sk.stuba.fiit.Tables.StoreNodeBDD;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ROBDD {

    HashSet<Character> variables = new HashSet<>();
    private int numberOfVariables;

    // table for storing nodes
    public StoreNodeBDD storeTable;

    // ensure if BDD is reduces --> store only new nodes
    private ReductionTableBDD reductionTable;
    private String bfunction;

    public HashMap<String, Boolean> values = new HashMap<>();
    private Node root;
    private int beforeReduction = 0;

    public ROBDD(Node root) {
        this.root = root;
    }

    public ROBDD(String bfunction) {
        this.bfunction = bfunction;
        this.numberOfVariables = countAndStoreVariables(bfunction);
        this.storeTable = new StoreNodeBDD();
        this.reductionTable = new ReductionTableBDD();
    }

    // BUILD
    public Node BDD_create(String bfunction, String order) {
        Or or = new Or(bfunction);
        or.setValues(values);
        root = BDD_create_helper(or, order, 0);
        root.order = order;
        return root;
    }

    private Node BDD_create_helper(Or or, String order, int variableIndex) {

        if (order.isEmpty()) {
            return null;
        }

        if (variableIndex > this.numberOfVariables-1) {

            Boolean value = or.evaluate();

            if (value != null && value == false) {
                return storeTable.search(0);
            } else {
                return storeTable.search(1);
            }

        } else {

            HashMap<String, Boolean> valuesZero = new HashMap<>(or.getValues());
            valuesZero.put(order.charAt(variableIndex) + "", false);
            Or orZero = new Or(or.getChildren());
            orZero.setValues(valuesZero);
            List<Expression> exprZero = orZero.replace();

            HashMap<String, Boolean> valuesOne = new HashMap<>(or.getValues());
            valuesOne.put(order.charAt(variableIndex) + "", true);
            Or orOne = new Or(or.getChildren());
            orOne.setValues(valuesOne);
            List<Expression> exprOne = orOne.replace();

            orZero.setChildren(exprZero);
            orOne.setChildren(exprOne);

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

        if (node.getLow().equals(node.getHigh())) {
            beforeReduction++;
            return node.getLow();
        } else if (reductionTable.check(node)) {
            indexOfNode = reductionTable.search(node);
            beforeReduction++;
            return storeTable.search(indexOfNode);
        } else {
            Node newNode = storeTable.insert(node);
            indexOfNode = storeTable.getSize() - 1;
            reductionTable.insert(indexOfNode, newNode);
            return newNode;
        }
    }

    // ORDER
    public Node BDD_create_with_best_order(String bfunction) {

        // linear ordering

        if (variables.isEmpty()) {
            return null;
        }

        String order = stringifyVariables();

        // looking for best BDD
        int finalNodes = Integer.MAX_VALUE;
        ROBDD finalBDD = null;

        for (int i = 0; i < order.length(); i++) {

            order = order.substring(1) + order.substring(0, 1);

            ROBDD currentBdd = new ROBDD(bfunction);
            currentBdd.BDD_create(bfunction, order);
            System.out.println(order);

            // plus root
            int countNodes = currentBdd.storeTable.getSize();
            int totalNodesBeforeReduction = currentBdd.beforeReduction + countNodes;
            System.out.println("num of nodes before reduction: " + totalNodesBeforeReduction);
            System.out.println("num of nodes after reduction: " + countNodes);

            final DecimalFormat df = new DecimalFormat("0.00");
            double percentage = (double) countNodes/currentBdd.beforeReduction * 100;
            double reductionPercentage = 100 - percentage;
            System.out.println("percentage reduction rate: " + df.format(reductionPercentage) + "%");



            if (countNodes < finalNodes) {

                finalNodes = countNodes;
                finalBDD = currentBdd;

                // testing
                finalBDD.root.reductionPercentage = reductionPercentage;
                System.out.println("best order: " + order);
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

    // USE
    public String BDD_use(Node node, String inputs) {

        if (inputs.isEmpty()) {
            return "-1";
        }

        for (int i = 0; i < inputs.length(); i++) {

            if (node == null) {
                return "-1";
            }

            if (inputs.charAt(i) == '0') {
                node = node.getLow();
            } else if (inputs.charAt(i) == '1') {
                node = node.getHigh();
            }

            if (node instanceof LeafNode) {
                return ((LeafNode) node).getValue() ? "1" : "0";
            }
        }

        return "-1";
    }

}
