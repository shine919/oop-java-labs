package THIRD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class second extends JFrame {

    // Компоненты GUI
    private JTextField sizeField;
    private JButton displayButton;
    private JTextArea resultArea;


    public second() {
        setTitle("Узор 5: Симметричный Бриллиант");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Центральное число (N): "));
        sizeField = new JTextField(5);
        inputPanel.add(sizeField);
        displayButton = new JButton("Отобразить Узор");
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
                    String pattern = generatePattern(n);
                    resultArea.setText(pattern);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(second.this,
                            "Ошибка ввода: " + ex.getMessage(),
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String generatePattern(int n) {
        StringBuilder sb = new StringBuilder();
        int totalRows = 2 * n - 1;
        int max_width = 2 * (2 * n - 1) - 1;

        for (int row = 1; row <= totalRows; row++) {

            int level;
            if (row <= n) {
                level = row;
            } else {
                level = 2 * n - row;
            }


            int center = n - level + 1;


            int count_of_nums = 2 * level - 1;


            int width_of_current_row = 2 * count_of_nums - 1;


            int padding_spaces = (max_width - width_of_current_row) / 2;


            StringBuilder rowStr = new StringBuilder();


            for (int i = 0; i < padding_spaces; i++) {
                rowStr.append(' ');
            }


            for (int num = n; num >= center; num--) {
                rowStr.append(num).append(" ");
            }


            for (int num = center + 1; num <= n; num++) {
                rowStr.append(num).append(" ");
            }


            if (rowStr.length() > 0 && rowStr.charAt(rowStr.length() - 1) == ' ') {
                rowStr.setLength(rowStr.length() - 1);
            }


            sb.append(rowStr.toString()).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            second app = new second();
            app.setVisible(true);
        });
    }
}
