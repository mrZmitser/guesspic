import org.junit.jupiter.api.Test;

import java.util.List;
import java.sql.SQLException;

class HibernateRequestTest {
    @Test
    public void requestTest(){
        List<Word> words = null;
        try {
            words = HibernateRequest.getAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(var w: words)
        System.out.println(w.getWord());
    }
}