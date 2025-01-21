import Classes.Client;
import Classes.Person;

public class Main {
    public static void main(String[] args)
    {
        Person client = new Client("Erkhan" , "Piriyev" , 1 , 21 , 9 , 30);
        System.out.println(client.toString());
    }
}