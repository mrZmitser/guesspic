package spring_server.chat_model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class GameChatMessage {
    @Getter
    @Setter
    private MessageType type;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private int senderId;

    @Getter
    @Setter
    private String time;

    @Getter
    @Setter
    private GameChatRoom chatRoom;
}