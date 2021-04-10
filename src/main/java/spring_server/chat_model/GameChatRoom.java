package spring_server.chat_model;

import database.Word;
import database.WordDao;
import lombok.Getter;

import java.util.*;

public class GameChatRoom {
    @Getter
    private final Queue<GameChatMessage> messages = new LinkedList<>();
    @Getter
    private final Map<Integer, User> users = new HashMap<>();
    @Getter
    private final String name;
    @Getter
    private final int id;

    private static int idCounter = 1;

    private Optional<Word> currWord = Optional.empty();

    public GameChatRoom(String chatRoomName) {
        this.name = chatRoomName;
        this.id = idCounter++;
    }

    public void addMessage(GameChatMessage message) {
        messages.add(message);

        if (currWord.isPresent() && message.getContent().equals(currWord.get().getWord())) {
            users.get(message.getSenderId()).addScore();
            updateWord();
        }
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public void removeUser(User user) {
        this.users.remove(user.getId());
    }

    private void updateWord() {
        currWord = WordDao.getRandWord();
    }
}
