//lelder
package spellingBee.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import spellingBee.view.PastWordsPanel;
import spellingBee.view.ScoringBar;

public class PastWords {
    // instance variable
    public ArrayList<String> answers;
    // the player's score
    int score;
    // the pastWords panel
    PastWordsPanel panel;
    // the score bar
    ScoringBar scoreBar;

    // constructor
    public PastWords() {
        this.answers = new ArrayList<>();
    }

    /**
     * adds a guess to the past words panel and calculates a score
     * 
     * @param playerGuess
     * @param allowedCharacters
     * @return true if something was added, false otherwise
     */
    public boolean add(String playerGuess, ArrayList allowedCharacters) {

        int index;
        // score is based on the word length
        int tempScore = playerGuess.length();
        if (answers != null) {
            // uses binary search to sort guesses in alphabetical order, uses a
            // comparator to ignore <b> tags
            index = Collections.binarySearch(answers, playerGuess,
                    new IgnoreTagsComparator());
            if (index < 0) {
                index = -(index + 1);
            }
            if (isPangram(playerGuess, allowedCharacters)) {
                playerGuess = "<b>" + playerGuess + "</b>";
                // score is multiplied by 2 if the word is a pangram
                tempScore = tempScore * 2;

            }
        } else {
            // add to start if answers is null
            index = 0;
        }

        // checks to see if the guess is already in the panel
        if (!alreadyGuessed(playerGuess, answers)) {
            score += tempScore;
            answers.add(index, playerGuess);

            // update the panel
            panel.updateText(answers);
            if (score < 136) {
                scoreBar.moveCircle(tempScore, score);
            }
            // let the scorebar know player has won if score > 136
            else {
                scoreBar.winState();
            }
            return true;
        }
        return false;
    }

    /**
     * checks to see if a guess is a pangram (uses all allowed letters)
     * 
     * @param playerGuess
     * @param allowedCharacters
     * @return true if the guess is a pangram
     */
    public boolean isPangram(String playerGuess, ArrayList allowedCharacters) {
        HashSet<Character> guessLetters = new HashSet<>();

        String lowerCaseGuess = playerGuess.toLowerCase();

        // for all characters in the player guess, add them to a hashSet
        for (char c : lowerCaseGuess.toCharArray()) {
            if (Character.isLetter(c)) {
                guessLetters.add(c);
            }
        }
        // if the hashset contains all of the allowed characters, return true
        return guessLetters.containsAll(allowedCharacters);

    }

    /**
     * adds a pastWordsPanel
     * 
     * @param panel
     */
    public void addPanel(PastWordsPanel panel) {
        this.panel = panel;
    }

    /**
     * adds a scorebar
     * 
     * @param scoreBar
     */
    public void addScoreBar(ScoringBar scoreBar) {
        this.scoreBar = scoreBar;
    }

    /**
     * comaparator that ignores <b> and </b> tags
     */
    private class IgnoreTagsComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            String str1 = s1.replaceAll("<(/)?b>", "");
            String str2 = s2.replaceAll("<(/)?b>", "");
            return str1.compareTo(str2);
        }

    }

    /**
     * checks if the player has already guessed a word
     * 
     * @param playerGuess
     * @param answers
     * @return true if already guessed
     */
    private boolean alreadyGuessed(String playerGuess,
            ArrayList<String> answers) {
        for (int i = 0; i < answers.size(); i++) {
            if (playerGuess.equals(answers.get(i))) {
                return true;
            }
        }
        return false;
    }
}
