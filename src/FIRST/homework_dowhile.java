package FIRST;

import java.util.Scanner;
import java.text.DecimalFormat;

public class homework_dowhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.print("Введите сумму вклада: ");
        double sum = scanner.nextDouble();

        int month = 1;
        System.out.println("\nМесяц\tСумма вклада");
        System.out.println("-------------------");

        do {
            sum = sum * (1 + 0.0095); // начисляем 0.95%
            System.out.println(month + "\t" + df.format(sum));
            month++;
        } while (month <= 12);

        scanner.close();
    }
}