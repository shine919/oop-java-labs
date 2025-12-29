package fourth;

import javax.swing.*;
import java.awt.*;

public class Lab4App0 {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Lab4App0 window = new Lab4App0();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Lab4App0() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        MyPanel panel = new MyPanel();

        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBounds(48, 45, 312, 184);
        frame.getContentPane().add(panel);
    }
}
