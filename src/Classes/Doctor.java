package Classes;
import java.util.Set;
import java.util.HashSet;

public class Doctor extends Person
{
    private Set<DayOfWeek> workingDays = new HashSet<>();
    private Set<SessionTime> sessionTimes = new HashSet<>();

    public Doctor()
    {

    }

    public Doctor(String name , String surname)
    {
        super(name , surname);
    }

    public void addWorkingDay(DayOfWeek day) {
        workingDays.add(day);
    }

    public void removeWorkingDay(DayOfWeek day) {
        workingDays.remove(day);
    }

    public boolean isWorkingOn(DayOfWeek day) {
        return workingDays.contains(day);
    }

    public Set<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    @Override
    public String toString() {
        String workDaysList = "   Work Days:";
        for(DayOfWeek day : workingDays)
        {
            workDaysList += (day+",");
        }
        workDaysList = workDaysList.substring(0 , workDaysList.length()-1);
        workDaysList+=".";
        return
                super.toString() + workDaysList;
    }


}
