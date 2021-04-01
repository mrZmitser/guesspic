package spring_server.chat_model;

import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ChatRoom {
    @Getter
    private Queue<ChatMessage> chats = new LinkedList<>();
    @Getter
    private HashSet<User> users = new HashSet<>();
    @Getter
    private final String nameChatRoom;
    @Getter
    private int idChatRoom;
    private static int idCounter = 1;


    public ChatRoom(String nameChatRoom)
    {
        this.nameChatRoom = nameChatRoom;
        this.idChatRoom = idCounter++;
    }

    public void addChats(ChatMessage chat)
    {
        this.chats.add(chat);
    }
    public void addUsers(User user)
    {
        this.users.add(user);
    }
    public void removeUsers(User user)
    {
        this.users.remove(user);
    }
}
