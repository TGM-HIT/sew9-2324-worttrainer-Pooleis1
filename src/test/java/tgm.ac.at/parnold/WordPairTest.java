package tgm.ac.at.parnold;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordPairTest {

    @DisplayName("Testen des URL Validation Systems")
    @Test
    public void validtURLtest(){
        WordPair word = new WordPair( "https://www.google.com", "wort");
        assertThrows(RuntimeException.class,()->{word.setImageURL(null);},"NULL is not a correct URL!");
        assertThrows(RuntimeException.class,()->{word.setImageURL("");},"Empty URL is not a correct URL!");
        assertThrows(RuntimeException.class,()->{word.setImageURL("google(dot)com");},"This is not a correct URL!");
    }

    @DisplayName("Testen des Wort Validation Systems")
    @Test
    public void validtWordtest(){
        WordPair word = new WordPair("https://www.google.com", "wort");
        assertThrows(RuntimeException.class, ()->{word.setWord("");},"This is not a correct word, with a leangth from 0!");
        assertThrows(RuntimeException.class, ()->{word.setWord(null);},"This is not a correct word, because the word is null!!");
    }

    @DisplayName("Testen die Wort überprüfung funktioniert")
    @Test
    public void wordCheckTest(){
        WordPair word = new WordPair("https://www.google.com", "wort");
        assertTrue(word.validate("wort"));
        assertFalse(word.validate("anderesWort"));
        assertFalse(word.validate(""));
        assertFalse(word.validate(null));
    }



}
