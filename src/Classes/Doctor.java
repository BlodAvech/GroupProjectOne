package Classes;

import java.time.DayOfWeek;
import java.util.*;

public class Doctor extends Person {
    private Map<DayOfWeek, Set<SessionTime>> schedule = new HashMap<>();
    private Set<DayOfWeek> restrictedDays = new HashSet<>();

    public Doctor() {
    }

    public Doctor(String name, String surname) {
        super(name, surname);
    }

    public void restrictDay(DayOfWeek day) {
        restrictedDays.add(day);
        schedule.remove(day);
    }

    private void validateDay(DayOfWeek day) {
        if (restrictedDays.contains(day)) {
            throw new IllegalArgumentException("Sessions are not allowed on " + day);
        }
    }

    public void addSession(DayOfWeek day, SessionTime sessionTime) {
        validateDay(day);
        schedule.putIfAbsent(day, new HashSet<>());
        schedule.get(day).add(sessionTime);
    }

    public void removeSession(DayOfWeek day, SessionTime sessionTime) {
        if (schedule.containsKey(day)) {
            schedule.get(day).remove(sessionTime);
            if (schedule.get(day).isEmpty()) {
                schedule.remove(day);
            }
        }
    }

    public boolean isWorkingOn(DayOfWeek day) {
        return schedule.containsKey(day) && !schedule.get(day).isEmpty();
    }

    public Set<SessionTime> getSessions(DayOfWeek day) {
        return schedule.getOrDefault(day, Collections.emptySet());
    }

    public Set<DayOfWeek> getWorkingDays() {
        return schedule.keySet();
    }

    @Override
    public String toString() {
        StringBuilder scheduleString = new StringBuilder(super.toString() + "   Schedule:\n");
        for (Map.Entry<DayOfWeek, Set<SessionTime>> entry : schedule.entrySet()) {
            scheduleString.append("      " + entry.getKey()).append(": ");
            for (SessionTime session : entry.getValue()) {
                scheduleString.append(session).append(", ");
            }
            scheduleString.setLength(scheduleString.length() - 2);
            scheduleString.append("\n");
        }
        return scheduleString.toString();
    }

}
