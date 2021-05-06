package spring_server.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_server.chat_model.*;

@RestController
public class RoomRequestController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    RoomsController roomsController;

    private static final Gson gson = new GsonBuilder().create();


    @PostMapping("new_user/{name}")
    public User addUser(@PathVariable("name") String name) {
        User user = new User(name);
        long roomId = roomsController.addToRandomRoom(user);

        messagingTemplate.convertAndSend("/topic/public",  Message.builder().
                type(MessageType.CONNECT).
                content(gson.toJson(user)).
                senderId(0).
                chatRoomId(roomId).
                build());

        return user;
    }

    @GetMapping("update_users/{roomId}")
    public User[] updateUsers(@PathVariable("roomId") long roomId) {
        return roomsController.getRoomById(roomId).getUsers().values().toArray(new User[0]);
    }

    @GetMapping("painter_id/{roomId}")
    public int painterId(@PathVariable("roomId") long roomId) {
        return roomsController.getRoomById(roomId).getPainterId();
    }

    @GetMapping("room/{roomId}/user/{painterId}/word")
    public String getWord(@PathVariable("roomId") long painterId, @PathVariable("painterId") int roomId) {
        Room room = roomsController.getRoomById(roomId);
        if (room.getPainterId() == painterId) {
            if (room.getCurrWord().isPresent()) {
                return room.getCurrWord().get().getWord();
            }
        }
        return null;
    }
}
