package tgm.ac.at.parnold;

public class Stats {

    private int rightWords;
    private int wrongWords;
    private int tryedWords;

    public Stats(){
        resetAll();
    }

    public Stats(int rightWords, int wrongWords, int tryedWords){
        this.rightWords = rightWords;
        this.wrongWords = wrongWords;
        this.tryedWords = tryedWords;
    }

    public void resetAll(){
        rightWords = 0;
        wrongWords = 0;
        tryedWords = 0;
    }

    public void addRightWords(){
        rightWords++;
        tryedWords++;
    }

    public void addWrongWords(){
        wrongWords++;
        tryedWords++;
    }

    public int getRightWords() {
        return rightWords;
    }

    public int getWrongWords() {
        return wrongWords;
    }

    public int getTryedWords() {
        return tryedWords;
    }
}
