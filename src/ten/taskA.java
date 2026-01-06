package ten;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class taskA extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton sortButton;

    public taskA() {
        setTitle("Задача A: Сортировка массива по возрастанию");

        inputTextArea = new JTextArea(5, 40);
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);

        outputTextArea = new JTextArea(5, 40);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false);

        sortButton = new JButton("Отсортировать");


        sortButton.addActionListener(e -> sortAndDisplay());

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Входной массив (целые числа через пробел)"));
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Отсортированный массив"));
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortButton);

        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void sortAndDisplay() {
        String inputText = inputTextArea.getText().trim();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Введите массив чисел.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String[] parts = inputText.split("\\s+");
            int[] array = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }


            bubbleSortWithSteps(array);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Неверный формат чисел. Введите целые числа, разделенные пробелами.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void bubbleSortWithSteps(int[] arr) {
        int n = arr.length;
        StringBuilder steps = new StringBuilder();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            steps.append("Шаг ").append(i + 1).append(": ").append(Arrays.toString(arr)).append("\n");
            if (!swapped) break;
        }


        outputTextArea.setText(steps.toString() + "\nРезультат:\n" + Arrays.toString(arr));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new taskA());
    }
}
