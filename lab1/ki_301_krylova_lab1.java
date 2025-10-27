package lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Клас public class ki_301_krylova_lab1 {
 * реалізує лабораторну роботу №1
 */
public class ki_301_krylova_lab1 {
    /**
     * Статичний метод main є точкою входу в програму
     *
     * @param args аргументи
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ввід розміру матриці
        // ПЕРЕКЛАДЕНО:
        System.out.println("Enter the matrix size: ");
        int row = sc.nextInt();

        // Ввід символа заповнювача
        // ПЕРЕКЛАДЕНО:
        System.out.println("Enter the filler symbol: ");
        String symbol = sc.next();

        // Перевірка на валідність символа заповнювача
        if (symbol.length() != 1) {
            // ПЕРЕКЛАДЕНО:
            System.out.println("Please enter a correct filler symbol");
            return;
        }

        String[][] arr = createLengthOfEachSubArr(row);
        String fileName = "Lab1.txt";

        // Запуск бізнес логіки для генерації зубчастого масиву (виводу в консоль і запису в файл)
        try {
            printMatrix(arr, symbol, row, fileName);
        } catch (IOException e) {
            // Обробка помилки під час запису в файл
            // ПЕРЕКЛАДЕНО:
            throw new RuntimeException("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Метод який генерує зубчастий масив (виводить в консоль і записує в файл)
     *
     * @param arr    масив для заповнення
     * @param symbol символ заповнювач
     * @param row    розмір масиву
     * @param file   назва файлу
     * @throws IOException якщо столась якась помилка при запису в файл
     */
    public static void printMatrix(String[][] arr, String symbol, int row, String file) throws IOException {
        // ПЕРЕКЛАДЕНО:
        System.out.println("Matrix result: ");

        try (FileWriter writer = new FileWriter(file)) {
            // Логіка для формування зубачастого масиву за варіантом
            for (int i = 0; i < row; i++) {
                int indexJ = 0;

                for (int j = 0; j < row; j++) {
                    if (j % 2 == 1) {
                        arr[i][indexJ] = symbol;

                        // Вивід в консоль і запис в файл
                        writer.write(arr[i][indexJ] + " ");
                        System.out.print(arr[i][indexJ] + " ");

                        indexJ++;
                    } else {
                        writer.write("  ");
                        System.out.print("  ");
                    }
                }
                System.out.println();
                writer.write("\n");
            }

            writer.flush();
        }
    }

    /**
     * Метод який знаходить для кожного під масива довжину
     *
     * @param row розмір масива
     * @return String[][]
     */
    public static String[][] createLengthOfEachSubArr(int row) {
        String[][] arr = new String[row][];

        for (int i = 0; i < row; i++) {
            int length = 0;

            for (int j = 0; j < row; j++) {
                if (j % 2 == 1) {
                    length++;
                }
            }

            arr[i] = new String[length];
        }

        return arr;
    }
}
