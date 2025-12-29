package fourth.task1;

import fourth.task1.MyPanel;

import javax.swing.*;
import java.awt.*;

public class task1 {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    fourth.task1.task1 window = new task1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public task1() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        fourth.task1.MyPanel panel = new MyPanel();

        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBounds(48, 45, 312, 184);
        frame.getContentPane().add(panel);
    }
}

