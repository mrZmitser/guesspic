package spring_server.chat_model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoomsController {

    private final int MAX_USERS = 5;
    @Getter
    private final ConcurrentHashMap<Long, Room> gameRooms = new ConcurrentHashMap<>();

    private long createRoom() {
        Room chatRoom = new Room("Room #" + gameRooms.size());
        gameRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom.getId();
    }

    public void removeFromRoom(User user) {
        gameRooms.get(user.getRoomId()).removeUser(user.getId());
    }

    public Room getRoomById(long id) {
        return gameRooms.get(id);
    }

    public long addToRandomRoom(User user) {
        for (Room room : gameRooms.values()) {
            if (room.getUsers().size() < MAX_USERS) {
                room.addUser(user);
                user.setRoomId(room.getId());
                return room.getId();
            }
        }

        long roomId = createRoom();
        gameRooms.get(roomId).addUser(user);
        user.setRoomId(roomId);
        user.setPainter(true);

        return roomId;
    }
}