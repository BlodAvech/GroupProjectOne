package Classes;

public class Client extends Person
{
    private Doctor doctor;
    private DayOfWeek day;
    private SessionTime time;

    public Client()
    {
        super();
    }

    public Client(String name , String surname)
    {
        super(name, surname);
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void addDay(DayOfWeek day) {
        this.day = day;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void addTime(SessionTime time) {
        this.time = time;
    }

    public SessionTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + "   Doctor:" + doctor.getFullName() + "\n   Day:" + day ;
    }
}
