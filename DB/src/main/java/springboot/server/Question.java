package springboot.server;

import lombok.Data;

@Data
public class Question {
    private final long id;
    private final String type;
    private final String question;

    public Question(long id, String type, int cost) {
        this.id = id;
        this.type = type;
        this.question = generateQuestion(cost);
    }

    private static String generateQuestion(int cost) {
        return "It could be a good question with " + cost + " cost";
    }
}
