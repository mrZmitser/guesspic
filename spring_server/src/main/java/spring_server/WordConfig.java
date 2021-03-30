package spring_server;

import database.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordConfig {

    @Bean
    public Word getWord() {
        Word word = new Word();
        word.setWord("word0");
        return word;
    }
}
