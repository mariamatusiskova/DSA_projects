package sk.stuba.fiit;

import sk.stuba.fiit.MathLogic.Expression;
import sk.stuba.fiit.MathLogic.Or;

import java.io.IOException;
import java.util.*;

public class Testing {

    HashSet<Character> variables = new HashSet<>();
    public HashMap<String, Boolean> values = new HashMap<>();


    // generate random boolean functions

    private Random random = new Random();

    public String generateBooleanFunction(int numVariables) {

        String sequence = generateVariables(numVariables);

        int numClauses = generateClauses(numVariables);

        String[] clauses = new String[numClauses];

        for (int i = 0; i < numClauses-1; i++) {
            String clause = generateRandomCombination(sequence);
            clauses[i] = clause;
        }

        boolean containsAllChars = true;
        ArrayList<Character> missingChars = new ArrayList<>();

        for (char c : sequence.toCharArray()) {
            boolean charFound = false;
            for (String clause : clauses) {
                if (clause != null && clause.indexOf(c) != -1) {
                    charFound = true;
                    break;
                }
            }
            if (!charFound) {
                containsAllChars = false;
                missingChars.add(c);
            }
        }

        if (!containsAllChars) {
            String cl = generateMissingVariables(missingChars);
            clauses[numClauses-1] = cl;
        } else {
            clauses[numClauses-1] = generateRandomCombination(sequence);
        }

        String dnf = String.join(" + ", clauses);
        return dnf;
    }

    private String generateVariables(int numVariables) {

        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        while (sb.length() < numVariables) {

            int index = random.nextInt(ALPHABET.length());
            char c = ALPHABET.charAt(index);
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int generateClauses(int numClauses) {

        int min = 1;
        int max = numClauses-1;

        return random.nextInt(max - min + 1) + min;
    }

    private String generateRandomCombination(String variableSequence) {

        StringBuilder sb = new StringBuilder();

        Set<Character> selectedVariables  = new HashSet<>();

        int randomAndNum = random.nextInt(variableSequence.length()) + 1;

        for (int i = 0; i < randomAndNum; i++) {
            int randomIndex = random.nextInt(variableSequence.length());
            char selectedVariable = variableSequence.charAt(randomIndex);

            if (selectedVariables.contains(selectedVariable)) {
                if (i == randomAndNum - 1 && sb.charAt(sb.length()-1) == '.') {
                    sb.deleteCharAt(sb.length()-1);
                }
                continue;
            }

            boolean negateVariable = random.nextBoolean();
            if (negateVariable) {
                sb.append("!");
            }

            sb.append(selectedVariable);
            selectedVariables.add(selectedVariable);
            if (i < randomAndNum - 1) {
                sb.append(".");
            }
        }

        return sb.toString();
    }

    private String generateMissingVariables(ArrayList<Character> addVariables) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < addVariables.size(); i++) {

            boolean negateVariable = random.nextBoolean();
            if (negateVariable) {
                sb.append("!");
            }

            sb.append(addVariables.get(i));
            if (i < addVariables.size() - 1) {
                sb.append(".");
            }
        }

        return sb.toString();
    }

    // generate random combination of bits
    public String generateBinaryCombinations(int length) {

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int bit = random.nextInt(2);
            sb.append(bit);
        }

        return sb.toString();
    }

      //  List<Expression> expression;

//        for (int i = 0; i < inputs.length(); i++) {
//
//            expression = booleanExpression.getChildren();
//
//            if (i == inputs.length() - 1) {
//
//                Boolean value = booleanExpression.evaluate();
//
//                if (value != null && value == false) {
//                    return "0";
//                } else if (value != null && value == true) {
//                    return "1";
//                } else {
//                    return "-1";
//                }
//            } else {
//
//                if (inputs.charAt(i) == '1') {
//                  //  values.put(order.charAt(i) + "", true);
//                    expression = booleanExpression.replace(order.charAt(i) + "", true);
//                } else if (inputs.charAt(i) == '0') {
//                  //  values.put(order.charAt(i) + "", false);
//                    expression = booleanExpression.replace(order.charAt(i) + "", false);
//                } else {
//                    return "-1";
//                }
//            }
//
//            booleanExpression.setChildren(expression);
//
////            if (i != inputs.length()-1) {
////                values.remove(order.charAt(i) + "");
////            }
//        }
//
//        return "-1";
//    }

    /// *******

    // the truth table for comparing result with the function BDD_use
    public String truthTable(String bfunction, String inputs, String order) {

        if (bfunction.isEmpty() || inputs.isEmpty()) {
            return "-1";
        }

        storeVariables(bfunction);
        Or or = new Or(bfunction);

        for (int i = 0; i < inputs.length(); i++) {

            if (inputs.charAt(i) == '1') {
                values.put(order.charAt(i) + "", true);
            } else if (inputs.charAt(i) == '0') {
                values.put(order.charAt(i) + "", false);
            } else {
                return "-1";
            }
        }

        or.setValues(values);

        Boolean value = or.evaluate();

        if (value != null && value == false) {
            return "0";
        } else if (value != null && value == true) {
            return "1";
        }

        return "-1";
    }

    private void storeVariables(String bfunction) {
        for (char c : bfunction.toCharArray()) {
            if (Character.isLetter(c)) {
                variables.add(c);
                values.put(c + "", null);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        final int MAX_BOOLEAN_FUNCTIONS = 100;
        final int[] numVariables = {13, 20};

        Testing testing = new Testing();

//        ROBDD robdd = new ROBDD("A.B + A.C + B.C");
//        Node root = robdd.BDD_create_with_best_order("A.B + A.C + B.C");
//        String result = robdd.BDD_use(root, "100");
//        String compareResult = testing.truthTable("A.B + A.C + B.C", "100", root.order);
//        System.out.println(result);
//        System.out.println("reeesult: " + compareResult  + "\n");

        // min
        System.out.println("BDDs with 13 variables");

        for (int i = 0; i < MAX_BOOLEAN_FUNCTIONS; i++) {
            String minFormula = testing.generateBooleanFunction(numVariables[0]);
            String minBits = testing.generateBinaryCombinations(numVariables[0]);

            System.out.println("#### inputminFormula " + minFormula);
            System.out.println("biiiits: " + minBits);

            ROBDD minRobdd = new ROBDD(minFormula);
            Node minRoot = minRobdd.BDD_create_with_best_order(minFormula);

            String minResult = minRobdd.BDD_use(minRoot, minBits);
            String compareResult = testing.truthTable(minFormula, minBits, minRoot.order);
            System.out.println("Min result: " + minResult);
            System.out.println("reeesult: " + compareResult  + "\n");

        }

//        // max
//        System.out.println("BDDs with 20 variables");
//
//        for (int i = 0; i < MAX_BOOLEAN_FUNCTIONS; i++) {
//            String maxFormula = testing.generateBooleanFunction(numVariables[1]);
//            String maxBits = testing.generateBinaryCombinations(numVariables[1]);
//
//            ROBDD maxRobdd = new ROBDD(maxFormula);
//            Node maxRoot = maxRobdd.BDD_create_with_best_order(maxFormula);
//
//            String maxResult = maxRobdd.BDD_use(maxRoot, maxBits);
//            System.out.println("Min result: " + maxResult + "\n");
//        }
    }

}