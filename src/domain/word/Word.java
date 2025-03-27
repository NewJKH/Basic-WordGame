package domain.word;

public class Word {
    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord(){
        return word;
    }

    public int getLength(){
        return word.length();
    }
}
