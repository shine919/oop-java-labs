package seven;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

public class task extends JPanel {

    // Цвета
    private static final Color WALL_COLOR = Color.BLACK;
    private static final Color PATH_COLOR = Color.WHITE;
    private static final Color EXIT_COLOR = Color.RED;


    private static final int CELL_SIZE = 40;
    private static final int PLAYER_SIZE = 20;
    private static final int EXIT_SIZE = 10;


    private int[][] currentLevelMatrix;
    private int rows, cols;
    private int playerRow, playerCol;
    private int exitRow, exitCol;
    private int currentLevel = 1;
    private boolean gameWon = false;

    public task() {
        loadLevel(currentLevel);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameWon) return;

                int newRow = playerRow;
                int newCol = playerCol;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        newRow--;
                        break;
                    case KeyEvent.VK_DOWN:
                        newRow++;
                        break;
                    case KeyEvent.VK_LEFT:
                        newCol--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        newCol++;
                        break;
                    default:
                        return;
                }


                if (isValidMove(newRow, newCol)) {
                    playerRow = newRow;
                    playerCol = newCol;
                    repaint();


                    if (playerRow == exitRow && playerCol == exitCol) {
                        levelComplete();
                    }
                }
            }
        });
    }


    private void loadLevel(int levelNumber) {
        String filename = "src/seven/input" + levelNumber + ".txt";
        try {
            Scanner fileScanner = new Scanner(new File(filename));


            rows = fileScanner.nextInt();
            cols = fileScanner.nextInt();
            fileScanner.nextLine();


            playerRow = fileScanner.nextInt();
            playerCol = fileScanner.nextInt();
            exitRow = fileScanner.nextInt();
            exitCol = fileScanner.nextInt();
            fileScanner.nextLine();


            currentLevelMatrix = new int[rows][cols];


            for (int i = 0; i < rows; i++) {
                String line = fileScanner.nextLine().trim();
                String[] numbers = line.split("\\s+");
                for (int j = 0; j < cols; j++) {
                    currentLevelMatrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }

            fileScanner.close();
            gameWon = false;

            System.out.println("Загружен уровень " + levelNumber);
            System.out.println("Персонаж: (" + playerRow + ", " + playerCol + ")");
            System.out.println("Выход: (" + exitRow + ", " + exitCol + ")");

        } catch (Exception e) {
            System.err.println("Ошибка при загрузке уровня " + levelNumber + ": " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Не удалось загрузить уровень " + levelNumber + ".\nИгра завершена.",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }


    private boolean isValidMove(int row, int col) {
        // Проверяем границы
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }

        return currentLevelMatrix[row][col] == 0;
    }


    private void levelComplete() {
        currentLevel++;
        if (currentLevel <= 3) {
            loadLevel(currentLevel);
            repaint();
        } else {

            gameWon = true;
            JOptionPane.showMessageDialog(this,
                    "Поздравляем! Вы прошли все уровни!",
                    "Победа!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * (CELL_SIZE + 1) + 1;
                int y = i * (CELL_SIZE + 1) + 1;

                // Определяем цвет ячейки
                if (currentLevelMatrix[i][j] == 0) {
                    g2d.setColor(PATH_COLOR);
                } else {
                    g2d.setColor(WALL_COLOR);
                }

                g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g2d.setColor(Color.GRAY);
                g2d.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }


        int exitX = exitCol * (CELL_SIZE + 1) + 1 + (CELL_SIZE - EXIT_SIZE) / 2;
        int exitY = exitRow * (CELL_SIZE + 1) + 1 + (CELL_SIZE - EXIT_SIZE) / 2;
        g2d.setColor(EXIT_COLOR);
        g2d.fillRect(exitX, exitY, EXIT_SIZE, EXIT_SIZE);


        int playerX = playerCol * (CELL_SIZE + 1) + 1 + (CELL_SIZE - PLAYER_SIZE) / 2;
        int playerY = playerRow * (CELL_SIZE + 1) + 1 + (CELL_SIZE - PLAYER_SIZE) / 2;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);


        if (gameWon) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics fm = g2d.getFontMetrics();
            String text = "ВЫ ПОБЕДИЛИ!";
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            int textX = (getWidth() - textWidth) / 2;
            int textY = getHeight() / 2;
            g2d.drawString(text, textX, textY);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Игра: Прохождение уровней");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            task gamePanel = new task();
            frame.add(gamePanel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


            gamePanel.requestFocusInWindow();
        });
    }
}
