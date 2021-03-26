import org.junit.jupiter.api.Test;

import java.util.List;
import java.sql.SQLException;

class HibernateRequestTest {
    @Test
    public void requestTest() {
        Dao<Word> wordDao = new WordDao();
        List<Word> words;
        try {
            words = HibernateRequest.getAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        for (var w : words)
            wordDao.save(w);
        for (var w : wordDao.getAll())
            System.out.println(w.getWord());
    }
}