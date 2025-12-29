package five.taskB;

import java.awt.*;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    private static final int MAX_DEPTH = 5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int initialLength = 80;


        drawRecursiveStar(g, centerX, centerY, 80, 20, -1);
    }

    private void drawRecursiveStar(Graphics g, int centerX, int centerY, int length, int wingLength, int counter) {
        if (counter == 5) return;

        int endX = centerX;
        int endY = centerY;


        if (counter == 0) {

            endY = centerY - length;
        } else if (counter == 1) {

            endX = centerX + length;
            endY = centerY - length / 2;
        } else if (counter == 2) {

            endX = centerX + length;
            endY = centerY + length / 2;
        } else if (counter == 3) {

            endY = centerY + length;
        } else if (counter == 4) {

            endX = centerX - length;
            endY = centerY;
        }




        g.drawLine(endX - wingLength, endY, endX, endY - length);
        g.drawLine(endX + wingLength, endY, endX, endY - length);


        g.drawLine(endX, endY - wingLength, endX + length, endY - length / 2);
        g.drawLine(endX, endY + wingLength, endX + length, endY - length / 2);


        g.drawLine(endX, endY - wingLength, endX + length, endY + length / 2);
        g.drawLine(endX, endY + wingLength, endX + length, endY + length / 2);


        g.drawLine(endX - wingLength, endY, endX, endY + length);
        g.drawLine(endX + wingLength, endY, endX, endY + length);


        g.drawLine(endX, endY - wingLength, endX - length, endY);
        g.drawLine(endX, endY + wingLength, endX - length, endY);

        System.out.println("Рисуем звезду на конце луча " + counter);


        drawRecursiveStar(g, centerX, centerY, length, wingLength, counter + 1);
    }
}