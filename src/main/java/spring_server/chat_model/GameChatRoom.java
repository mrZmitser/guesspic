package spring_server.chat_model;

import database.Word;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameChatRoom {
    @Getter
    private final Queue<Message> messages = new LinkedList<>();
    @Getter
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    @Getter
    private final String name;
    @Getter
    private final Long id;
    @Getter
    private int painterId = 1;

    private static long idCounter = 1;

    private Optional<Word> currWord = Optional.empty();

    public GameChatRoom(String chatRoomName) {
        this.name = chatRoomName;
        this.id = idCounter++;
    }

    public boolean addMessage(Message message) {
        messages.add(message);

        if (currWord.isPresent() && message.getContent().equals(currWord.get().getWord())) {
            users.get(message.getSenderId()).addScore();
            updateWordAndPainter();
            return true;
        }
        return false;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public void addUser(User user) {
        if (users.size() == 2) {
            updateWordAndPainter();
        }
        this.users.put(user.getId(), user);
    }

    public void removeUser(int userId) {
        users.remove(userId);
    }

    // TO MAKE BETTER
    private void updateWordAndPainter() {
        //currWord = WordDao.getRandWord();
        currWord = Optional.of(new Word("it's like rand word"));

        if (painterId == users.size() - 1) {
            painterId = 0;
        }
        while (!users.containsKey(painterId)) {
            painterId++;
        }
    }
}
