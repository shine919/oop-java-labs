package FIRST;

import java.util.Scanner;
import java.text.DecimalFormat;

public class homework_while {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.print("Введите сумму вклада: ");
        double sum = scanner.nextDouble();

        int month = 1;
        System.out.println("\nМесяц\tСумма вклада");
        System.out.println("-------------------");

        while (month <= 12) {
            sum = sum * (1 + 0.0095);
            System.out.println(month + "\t" + df.format(sum));
            month++;
        }

        scanner.close();
    }
}