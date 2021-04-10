package spring_server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import spring_server.chat_model.GameChatController;
import spring_server.chat_model.GameChatMessage;

@Controller
public class WebSocketChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public GameChatMessage sendMessage(@Payload GameChatMessage chatMessage) {
        GameChatController.getRoom(chatMessage.getChatRoomId()).addMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public GameChatMessage newUser(@Payload GameChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        GameChatController.getRoom(chatMessage.getChatRoomId()).addMessage(chatMessage);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderId());
        return chatMessage;
    }

}