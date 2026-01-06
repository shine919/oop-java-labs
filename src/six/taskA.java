package six;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class taskA {


    private static final Color[] CELL_COLORS = {
            Color.BLACK,
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN,
            Color.WHITE,
            Color.BLACK,
            Color.RED
    };


    private static final Color OUT_OF_RANGE_COLOR = Color.RED;


    private static final int CELL_SIZE = 40;

    private static final int CELL_GAP = 1;

    public static void main(String[] args) {
        int[][] matrix = readMatrixFromFile("src/six/inputA1.txt");

        if (matrix == null) {
            System.err.println("Ошибка при чтении файла.");
            return;
        }

        System.out.println("Исходный массив:");
        printMatrixToConsole(matrix);


        processMatrix(matrix);

        System.out.println("\nОбработанный массив:");
        printMatrixToConsole(matrix);


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Визуализация массива");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Создаём панель для отображения
            MatrixPanel matrixPanel = new MatrixPanel(matrix);
            frame.add(matrixPanel);

            frame.pack(); // Устанавливает размер окна, чтобы вместить панель
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    private static int[][] readMatrixFromFile(String filename) {
        try {

            Scanner fileScanner = new Scanner(new File(filename));

            java.util.List<String> lines = new java.util.ArrayList<>();
            int rows = fileScanner.nextInt();
            int cols = fileScanner.nextInt();
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
            System.out.println(lines);
            fileScanner.close();

            if (lines.isEmpty()) {
                return null;
            }



            System.out.println(rows);
            System.out.println(cols);

            int[][] matrix = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String[] numbers = lines.get(i).trim().split("\\s+");
                System.out.println(numbers);
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }

            return matrix;

        } catch (java.io.FileNotFoundException e) {
            System.err.println("Файл " + filename + " не найден.");
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка при чтении чисел из файла: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static void processMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return;
        int cols = matrix[0].length;


        for (int j = 0; j < cols; j++) {
            int maxInColumn = matrix[0][j];
            for (int i = 1; i < rows; i++) {
                if (matrix[i][j] > maxInColumn) {
                    maxInColumn = matrix[i][j];
                }
            }


            for (int i = 0; i < rows; i++) {

                if (matrix[i][j] % 2 != 0) {
                    matrix[i][j] = maxInColumn;
                }
            }
        }
    }


    private static void printMatrixToConsole(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }


    static class MatrixPanel extends JPanel {
        private final int[][] matrix;

        public MatrixPanel(int[][] matrix) {
            this.matrix = matrix;

            int rows = matrix.length;
            int cols = (rows > 0) ? matrix[0].length : 0;
            setPreferredSize(new Dimension(
                    cols * (CELL_SIZE + CELL_GAP) + CELL_GAP,
                    rows * (CELL_SIZE + CELL_GAP) + CELL_GAP
            ));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int rows = matrix.length;
            if (rows == 0) return;
            int cols = matrix[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int x = j * (CELL_SIZE + CELL_GAP) + CELL_GAP;
                    int y = i * (CELL_SIZE + CELL_GAP) + CELL_GAP;

                    int value = matrix[i][j];
                    Color color;


                    if (value >= 0 && value <= 9) {
                        color = CELL_COLORS[value];
                    } else {
                        color = OUT_OF_RANGE_COLOR;
                    }

                    g2d.setColor(color);
                    g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);


                    g2d.setColor(Color.GRAY);
                    g2d.drawRect(x, y, CELL_SIZE, CELL_SIZE);


                    g2d.setColor(Color.WHITE);
                    FontMetrics fm = g2d.getFontMetrics();
                    String text = String.valueOf(value);
                    int textWidth = fm.stringWidth(text);
                    int textHeight = fm.getAscent() - fm.getDescent();
                    int textX = x + (CELL_SIZE - textWidth) / 2;
                    int textY = y + (CELL_SIZE + textHeight) / 2;
                    g2d.drawString(text, textX, textY);
                }
            }
        }
    }
}
