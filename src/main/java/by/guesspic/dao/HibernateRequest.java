package by.guesspic.dao;

import by.guesspic.data.Word;
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

        return words;
    }

    public static List<Word> getById() throws SQLException {
        List<Word> words = new ArrayList<>();

        try {
            Query<Word> query = session.createQuery("from database.Word ORDER BY RAND()", Word.class).setMaxResults(1);
            words = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }
}