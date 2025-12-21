package FIRST;
import java.util.Scanner;
public class five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N (количество чисел): ");
        int n = scanner.nextInt();

        int i = 1;
        int a = 1;
        while (i <= n) {
            a = a * i;
            System.out.print(a + " ");
            i++;
        }

        scanner.close();
    }
}