package sk.stuba.fiit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Testing {

    public static void main(String[] args) throws IOException {

        ROBDD robdd = new ROBDD("A.B.C+B.C+E.F");
        robdd.BDD_create_with_best_order("A.B.C+B.C+E.F");

      //  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
//        String booleanValue = reader.readLine();

        // Printing the read line
//        System.out.println("Write sth:");
//        System.out.println(booleanValue);


    }

}