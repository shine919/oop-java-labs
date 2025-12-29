package five.HomeTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JFrame {

    private JTextField xField, mField;
    private JLabel resultLabel;

    public MyPanel() {
        setTitle("Cosine Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(5, 1));

        add(new JLabel("Введите x:"));
        xField = new JTextField();
        add(xField);

        add(new JLabel("Введите m (количество членов):"));
        mField = new JTextField();
        add(mField);

        JButton calcButton = new JButton("Вычислить cos(x)");
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(xField.getText());
                    int m = Integer.parseInt(mField.getText());
                    double res = computeCos(x, m);
                    resultLabel.setText(String.format("cos(%f) ≈ %f", x, res));
                } catch (Exception ex) {
                    resultLabel.setText("Ошибка ввода!");
                }
            }
        });
        add(calcButton);

        resultLabel = new JLabel("Результат:");
        add(resultLabel);
    }

    private double computeCos(double x, int m) {
        return computeCosRecursive(x, m, 0);
    }

    private double computeCosRecursive(double x, int m, int k) {
        if (k > m) return 0.0;
        double term = Math.pow(-1, k) * Math.pow(x, 2 * k) / factorial(2 * k);
        return term + computeCosRecursive(x, m, k + 1);
    }

    private long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MyPanel().setVisible(true);
        });
    }
}
