package by.guesspic.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import by.guesspic.data.Message;
import by.guesspic.data.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import by.guesspic.room.Room;
import by.guesspic.service.RoomActivityService;
import by.guesspic.service.RoomService;

@Controller
public class WebSocketChatController {
    @Autowired
    private RoomActivityService activityController;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired
    private RoomService roomsController;

    private static final Gson gson = new GsonBuilder().create();

    @MessageMapping("/{roomId}/chat.send")
    public Message sendMessage(@DestinationVariable int roomId, String jsonMessage) {
        Message message = gson.fromJson(jsonMessage, Message.class);
        Room room = roomsController.getRoomById(message.getChatRoomId());

        messagingTemplate.convertAndSend("/topic/public" + "/" + roomId, message);

        if (message.getType() == MessageType.DISCONNECT) {
            sendDisconnectMessage(message, room);
        } else {
            activityController.updateActivity(room, message.getSenderId());
            deleteInactive(room);
        }

        if (message.getType() == MessageType.CHAT && room.guessWord(message)) {
            sendWinMessage(message, room);
        }

        return message;
    }

    // make painter update
    private void sendDisconnectMessage(Message message, Room room) {
        int prevPainterId = room.getPainterId();
        room.removeUser(message.getSenderId());
        if (prevPainterId == message.getSenderId()) {
            messagingTemplate.convertAndSend("/topic/public" + "/" + room.getId(),
                    Message.builder().
                            type(MessageType.INIT_WORD_AND_PAINTER).
                            content(String.valueOf(room.getPainterId())).
                            senderId(message.getSenderId()).
                            chatRoomId(room.getId()).
                            build()
            );
        }
    }

    private void sendWinMessage(Message message, Room room) {
        messagingTemplate.convertAndSend("/topic/public" + "/" + room.getId(),
                Message.builder().
                        type(MessageType.WIN).
                        content("{\"winnerId\":\"" + message.getSenderId() + "\", \"bonus\":\"" + room.getBonus() +
                                "\", \"nextPainterId\":\"" + room.getPainterId() + "\"}").
                        senderId(message.getSenderId()).
                        chatRoomId(room.getId()).
                        build()
        );
    }

    private void deleteInactive(Room room) {
        int prevPainterId = room.getPainterId();
        for (int inactiveId : activityController.deleteInactive(room)) {
            if (inactiveId == prevPainterId) {
                messagingTemplate.convertAndSend("/topic/public" + "/" + room.getId(),
                        Message.builder().
                                type(MessageType.INIT_WORD_AND_PAINTER).
                                content(String.valueOf(room.getPainterId())).
                                senderId(inactiveId).
                                chatRoomId(room.getId()).
                                build()
                );
            }
            messagingTemplate.convertAndSend("/topic/public" + "/" + room.getId(),
                    Message.builder().
                            type(MessageType.DISCONNECT).
                            content("").
                            senderId(inactiveId).
                            chatRoomId(room.getId()).
                            build()
            );
        }
    }
}