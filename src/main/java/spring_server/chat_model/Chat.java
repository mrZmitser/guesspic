package spring_server.chat_model;

import lombok.Getter;

import java.util.HashSet;

public class Chat {
    @Getter
    private HashSet<ChatRoom> chat = new HashSet<>();

    public void addChatRoom(ChatRoom chatRoom)
    {
        this.chat.add(chatRoom);
    }
    public void removeChatRoom(ChatRoom chatRoom)
    {
        this.chat.remove(chatRoom);
    }
}
