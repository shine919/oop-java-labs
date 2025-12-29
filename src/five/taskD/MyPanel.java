package five.taskD ;

import java.awt.*;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    private static final int MAX_DEPTH = 3;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        int w = getWidth(), h = getHeight();
        draw5(g, w / 2, h / 2, 60, 20, 0);
    }

    private void draw5(Graphics g, int cx, int cy, int len, int wing, int depth) {
        if (depth > MAX_DEPTH || len < 15) return;

        g.drawLine(cx - wing, cy, cx, cy - len);
        g.drawLine(cx + wing, cy, cx, cy - len);

        g.drawLine(cx, cy - wing, cx + len, cy - len / 2);
        g.drawLine(cx, cy + wing, cx + len, cy - len / 2);

        g.drawLine(cx, cy - wing, cx + len, cy + len / 2);
        g.drawLine(cx, cy + wing, cx + len, cy + len / 2);

        g.drawLine(cx - wing, cy, cx, cy + len);
        g.drawLine(cx + wing, cy, cx, cy + len);

        g.drawLine(cx, cy - wing, cx - len, cy);
        g.drawLine(cx, cy + wing, cx - len, cy);

        g.setColor(Color.RED);
        g.drawString(depth + "F", cx - 4, cy + 4);
        g.setColor(Color.BLUE);

        draw4(g, cx, cy - len, len / 2, wing / 2, depth + 1);
        draw4(g, cx + len, cy - len / 2, len / 2, wing / 2, depth + 1);
        draw4(g, cx + len, cy + len / 2, len / 2, wing / 2, depth + 1);
        draw4(g, cx, cy + len, len / 2, wing / 2, depth + 1);
        draw4(g, cx - len, cy, len / 2, wing / 2, depth + 1);
    }

    private void draw4(Graphics g, int cx, int cy, int len, int wing, int depth) {
        if (depth > MAX_DEPTH || len < 15) return;

        g.drawLine(cx - wing, cy, cx, cy - len);
        g.drawLine(cx + wing, cy, cx, cy - len);

        g.drawLine(cx - wing, cy, cx, cy + len);
        g.drawLine(cx + wing, cy, cx, cy + len);

        g.drawLine(cx, cy - wing, cx - len, cy);
        g.drawLine(cx, cy + wing, cx - len, cy);

        g.drawLine(cx, cy - wing, cx + len, cy);
        g.drawLine(cx, cy + wing, cx + len, cy);

        g.setColor(Color.RED);
        g.drawString(depth + "4", cx - 4, cy + 4);
        g.setColor(Color.BLUE);

        draw5(g, cx, cy - len, len / 2, wing / 2, depth + 1);
        draw5(g, cx, cy + len, len / 2, wing / 2, depth + 1);
        draw5(g, cx - len, cy, len / 2, wing / 2, depth + 1);
        draw5(g, cx + len, cy, len / 2, wing / 2, depth + 1);
    }
}