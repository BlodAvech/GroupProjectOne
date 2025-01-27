import Classes.Client;
import Classes.Doctor;
import Classes.Person;

public class Main {
    public static void main(String[] args)
    {
        Doctor doctor = new Doctor("Erkhan" , "Erkhan");
        doctor.addWorkday("Monday");
        doctor.addWorkday("Thuestay");

        Client client = new Client("Black" , "Black");
        client.setDoctor(doctor);
        client.setDay("Monday");

        System.out.println(client.toString());
        System.out.println(doctor.toString());
    }
}