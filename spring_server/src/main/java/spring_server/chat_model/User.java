package spring_server.chat_model;

import lombok.Getter;

public class User {
    @Getter
    private final String name;
    @Getter
    private int id;
    private static int userCounter = 1;
    @Getter
    private int score = 0;
    private final int supplement = 5;

    public User(String name) {
        this.name = name;
        this.id = userCounter++;
    }
    public void addScore()
    {
        this.score += supplement;
    }
}
