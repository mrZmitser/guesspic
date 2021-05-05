package spring_server.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import spring_server.chat_model.*;

@Controller
public class WebSocketChatController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private RoomsController roomsController;

    private static final Gson gson = new GsonBuilder().create();

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(String jsonMessage) {
        Message chatMessage = gson.fromJson(jsonMessage, Message.class);
        Room room = roomsController.getRoomById(chatMessage.getChatRoomId());

        if (chatMessage.getType() == MessageType.DISCONNECT) {
            roomsController.getRoomById(chatMessage.getChatRoomId()).removeUser(chatMessage.getSenderId());
        }

        if (room.addMessage(chatMessage)) {
            messagingTemplate.convertAndSend("/topic/public",
                    Message.builder().
                    type(MessageType.WIN).
                    content("{\"winnerId\":\"" + chatMessage.getSenderId() + "\", \"bonus\":\"" + room.getBonus() +
                            "\", \"nextPainterId\":\"" + room.getPainterId() + "\"}").
                    senderId(chatMessage.getSenderId()).
                    chatRoomId(room.getId()).
                    build()
            );
        }

        return chatMessage;
    }
}