package Classes;

public class Patient extends Person
{
    private Doctor doctor;
    private DayOfWeek day;
    private SessionTime time;

    public Patient()
    {
        super();
    }

    public Patient(String name , String surname)
    {
        super(name, surname);
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setTime(SessionTime time) {
        this.time = time;
    }

    public SessionTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + "   Doctor:" + doctor.getFullName() + "\n   Day:" + day  + "\n   Time:" + time;
    }
}
