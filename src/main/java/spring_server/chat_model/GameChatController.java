package spring_server.chat_model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameChatController {
    @Getter
    private final ConcurrentHashMap<Integer, GameChatRoom> gameRooms = new ConcurrentHashMap<>();

    public void add(GameChatRoom chatRoom) {
        gameRooms.put(chatRoom.getId(), chatRoom);
    }

    public void remove(int chatRoomId) {
        gameRooms.remove(chatRoomId);
    }

    public GameChatRoom getRoom(int id) {
        return gameRooms.get(id);
    }

    public void addToRoom(int roomId, User user) {
        getRoom(roomId).addUser(user);
    }

}