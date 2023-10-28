package tgm.ac.at.parnold;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class JSONSaveManager implements WordTrainerSaveManager{


    @Override
    public void save(String filepath, WordspellTrainer wordTrainer) throws IOException {
        ObjectMapper om = new ObjectMapper();
        savableWordspellTrainer swst = new savableWordspellTrainer();

        Stats st = wordTrainer.getStatistic();
        swst.stats = new savableStats();
        swst.stats.rightWords = st.getRightWords();
        swst.stats.wrongWords = st.getWrongWords();
        swst.stats.tryedWords = st.getTryedWords();

        WordPair picked = wordTrainer.getPickedWord();
        swst.pickedword = new savableWordpair();
        swst.pickedword.word = picked.getWord();
        swst.pickedword.imageURL = picked.getImageURL();

        WordPair[] pairs = wordTrainer.getWordpairs();
        swst.wordpairs = new savableWordpair[pairs.length];
        for(int i = 0; i < pairs.length; i++){
            swst.wordpairs[i] = new savableWordpair();
            swst.wordpairs[i].imageURL = pairs[i].getImageURL();
            swst.wordpairs[i].word = pairs[i].getWord();
        }

        om.writeValue(new File(filepath),swst);
    }

    @Override
    public WordspellTrainer load(String filepath) throws IOException {
        ObjectMapper om = new ObjectMapper();

        Scanner sc = new Scanner(new File(filepath));
        String readJSON = sc.next();
        System.out.println(readJSON);
        savableWordspellTrainer swst = om.readValue(readJSON, savableWordspellTrainer.class);
        WordspellTrainer trainer = new WordspellTrainer();

        trainer.setPickedWord(new WordPair(swst.pickedword.imageURL, swst.pickedword.word));

        trainer.setStats(new Stats(swst.stats.rightWords,swst.stats.wrongWords,swst.stats.tryedWords));

        for (savableWordpair swp: swst.wordpairs){
            trainer.addWordpair(new WordPair(swp.imageURL,swp.word));
            System.out.println(swp.word);
        }

        return trainer;
    }

    private static class savableWordpair implements Serializable {
        public String imageURL;
        public String word;
    }

    private static class savableStats implements Serializable {
        public int rightWords;
        public int wrongWords;
        public int tryedWords;
    }

    private static class savableWordspellTrainer implements Serializable {
        @Serial
        private static final long serialVersionUID = 256584L;
        public savableWordpair[] wordpairs;
        public savableWordpair pickedword;
        public savableStats stats;
    }

}
