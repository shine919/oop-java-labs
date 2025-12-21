package FIRST;
import java.util.Scanner;
public class second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N (>10): ");
        int n = scanner.nextInt();


        if (n <= 10) {
            System.out.println("Ошибка: N должно быть больше 10!");
            return;
        }

        int current = n;
        while (current >= 10) {
            System.out.print(current + " ");
            current -= 10;
        }

        scanner.close();
    }
}


