package THIRD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class first extends JFrame {

    // Компоненты GUI
    private JTextField sizeField;
    private JButton displayButton;
    private JTextArea resultArea;

    public first() {
        setTitle("Цифровые Узоры");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Размер фигуры (N): "));
        sizeField = new JTextField(5);
        inputPanel.add(sizeField);
        displayButton = new JButton("Отобразить Узоры");
        inputPanel.add(displayButton);
        add(inputPanel, BorderLayout.NORTH);


        resultArea = new JTextArea(20, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);


        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(sizeField.getText().trim());
                    if (n <= 0) {
                        throw new NumberFormatException("Размер должен быть положительным целым числом.");
                    }
                    displayAllPatterns(n);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(first.this,
                            "Ошибка ввода: " + ex.getMessage(),
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    private void displayAllPatterns(int n) {
        StringBuilder output = new StringBuilder();

        output.append("=== УЗОР 1  ===\n");
        output.append(generatePattern1(n)).append("\n\n");

        output.append("=== УЗОР 2  ===\n");
        output.append(generatePattern2(n)).append("\n\n");

        output.append("=== УЗОР 3  ===\n");
        output.append(generatePattern3(n)).append("\n\n");

        output.append("=== УЗОР 4  ===\n");
        output.append(generatePattern4(n)).append("\n");

        resultArea.setText(output.toString());
    }

    private String generatePattern1(int n) {
        StringBuilder sb = new StringBuilder();
        int row = 1;

        while (row <= n) {
            int col = 1;
            StringBuilder rowStr = new StringBuilder();

            while (col <= n) {
                int startNum = row + col - 1;
                int currentNum = startNum;

                for (int i = 0; i < 1; i++) {
                    rowStr.append(currentNum).append(" ");
                    currentNum += 2;
                }
                col++;
            }
            sb.append(rowStr.toString().trim()).append("\n");
            row++;
        }
        return sb.toString().trim();
    }


    private String generatePattern2(int n) {
        StringBuilder sb = new StringBuilder();
        int row = 1;

        while (row <= n) {
            int num = 1;
            StringBuilder rowStr = new StringBuilder();

            while (num <= row) {
                rowStr.append(num).append(" ");
                num++;
            }
            sb.append(rowStr.toString().trim()).append("\n");
            row++;
        }
        return sb.toString().trim();
    }


    private String generatePattern3(int n) {
        StringBuilder sb = new StringBuilder();
        int rowsLeft = n;

        do {
            int num = 1;
            StringBuilder rowStr = new StringBuilder();

            do {
                rowStr.append(num).append(" ");
                num++;
            } while (num <= rowsLeft);

            sb.append(rowStr.toString().trim()).append("\n");
            rowsLeft--;
        } while (rowsLeft > 0);

        return sb.toString().trim();
    }


    private String generatePattern4(int n) {
        StringBuilder sb = new StringBuilder();
        int rowsLeft = n;

        do {
            int num = n;
            StringBuilder rowStr = new StringBuilder();

            do {
                rowStr.append(num).append(" ");
                num--;
            } while (num >= (n - rowsLeft + 1));

            sb.append(rowStr.toString().trim()).append("\n");
            rowsLeft--;
        } while (rowsLeft > 0);

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            first app = new first();
            app.setVisible(true);
        });
    }
}