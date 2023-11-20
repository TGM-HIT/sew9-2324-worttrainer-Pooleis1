package tgm.ac.at.parnold;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * WordPair ist eine Klasse in der ein Word und eine ImageURL gespeichert werden und in dieser auch die eingabe eines anderen Wortes mit dem Wort verglichen werden kann
 * @author Pia Arnold
 * @version 23-10-28
 */
public class WordPair {

    private String imageURL;
    private String word;

    /**
     * Mit dem Konstruktor kann ein neues Wortpaar erstellt werden
     * @param imageURL hier wird die URL eines Bildes übergeben
     * @param word hier wird zur URL das passende Wort eingegeben
     */
    public WordPair(String imageURL, String word){
        setImageURL(imageURL);
        setWord(word);
    }

    /**
     * Hier kann man die ImageURL setzen und diese wird auf eine echte URL geprüft
     * @param imageURL hier wird die URL eines files übergeben
     */
    public void setImageURL(String imageURL) {
        try {
            new URL(imageURL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("This is not a correct URL!");
        }
        if (!checkImageURL(imageURL)) throw new IllegalArgumentException("This is not a image URL!");
        this.imageURL = imageURL;
    }

    public boolean checkImageURL(String imageURL) {
        try{
            ImageIcon imageIcon = new ImageIcon(new URL(imageURL));
            while(imageIcon.getImageLoadStatus()==MediaTracker.LOADING){
                Thread.sleep(500);
            }
            if(!(imageIcon.getImageLoadStatus()==MediaTracker.ABORTED || imageIcon.getImageLoadStatus()==MediaTracker.ERRORED)) return true;
        } catch (MalformedURLException e){
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Hier kann man ein Wort zu der passenden URL setzen
     * @param word hier wird das passende Wort übergeben
     */
    public void setWord(String word) {
        if (word==null) throw new IllegalArgumentException("This is not a correct word!");
        if (word.length()==0) throw new IllegalArgumentException("This is not a correct word!");
        this.word = word;
    }

    /**
     * Die Methode validate überprüft, ob das übergebene Wort mit dem gespeicherten Wort übereinstimmt
     * @param word hier wird ein Wort übergeben, welches auf die richtigkeit überprüft wird
     * @return die Methode gibt, zurück ob, das eingegeben Wort dem gespeicherten Word übereinstimmt und gibt je nachdem true oder false zurück
     */
    public boolean validate(String word){
        if(word == null) return false;
        return this.word.equalsIgnoreCase(word);
    }

    /**
     * Die Methode gibt einem die gespeicherte ImageURL zurück
     * @return gibt die ImageURl als String zurück
     */
    public String getImageURL(){
        return imageURL;
    }

    /**
     * Die Methode gibt das gespeicherte Wort zurück
     * @return gibt das Wort als String zurück
     */
    public String getWord(){
        return word;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof WordPair pair)) return false;
        if(!this.getWord().equals(pair.getWord())) return false;
        return this.imageURL.equals(pair.getImageURL());
    }
}
