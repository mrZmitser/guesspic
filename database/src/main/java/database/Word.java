package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.management.ConstructorParameters;
import javax.persistence.*;

@Component("word")
@Entity
@NoArgsConstructor
@Table(name = "words")
public class Word {
    @Getter
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "word", length = 15, nullable = false)
    private String word;

    public Word(String word) {
        this.word = word;
    }
}