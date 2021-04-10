package spring_server.chat_model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameChatController {
    @Getter
    private static final Map<Integer, GameChatRoom> chats = new ConcurrentHashMap<>();

    public static void add(GameChatRoom chatRoom) {
        chats.put(chatRoom.getId(), chatRoom);
    }

    public static void remove(int chatRoomId) {
        chats.remove(chatRoomId);
    }

    public static GameChatRoom getRoom(int id) {
        return chats.get(id);
    }

}