package tgm.ac.at.parnold;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WordspellTrainerTest {

    @DisplayName("Testen vom richtigen hinzufügen und bekommen vom Wordspelltrainer")
    @Test
    public void wordpairAddAndGetfromWordspellTrainerTest(){
        WordspellTrainer trainer = new WordspellTrainer();
        WordPair w = new WordPair("https://images.eatsmarter.de/sites/default/files/styles/576x432/public/apfel-576x432.jpg", "Apfel");
        WordPair w1 = new WordPair("https://www.kindersache.de/sites/default/files/styles/teaser/public/banana-42793_1280.jpg?itok=TbRV17I6", "Banane");
        WordPair w2 = new WordPair("https://www.online-gartencenter.at/8738/pfirsich-kernechte-von-vorgebirge.jpg", "Pfirsich");
        WordPair w3 = new WordPair("https://cdn.gurkerl.at/images/grocery/products/7957/7957-1603471900844.jpg", "Ananas");
        trainer.addWordpair(w);
        trainer.addWordpair(w1);
        trainer.addWordpair(w2);
        trainer.addWordpair(w3);
        assertArrayEquals(new WordPair[]{w,w1,w2,w3}, trainer.getWordpairs(), "Wordpair List is not correct!");
        assertEquals(w, trainer.getWordpair(0));
        assertEquals(w1, trainer.getWordpair(1));
        assertEquals(w2, trainer.getWordpair(2));
        assertEquals(w3, trainer.getWordpair(3));
        assertThrows(IllegalArgumentException.class,()->{trainer.addWordpair(null);},"It is possible to add a null wordpair");
        assertThrows(IllegalArgumentException.class,()->{trainer.getWordpair(4);},"Index is out of Bounds of Wordpair");
    }

    @DisplayName("Testen des überprüfen von Worteingabe und dem Wort")
    @Test
    public void wordSpellCheckTest(){
        WordspellTrainer trainer = new WordspellTrainer();
        WordPair w = new WordPair("https://images.eatsmarter.de/sites/default/files/styles/576x432/public/apfel-576x432.jpg", "Apfel");
        WordPair w1 = new WordPair("https://www.kindersache.de/sites/default/files/styles/teaser/public/banana-42793_1280.jpg?itok=TbRV17I6", "Banane");
        WordPair w2 = new WordPair("https://www.online-gartencenter.at/8738/pfirsich-kernechte-von-vorgebirge.jpg", "Pfirsich");
        WordPair w3 = new WordPair("https://cdn.gurkerl.at/images/grocery/products/7957/7957-1603471900844.jpg", "Ananas");
        trainer.addWordpair(w);
        trainer.addWordpair(w1);
        trainer.addWordpair(w2);
        trainer.addWordpair(w3);

        trainer.pick(0);
        assertTrue(trainer.check("Apfel"),"Validation of the right Word is wrong");
        trainer.pick(0);
        assertFalse(trainer.check("Apl"),"Validation of the wrong Word is wrong");
    }

    @DisplayName("Testen ob Statistik zählen und setzen richtig funktioniert")
    @Test
    public void statisticTest(){
        WordspellTrainer trainer = new WordspellTrainer();
        trainer.setStats(new Stats(5,-10,5));
        assertEquals(0,trainer.getStatistic().getWrongWords(), "Negative werte sollten nicht zurück gegeben werden");
        assertEquals(5,trainer.getStatistic().getRightWords(), "Correct Words Amount is incorrect");
        assertEquals(0,trainer.getStatistic().getWrongWords(), "Wrong Words Amount is incorrect");
        assertEquals(5,trainer.getStatistic().getTryedWords(), "Tryed Words Amount is incorrect");

        WordPair w = new WordPair("https://images.eatsmarter.de/sites/default/files/styles/576x432/public/apfel-576x432.jpg", "Apfel");
        WordPair w1 = new WordPair("https://www.kindersache.de/sites/default/files/styles/teaser/public/banana-42793_1280.jpg?itok=TbRV17I6", "Banane");
        WordPair w2 = new WordPair("https://www.online-gartencenter.at/8738/pfirsich-kernechte-von-vorgebirge.jpg", "Pfirsich");
        WordPair w3 = new WordPair("https://cdn.gurkerl.at/images/grocery/products/7957/7957-1603471900844.jpg", "Ananas");
        trainer.addWordpair(w);
        trainer.addWordpair(w1);
        trainer.addWordpair(w2);
        trainer.addWordpair(w3);

        trainer.setStats(new Stats(0,0,0));
        trainer.pick(0);
        trainer.check("Apfel");
        trainer.pick(0);
        trainer.check("Apel");
        trainer.pick(1);
        trainer.check("Banane");

        assertEquals(2,trainer.getStatistic().getRightWords(),"Statistic of Correct Word Amount is incorrect");
        assertEquals(1,trainer.getStatistic().getWrongWords(),"Statistic of Wrong Word Amount is incorrect");
        assertEquals(3,trainer.getStatistic().getTryedWords(),"Statistic of Tryed Word Amount is incorrect");
    }

    @DisplayName("Testen des speichern und ladens vom Wordspelltrainer mittels JsonSaveManager")
    @Test
    public void savingTest(){
        WordspellTrainer trainer = new WordspellTrainer();
        WordPair w = new WordPair("https://images.eatsmarter.de/sites/default/files/styles/576x432/public/apfel-576x432.jpg", "Apfel");
        WordPair w1 = new WordPair("https://www.kindersache.de/sites/default/files/styles/teaser/public/banana-42793_1280.jpg?itok=TbRV17I6", "Banane");
        WordPair w2 = new WordPair("https://www.online-gartencenter.at/8738/pfirsich-kernechte-von-vorgebirge.jpg", "Pfirsich");
        WordPair w3 = new WordPair("https://cdn.gurkerl.at/images/grocery/products/7957/7957-1603471900844.jpg", "Ananas");
        trainer.addWordpair(w);
        trainer.addWordpair(w1);
        trainer.addWordpair(w2);
        trainer.addWordpair(w3);
        trainer.pickRandom();

        trainer.setStats(new Stats(10,5,15));
        WordTrainerSaveManager saveManager = new JSONSaveManager();
        trainer.setSaveManager(saveManager);

        assertDoesNotThrow(()->trainer.save("saveTrainer.json"), "The filepath is not accpeted");

        WordspellTrainer trainer1 = null;
        try{
            trainer1 = new WordspellTrainer(saveManager,"saveTrainer.json");
        } catch (IOException e){
            e.printStackTrace();
            fail("Couldn't load save file");
        }
        assertEquals(trainer,trainer1,"Loaded Wordspelltrainer is diffent to saved");
        WordspellTrainer trainer2 = new WordspellTrainer(saveManager);
        assertDoesNotThrow(()->trainer2.load("saveTrainer.json"),"File loading from path doesn't work even when saving does");
        assertEquals(trainer,trainer2,"Loading with loadmethod doesn't create a correct Equal Object");
    }

}
