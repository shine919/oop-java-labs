package FIRST;

import java.util.Scanner;

public class first {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = scanner.nextInt();

        int i = 1;
        while (i <= n) {
            System.out.print(i + " ");
            i++;
        }

        scanner.close();
    }
}