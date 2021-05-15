package by.guesspic.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import by.guesspic.data.Message;
import by.guesspic.data.MessageType;
import by.guesspic.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import by.guesspic.room.Room;
import by.guesspic.service.RoomActivityService;
import by.guesspic.service.RoomService;

@RestController
public class RoomRequestController {
    @Autowired
    private RoomActivityService activityController;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    RoomService roomsController;

    private static final Gson gson = new GsonBuilder().create();

    @PostMapping("new_user/{name}")
    public User addUser(@PathVariable("name") String name) {
        User user = new User(name);
        int roomId = roomsController.addToNearestFreeRoom(user);
        activityController.updateActivity(roomsController.getRoomById(roomId),
                user.getId());

        messagingTemplate.convertAndSend("/topic/public" + "/" + roomId,  Message.builder().
                type(MessageType.CONNECT).
                content(gson.toJson(user)).
                senderId(0).
                chatRoomId(roomId).
                build());

        return user;
    }

    @GetMapping("update_users/{roomId}")
    public User[] updateUsers(@PathVariable("roomId") int roomId) {
        return roomsController.getRoomById(roomId).getUsers().values().toArray(new User[0]);
    }

    @GetMapping("painter_id/{roomId}")
    public int painterId(@PathVariable("roomId") int roomId) {
        return roomsController.getRoomById(roomId).getPainterId();
    }

    @GetMapping("room/{roomId}/user/{painterId}/word")
    public String getWord(@PathVariable("roomId") int roomId, @PathVariable("painterId") int painterId) {
        Room room = roomsController.getRoomById(roomId);
        if (room.getPainterId() == painterId) {
            if (room.getCurrWord().isPresent()) {
                return room.getCurrWord().get().getWord();
            }
        }
        return null;
    }
}
