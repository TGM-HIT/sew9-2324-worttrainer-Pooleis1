package tgm.ac.at.parnold;

/**
 * Die Klasse speichert Statistiken über die Versuche und wie viele davon richtig oder falsch waren
 * @author Pia Arnold
 * @version 23-10-28
 */
public class Stats {

    private int rightWords;
    private int wrongWords;
    private int tryedWords;

    /**
     * Hier wird eine neue Statistik erstellt und alle Werte auf 0 gesetzt
     */
    public Stats(){
        resetAll();
    }

    /**
     * Hier wird eine neue Statistik erstellt mit übergeben Werten für die Statistik
     * @param rightWords hier können die richtigen Wörter übergeben werden
     * @param wrongWords hier können die falschen Wörter übergeben werden
     * @param tryedWords hier können die Versuche übergeben werden
     */
    public Stats(int rightWords, int wrongWords, int tryedWords){
        if(rightWords<0)this.rightWords = 0; else this.rightWords = rightWords;
        if(wrongWords<0)this.wrongWords = 0; else this.wrongWords = wrongWords;
        if(tryedWords<0)this.tryedWords = 0; else this.tryedWords = tryedWords;
    }

    /**
     * Die Methode setzt alle Werte auf 0 zurück
     */
    public void resetAll(){
        rightWords = 0;
        wrongWords = 0;
        tryedWords = 0;
    }

    /**
     * Die Methode erhöht die Versuche, sowie die richtigen Wörter um jeweils 1
     */
    public void addRightWords(){
        rightWords++;
        tryedWords++;
    }

    /**
     * Die Methode erhöht die Versuche, sowie die falschen Wörter um jeweils 1
     */
    public void addWrongWords(){
        wrongWords++;
        tryedWords++;
    }

    /**
     * Die Methode übergibt einem die Statistik der richtigen Wörter
     * @return hier wird die Anzahl der richtigen Wörter zurückgegeben
     */
    public int getRightWords() {
        return rightWords;
    }

    /**
     * Die Methode übergibt einem die Statistik der falschen Wörter
     * @return hier wird die Anzahl der falschen Wörter zurückgegeben
     */
    public int getWrongWords() {
        return wrongWords;
    }

    /**
     * Die Methode übergibt einem die Statistik der Versuche
     * @return hier wird die Anzahl der Versuche zurückgegeben
     */
    public int getTryedWords() {
        return tryedWords;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Stats stats)) return false;
        if(stats.getRightWords()!=this.getRightWords()) return false;
        if(stats.getWrongWords()!=this.getWrongWords()) return false;
        if(stats.getTryedWords()!=this.getTryedWords()) return false;
        return true;
    }
}
