package Classes;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private List<DayOfWeek> restrictedDays;
    private List<Availability> availableTimes;

    public Doctor() {
        super();
        this.restrictedDays = new ArrayList<>();
        this.availableTimes = new ArrayList<>();
    }

    public Doctor(String name, String surname) {
        super(name, surname);
        this.restrictedDays = new ArrayList<>();
        this.availableTimes = new ArrayList<>();
    }

    public void restrictDay(DayOfWeek day) {
        restrictedDays.add(day);
    }

    public void addAvailableTime(DayOfWeek day, String time) {
        availableTimes.add(new Availability(day, time));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" Restricted Days: ").append(restrictedDays);
        sb.append(" Available Times: ");
        for (Availability availability : availableTimes) {
            sb.append(availability).append(" ");
        }
        return sb.toString();
    }


    private static class Availability {
        private DayOfWeek day;
        private String time;

        public Availability(DayOfWeek day, String time) {
            this.day = day;
            this.time = time;
        }

        @Override
        public String toString() {
            return day.name() + " at " + time;
        }
    }
}
