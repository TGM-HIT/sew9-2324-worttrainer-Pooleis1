package tgm.ac.at.parnold;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordspellTrainer {

    private List<WordPair> wordpairs = new ArrayList<WordPair>();
    private WordPair pickedWord;
    private Stats stats;
    private WordTrainerSaveManager saveManager;

    public WordspellTrainer(){
        stats = new Stats();
    }

    public WordspellTrainer(WordTrainerSaveManager saveManager){
        stats = new Stats();
        setSaveManager(saveManager);
    }

    public WordspellTrainer(WordTrainerSaveManager saveManager, String path) throws IOException {
        stats = new Stats();
        setSaveManager(saveManager);
        if(path == null) throw new IllegalArgumentException("Es kann von einem ungültigen Pfad nichts geladen werden!");
        load(path);
    }

    public void addWordList(List<WordPair> wordpairs){
        this.wordpairs = wordpairs;
    }

    public void addWordList(WordPair[] wordpairs){
        for(WordPair pairs: wordpairs)
            this.wordpairs.add(pairs);
    }

    public void addWordpair(WordPair wordpair){
        wordpairs.add(wordpair);
    }

    public void setSaveManager(WordTrainerSaveManager saveManager){
        this.saveManager = saveManager;
    }

    public void setStats(Stats stats){
        this.stats = stats;
    }

    public void setPickedWord(WordPair pickedWord){
        this.pickedWord = pickedWord;
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
            pickRandom();
            return true;
        } else
            stats.addWrongWords();
        return false;
    }

    public void load(String path) throws IOException{
        if(saveManager==null) throw new IllegalArgumentException("Es ist kein SaveManger vorhanden mit dem das speichern möglich ist.");
        if(path==null) throw new IllegalArgumentException("Es kann von einem ungültigen Pfad nichts geladen werden!");
        WordspellTrainer trainer = saveManager.load(path);
        this.stats = trainer.getStatistic();
        this.pickedWord = trainer.getPickedWord();
        this.wordpairs.clear();
        this.addWordList(trainer.getWordpairs());
    }

    public void save(String path) throws IOException{
        if(saveManager==null) throw new IllegalArgumentException("Es ist kein SaveManger vorhanden mit dem das speichern möglich ist.");
        if(path==null) throw new IllegalArgumentException("Es kann von einem ungültigen Pfad nichts geladen werden!");
        saveManager.save(path, this);
    }

    public WordPair getPickedWord() {
        return pickedWord;
    }

    public WordPair getWordpair(int index){
        return wordpairs.get(index);
    }

    public WordPair[] getWordpairs(){
        return wordpairs.toArray(new WordPair[wordpairs.size()]);
    }

    public Stats getStatistic(){
        return stats;
    }

    public String getTextStatistic(){
        return "Statistik:\nGesamt: " + stats.getTryedWords() + "; Richtig: " + stats.getRightWords() + "; Falsch: " + stats.getWrongWords();
    }
}
