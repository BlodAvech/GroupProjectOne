import Classes.Client;
import Classes.Doctor;
import Classes.Person;

public class Main {
    public static void main(String[] args)
    {
        Person doctor = new Doctor("Name" , "Surname" , true , true ,true ,true,true,true);
        System.out.println(doctor.toString());
    }
}