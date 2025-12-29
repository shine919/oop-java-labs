package SECOND;


import java.util.Scanner;

public class hw_second_do_while {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = scanner.nextInt();

        int row = 1;
        do {
            int col = 1;
            int tmn = n - row;  // количество пробелов
            do {
                System.out.print("  ");  // два пробела
                tmn--;
            } while (tmn >= 0);

            do {
                System.out.print(col + " ");
                col++;
            } while (col <= row);

            System.out.println();
            row++;
        } while (row <= n);

        row = n - 1;
        do {
            int col = 1;
            int tmn = n - row;
            do {
                System.out.print("  ");
                tmn--;
            } while (tmn >= 0);

            do {
                System.out.print(col + " ");
                col++;
            } while (col <= row);

            System.out.println();
            row--;
        } while (row >= 1);

        scanner.close();
    }
}