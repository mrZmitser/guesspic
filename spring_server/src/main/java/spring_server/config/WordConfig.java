package spring_server.config;

import database.Word;
import database.WordDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfig {
    static WordDao dao = new WordDao();

    @Bean("word")
    public Word getWord() {
        if (dao.getRandomWord().isPresent()) {
            return dao.getRandomWord().get();
        } else {
            var word = new Word();
            word.setWord("Error");
            return word;
        }
    }
}