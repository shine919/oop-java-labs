package SECOND;

import java.util.Scanner;

public class hw_second_while {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = scanner.nextInt();


        int row = 1;
        while (row <= n) {
            int col = 1;
            int tmn = n-row;
            while(tmn != 0) {
                System.out.print("  ");
                tmn--;
            }
            while (col <= row) {

                System.out.print(col + " ");
                col++;
            }
            System.out.println();
            row++;
        }

        row = n - 1;
        while (row >= 1) {
            int col = 1;
            int tmn = n-row;
            while(tmn != 0) {
                System.out.print("  ");
                tmn--;
            }
            while (col <= row) {
                System.out.print(col + " ");
                col++;
            }
            System.out.println();
            row--;
        }

        scanner.close();
    }
}
