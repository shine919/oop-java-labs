package eight.hwC;



import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class hwС extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton processButton;

    public hwС() {
        setTitle("Вариант С3: Инверсия регистра в словах с твердым/мягким знаком");

        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setEditable(false);

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);

        processButton = new JButton("Обработать текст");

        loadTextFromFile("src/eight/hwC/input.txt");

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

    private void processAndSaveText() {
        String originalText = inputTextArea.getText();
        if (originalText == null || originalText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет текста для обработки.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Разбиваем на слова
        String[] words = originalText.split("\\s+");
        ModifiableString result = new ModifiableString();

        for (int i = 0; i < words.length; i++) {
            ModifiableString word = new ModifiableString(words[i]);
            if (word.contains('ъ') || word.contains('ь')) {
                word.invertCase();
            }
            result.append(word.toString());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        String processedText = result.toString();
        outputTextArea.setText(processedText);
        saveTextToFile(processedText, "src/eight/hwC/output.txt");

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
        SwingUtilities.invokeLater(() -> new hwС());
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


        public void append(String str) {
            if (str == null) return;
            char[] strChars = str.toCharArray();
            ensureCapacity(length + strChars.length);
            System.arraycopy(strChars, 0, chars, length, strChars.length);
            length += strChars.length;
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