import Classes.Doctor;
import Classes.Person;
import Classes.Person.DayOfWeek;
import Classes.Person.SessionTime;

public class Main {
    public static void main(String[] args) {
        Doctor doctor = new Doctor("Jane", "Doe");

        doctor.restrictDay(DayOfWeek.MONDAY);

        try {
            doctor.addSession(DayOfWeek.MONDAY, SessionTime.NINE_AM);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        doctor.addSession(DayOfWeek.WEDNESDAY, SessionTime.TEN_AM);
        doctor.addSession(DayOfWeek.FRIDAY, SessionTime.ELEVEN_AM);

        System.out.println(doctor);
    }
}
