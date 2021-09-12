package com.example.sbdemo.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class BIOTest {
    public static void main(String[] args) {
        scannerAndPrintWriter();
    }

    private static void stream() {

    }


    private static void scannerAndPrintWriter() {
//        URL resource = BIOTest.class.getClassLoader().getResource(File.separator + "tmp" + File.separator + "bio.txt");
        try(Scanner scanner = new Scanner(System.in);
            PrintWriter printWriter = new PrintWriter(new File("temp" + File.separator + "bio.txt"))) {

            if (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                printWriter.println(nextLine);
            }

            printWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
