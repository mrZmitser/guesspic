package spring_server;

import database.Word;
import lombok.Data;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Data
public class Question {
    private static List<Word> words;

    private final long id;
    private final String type;
    private final String question;

    public Question(long id, String type, int cost) throws SQLException {
        if (words == null) {
            words = database.HibernateRequest.getAllTable();
        }

        this.id = id;
        this.type = type;
        this.question = generateQuestion(cost);
    }

    private static String generateQuestion(int cost) throws SQLException {
        return words.get(new Random().nextInt(words.size())).getWord();
    }
}