package eight.hwD;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class hwD extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton processButton;

    public hwD() {
        setTitle("Вариант D3: Выделение слов из словаря");

        inputTextArea = new JTextArea(10, 40);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setEditable(false);

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);

        processButton = new JButton("Обработать текст");

        loadTextFromFile("src/eight/hwD/input.txt");

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

        Set<String> dictionary = loadDictionary("src/eight/hwD/diction.dic");
        if (dictionary == null) {
            JOptionPane.showMessageDialog(this, "Не удалось загрузить словарь.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] words = originalText.split("\\s+");
        ModifiableString result = new ModifiableString();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            String cleanWord = word.replaceAll("[^\\p{L}\\p{N}]", "");
            if (dictionary.contains(cleanWord)) {

                ModifiableString modifiedWord = new ModifiableString(word);
                if (modifiedWord.length() > 0) {
                    char first = modifiedWord.charAt(0);
                    if (first >= 'a' && first <= 'z') {
                        modifiedWord.setCharAt(0, (char) (first - 32));
                    }
                    if (modifiedWord.length() > 1) {
                        char last = modifiedWord.charAt(modifiedWord.length() - 1);
                        if (last >= 'a' && last <= 'z') {
                            modifiedWord.setCharAt(modifiedWord.length() - 1, (char) (last - 32));
                        }
                    }
                }
                result.append("<").append(modifiedWord.toString()).append(">");
            } else {
                result.append(word);
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        String processedText = result.toString();
        outputTextArea.setText(processedText);
        saveTextToFile(processedText, "src/eight/hwD/output.txt");

        JOptionPane.showMessageDialog(this, "Обработка завершена. Результат сохранён в output.txt.", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }

    private Set<String> loadDictionary(String filename) {
        Set<String> dict = new HashSet<>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    dict.add(line);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            return null;
        }
        return dict;
    }

    private void saveTextToFile(String text, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(text);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при записи в файл: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new hwD());
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

