package tgm.ac.at.parnold;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordspellTrainer {

    private List<WordPair> wordpairs = new ArrayList<WordPair>();
    private WordPair pickedWord;
    private Stats stats;

    public WordspellTrainer(){
        stats = new Stats();
    }

    public void addWordList(List<WordPair> wordpairs){
        for(WordPair pairs: wordpairs)
            this.wordpairs.add(pairs);
    }

    public void addWordpair(WordPair wordpair){
        wordpairs.add(wordpair);
    }

    public void pick(int index){
        pickedWord = wordpairs.get(index);
    }

    public void pickRandom(){
        Random r = new Random();
        pick(r.nextInt(wordpairs.size()));
    }

    public boolean check(String input){
        if(pickedWord.validate(input)) {
            stats.addRightWords();
            return true;
        } else
            stats.addWrongWords();
        pickRandom();
        return false;
    }

    public WordPair getPickedWord() {
        return pickedWord;
    }

    public WordPair getWordpair(int index){
        return wordpairs.get(index);
    }

    public WordPair[] getWordpairs(){
        return (WordPair[]) wordpairs.toArray();
    }

    public String getStatistic(){
        return "Statistik:\nGesamt: " + stats.getTryedWords() + "; Richtig: " + stats.getRightWords() + "; Falsch: " + stats.getWrongWords();
    }

}
