package Classes;

public class Client extends Person
{
    private Doctor doctor;
    private String day;
    private int hours;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return super.toString() + "   Doctor:" + doctor.getFullName() + "\n   Day:" + day ;
    }
}
