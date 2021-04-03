package database;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateRequest {

    public static List<Word> getAllTable() throws SQLException {
        List<Word> words = new ArrayList<>();

        try {
            var session = HibernateUtil.getSessionFactory().openSession();
            Query<Word> query = session.createQuery("from database.Word group by id", Word.class);
            words = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateUtil.shutdown();
        return words;
    }

    public static List<Word> getById() throws SQLException {
        List<Word> words = new ArrayList<>();

        try {
            var session = HibernateUtil.getSessionFactory().openSession();
            Query<Word> query = session.createQuery("from database.Word ORDER BY RAND()", Word.class).setMaxResults(1);
            words = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateUtil.shutdown();
        return words;
    }
}
