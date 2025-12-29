package five.taskA;

import five.taskA.MyPanel;

import javax.swing.*;
import java.awt.*;

public class taskA {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    five.taskA.taskA window = new five.taskA.taskA();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public taskA() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        five.taskA.MyPanel panel = new MyPanel();

        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBounds(48, 45, 400, 400);
        frame.getContentPane().add(panel);
    }
}
