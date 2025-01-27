package Classes;

import java.util.ArrayList;

public class Doctor extends Person
{
    private ArrayList<String> workdays = new ArrayList<>();

    public Doctor()
    {
        super();
    }

    public Doctor(String name , String surname)
    {
        super(name , surname);
    }

    @Override
    public String toString() {
        String workDaysList = "   Work Days:";
        for(String day : workdays)
        {
            workDaysList += (day+",");
        }
        workDaysList = workDaysList.substring(0 , workDaysList.length()-1);
        workDaysList+=".";
        return
                super.toString() + workDaysList;
    }

    public ArrayList<String> getWorkdays() {return  workdays;}

    public void  addWorkday(String day)
    {
        workdays.add(day);
    }

    public void  removeWorkday(String day)
    {
        workdays.remove(day);
    }

}
