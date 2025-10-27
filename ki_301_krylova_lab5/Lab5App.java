package ki_301_krylova_lab5;

import java.io.*;
import java.util.Scanner;
import static java.lang.System.out;
import java.util.Locale; // Імпорт для виправлення коми/крапки

/**
 * Class Lab5App implements a driver program for testing
 * the ResultIO class and Equations class.
 *
 * @author KI-301 Krylova
 * @version 1.0
 */
public class Lab5App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        out.println("Current Working Directory: " + System.getProperty("user.dir"));

        try {
            // 1. Отримуємо вхідні дані
            Scanner in = new Scanner(System.in);
            // Примусово очікуємо крапку ('.') при вводі з консолі
            in.useLocale(Locale.US); 
            
            out.print("Enter X (in degrees): ");
            
            // ⬇️ ЗМІНЕНО ТУТ (int -> double) ⬇️
            double x = in.nextDouble();

            // 2. Обчислюємо результат (використовуючи клас з Лаб. 4)
            Equations eq = new Equations();
            double result = eq.calculate(x);
            out.println("Calculated result: " + result);

            // 3. Створюємо екземпляр нового класу для I/O
            ResultIO io = new ResultIO();
            String textFile = "result.txt";
            String binFile = "result.dat";

            // 4. Тестуємо запис/читання у текстовому форматі
            out.println("\nTesting Text I/O...");
            io.writeText(result, textFile);
            double textResult = io.readText(textFile);
            out.println("Result read from text file: " + textResult);

            // 5. Тестуємо запис/читання у двійковому форматі
            out.println("\nTesting Binary I/O...");
            io.writeBinary(result, binFile);
            double binResult = io.readBinary(binFile);
            out.println("Result read from binary file: " + binResult);

            // 6. Перевірка
            if (result == textResult && result == binResult) {
                out.println("\nSuccess! Results match.");
            } else {
                out.println("\nError! Results do not match.");
            }

        } catch (CalcException ex) {
            // Обробка виключення з Лаб. 4
            out.println("Calculation error: " + ex.getMessage());
        } catch (IOException ex) {
            // Обробка виключень вводу/виводу з Лаб. 5
            out.println("I/O error: " + ex.getMessage());
        } catch (java.util.InputMismatchException ex) {
            // Більш конкретний catch для помилки вводу
            out.println("Input error: Please enter a valid number (e.g., 0.5 or 45).");
        } catch (Exception ex) {
            // Інші непередбачувані помилки
            out.println("An unexpected error occurred: " + ex.getMessage());
        }
    }
}

/**
 * Class ResultIO implements methods for reading/writing calculation results
 * in text and binary formats.
 *
 * @author KI-301 Krylova
 * @version 1.0
 */
class ResultIO {

    /**
     * Writes the result to a text file.
     */
    public void writeText(double result, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(result);
        }
    }

    /**
     * Reads a result from a text file.
     */
    public double readText(String filename) throws IOException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            // Примусово очікувати крапку ('.') при читанні з файлу
            scanner.useLocale(Locale.US); 
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                throw new IOException("File does not contain a valid double value.");
            }
        }
    }

    /**
     * Writes the result to a binary file.
     */
    public void writeBinary(double result, String filename) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            dos.writeDouble(result);
        }
    }

    /**
     * Reads a result from a binary file.
     */
    public double readBinary(String filename) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            return dis.readDouble();
        }
    }
}


// ======================================================
// КЛАСИ З ЛАБОРАТОРНОЇ РОБОТИ №4 (потрібні для роботи)
// ======================================================

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
     * @param x Кут в градусах (тепер типу double)
     * @throws CalcException
     */
    
    // ⬇️ ЗМІНЕНО ТУТ (int x -> double x) ⬇️
    public double calculate(double x) throws CalcException {
        double y, rad_x, rad_8x;
        rad_x = Math.toRadians(x);
        rad_8x = Math.toRadians(8.0 * x);

        try {
            double tan_8x = Math.tan(rad_8x);
            double ctg_8x = 1.0 / tan_8x;
            y = Math.sin(rad_x) / ctg_8x;

            if (y == Double.NaN || y == Double.NEGATIVE_INFINITY || y == Double.POSITIVE_INFINITY) {
                throw new ArithmeticException();
            }
        } catch (ArithmeticException ex) {
            // Перевірки тепер також працюють з double
            if ((8 * x) % 180 == 90) {
                throw new CalcException("Exception reason: Division by zero (ctg(8x) = 0) for x = " + x);
            } else if ((8 * x) % 180 == 0) {
                throw new CalcException("Exception reason: Calculation error (ctg(8x) is undefined) for x = " + x);
            } else {
                throw new CalcException("Unknown reason of the exception during exception calculation");
            }
        }
        return y;
    }
}