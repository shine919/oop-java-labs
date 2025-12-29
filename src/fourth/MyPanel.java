package fourth;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawLine(10, 100, 100, 10);
    }
}
