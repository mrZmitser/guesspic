package by.guesspic.room;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class RoomActivity {
    private final Room room;
    private final Long delay;

    @Getter
    private final ConcurrentHashMap<Integer, Long> activity = new ConcurrentHashMap<>();

    public void updateActivity(int userId) {
        activity.put(userId, System.currentTimeMillis());
    }

    public List<Integer> deleteInactive() {
        List<Integer> inactive = new ArrayList<>();

        Long currMillis = System.currentTimeMillis();
        for (var entry : activity.entrySet()) {
            if (currMillis - entry.getValue() > delay) {
                inactive.add(entry.getKey());
                activity.remove(entry.getKey());
                room.removeUser(entry.getKey());
            }
        }

        return inactive;
    }

}
