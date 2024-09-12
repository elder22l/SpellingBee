package View;

import java.util.ArrayList;

import javax.swing.JEditorPane;

/**
 * Class provides a JEditorPane to store past words and pangrams
 */
public class PastWordsPanel extends JEditorPane{
    StringBuilder words;
    
    public PastWordsPanel(){
        super("text/html", "You have found 0 words");
        words = new StringBuilder();
    }
    
    public void updateText(ArrayList<String> pastWordArray) {
        clearPanel();
        words.append("You have found " + pastWordArray.size() + " words");
        for(String word: pastWordArray) {
            words.append("<br>" + word);
        }
        setText(words.toString());
    }
    private void clearPanel() {
        words.setLength(0);
        
    }

}
