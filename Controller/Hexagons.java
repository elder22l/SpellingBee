package Controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JComponent;

/**
 * Creates shapes & text for SpellingBee interface
 */
public class Hexagons extends JComponent {
    // Colors for hexagon display
    private static final Color YELLOW_HEXAGON = new Color(252, 228, 66);
    private static final Color GRAY_HEXAGON = new Color(237, 237, 237);
    ArrayList<Character> allowedLetters;
    
    public Hexagons(ArrayList<Character> allowedLetters) {
        this.allowedLetters = allowedLetters;
    }
   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Yellow center hexagon
        g.setColor(YELLOW_HEXAGON);
        int[] yellowX = {164, 234, 267, 234, 164, 129};
        int[] yellowY = {220, 220, 280, 340, 340, 280};
        g.fillPolygon(yellowX, yellowY, 6);
        
        // Gray outer hexagons
        g.setColor(GRAY_HEXAGON);
        int[] x1 = {164, 234, 267, 234, 164, 129};
        int[] y1 = {90, 90, 150, 210, 210, 150};
        g.fillPolygon(x1, y1, 6);
        
        int[] x2 = {277, 347, 380, 347, 277, 242};
        int[] y2 = {150, 150, 210, 270, 270, 210};
        g.fillPolygon(x2, y2, 6);
        
        int[] x3 = {277, 347, 380, 347, 277, 242};
        int[] y3 = {280, 280, 340, 400, 400, 340};
        g.fillPolygon(x3, y3, 6);
        
        int[] x4 = {164, 234, 267, 234, 164, 129};
        int[] y4 = {350, 350, 410, 470, 470, 410};
        g.fillPolygon(x4, y4, 6);
        
        int[] x5 = {52, 122, 155, 122, 52, 17};
        int[] y5 = {280, 280, 340, 400, 400, 340};
        g.fillPolygon(x5, y5, 6);
        
        int[] x6 = {52, 122, 155, 122, 52, 17};
        int[] y6 = {150, 150, 210, 270, 270, 210};
        g.fillPolygon(x6, y6, 6);
        
        // Add letters to hexagons
        g.setColor(Color.BLACK);
        Font font = new Font("MS Gothic", Font.BOLD, 40);
        g.setFont(font);
        drawCenteredString(g, allowedLetters.get(0).toString().toUpperCase(), 10, 193, 295);
        drawCenteredString(g, allowedLetters.get(1).toString().toUpperCase(), 10, 80, 225);
        drawCenteredString(g, allowedLetters.get(2).toString().toUpperCase(), 10, 80, 355);
        drawCenteredString(g, allowedLetters.get(3).toString().toUpperCase(), 10, 310, 225);
        drawCenteredString(g, allowedLetters.get(4).toString().toUpperCase(), 10, 310, 355);
        drawCenteredString(g, allowedLetters.get(5).toString().toUpperCase(), 10, 193, 165);
        drawCenteredString(g, allowedLetters.get(6).toString().toUpperCase(), 10, 193, 425);
    }
    
    /**
     * Method to center string on hexagon
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
    
}
