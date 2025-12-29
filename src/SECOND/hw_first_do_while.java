package SECOND;

import java.util.Scanner;

/**
 * Программа выводит "ступеньки" от 1 до N с использованием цикла do-while.
 */
public class hw_first_do_while {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = scanner.nextInt();

        int row = 1;
        do {
            int col = row;
            do {
                System.out.print(col + " ");
                col--;
            } while (col >= 1);
            System.out.println();
            row++;
        } while (row <= n);

        scanner.close();
    }
}