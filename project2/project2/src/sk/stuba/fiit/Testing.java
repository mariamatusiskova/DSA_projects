package sk.stuba.fiit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Testing {

    public static void main(String[] args) throws IOException {
//
//        ROBDD robdd = new ROBDD("A.!C + A.B.C + !A.B + !B.C");
//        robdd.BDD_create_with_best_order("A.!C + A.B.C + !A.B + !B.C");

        ROBDD robdd = new ROBDD("A.B + C.D + E.F + G.H");
        Node root = robdd.BDD_create_with_best_order("A.B + C.D + E.F + G.H");
        String result = robdd.BDD_use(root, "01101001");
        System.out.println(result);

      //  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
//        String booleanValue = reader.readLine();

        // Printing the read line
//        System.out.println("Write sth:");
//        System.out.println(booleanValue);


    }

}