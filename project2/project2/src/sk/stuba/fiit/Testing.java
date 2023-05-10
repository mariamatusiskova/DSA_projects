package sk.stuba.fiit;

import sk.stuba.fiit.MathLogic.Or;
import sk.stuba.fiit.Tree.Node;
import sk.stuba.fiit.Tree.ROBDD;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.DoubleStream;

public class Testing {

    HashSet<Character> variables = new HashSet<>();
    public HashMap<String, Boolean> values = new HashMap<>();


    // generate random boolean functions

    private Random random = new Random();

    public String generateBooleanFunction(int numVariables) {

        if (numVariables <= 0) {
            return null;
        }

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

        if (numClauses <= 0) {
            return 0;
        }

        int min = 1;
        int max = numClauses-1;

        return random.nextInt(max - min + 1) + min;
    }

    private String generateRandomCombination(String variableSequence) {

        if (variableSequence.isEmpty()) {
            return null;
        }

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

        double[] reductionPercentage = new double[100];
        long totalTime_BDD_create_with_best_order = 0;
        long totalTime_BDD_create = 0;
        long totalTime_BDD_use = 0;


//        ROBDD robdd = new ROBDD("A.B + A.C + B.C");
//        Node root = robdd.BDD_create_with_best_order("A.B + A.C + B.C");
//        String result = robdd.BDD_use(root, "100");
//        String compareResult = testing.truthTable("A.B + A.C + B.C", "100", root.order);
//        System.out.println("Result from BDD_use: " + result);
//        System.out.println("result from the Truth Table: " + compareResult  + "\n");

//        // min
        System.out.println("BDDs with 13 variables");

        for (int i = 0; i < MAX_BOOLEAN_FUNCTIONS; i++) {
            String minFormula = testing.generateBooleanFunction(numVariables[0]);
            String minBits = testing.generateBinaryCombinations(numVariables[0]);

            System.out.println("formula: " + minFormula);
            System.out.println("bits: " + minBits);

            ROBDD minRobdd = new ROBDD(minFormula);

            long start = System.nanoTime();
            Node minRoot = minRobdd.BDD_create_with_best_order(minFormula);
            long durationTime = (System.nanoTime() - start);
            totalTime_BDD_create_with_best_order += durationTime;

            if (minRoot == null) {
                return;
            }

            start = System.nanoTime();
            minRobdd.BDD_create(minFormula, minRoot.order);
            durationTime = (System.nanoTime() - start);
            totalTime_BDD_create += durationTime;

            start = System.nanoTime();
            String minResult = minRobdd.BDD_use(minRoot, minBits);
            durationTime = (System.nanoTime() - start);
            totalTime_BDD_use += durationTime;

            String compareResult = testing.truthTable(minFormula, minBits, minRoot.order);
            System.out.println("Result from BDD_use: " + minResult);
            System.out.println("result from the Truth Table: " + compareResult  + "\n");

            // for calculating average
            reductionPercentage[i] = minRoot.reductionPercentage;
        }

        // average of all BDD

        double averageOfReductionPercentage = DoubleStream.of(reductionPercentage).average().getAsDouble();
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Average of the reduction percentage is: " + df.format(averageOfReductionPercentage) + "%");
        System.out.println("Average of a time duration of BDD_create_with_best_order: " + totalTime_BDD_create_with_best_order/100 + " nanoseconds");
        System.out.println("Average of a time duration of BDD_create: " + totalTime_BDD_create/100 + " nanoseconds");
        System.out.println("Average of a time duration of BDD_use: " + totalTime_BDD_use/100 + " nanoseconds");

//        // max
//        System.out.println("BDDs with 20 variables");
//
//        double[] reductionPercentageMax = new double[100];
//        totalTime_BDD_create_with_best_order = 0;
//        totalTime_BDD_create = 0;
//        totalTime_BDD_use = 0;
//
//        System.out.println("BDDs with 20 variables");
//
//        for (int i = 0; i < MAX_BOOLEAN_FUNCTIONS; i++) {
//            String maxFormula = testing.generateBooleanFunction(numVariables[1]);
//            String maxBits = testing.generateBinaryCombinations(numVariables[1]);
//
//            ROBDD maxRobdd = new ROBDD(maxFormula);
//
//            long start = System.nanoTime();
//            Node maxRoot = maxRobdd.BDD_create_with_best_order(maxFormula);
//            long durationTime = (System.nanoTime() - start);
//            totalTime_BDD_create_with_best_order += durationTime;
//
//            start = System.nanoTime();
//            maxRobdd.BDD_create(maxFormula, maxRoot.order);
//            durationTime = (System.nanoTime() - start);
//            totalTime_BDD_create += durationTime;
//
//            start = System.nanoTime();
//            String maxResult = maxRobdd.BDD_use(maxRoot, maxBits);
//            durationTime = (System.nanoTime() - start);
//            totalTime_BDD_use += durationTime;
//
//            // for calculating average
//            reductionPercentageMax[i] = maxRoot.reductionPercentage;
//
//            String compareResult = testing.truthTable(maxFormula, maxBits, maxRoot.order);
//            System.out.println("Result from BDD_use: " + maxResult);
//            System.out.println("result from the Truth Table: " + compareResult  + "\n");
//        }
//
//        double averageOfReductionPercentage = DoubleStream.of(reductionPercentageMax).average().getAsDouble();
//        DecimalFormat df = new DecimalFormat("0.00");
//
//        System.out.println("Average of the reduction percentage is: " + df.format(averageOfReductionPercentage));
//        System.out.println("Average of a time duration of BDD_create_with_best_order: " + totalTime_BDD_create_with_best_order/100 + " nanoseconds");
//        System.out.println("Average of a time duration of BDD_create: " + totalTime_BDD_create/100 + " nanoseconds");
//        System.out.println("Average of a time duration of BDD_use: " + totalTime_BDD_use/100 + " nanoseconds");

    }

}