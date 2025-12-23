package SECOND;

import java.util.Scanner;

public class hw_first_while {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = scanner.nextInt();

        int row = 1;
        while (row <= n) {
            int col = row;
            while (col >= 1) {
                System.out.print(col + " ");
                col--;
            }
            System.out.println();
            row++;
        }

        scanner.close();
    }
}