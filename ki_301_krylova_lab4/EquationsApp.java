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
package ki_301_krylova_lab4;

import java.util.Scanner;
import java.io.*;
import static java.lang.System.out;

/**
* Class <code>EquationsApp</code> Implements driver for Equations class
* @author Vira
* @version 1.0
*/
public class EquationsApp {
    /**
    * Main method of the program
    * @param args Command line arguments
    */
    public static void main(String[] args) {
        // try-with-resources забезпечує автоматичне закриття Scanner
        try (Scanner in = new Scanner(System.in)) {
            out.print("Enter file name: ");
            String fName = in.nextLine();

            // try-with-resources забезпечує автоматичне закриття PrintWriter
            try (PrintWriter fout = new PrintWriter(new File(fName))) {
                Equations eq = new Equations();

                out.print("Enter X: ");
                // Перевірка, чи введено число
                if (in.hasNextDouble()) {
                    double x = in.nextDouble();
                    double result = eq.calculate(x);
                    fout.print(result);
                    out.println("Result written to file.");
                } else {
                    out.println("Input error: X must be a numeric value.");
                }
            } catch (CalcException ex) {
                out.print(ex.getMessage());
            } catch (FileNotFoundException ex) {
                out.print("Exception reason: Perhaps wrong file path");
            }
        }
    }
}

/**
* Class <code>CalcException</code> more precise ArithmeticException
* @author Vira
* @version 1.0
*/
class CalcException extends ArithmeticException {
    public CalcException() {}
    public CalcException(String cause) {
        super(cause);
    }
}

/**
* Class <code>Equations</code> implements method for sin(x)/ctg(8x) expression calculation
* @author Vira
* @version 1.0
*/
class Equations {
    /**
    * Method calculates the expression y = sin(x) / ctg(8x)
    * @param x Angle in degrees
    * @throws CalcException if illegal value of x or division by zero
    */
    public double calculate(double x) throws CalcException {
        double y, rad, rad8;
        rad = x * Math.PI / 180.0;
        rad8 = 8 * x * Math.PI / 180.0;

        try {
            double cot = 1.0 / Math.tan(rad8); // обчислення ctg(8x)
            y = Math.sin(rad) / cot;

            // перевірка на коректність результату
            if (Double.isNaN(y) || Double.isInfinite(y))
                throw new ArithmeticException();
        } catch (ArithmeticException ex) {
            if (Math.tan(rad8) == 0)
                throw new CalcException("Exception reason: tan(8x) = 0, ctg(8x) is undefined");
            else
                throw new CalcException("Unknown reason of the exception during expression calculation");
        }

        return y;
    }
}
