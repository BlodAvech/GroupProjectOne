package Classes;

public abstract class Person
{
    private String name;
    private String surname;

    public Person()
    {

    }

    public Person(String name , String surname)
    {
        this();
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
