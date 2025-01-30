package models;

public abstract class Person
{
    private int id;
    private String name;
    private String surname;

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public enum SessionTime {
        NINE_AM("9:00"),
        TEN_AM("10:00"),
        ELEVEN_AM("11:00"),
        Twelve_AM("12:00") ,
        One_PM("13:00") ,
        Two_PM("14:00") ,
        Three_PM("15:00") ,
        Four_PM("16:00") ,
        Five_PM("17:00") ,
        Six_PM("18:00");
        private final String time;

        SessionTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return time;
        }
    }

    public Person()
    {

    }

    public Person(String name , String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public Person(int id , String name , String surname)
    {
        this(name , surname);
        this.id = id;
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

    public int getId() {return id;}
    public void setId(int id)  {this.id = id;}

    @Override
    public String toString() {
        return  name + " " + surname + "\n";
    }
}
