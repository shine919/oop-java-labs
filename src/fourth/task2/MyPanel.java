package fourth.task2;

import java.awt.*;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.RED);


        int startX = 50;
        int startY = 100;


        int endX = 250;
        int endYStart = 50;
        int endYEnd = 150;
        int step = 10;


        for (int endY = endYStart; endY <= endYEnd; endY += step) {
            g.drawLine(startX, startY, endX, endY);
        }
    }
}
