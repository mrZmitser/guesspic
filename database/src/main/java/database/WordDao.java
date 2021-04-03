package database;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.*;

public class WordDao implements Dao<Word> {

    private List<Word> words = new ArrayList<>();

    public Optional<Word> get(long id) {
        return Optional.ofNullable(words.get((int) id));
    }

    public List<Word> getAll() {
        return words;
    }

    public void save(Word word) {
        words.add(word);
    }

    public void createList(List<Word> words) {
        this.words = words;
    }

    public void update(Word word, String[] params) {
        word.setWord(Objects.requireNonNull(params[0], "Name cannot be null"));
        words.add(word);
    }

    public static Optional<WordDao> bindWithDatabase(){
        WordDao wordDao = new WordDao();
        try {
            wordDao.createList(HibernateRequest.getAllTable());
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(wordDao);
    }

    public void delete(Word word) {
        words.remove(word);
    }

    public Optional<Word> getRandomWord(){
        Word word = null;
        try {
            word = HibernateRequest.getById().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(word);
    }
}
