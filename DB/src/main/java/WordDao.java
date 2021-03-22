import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class WordDao implements Dao<Word> {

    private ArrayList<Word> words = new ArrayList<>();

    public WordDao() {

    }

    @Override
    public Optional<Word> get(long id) {
        return Optional.ofNullable(words.get((int) id));
    }

    @Override
    public ArrayList<Word> getAll() {
        return words;
    }

    @Override
    public void save(Word word) {
        words.add(word);
    }

    @Override
    public void update(Word word, String[] params) {
        word.setWord(Objects.requireNonNull(params[0], "Name cannot be null"));
        words.add(word);
    }

    @Override
    public void delete(Word word) {
        words.remove(word);
    }
}
