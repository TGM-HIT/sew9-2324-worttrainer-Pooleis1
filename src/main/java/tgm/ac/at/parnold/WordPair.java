package tgm.ac.at.parnold;

import java.net.MalformedURLException;
import java.net.URL;

public class WordPair {

    private String imageURL;
    private String word;

    public WordPair(String imageURL, String word){
        setImageURL(imageURL);
        setWord(word);
    }

    public void setImageURL(String imageURL) {
        try {
            URL test = new URL(imageURL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("This is not a correct URL!");
        }
        this.imageURL = imageURL;
    }

    public void setWord(String word) {
        if (word==null) return;
        this.word = word;
    }

    public boolean validate(String word){
        if(this.word.equalsIgnoreCase(word))return true;
        else return false;
    }

    public String getImageURL(){
        return imageURL;
    }

    public String getWord(){
        return word;
    }
}
