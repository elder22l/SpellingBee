package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Creates scoring bar for SpellingBee interface
 */
public class ScoringBar extends JComponent{
    private int circleLeft = 475;
    private final int CIRCLE_RADIUS = 35;
    private static final Color YELLOW_CIRCLE = new Color(252, 228, 66);
    private static final Color GRAY_RECTANGLE = new Color(237, 237, 237);
    String score = "0";
    
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        
        g.setColor(GRAY_RECTANGLE);
        g.fillRect(475, 20, 300, 5);
        
        g.setColor(YELLOW_CIRCLE);
        g.fillOval(circleLeft, 5, CIRCLE_RADIUS, CIRCLE_RADIUS);
        
        g.setColor(Color.BLACK);
        Font font = new Font("MS Gothic", Font.BOLD, 10);
        g.setFont(font);
        drawCenteredString(g, score, 10, circleLeft+13, 27);
    }
    
    /**
     * Method to center string on circle
     * @param g
     * @param text the String passed in
     * @param width the width of the String
     * @param x the x position
     * @param y the y position
     */
    public void drawCenteredString(Graphics g, String text, int width, int x, int y) {
        FontMetrics metrics = g.getFontMetrics();
        int letterRect = (int) metrics.getStringBounds(text, g).getWidth();
        int position = width/2 - letterRect/2; 
        g.drawString(text, position + x, y);
    }
    
    /**
     * Method to move circle when score increases
     * @param x
     */
    public void moveCircle(int x, int currentScore) {
        circleLeft = circleLeft + x*2;
        String currentScoreString  = currentScore + "";
        score = currentScoreString;
        repaint();
    }
    public void winState() {
        score = "Genius";
        repaint();
    }

}