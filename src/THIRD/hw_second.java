package THIRD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class hw_second extends JFrame {


    private JTextField sizeField;
    private JButton displayButton;
    private JTextArea resultArea;


    public hw_second() {
        setTitle("Узор 7: Пирамида-Зеркало (По ГСА)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Размер фигуры (N): "));
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
                    String pattern = generatePattern7(n);
                    resultArea.setText(pattern);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(hw_second.this,
                            "Ошибка ввода: " + ex.getMessage(),
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    private String generatePattern7(int n) {
        StringBuilder sb = new StringBuilder();
        int row = 1;


        while (row <= 2 * n - 1) {

            int level;
            if (row <= n) {
                level = row;
            } else {
                level = 2 * n - row;
            }

            StringBuilder rowStr = new StringBuilder();


            int spaces = n - level;
            int space_count = 1;
            while (space_count <= spaces) {
                rowStr.append("  ");
                space_count++;
            }


            int num = 1;
            while (num <= level) {
                rowStr.append(num).append(" ");
                num++;
            }


            sb.append(rowStr.toString()).append("\n");
            row++;
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            hw_second app = new hw_second();
            app.setVisible(true);
        });
    }
}
