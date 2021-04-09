package database;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
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

    public static Optional<WordDao> bindWithDatabase() {
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

    public static Optional<Word> getRandWord() {
        Word word;
        try {
            word = HibernateRequest.getById().get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(word);
    }
}