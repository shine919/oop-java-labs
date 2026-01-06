package eight.hwA;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class hwA extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton processButton;

    public hwA() {
        setTitle("Вариант А3: Замена больших букв на '#'");

        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setEditable(false);

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);

        processButton = new JButton("Обработать текст");

        loadTextFromFile("src/eight/hwA/inputA.txt");

        processButton.addActionListener(e -> processAndSaveText());

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Исходный текст"));
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Результат"));
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(processButton);

        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadTextFromFile(String filename) {
        try {
            StringBuilder content = new StringBuilder();
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
            inputTextArea.setText(content.toString().trim());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Файл " + filename + " не найден.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            inputTextArea.setText("Ошибка загрузки файла.");
        }
    }

    private void processAndSaveText() {
        String originalText = inputTextArea.getText();
        if (originalText == null || originalText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет текста для обработки.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder result = new StringBuilder();
        for (char c : originalText.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append('#');
            } else {
                result.append(c);
            }
        }

        String processedText = result.toString();
        outputTextArea.setText(processedText);
        saveTextToFile(processedText, "src/eight/hwA/output.txt");

        JOptionPane.showMessageDialog(this, "Обработка завершена. Результат сохранён в output.txt.", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveTextToFile(String text, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(text);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при записи в файл: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new hwA());
    }
}