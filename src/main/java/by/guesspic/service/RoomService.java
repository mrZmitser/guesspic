package by.guesspic.service;

import by.guesspic.data.User;
import lombok.Getter;
import org.springframework.stereotype.Component;
import by.guesspic.room.Room;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoomService {

    private final int MAX_USERS = 5;
    @Getter
    private final ConcurrentHashMap<Integer, Room> gameRooms = new ConcurrentHashMap<>();

    private int createRoom() {
        Room chatRoom = new Room("Room #" + gameRooms.size());
        gameRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom.getId();
    }

    public void removeFromRoom(User user) {
        gameRooms.get(user.getRoomId()).removeUser(user.getId());
    }

    public Room getRoomById(int id) {
        return gameRooms.get(id);
    }

    public int addToNearestFreeRoom(User user) {
        for (Room room : gameRooms.values()) {
            if (room.getUsers().size() < MAX_USERS) {
                room.addUser(user);
                user.setRoomId(room.getId());
                return room.getId();
            }
        }

        int roomId = createRoom();
        gameRooms.get(roomId).addUser(user);
        user.setRoomId(roomId);
        user.setPainter(true);

        return roomId;
    }
}