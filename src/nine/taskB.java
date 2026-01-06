package nine;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class taskB extends JFrame {

    private JTextArea inputTextArea;
    private JTextField sequenceField;
    private JButton processButton;

    public taskB() {
        setTitle("Задача B: Подсчет слов, начинающихся с последовательности");

        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setEditable(false);

        sequenceField = new JTextField(20);
        processButton = new JButton("Обработать текст");

        loadTextFromFile("src/nine/inputB.txt");

        processButton.addActionListener(e -> processAndSaveHtml());

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Исходный текст"));
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);

        JPanel sequencePanel = new JPanel();
        sequencePanel.setBorder(BorderFactory.createTitledBorder("Начало слова"));
        sequencePanel.add(new JLabel("Введите последовательность: "));
        sequencePanel.add(sequenceField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(processButton);

        add(inputPanel, BorderLayout.NORTH);
        add(sequencePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadTextFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            ModifiableString content = new ModifiableString();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                content.append(line);
                if (fileScanner.hasNextLine()) {
                    content.append("\n");
                }
            }

            fileScanner.close();
            inputTextArea.setText(content.toString().trim());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Файл " + filename + " не найден.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            inputTextArea.setText("Ошибка загрузки файла.");
        }
    }

    private void processAndSaveHtml() {
        String originalText = inputTextArea.getText();
        if (originalText == null || originalText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет текста для обработки.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sequence = sequenceField.getText().trim();
        if (sequence.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Введите последовательность для поиска.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int count = countWordsStartingWithSequence(originalText, sequence);

        String htmlContent = generateHtml(count, sequence);

        saveHtmlToFile(htmlContent, "src/nine/outputB.html");

        JOptionPane.showMessageDialog(this, "Обработка завершена. Результат сохранён в outputB.html.", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }

    private int countWordsStartingWithSequence(String text, String sequence) {
        if (text == null || text.trim().isEmpty() || sequence.isEmpty()) {
            return 0;
        }

        String[] words = text.split("\\s+");
        int count = 0;

        for (String word : words) {
            if (!word.isEmpty() && word.startsWith(sequence)) {
                count++;
            }
        }

        return count;
    }

    private String generateHtml(int count, String sequence) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Результат</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Лабораторная работа №9</h1>\n" +
                "    <p><strong>Вариант B</strong></p>\n" +
                "    <p>Выполнил студент группы <i>ИВТ ВТАСБЗ-11</i> <b>Хайров С.Ш.</b></p>\n" +
                "    <p>Файл: <b>inputB.txt</b></p>\n" +
                "    <p>Начало слова: <b>" + sequence + "</b></p>\n" +
                "    <p>Количество найденных слов: <b>" + count + "</b></p>\n" +
                "</body>\n" +
                "</html>";
    }

    private void saveHtmlToFile(String content, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(content);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при записи в файл: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new taskB());
    }

    static class ModifiableString {
        private char[] chars;
        private int length;

        public ModifiableString(String str) {
            if (str == null) {
                this.chars = new char[0];
                this.length = 0;
            } else {
                this.chars = str.toCharArray();
                this.length = chars.length;
            }
        }

        public ModifiableString() {
            this.chars = new char[16];
            this.length = 0;
        }

        public int length() {
            return length;
        }

        public char charAt(int index) {
            if (index < 0 || index >= length) {
                throw new IndexOutOfBoundsException();
            }
            return chars[index];
        }

        public void setCharAt(int index, char c) {
            if (index < 0 || index >= length) {
                throw new IndexOutOfBoundsException();
            }
            chars[index] = c;
        }

        public boolean contains(char target) {
            for (int i = 0; i < length; i++) {
                if (chars[i] == target) {
                    return true;
                }
            }
            return false;
        }

        public void invertCase() {
            for (int i = 0; i < length; i++) {
                char c = chars[i];
                if (c >= 'A' && c <= 'Z') {
                    chars[i] = (char) (c + 32);
                } else if (c >= 'a' && c <= 'z') {
                    chars[i] = (char) (c - 32);
                }
            }
        }

        public ModifiableString append(String str) {
            if (str == null) return this;
            char[] strChars = str.toCharArray();
            ensureCapacity(length + strChars.length);
            System.arraycopy(strChars, 0, chars, length, strChars.length);
            length += strChars.length;
            return this;
        }

        private void ensureCapacity(int minCapacity) {
            if (minCapacity > chars.length) {
                int newCapacity = Math.max(chars.length * 2, minCapacity);
                char[] newChars = new char[newCapacity];
                System.arraycopy(chars, 0, newChars, 0, length);
                chars = newChars;
            }
        }

        @Override
        public String toString() {
            return new String(chars, 0, length);
        }
    }
}
