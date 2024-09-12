package Controller;

import Model.PastWords;
import Model.SpellingBeeContent;
import View.PastWordsPanel;
import View.ScoringBar;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 * Class provides a GUI that allows users to enter guesses and see word count, correct words 
 * and pangrams. The user will also see error messages if they input invalid words.
 */
public class SpellingBeeGUI {
    // Timer to control error messages
    private static Timer t;
    
    // Number of milliseconds error messages appear for
    private static final int MESSAGE = 1500;
    
    SpellingBeeContent gameContent;

    /**
     * Constructs user interface for SpellingBee
     */
    public SpellingBeeGUI() {
        gameContent = new SpellingBeeContent();
        gameContent.createCommonDictionarySet();
        gameContent.createUncommonDictionarySet();
        gameContent.createNewLetterSet();
        
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font font = new Font("MS Gothic", Font.PLAIN, 20);
        
        // Creates TextField for entering guess
        JTextField word = new JTextField(15);
        JLabel wordLabel = new JLabel("Your word: ");
        word.setPreferredSize(new Dimension(300, 35));
        wordLabel.setPreferredSize(new Dimension(110, 35));
        word.setFont(font);
        wordLabel.setFont(font);
        
        // Creates SpellingBee hexagon display
        Hexagons hexagon = new Hexagons(gameContent.getAllowedCharacters());
        hexagon.setPreferredSize(new Dimension(450, 580));
        
        // Creates JEditorPane to store word count, correct words, and pangrams
        PastWords pastWords = new PastWords();
        PastWordsPanel panel = new PastWordsPanel();
        gameContent.addPastWord(pastWords);
        pastWords.addPanel(panel);       
        panel.setPreferredSize(new Dimension(290, 590));
        panel.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        panel.setFont(font);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVisible(true);
        
        // Creates score bar
        ScoringBar score = new ScoringBar();
        score.setPreferredSize(new Dimension(800, 500));
        score.setVisible(true);
        pastWords.addScoreBar(score);
        
        // Add listeners to JTextField component and create error messages
        word.addActionListener(event -> {
            
        Popup popup;
        PopupFactory pf = new PopupFactory();
        JLabel popupLabel = new JLabel();
        popupLabel.setForeground(Color.WHITE);
        popupLabel.setFont(font);
        JPanel popupPanel = new JPanel();
        popupPanel.setBackground(Color.BLACK);
        popupPanel.add(popupLabel);
        popup = pf.getPopup(f, popupPanel, 265, 150);
            
        String playerGuess = word.getText();
        String errorMessage = gameContent.checkGuess(playerGuess);
        
            popupLabel.setText(errorMessage);
            if (errorMessage != "" ) {
            t = new Timer(MESSAGE, event1 -> {
                popup.hide();
                t.stop();
            }
                    );
            t.start();
            popup.show();
            }
            
        });
        
        // Add all GUI elements to display
        JPanel eastPanel = new JPanel();
        eastPanel.add(scrollPane);
        
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.WHITE);
        northPanel.setPreferredSize(new Dimension(50, 50));
        northPanel.add(score);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(hexagon);
        centerPanel.add(eastPanel, BorderLayout.EAST);
        
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(50, 50));
        southPanel.add(wordLabel);
        southPanel.add(word);
        
        f.add(northPanel, BorderLayout.NORTH);
        f.add(centerPanel, BorderLayout.CENTER);
        f.add(southPanel, BorderLayout.SOUTH);
        
        // Set size of JFrame
        f.setSize(1000, 750);
        f.setResizable(false);
        f.setVisible(true);
    }
    
    /**
     * Main method to start program
     * 
     * @param args None required
     */
    public static void main(String[] args) {
        new SpellingBeeGUI();
    }

}
