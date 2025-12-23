package FIRST;
import java.util.Scanner;
public class fivedo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите N (количество чисел): ");
        int n = scanner.nextInt();

        int i = 1;
        int a = 1;
        do {
            a = a * i;
            System.out.print(a + " ");
            i++;
        }while (i <= n);

        scanner.close();
    }
}