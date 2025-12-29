package THIRD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hw_first extends JFrame {

    // Компоненты GUI
    private JTextField sizeField;
    private JButton displayButton;
    private JTextArea resultArea;

    public hw_first() {
        setTitle("Узор 6: Обратные Строки");
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
                    String pattern = generatePattern6(n);
                    resultArea.setText(pattern);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(hw_first.this,
                            "Ошибка ввода: " + ex.getMessage(),
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    private String generatePattern6(int n) {
        StringBuilder sb = new StringBuilder();

        int row = 1;

        while (row <= n) {
            StringBuilder rowStr = new StringBuilder();
            int num = row;

            while (num >= 1) {
                rowStr.append(num).append(" ");
                num--;
            }

            sb.append(rowStr.toString().trim()).append("\n");
            row++;
        }

        return sb.toString().trim();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            hw_first app = new hw_first();
            app.setVisible(true);
        });
    }
}
