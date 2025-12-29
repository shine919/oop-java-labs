package fourth.task1;

import java.awt.*;
import javax.swing.JPanel;

public class MyPanel extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        drawHouse(g, 50, 50);
    }


    private void drawHouse(Graphics g, int x, int y) {

        g.setColor(Color.BLACK);


        g.drawRect(x, y, 100, 80);
        int x1 = 130, y1 = 10;
        int x2 = 140, y2 = 40;
        int[] rectX = {130, x2, x2, 130};
        int[] rectY = {y1, y1, 45, 40};
        int[] roofX = {x, x + 50, x + 100};
        int[] roofY = {y, y - 30, y};
        g.drawPolygon(roofX, roofY, 3);
        g.drawPolygon(rectX, rectY, 4);
    }
}
