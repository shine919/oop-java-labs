package six;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class taskB {


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


        int rowIndexToDelete = findRowWithMaxOddNumbers(matrix);
        if (rowIndexToDelete == -1) {
            System.out.println("Не удалось найти строку для удаления.");
            return;
        }

        System.out.println("\nСтрока для удаления (индекс " + rowIndexToDelete + "):");
        for (int val : matrix[rowIndexToDelete]) {
            System.out.print(val + " ");
        }
        System.out.println();


        int[][] newMatrix = removeRow(matrix, rowIndexToDelete);

        System.out.println("\nМассив после удаления строки:");
        printMatrixToConsole(newMatrix);


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Визуализация массива (taskB)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MatrixPanel matrixPanel = new MatrixPanel(newMatrix);
            frame.add(matrixPanel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    private static int[][] readMatrixFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));


            int rows = fileScanner.nextInt();
            int cols = fileScanner.nextInt();
            fileScanner.nextLine();

            int[][] matrix = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = fileScanner.nextLine().trim();
                String[] numbers = line.split("\\s+");
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }

            fileScanner.close();
            return matrix;

        } catch (java.io.FileNotFoundException e) {
            System.err.println("Файл " + filename + " не найден.");
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка при чтении чисел из файла: " + e.getMessage());
            return null;
        }
    }


    private static int findRowWithMaxOddNumbers(int[][] matrix) {
        int maxOddCount = -1;
        int rowIndex = -1;

        for (int i = 0; i < matrix.length; i++) {
            int oddCount = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] % 2 != 0) {
                    oddCount++;
                }
            }
            if (oddCount > maxOddCount) {
                maxOddCount = oddCount;
                rowIndex = i;
            }
        }

        return rowIndex;
    }


    private static int[][] removeRow(int[][] matrix, int rowIndexToDelete) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] newMatrix = new int[rows - 1][cols];

        int newRowIdx = 0;
        for (int i = 0; i < rows; i++) {
            if (i != rowIndexToDelete) {
                System.arraycopy(matrix[i], 0, newMatrix[newRowIdx], 0, cols);
                newRowIdx++;
            }
        }

        return newMatrix;
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
