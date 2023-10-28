package tgm.ac.at.parnold;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Klasse ist für das Backend des Worttrainer zuständig und soll Wortpaare speichern, sowie die Eingaben des GUIs verarbeiten
 * @author Pia Arnold
 * @version 23-10-28
 */
public class WordspellTrainer {

    private List<WordPair> wordpairs = new ArrayList<WordPair>();
    private WordPair pickedWord;
    private Stats stats;
    private WordTrainerSaveManager saveManager;

    /**
     * Die Klasse erstellt einen neuen Worttrainer ohne gewisse vorgaben
     */
    public WordspellTrainer(){
        stats = new Stats();
    }

    /**
     * Die Klasse erstellt einen neuen Worttrainer mit einem übergebenen saveManager, welcher für das speichern und laden des Trainer zuständig ist
     * @param saveManager ein saveManger welches das speichern und laden des Worttrainers übernimmt
     */
    public WordspellTrainer(WordTrainerSaveManager saveManager){
        stats = new Stats();
        setSaveManager(saveManager);
    }

    /**
     * Die Klasse erstellt einen neuen Worttrainer mit einem übergebenen saveManager, welcher für das speichern und laden des Trainer zuständig ist und einem Pfad von dem das File geladen werden soll
     * @param saveManager ein saveManger welches das speichern und laden des Worttrainers übernimmt
     * @param path der Pfad von dem ein File geladen werden soll und hingespeichert werden soll
     */
    public WordspellTrainer(WordTrainerSaveManager saveManager, String path) throws IOException {
        stats = new Stats();
        setSaveManager(saveManager);
        if(path == null) throw new IllegalArgumentException("Es kann von einem ungültigen Pfad nichts geladen werden!");
        load(path);
    }

    /**
     * Die Methode speichert eine übergebene Wortliste in die Wortpaare
     * @param wordpairs eine Liste von Wortpaaren die übergeben wird
     */
    public void addWordList(List<WordPair> wordpairs){
        this.wordpairs = wordpairs;
    }

    /**
     * Die Methode speichert eine übergebene Wortliste in die Wortpaare
     * @param wordpairs ein Array von Wortpaaren welches übergeben wird
     */
    public void addWordList(WordPair[] wordpairs){
        for(WordPair pairs: wordpairs)
            this.wordpairs.add(pairs);
    }

    /**
     * Die Methode speichert ein Wortpaar zu den Wortpaaren dazu
     * @param wordpair ein übergeben Wortpaar
     */
    public void addWordpair(WordPair wordpair){
        wordpairs.add(wordpair);
    }

    /**
     * Die Methode setzt einen neuen SaveManger
     * @param saveManager der neu übergebene SaveManager
     */
    public void setSaveManager(WordTrainerSaveManager saveManager){
        this.saveManager = saveManager;
    }

    /**
     * Die Methode setzt neue Statistiken
     * @param stats die neu übergebenen Statstiken
     */
    public void setStats(Stats stats){
        this.stats = stats;
    }

    /**
     * Die Methode setzt das ausgewählte Wort
     * @param pickedWord das neu pbergebene ausgewählte Wort
     */
    public void setPickedWord(WordPair pickedWord){
        this.pickedWord = pickedWord;
    }

    /**
     * Die Methode wählt ein spezielles Wort aus der Wortliste über den Index aus
     * @param index der Index gibt an welches Wort aus der Wortliste gewählt wird
     */
    public void pick(int index){
        pickedWord = wordpairs.get(index);
    }

    /**
     * Die Methode wählt ein zufälliges Wort aus der Wortliste aus
     */
    public void pickRandom(){
        Random r = new Random();
        pick(r.nextInt(wordpairs.size()));
    }

    /**
     * Die Methode vergleicht eine Eingabe mit dem ausgewählten Wort auf gleichheit und erhöht je nachdem die Statistik und gibt zurück ob dies übereinstimmt
     * @param input ein Wort welches mit dem ausgewöhlten Wort verglicchen werden soll
     * @return ob die Wörter miteinandern übereinstimmen oder nicht und gibt je nachdem true oder false zurück
     */
    public boolean check(String input){
        if(pickedWord.validate(input)) {
            stats.addRightWords();
            pickRandom();
            return true;
        } else
            stats.addWrongWords();
        return false;
    }

    /**
     * Die Methode ist für das laden des Worttrainer aus einem gewissen Pfad verantwortlich
     * @param path der Pfad von wo der Worttrainer geladen werden soll
     */
    public void load(String path) throws IOException{
        if(saveManager==null) throw new IllegalArgumentException("Es ist kein SaveManger vorhanden mit dem das speichern möglich ist.");
        if(path==null) throw new IllegalArgumentException("Es kann von einem ungültigen Pfad nichts geladen werden!");
        WordspellTrainer trainer = saveManager.load(path);
        this.stats = trainer.getStatistic();
        this.pickedWord = trainer.getPickedWord();
        this.wordpairs.clear();
        this.addWordList(trainer.getWordpairs());
    }

    /**
     * Die Methode ist für das speichern des Worttrainer in einem gewissen Pfad verantwortlich
     * @param path der Pfad in den Worttrainer gespeichert werden soll
     */
    public void save(String path) throws IOException{
        if(saveManager==null) throw new IllegalArgumentException("Es ist kein SaveManger vorhanden mit dem das speichern möglich ist.");
        if(path==null) throw new IllegalArgumentException("Es kann von einem ungültigen Pfad nichts geladen werden!");
        saveManager.save(path, this);
    }

    /**
     * Die Methode gibt das ausgewählte Wort zurück
     * @return das ausgewählte Wort wird als Klasse Wortpaar zurückgegeben
     */
    public WordPair getPickedWord() {
        return pickedWord;
    }

    /**
     * Die Methode gibt einem ein spezielles über Index auswählbares Wort zurück
     * @param index der Index wodurch das Wort aus den Wortpaaren ausgewählt werden kann
     * @return das über Index ausgewählte Wort
     */
    public WordPair getWordpair(int index){
        return wordpairs.get(index);
    }

    /**
     * Die Methode gibt die Wortpaare als Array von der Klasse Wortpaare aus
     * @return ein Array von der Klasse Worpaare gibt alle Wortpaare zurück
     */
    public WordPair[] getWordpairs(){
        return wordpairs.toArray(new WordPair[wordpairs.size()]);
    }

    /**
     * Die Methode gibt die Statistik aus
     * @return die Statistik als Klasse der Stats
     */
    public Stats getStatistic(){
        return stats;
    }

    /**
     * Die Methode gibt die Statistik als Text zurück
     * @return die Statistik wird als Text im Format Gesamt: ; Richtig: ; Falsch: ; zurückgegeben
     */
    public String getTextStatistic(){
        return "Statistik:\nGesamt: " + stats.getTryedWords() + "; Richtig: " + stats.getRightWords() + "; Falsch: " + stats.getWrongWords();
    }
}
