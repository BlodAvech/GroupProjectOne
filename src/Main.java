import Classes.Client;
import Classes.Doctor;
import Classes.Person;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();

        ShowAllPerson(doctors , clients);
    }

    private static void ShowAllPerson(ArrayList<Doctor> doctors, ArrayList<Client> clients)
    {
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