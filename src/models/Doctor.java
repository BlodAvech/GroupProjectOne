package models;

import java.util.*;

public class Doctor extends Person {
    private Map<DayOfWeek, Set<SessionTime>> schedule = new HashMap<>();

    public Doctor() {
        initializeSchedule();
    }

    public Doctor(String name, String surname) {
        super(name, surname);
        initializeSchedule();
    }

    private void initializeSchedule() {
        for (DayOfWeek day : DayOfWeek.values()) {
            schedule.put(day, new HashSet<>());
        }
    }

    public void restrictDay(DayOfWeek day) {
        schedule.remove(day);
    }

    public void addSession(DayOfWeek day, SessionTime sessionTime) {
        schedule.get(day).add(sessionTime);
    }

    public void removeSession(DayOfWeek day, SessionTime sessionTime) {
        if (schedule.containsKey(day)) {
            schedule.get(day).remove(sessionTime);
        }
    }

    public boolean isWorkingOn(DayOfWeek day) {
        return schedule.containsKey(day);
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
            scheduleString.append("      ").append(entry.getKey()).append(": ");
            if (entry.getValue().isEmpty()) {
                scheduleString.append("No sessions");
            } else {
                for (SessionTime session : entry.getValue()) {
                    scheduleString.append(session).append(", ");
                }
                scheduleString.setLength(scheduleString.length() - 2);
            }
            scheduleString.append("\n");
        }
        return scheduleString.toString();
    }
}