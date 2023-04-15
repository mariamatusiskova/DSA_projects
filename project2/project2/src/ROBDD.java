// ROBDD - Reduced ordered binary decision diagram

import java.util.HashMap;

public class ROBDD {

    public HashMap<String, Boolean> values = new HashMap<>();

    private Node root;

    public ROBDD(Node root) {
        this.root = root;
    }

    public Node BDD_create(String bfunction, String order) {

        Node node = new Node();

        // TODO:change
        int orderN = 1;
        int n = 0;

        boolean value = false;
        //

        if (orderN > n) {
            if (value == false) {
                return node.getLow();
            } else {
                return node.getHigh();
            }
        } else {
            Node v0 = BDD_create("seva", "abc");
            Node v1 = BDD_create("bleh", "frrr");
            return node;
        }
    }

    public ROBDD BDD_create_with_best_order(String bfunction) {

        return null;
    }

    public char BDD_use(ROBDD bdd, String inputs) {

        return 'a';
    }

}
