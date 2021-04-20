package spring_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring_server.chat_model.*;

@RestController
public class GameChatRoomController {
    @Autowired
    GameChatController gameChatController;

    @GetMapping("new_room/{name}")
    public int gameChatRoomId(@PathVariable String name) {
        GameChatRoom newRoom = new GameChatRoom(name);
        gameChatController.add(newRoom);
        return newRoom.getId();
    }

    @GetMapping("add_user/{name}/room_id/{roomId}")
    public User addUser(@PathVariable("name") String name, @PathVariable("roomId") int roomId) {
        User user = new User(name);
        gameChatController.addToRoom(roomId, user);
        return user;
    }

    @GetMapping("update_users/{roomId}")
    public Object[] roomUsers(@PathVariable int roomId) {
        return gameChatController.getRoom(roomId).getUsers().values().toArray();
    }
}
