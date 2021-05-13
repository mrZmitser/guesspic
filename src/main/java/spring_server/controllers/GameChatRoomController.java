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

import java.util.Arrays;

@RestController
public class GameChatRoomController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    GameChatController gameChatController;

    private static final Gson gson = new GsonBuilder().create();


    @PostMapping("new_user/{name}")
    public User addUser(@PathVariable("name") String name) {
        User user = new User(name);
        long roomId = gameChatController.addToRandomRoom(user);

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
        return gameChatController.getRoomById(roomId).getUsers().values().toArray(new User[0]);
    }

    @GetMapping("painter_id/{roomId}")
    public int painterId(@PathVariable("roomId") long roomId) {
        return gameChatController.getRoomById(roomId).getPainterId();
    }
}
