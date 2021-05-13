package spring_server.chat_model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameChatController {

    private final int MAX_USERS = 5;
    @Getter
    private final ConcurrentHashMap<Long, GameChatRoom> gameRooms = new ConcurrentHashMap<>();

    private long createRoom() {
        GameChatRoom chatRoom = new GameChatRoom("Room #" + gameRooms.size());
        gameRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom.getId();
    }

    public void removeFromRoom(User user) {
        gameRooms.get(user.getRoomId()).removeUser(user.getId());
    }

    public GameChatRoom getRoomById(long id) {
        return gameRooms.get(id);
    }

    public long addToRandomRoom(User user) {
        for (GameChatRoom room : gameRooms.values()) {
            if (room.getUsers().size() < MAX_USERS) {
                room.addUser(user);
                user.setRoomId(room.getId());
                return room.getId();
            }
        }

        long roomId = createRoom();
        gameRooms.get(roomId).addUser(user);
        user.setRoomId(roomId);

        return roomId;
    }

}