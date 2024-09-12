package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

//METHODS ARE PUBLIC FOR THE PURPOSES OF TESTING IN THE TEST FOLDER

public class SpellingBeeContent {
    // the 7 letters to make words with
    private ArrayList<Character> allowedCharacters;
    // a list of player answers
    private PastWords answers;
    // the uncommonDictionary hashset to use
    private HashSet<String> uncommonDictionary;
    // the common dictionary hashSet
    private HashSet<String> commonDictionary;

    /**
     * checks if a player guess is valid. if soadds it to pastWords
     * 
     * @param a player guess
     * @return a string stating the error made or "" if the guess is valid
     */
    public String checkGuess(String playerGuess) { 

        if (!checkAllowedLetters(playerGuess)) {
            return "Invalid letters";
        } else if (playerGuess.length() < 3) {
            return "That word is too short";
        } else if (!hasCenterLetter(playerGuess)) {
            return "Missing center letter";
        } else if (!isWord(playerGuess)) {
            return "not in word list";
        } else if (answers != null) {
            answers.add(playerGuess, allowedCharacters);
            return "";
        } else {
            return "";
        }

    }

    // -----------methods to call on startup--------------

    /**
     * uses the commonWords dictionary to find all 7 letter words with all
     * unique letters then appends those into an ArrayList. Picks a random index
     * in the 7 letter word ArrayList and adds each character in that word into
     * a character array to be used as a letter set for the game.
     * 
     * @return the letter set used in the game
     */
    public ArrayList<Character> createNewLetterSet() {
        ArrayList<Character> toReturn = new ArrayList<Character>();
        ArrayList<String> sevenLetterWords = new ArrayList<String>();
        File file = new File("common_words.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                // adds all lines in the dicitonary to an ArrayList if it is 7
                // letters and has all unique letters
                String line = sc.nextLine();
                if (line.length() == 7 && allLettersAreUnique(line)) {
                    sevenLetterWords.add(line);
                }
            }
            //System.out.println(sevenLetterWords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // create a random integer to use as an index
        Random random = new Random();
        int randInt = random.nextInt(sevenLetterWords.size());
        String baseString = sevenLetterWords.get(randInt);

        // adds the characters from the word into a character array
        for (int i = 0; i < baseString.length(); i++) {
            toReturn.add(baseString.charAt(i));
        }
        //System.out.println(toReturn);
        setAllowedCharacters(toReturn);
        return toReturn;
    }

    /**
     * creates a hashset of the common words dictionary
     * 
     * @return common dictionary hashset
     */
    public HashSet<String> createCommonDictionarySet() {
        HashSet<String> commonDictionarySet = new HashSet<>();
        File file = new File("common_words.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                commonDictionarySet.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setCommonDictionary(commonDictionarySet);
        return commonDictionarySet;

    }

    /**
     * creates a hashset of the uncommon words dictionary
     * 
     * @return uncommon dictionary hashset
     */
    public HashSet<String> createUncommonDictionarySet() {
        HashSet<String> unCommonDictionarySet = new HashSet<>();
        File file = new File("EnglishWords.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                unCommonDictionarySet.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setUncommonDictionary(unCommonDictionarySet);
        return unCommonDictionarySet;

    }

    public void addPastWord(PastWords answer) {
        this.answers = answer;
    }
    // -------------------------------------------------

    /**
     * check that there are no repeating letters in a word
     * 
     * @param word
     * @return boolean, true if no repeating letters
     */
    public boolean allLettersAreUnique(String word) {
        HashSet<Character> lettersInWord = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            Character toAdd = word.charAt(i);
            if (lettersInWord.contains(toAdd)) {
                return false;
            }
            lettersInWord.add(toAdd);
        }
        return true;

    }

    /**
     * check to make sure only allowed letters are used in a guess
     * 
     * @param playerGuess
     * @return true if only allowed letters are used, false otherwise
     */
    public boolean checkAllowedLetters(String playerGuess) {
        ArrayList<Character> allowedLetters = getAllowedCharacters();
        HashSet<Character> allowedLetterSet = new HashSet<>();
        for (int i = 0; i < allowedLetters.size(); i++) {
            allowedLetterSet.add(allowedLetters.get(i));
        }
        for (int i = 0; i < playerGuess.length(); i++) {
            if (!allowedLetterSet.contains(playerGuess.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    /**
     * check to make sure the center letter is used
     * 
     * @param playerGuess
     * @return true if only center letter is used, false otherwise
     */
    private boolean hasCenterLetter(String playerGuess) {
        Character centerLetter = allowedCharacters.get(0);
        for (int i = 0; i < playerGuess.length(); i++) {
            if (playerGuess.charAt(i) == centerLetter) {
                return true;

            }
        }
        return false;
    }

    /**
     * check to make sure the guess is a word in one of the dictionaries
     * 
     * @param playerGuess
     * @return true if the guess is a word
     */
    private boolean isWord(String playerGuess) {
        HashSet<String> uncommonDictionaryToCheck = getUncommonDictionary();
        HashSet<String> commonDictionaryToCheck = getCommonDictionary();

        if (commonDictionaryToCheck.contains(playerGuess)) {
            return true;
        } else if (uncommonDictionaryToCheck.contains(playerGuess)) {
            return true;
        } else {
            return false;

        }

    }

    /**
     * gets allowed characters
     * 
     * @return allowed characters
     */
    public ArrayList<Character> getAllowedCharacters() {
        return allowedCharacters;
    }

    /**
     * gets uncommon words dictionary hashset
     * 
     * @return uncommon word dictionary hashset
     */
    public HashSet<String> getUncommonDictionary() {
        return uncommonDictionary;
    }

    /**
     * @return common dictionary hashset
     */
    public HashSet<String> getCommonDictionary() {
        return commonDictionary;
    }

    /**
     * sets allowed characters
     * 
     * @param allowedCharacters
     */
    public void setAllowedCharacters(ArrayList<Character> allowedCharacters) {
        this.allowedCharacters = allowedCharacters;
    }

    /**
     * sets uncommon dictionary set
     * 
     * @param uncommonDictionary
     */
    public void setUncommonDictionary(HashSet<String> uncommonDictionary) {
        this.uncommonDictionary = uncommonDictionary;
    }

    /**
     * sets common dicitonary set
     * 
     * @param commonDictionary
     */
    public void setCommonDictionary(HashSet<String> commonDictionary) {
        this.commonDictionary = commonDictionary;
    }

}
