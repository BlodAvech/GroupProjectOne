//SRP OCP LSP
package models;

public class Person
{
    private int id;
    private String name;
    private String surname;

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
