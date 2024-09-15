//lelder
package spellingBee;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spellingBee.model.PastWords;
import spellingBee.model.SpellingBeeContent;

class SpellingBeeTest {
    private PastWords pastWords;
    private SpellingBeeContent gameContent;
    @BeforeEach
    void setUp() throws Exception {
        pastWords = new PastWords();
        gameContent = new SpellingBeeContent();
    }
 
    @Test
    void testIsPangram() {
        ArrayList<Character> testCharacters = new ArrayList<>();
        String testString = "storage";
        for (int i = 0; i < testString.length(); i ++) {
            testCharacters.add(testString.charAt(i));
        }
        assertEquals(pastWords.isPangram("storage", testCharacters), true);
        assertEquals(pastWords.isPangram("sort", testCharacters), false);
        assertEquals(pastWords.isPangram("sortageresfasdfasdf", testCharacters), true);
        assertEquals(pastWords.isPangram("raged", testCharacters), false);
    }
    @Test
    void testAllLettersAreUnique() {
        
        boolean testCase = gameContent.allLettersAreUnique("please");
        boolean testCase2 = gameContent.allLettersAreUnique("outing");
        assertEquals(testCase, false);
        assertEquals(testCase2, true);
    }
    @Test
    void testCheckGuess() {
        SpellingBeeContent gameContent = new SpellingBeeContent();
        gameContent.createCommonDictionarySet();
        gameContent.createUncommonDictionarySet();
        ArrayList<Character> testCharacters = new ArrayList<>();
        String testString = "storage";
        for (int i = 0; i < testString.length(); i ++) {
            testCharacters.add(testString.charAt(i));
        }
        
        gameContent.setAllowedCharacters(testCharacters);
        
        assertEquals(gameContent.checkGuess("storage"), "");
        assertEquals(gameContent.checkGuess("rats"), "");
        assertEquals(gameContent.checkGuess("rage"), "Missing center letter");
        assertEquals(gameContent.checkGuess("hello"), "Invalid letters");
        assertEquals(gameContent.checkGuess("so"), "That word is too short"); 
    }
    @Test
    void testCheckAllowedLetters() {
        ArrayList<Character> testCharacters = new ArrayList<>();
        String testString = "storage";
        for (int i = 0; i < testString.length(); i ++) {
            testCharacters.add(testString.charAt(i));
        }
        gameContent.setAllowedCharacters(testCharacters);
        assertEquals(gameContent.checkAllowedLetters(testString), true);
        assertEquals(gameContent.checkAllowedLetters("rage"), true);
        assertEquals(gameContent.checkAllowedLetters("bouncy"), false); 
    }
}