/****************************************************************************
* Copyright (c) 2013-2023 Lviv Polytechnic National University. All Rights Reserved.
*
* This program and the accompanying materials are made available under the terms
* of the Academic Free License v. 3.0 which accompanies this distribution, and is
* available at https://opensource.org/license/afl-3-0-php/
*
* SPDX-License-Identifier: AFL-3.0
****************************************************************************/

// Пакет змінено на ваш
package ki_301_krylova_lab4;

import java.util.Scanner;
import java.io.*;
import static java.lang.System.out;

/**
 * Class EquationsApp реалізує драйвер (головний клас) для класу Equations
 * @author EOM Stuff (взято з прикладу)
 * @version 1.0
 */
public class EquationsApp {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Цей 'main' метод ідентичний прикладу, оскільки він
        // виконує роль драйвера: читає дані, викликає обчислення
        // та обробляє виключення роботи з файлом.
        try {
            out.print("Enter file name: ");
            Scanner in = new Scanner(System.in);
            String fName = in.nextLine();
            PrintWriter fout = new PrintWriter(new File(fName));
            try {
                try {
                    Equations eq = new Equations();
                    out.print("Enter X (in degrees): ");
                    // Отримуємо X від користувача та передаємо в метод
                    fout.print(eq.calculate(in.nextInt()));
                } finally {
                    // Цей блок виконається за будь-яких обставин,
                    // гарантуючи закриття файлу
                    fout.flush();
                    fout.close();
                }
            } catch (CalcException ex) {
                // Блок перехоплює помилки обчислень виразу
                out.print(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            // Блок перехоплює помилки роботи з файлом
            out.print("Exception reason: Perhaps wrong file path");
        }
    }
}

/**
 * Class CalcException деталізує ArithmeticException
 * @author EOM Stuff (взято з прикладу)
 * @version 1.0
 */
class CalcException extends ArithmeticException {
    public CalcException() {}

    public CalcException(String cause) {
        super(cause);
    }
}

/**
 * Class Equations реалізує метод для обчислення виразу (sin(x) / ctg(8x))
 * @author KI-301 Krylova
 * @version 1.0
 */
class Equations {
    /**
     * Метод обчислює вираз y = sin(x) / ctg(8x)
     * * @param x Кут в градусах
     * @throws CalcException
     */
    public double calculate(int x) throws CalcException {
        double y, rad_x, rad_8x;

        // Конвертуємо градуси в радіани
        rad_x = Math.toRadians(x);
        rad_8x = Math.toRadians(8.0 * x);

        try {
            // Обчислюємо компоненти виразу
            // ctg(a) = 1 / tan(a)
            double tan_8x = Math.tan(rad_8x);
            double ctg_8x = 1.0 / tan_8x;

            y = Math.sin(rad_x) / ctg_8x;

            // Якщо результат не є числом (NaN) або нескінченністю,
            // це означає, що сталася помилка (наприклад, ділення на 0).
            // Генеруємо базове виключення, яке ми перехопимо нижче.
            if (y == Double.NaN || y == Double.NEGATIVE_INFINITY || y == Double.POSITIVE_INFINITY) {
                throw new ArithmeticException();
            }
        } catch (ArithmeticException ex) {
            // Перехоплюємо базове виключення та створюємо наше власне,
            // більш детальне (CalcException) з описом причини.

            // Причина 1: Ділення на нуль.
            // Це стається, коли ctg(8x) = 0.
            // ctg(a) = 0, коли a = 90, 270, ... (тобто a % 180 == 90)
            if ((8 * x) % 180 == 90) {
                throw new CalcException("Exception reason: Division by zero (ctg(8x) = 0) for x = " + x);
            }
            // Причина 2: ctg(8x) не визначений (нескінченність).
            // Це стається, коли tan(8x) = 0 (ділення на 0 в 1/tan(8x)).
            // tan(a) = 0, коли a = 0, 180, 360, ... (тобто a % 180 == 0)
            // У цьому випадку y = sin(x) / Infinity = 0. Це валідний результат.
            // Але якщо Java все ж згенерувала помилку (напр. 0/0 -> NaN),
            // ми її теж перехопимо.
            else if ((8 * x) % 180 == 0) {
                // В x=0, sin(0)=0, tan(0)=0, ctg(0)=Inf. y=0/Inf=0. Це не помилка.
                // В x=45, 8x=360, sin(45)=0.7, tan(360)=0, ctg(360)=Inf. y=0.7/Inf=0. Це не помилка.
                // Цей блок спрацює, якщо виникне непередбачувана ситуація NaN.
                throw new CalcException("Exception reason: Calculation error (ctg(8x) is undefined) for x = " + x);
            } else {
                // Якщо причина невідома
                throw new CalcException("Unknown reason of the exception during exception calculation");
            }
        }
        return y;
    }
}