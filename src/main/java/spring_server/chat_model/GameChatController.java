package spring_server.chat_model;

import lombok.Getter;

import java.util.HashSet;

public class GameChatController {
    @Getter
    private final HashSet<GameChatRoom> chats = new HashSet<>();

    public void add(GameChatRoom chatRoom)
    {
        this.chats.add(chatRoom);
    }

    public void remove(GameChatRoom chatRoom)
    {
        this.chats.remove(chatRoom);
    }
}