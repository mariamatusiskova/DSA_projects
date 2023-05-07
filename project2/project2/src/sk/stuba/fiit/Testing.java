package sk.stuba.fiit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Testing {

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

    public static void main(String[] args) throws IOException {

        final int MAX_BOOLEAN_FUNCTIONS = 100;
        final int[] numVariables = {13, 20};

        Testing testing = new Testing();

        // min
        System.out.println("BDDs with 13 variables");

        for (int i = 0; i < MAX_BOOLEAN_FUNCTIONS; i++) {
            String minFormula = testing.generateBooleanFunction(numVariables[0]);
            String minBits = testing.generateBinaryCombinations(numVariables[0]);

            ROBDD minRobdd = new ROBDD(minFormula);
            Node minRoot = minRobdd.BDD_create_with_best_order(minFormula);

            String minResult = minRobdd.BDD_use(minRoot, minBits);
            System.out.println("Min result: " + minResult  + "\n");
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