"""
ki_301_krylova_lab1.py
Лабораторна робота №1 (Python)
Генерація зубчастого списку за варіантом (заштриховані області квадратної матриці).
"""

from __future__ import annotations


def create_jagged_storage(n: int) -> list[list[str]]:
    """
    Створює зубчастий список, який містить лише заштриховані елементи кожного рядка.
    У даному варіанті "заштриховані" — це елементи з непарними індексами стовпця j (j % 2 == 1).
    """
    # Кількість заштрихованих елементів у рядку = кількість непарних j у [0..n-1]
    shaded_count = sum(1 for j in range(n) if j % 2 == 1)

    jagged: list[list[str]] = []
    for _ in range(n):
        jagged.append([""] * shaded_count)
    return jagged


def print_matrix(jagged: list[list[str]], symbol: str, n: int, filename: str) -> None:
    """
    Заповнює зубчастий список символом, друкує повну матрицю на екран
    і записує її у файл (аналогічно Java-версії).
    """
    print("Matrix result:")

    with open(filename, "w", encoding="utf-8") as f:
        for i in range(n):
            idx = 0  # індекс у зубчастому рядку (тільки для заштрихованих колонок)

            for j in range(n):
                if j % 2 == 1:
                    jagged[i][idx] = symbol

                    # Вивід у консоль + запис у файл
                    f.write(jagged[i][idx] + " ")
                    print(jagged[i][idx] + " ", end="")

                    idx += 1
                else:
                    f.write("  ")
                    print("  ", end="")

            f.write("\n")
            print()  # новий рядок у консолі


def main() -> None:
    # Ввід розміру матриці
    try:
        n_str = input("Enter the matrix size: ").strip()
        if not n_str:
            print("No size entered. Program terminated.")
            return
        n = int(n_str)
        if n <= 0:
            print("Matrix size must be positive. Program terminated.")
            return
    except ValueError:
        print("Invalid matrix size. Program terminated.")
        return

    # Ввід символа-заповнювача
    symbol = input("Enter the filler symbol: ").strip()

    # Перевірка на валідність символа заповнювача (має бути рівно 1 символ)
    if len(symbol) != 1:
        print("Please enter a correct filler symbol")
        return

    jagged = create_jagged_storage(n)
    filename = "Lab1.txt"

    # Запуск бізнес-логіки
    try:
        print_matrix(jagged, symbol, n, filename)
    except OSError as e:
        raise RuntimeError(f"An error occurred while writing to the file: {e}") from e


if __name__ == "__main__":
    main()