//SOLID
package models;

public class Patient extends Person
{
    public Patient()
    {
        super();
    }

    public Patient(String name , String surname)
    {
        super(name, surname);
    }

    public Patient(int id , String name , String surname)
    {
        super(id , name, surname);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
