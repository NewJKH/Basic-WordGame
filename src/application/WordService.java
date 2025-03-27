package application;

import domain.word.Word;
import domain.word.WordRepository;

public class WordService {
    private final WordRepository repository;

    public WordService() {
        repository = new WordRepository();
    }
    public Word getRandomWord(){
        return new Word(repository.random());
    }
}
