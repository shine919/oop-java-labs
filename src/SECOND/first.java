package SECOND;

import java.util.Scanner;

public class first {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Задача 1.A и 1.B ===");
        System.out.println("Выберите подзадачу:");
        System.out.println("1-1.A.1 - Чётные/нечётные строки");
        System.out.println("2-1.A.2 - Треугольник 1..i");
        System.out.println("3-1.A.3 - Убывающий треугольник");
        System.out.println("4-1.A.4 - Убывающий треугольник с обратным порядком");
        System.out.println("5-1.B - Симметричный ромб (бриллиант)");
        System.out.println("0 - Выход");

        while (true) {
            System.out.print("\nВведите номер (0 для выхода): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    task1A1();
                    break;
                case 2:
                    task1A2();
                    break;
                case 3:
                    task1A3();
                    break;
                case 4:
                    task1A4();
                    break;
                case 5:
                    task1B();
                    break;
                case 0:

                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    // Задача 1
    private static void task1A1() {
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        int l = 1;
        int r = 1;
        for (int i = 1; i <= n; i++) {
            
            for (int j = 1; j <= n; j++) {
                System.out.print((l) + " ");
                l+=2;
            }

            System.out.println();
        }
    }

    // Задача 2
    private static void task1A2() {
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // Задача 3
    private static void task1A3() {
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // Задача 4
    private static void task1A4() {
        System.out.print("Введите N: ");
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= i; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // Задача 1-B
    private static void task1B() {
        System.out.print("Введите N: ");
        int n = scanner.nextInt();


        for (int row = 1; row <= n; row++) {

            for (int space = 1; space <= n - row; space++) {
                System.out.print("  ");
            }


            for (int j = n; j >= n - row + 1; j--) {
                System.out.print(j + " ");
            }


            for (int j = n - row + 2; j <= n; j++) {
                System.out.print(j + " ");
            }

            System.out.println();
        }


        for (int row = n - 1; row >= 1; row--) {

            for (int space = 1; space <= n - row; space++) {
                System.out.print("  ");
            }


            for (int j = n; j >= n - row + 1; j--) {
                System.out.print(j + " ");
            }


            for (int j = n - row + 2; j <= n; j++) {
                System.out.print(j + " ");
            }

            System.out.println();
        }
    }
}
