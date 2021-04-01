package src.main.java.database;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateRequest {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public static List<Word> getAllTable() throws SQLException {
        List<Word> words = new ArrayList<>();

        try {
            Query<Word> query = session.createQuery("from database.Word group by id", Word.class);
            words = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateUtil.shutdown();
        return words;
    }
}
