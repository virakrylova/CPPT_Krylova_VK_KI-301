package lab1;

import java.io.*;
import java.util.*;

/**
 * Клас Lab1 реалізує приклад програми до лабораторної роботи №1.
 * Програма генерує квадратну матрицю заданого розміру та символ-заповнювач,
 * виводить її на екран та зберігає у текстовий файл.
 * 
 * @author Віра
 * @version 1.0
 * @since 2025
 */
public class matrix_lab1 {

    /**
     * Точка входу в програму.
     * 
     * @param args параметри командного рядка (не використовуються)
     * @throws FileNotFoundException якщо файл не може бути створений
     */
    public static void main(String[] args) throws FileNotFoundException {
        int matrixSize;           // розмір квадратної матриці
        char[][] matrix;          // масив для збереження матриці
        String fillChar;          // символ-заповнювач

        try (Scanner scanner = new Scanner(System.in)) {
            // Файл для збереження матриці
            File dataFile = new File("MyFile.txt");
            PrintWriter writer = new PrintWriter(dataFile);

            // Зчитування розміру матриці
            System.out.print("Enter the size of the matrix: ");
            matrixSize = scanner.nextInt();
            scanner.nextLine();

            if (matrixSize <= 0) {
                System.out.println("Error: Matrix size must be positive. Program terminated.");
                writer.close();
                return;
            }

            // Зчитування символу-заповнювача
            System.out.print("\nEnter a placeholder character: ");
            fillChar = scanner.nextLine();

            if (fillChar.length() != 1) {
                System.out.println("Error: enter exactly one character. Program terminated.");
                writer.close();
                return;
            }

            // Створення квадратної матриці та заповнення символом
            matrix = new char[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                System.out.print("  "); // відступ на початку рядка
                writer.print("  ");
                for (int j = 0; j < matrixSize; j++) {
                    matrix[i][j] = fillChar.charAt(0);
                    System.out.print(matrix[i][j] + " ");
                    writer.print(matrix[i][j] + " ");
                }
                System.out.print("\n");
                writer.print("\n");
            }

            writer.flush();
            writer.close();
            System.out.println("\nMatrix saved to file: " + dataFile.getAbsolutePath());
        }
    }
}
