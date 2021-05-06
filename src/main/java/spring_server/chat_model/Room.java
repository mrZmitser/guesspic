package spring_server.chat_model;

import database.Word;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Room {
    @Getter
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    private final ConcurrentLinkedQueue<User> paintersQueue = new ConcurrentLinkedQueue<>();

    @Getter
    private final String name;
    @Getter
    private final Long id;
    @Getter
    private int painterId = 1;
    @Getter
    private final int bonus = 5;

    private static long idCounter = 1;

    @Getter
    private Optional<Word> currWord = Optional.empty();

    public Room(String chatRoomName) {
        this.name = chatRoomName;
        this.id = idCounter++;
    }

    public boolean addMessage(Message message) {
        if (currWord.isPresent() && message.getContent().equalsIgnoreCase(currWord.get().getWord())) {
            users.get(message.getSenderId()).addScore(bonus);
            updateWordAndPainter();
            return true;
        }
        return false;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public void addUser(User user) {
        paintersQueue.add(user);
        this.users.put(user.getId(), user);

        if (users.size() == 1) {
            painterId = user.getId();
            updateWordAndPainter();
        }
    }

    public void removeUser(int userId) {
        paintersQueue.remove(getUserById(userId));
        users.remove(userId);
        if (userId == painterId) {
            updateWordAndPainter();
        }
    }

    // TO MAKE BETTER
    private void updateWordAndPainter() {
        if (users.size() == 0) return;

        if (paintersQueue.isEmpty()) {
            paintersQueue.addAll(users.values());
        }

        users.get(painterId).setPainter(false);
        painterId = paintersQueue.poll().getId();
        users.get(painterId).setPainter(true);

        //currWord = WordDao.getRandWord();
        currWord = Optional.of(new Word("rap"));
    }
}
