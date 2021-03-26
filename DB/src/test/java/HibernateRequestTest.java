import org.junit.jupiter.api.Test;

import java.util.List;
import java.sql.SQLException;

class HibernateRequestTest {
    @Test
    public void requestTest() {
        Dao<Word> WordDao = new WordDao();
        List<Word> words = null;
        try {
            words = HibernateRequest.getAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (var w : words)
            WordDao.save(w);
        for (var w : WordDao.getAll())
            System.out.println(w.getWord());
    }
}