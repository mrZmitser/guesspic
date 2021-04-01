package spring_server.config;

import database.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfig {

    @Bean("word")
    public Word getWord() {
        Word word = new Word();
        word.setWord("word0");
        return word;
    }
}
