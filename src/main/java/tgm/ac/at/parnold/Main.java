package tgm.ac.at.parnold;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        WordspellTrainer trainer;

        try{
            trainer = new WordspellTrainer(new JSONSaveManager(), "saveTrainer.json");
        } catch (IOException e){
            trainer = new WordspellTrainer(new JSONSaveManager());

            List<WordPair> words = new ArrayList<>();
            words.add(new WordPair("https://images.eatsmarter.de/sites/default/files/styles/576x432/public/apfel-576x432.jpg", "Apfel"));
            words.add(new WordPair("https://www.kindersache.de/sites/default/files/styles/teaser/public/banana-42793_1280.jpg?itok=TbRV17I6", "Banane"));
            words.add(new WordPair("https://www.online-gartencenter.at/8738/pfirsich-kernechte-von-vorgebirge.jpg", "Pfirsich"));
            words.add(new WordPair("https://cdn.gurkerl.at/images/grocery/products/7957/7957-1603471900844.jpg", "Ananas"));

            trainer.addWordList(words);
            trainer.pickRandom();
        }

        boolean beendet = false;

        WordTrainerGUI frame = new WordTrainerGUI(trainer);

        while(!beendet)  frame.setImage(trainer.getPickedWord().getImageURL());

    }
}