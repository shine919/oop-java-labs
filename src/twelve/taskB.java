package twelve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class taskB extends JFrame {
    private LinkedList<String> list = new LinkedList<>();
    private JTextField inputField;
    private JTextField deleteField;
    private JTextField searchField;
    private JTextArea outputArea;

    public taskB() {
        setTitle("Односвязный список (LinkedList + Итераторы)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Управление"));

        topPanel.add(new JLabel("Добавить строку:"));
        inputField = new JTextField();
        topPanel.add(inputField);

        topPanel.add(new JLabel("Удалить строку (по тексту):"));
        deleteField = new JTextField();
        topPanel.add(deleteField);

        topPanel.add(new JLabel("Поиск (часть текста):"));
        searchField = new JTextField();
        topPanel.add(searchField);

        add(topPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");
        JButton showForwardButton = new JButton("Показать прямой");
        JButton showBackwardButton = new JButton("Показать обратный");
        JButton searchButton = new JButton("Найти");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showForwardButton);
        buttonPanel.add(showBackwardButton);
        buttonPanel.add(searchButton);

        add(buttonPanel, BorderLayout.CENTER);


        outputArea = new JTextArea(15, 60);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);



        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                list.add(text);
                inputField.setText("");
                refreshOutput("Добавлено: " + text);
            }
        });

        deleteButton.addActionListener(e -> {
            String text = deleteField.getText().trim();
            if (!text.isEmpty()) {
                boolean removed = list.remove(text);
                deleteField.setText("");
                if (removed) {
                    refreshOutput("Удалено: " + text);
                } else {
                    refreshOutput("Не найдено: " + text);
                }
            }
        });

        showForwardButton.addActionListener(e -> {
            displayListWithIterator("Прямой порядок (итератор):", list.iterator());
        });

        showBackwardButton.addActionListener(e -> {
            displayListWithListIterator("Обратный порядок (ListIterator):", list.listIterator(list.size()));
        });

        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                List<String> results = searchWithIterator(query);
                if (results.isEmpty()) {
                    outputArea.setText("Ничего не найдено по запросу: \"" + query + "\"");
                } else {
                    displayList(results, "Результаты поиска (содержат \"" + query + "\"):");
                }
            }
        });
    }

    private void refreshOutput(String message) {
        outputArea.setText(message + "\n\nТекущий список:\n" + formatList(list));
    }

    private void displayListWithIterator(String title, Iterator<String> iterator) {
        StringBuilder sb = new StringBuilder(title).append("\n");
        int index = 1;
        while (iterator.hasNext()) {
            sb.append(index++).append(". ").append(iterator.next()).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void displayListWithListIterator(String title, ListIterator<String> iterator) {
        StringBuilder sb = new StringBuilder(title).append("\n");
        int index = 1;
        while (iterator.hasPrevious()) {
            sb.append(index++).append(". ").append(iterator.previous()).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private List<String> searchWithIterator(String query) {
        List<String> results = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.contains(query)) {
                results.add(item);
            }
        }
        return results;
    }

    private void displayList(List<String> items, String title) {
        StringBuilder sb = new StringBuilder(title).append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private String formatList(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new taskB().setVisible(true);
        });
    }
}
