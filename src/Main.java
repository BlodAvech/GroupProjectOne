import Classes.Client;
import Classes.Doctor;
import Classes.Person;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

        Doctor doctor = new Doctor("Erkhan" , "Piriyev");
        doctor.addWorkday("Monday");
        doctor.addWorkday("Tuesday");
        doctor.addWorkday("Wednesday");
        doctor.addWorkday("Friday");

        doctors.add(doctor);

        doctor = new Doctor("Cheto" , "Tam");
        doctor.addWorkday("Monday");
        doctor.addWorkday("Wednesday");
        doctor.addWorkday("Thursday");
        doctor.addWorkday("Saturday");

        doctors.add(doctor);

        Client client = new Client("Zhanklod" , "Mamdral");
        client.setDoctor(doctor);
        client.setDay("Monday");

        clients.add(client);

        client = new Client("Ktoto" , "Tam");
        client.setDoctor(doctor);
        client.setDay("Saturday");

        clients.add(client);


        int i = 1;
        System.out.println("Doctors");
        for(Doctor d : doctors)
        {
            System.out.println(i+". " +d.toString());
            i++;
        }
        i=1;
        System.out.println("\n");
        System.out.println("Clients");
        for(Client c : clients)
        {
            System.out.println(i+". " +c.toString());
            i++;
        }
    }
}