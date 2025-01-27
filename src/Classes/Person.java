package Classes;

public abstract class Person
{
    private String name;
    private String surname;

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public enum SessionTime {
        Nine_AM , Ten_AM , Eleven_AM , Twelve_AM , One_PM , Two_PM , Three_PM , Four_PM , Five_PM , Six_PM
    }

    public Person()
    {

    }

    public Person(String name , String surname)
    {
        this();
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {return name + " " + surname;}
    @Override
    public String toString() {
        return  name + " " + surname + "\n";
    }
}
