package FIRST;
import java.util.Scanner;
public class third {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N: ");
        int n = scanner.nextInt();

        int a = 1;

        while (a <= n) {
            System.out.print(a + " ");
            a = a * 2;
        }

        scanner.close();
    }
}
