package five.taskD;





import five.taskD.MyPanel;

import javax.swing.*;
import java.awt.*;

public class taskD {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    five.taskD.taskD window = new five.taskD.taskD();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public taskD() {
        initialize();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        five.taskD.MyPanel panel = new MyPanel();

        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.setBounds(48, 45, 600, 600);
        frame.getContentPane().add(panel);
    }
}
