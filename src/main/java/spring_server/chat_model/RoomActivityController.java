package spring_server.chat_model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoomActivityController {
    private final Long DELAY = 60000L;

    private final ConcurrentHashMap<Integer, RoomActivity> activities = new ConcurrentHashMap<>();

    public void deleteUser(Room room, int userId) {
        activities.get(room.getId()).getActivity().remove(userId);
    }

    public List<Integer> deleteInactive(Room room) {
        List<Integer> inactive = activities.get(room.getId()).deleteInactive();
        if (activities.get(room.getId()).getActivity().isEmpty()) {
            activities.remove(room.getId());
        }
        return inactive;
    }

    public void updateActivity(Room room, int userId) {
        if (!activities.containsKey(room.getId())) {
            activities.put(room.getId(), new RoomActivity(room, DELAY));
        }
        activities.get(room.getId()).updateActivity(userId);
    }
}
