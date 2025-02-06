package models;

public class Patient extends Person
{
    private String day;
    private String time;

    public Patient()
    {
        super();
    }

    public Patient(String name , String surname , String day , String time)
    {
        super(name, surname);
        this.day = day;
        this.time = time;
    }

    public Patient(int id , String name , String surname, String day , String time)
    {
        super(id , name, surname);
        this.day = day;
        this.time = time;
    }




    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + "   Day:" + day  + "\n   Time:" + time;
    }
}
