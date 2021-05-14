package by.guesspic.data;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    private final String name;

    @Getter
    private final int id;

    private transient static int userCounter = 1;

    @Getter
    @Setter
    private int roomId;

    @Getter
    private transient int score = 0;

    @Getter
    @Setter
    private boolean isPainter = false;

    public User(String name) {
        this.name = name;
        this.id = userCounter++;
    }

    public void addScore(int score) {
        this.score += score;
    }
}