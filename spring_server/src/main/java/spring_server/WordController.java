package spring_server;

import database.Word;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@RestController
public class WordController {
    private static List<Word> words;

    @GetMapping("/word")
    public String word() throws SQLException {
        if (words == null) {
            words = database.HibernateRequest.getAllTable();
        }
        return generateWord();
    }

    private static String generateWord() {
        return words.get(new Random().nextInt(words.size())).getWord();
    }
}
