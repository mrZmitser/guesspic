package src.main.java.database;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class WordDao implements Dao<Word> {
    private ArrayList<Word> words = new ArrayList<>();

    public Optional<Word> get(long id) {
        return Optional.ofNullable(words.get((int) id));
    }

    public ArrayList<Word> getAll() {
        return words;
    }

    public void save(Word word) {
        words.add(word);
    }

    public void update(Word word, String[] params) {
        word.setWord(Objects.requireNonNull(params[0], "Name cannot be null"));
        words.add(word);
    }

    public void delete(Word word) {
        words.remove(word);
    }
}
