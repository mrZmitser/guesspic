package spring_server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import spring_server.chat_model.GameChatMessage;
import spring_server.chat_model.MessageType;


@Component
public class WebSocketChatEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketChatEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        int userId = (int) headerAccessor.getSessionAttributes().get("username");

        GameChatMessage chatMessage = GameChatMessage.builder().
                type(MessageType.DISCONNECT).
                senderId(userId).
                build();

        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}