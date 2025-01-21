package Classes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Client extends Person
{
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private String dateTime;

    public Client()
    {
        super();
    }

    public  Client(String name , String surname , int month , int day , int hour , int minute )
    {
        super(name , surname);
        this.time = LocalDateTime.of(LocalDateTime.now().getYear() , month , day , hour , minute);
        dateTime = time.format(formatter);
    }


    @Override
    public String toString() {
        return super.toString() + dateTime;
    }
}
