package five.taskC;

import java.awt.*;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    private static final int MAX_DEPTH = 3;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int initialLength = 60;

        drawStar(g, centerX, centerY, initialLength, 20, true, 0);
    }

    private void drawStar(Graphics g, int cx, int cy, int length, int wingLength, boolean fivePointed, int depth) {
        if (depth > MAX_DEPTH || length < 15) return;


        Color originalColor = g.getColor();

        if (fivePointed) {
            drawFivePointedStarLikeOriginal(g, cx, cy, length, wingLength);
        } else {
            drawFourPointedStarLikeOriginal(g, cx, cy, length, wingLength);
        }


        g.setColor(Color.RED);
        g.drawString(depth + (fivePointed ? "F" : "4"), cx - 4, cy + 4);
        g.setColor(originalColor);


        if (fivePointed) {

            int[][] offsets = {
                    {0, -length},
                    {length, -length / 2},
                    {length, length / 2},
                    {0, length},
                    {-length, 0}
            };
            for (int[] off : offsets) {
                int endX = cx + off[0];
                int endY = cy + off[1];
                drawStar(g, endX, endY,
                        (int)(length * 0.5), (int)(wingLength * 0.5),
                        false, depth + 1);
            }
        } else {

            int[][] offsets = {
                    {0, -length},
                    {0, length},
                    {-length, 0},
                    {length, 0}
            };
            for (int[] off : offsets) {
                int endX = cx + off[0];
                int endY = cy + off[1];
                drawStar(g, endX, endY,
                        (int)(length * 0.5), (int)(wingLength * 0.5),
                        true, depth + 1);
            }
        }
    }


    private void drawFivePointedStarLikeOriginal(Graphics g, int cx, int cy, int length, int wingLength) {

        g.drawLine(cx - wingLength, cy, cx, cy - length);
        g.drawLine(cx + wingLength, cy, cx, cy - length);


        g.drawLine(cx, cy - wingLength, cx + length, cy - length / 2);
        g.drawLine(cx, cy + wingLength, cx + length, cy - length / 2);


        g.drawLine(cx, cy - wingLength, cx + length, cy + length / 2);
        g.drawLine(cx, cy + wingLength, cx + length, cy + length / 2);


        g.drawLine(cx - wingLength, cy, cx, cy + length);
        g.drawLine(cx + wingLength, cy, cx, cy + length);


        g.drawLine(cx, cy - wingLength, cx - length, cy);
        g.drawLine(cx, cy + wingLength, cx - length, cy);
    }


    private void drawFourPointedStarLikeOriginal(Graphics g, int cx, int cy, int length, int wingLength) {

        g.drawLine(cx - wingLength, cy, cx, cy - length);
        g.drawLine(cx + wingLength, cy, cx, cy - length);


        g.drawLine(cx - wingLength, cy, cx, cy + length);
        g.drawLine(cx + wingLength, cy, cx, cy + length);


        g.drawLine(cx, cy - wingLength, cx - length, cy);
        g.drawLine(cx, cy + wingLength, cx - length, cy);


        g.drawLine(cx, cy - wingLength, cx + length, cy);
        g.drawLine(cx, cy + wingLength, cx + length, cy);
    }
}