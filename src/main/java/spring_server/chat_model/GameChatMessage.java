package spring_server.chat_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
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
    private int chatRoomId;
}