/****************************************************************************
* Copyright (c) 2013-2025 Lviv Polytechnic National University.
* All Rights Reserved.
*
* This program and the accompanying materials are made available under the terms
* of the Academic Free License v. 3.0 which accompanies this distribution, and is
* available at https://opensource.org/license/afl-3-0-php/
*
* SPDX-License-Identifier: AFL-3.0
****************************************************************************/
package ki_301_krylova_lab5;

import java.io.*;
import java.util.*;

/**
* Class <code>FioApp</code> Implements driver for CalcWFio class
* @author Vira
* @version 1.0
*/
public class FioApp {
    /**
    * Main method of the program
    * @param args Command line arguments
    */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CalcWFio obj = new CalcWFio();
        Scanner s = new Scanner(System.in);
        System.out.print("Enter X (in degrees): ");
        double data = s.nextDouble();

        obj.calculate(data);
        System.out.println("Result is: " + obj.getResult());

        // Запис результату у текстовий файл
        obj.writeResTxt("textRes.txt");
        // Запис результату у двійковий файл
        obj.writeResBin("BinRes.bin");

        // Зчитування з двійкового файлу
        obj.readResBin("BinRes.bin");
        System.out.println("Result read from binary file: " + obj.getResult());

        // Зчитування з текстового файлу
        obj.readResTxt("textRes.txt");
        System.out.println("Result read from text file: " + obj.getResult());

        s.close();
    }
}

/**
* Class <code>CalcWFio</code> implements methods for reading/writing and calculating sin(x)/ctg(8x)
* @author Vira
* @version 1.0
*/
class CalcWFio {
    private double result;

    /**
    * Method writes result to a text file
    * @param fName File name
    * @throws FileNotFoundException
    */
    public void writeResTxt(String fName) throws FileNotFoundException {
        PrintWriter f = new PrintWriter(fName);
        f.printf(Locale.US, "%f", result); // Запис з крапкою як роздільником
        f.close();
    }

    /**
    * Method reads result from a text file
    * @param fName File name
    */
    public void readResTxt(String fName) {
        try {
            File f = new File(fName);
            if (f.exists()) {
                // Читання з локаллю US, щоб підтримувалася крапка як десятковий роздільник
                Scanner s = new Scanner(f).useLocale(Locale.US);
                if (s.hasNextDouble()) {
                    result = s.nextDouble();
                } else {
                    System.out.println("Error: cannot read double value from text file");
                }
                s.close();
            } else {
                throw new FileNotFoundException("File " + fName + " not found");
            }
        } catch (FileNotFoundException ex) {
            System.out.print(ex.getMessage());
        }
    }

    /**
    * Method writes result to a binary file
    * @param fName File name
    * @throws FileNotFoundException, IOException
    */
    public void writeResBin(String fName) throws FileNotFoundException, IOException {
        DataOutputStream f = new DataOutputStream(new FileOutputStream(fName));
        f.writeDouble(result);
        f.close();
    }

    /**
    * Method reads result from a binary file
    * @param fName File name
    * @throws FileNotFoundException, IOException
    */
    public void readResBin(String fName) throws FileNotFoundException, IOException {
        DataInputStream f = new DataInputStream(new FileInputStream(fName));
        result = f.readDouble();
        f.close();
    }

    /**
    * Method calculates y = sin(x) / ctg(8x)
    * @param x Angle in degrees
    */
    public void calculate(double x) {
        double rad = x * Math.PI / 180.0;
        double rad8 = 8 * x * Math.PI / 180.0;
        double cot = 1.0 / Math.tan(rad8);
        result = Math.sin(rad) / cot;
    }

    /**
    * Method returns calculated result
    * @return double result
    */
    public double getResult() {
        return result;
    }
}
