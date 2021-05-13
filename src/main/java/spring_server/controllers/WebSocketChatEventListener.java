package spring_server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import spring_server.chat_model.RoomsController;

@Component
public class WebSocketChatEventListener {
    @Autowired
    RoomsController roomsController;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketChatEventListener.class);

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        logger.info("Connection has lost!");
    }
}