package models;

import java.util.*;

public class Doctor extends Person {

    private boolean[] workdays = new boolean[6];

    public Doctor() {
    }

    public Doctor(String name, String surname , boolean[] workdays)
    {
        super(name, surname);
        this.workdays = workdays;
    }

    public Doctor(int id , String name, String surname , boolean[] workdays)
    {
        super(id , name, surname);
        this.workdays = workdays;
    }


    @Override
    public String toString() {
        String dayslist = "Working Days: ";
        if(workdays[0] == true) dayslist += "Mon,";
        if(workdays[1] == true) dayslist += "Tue,";
        if(workdays[2] == true) dayslist += "Wed,";
        if(workdays[3] == true) dayslist += "Thu,";
        if(workdays[4] == true) dayslist += "Fri,";
        if(workdays[5] == true) dayslist += "Sat";
        return super.toString() + "   " + dayslist;
    }

    public void setWorkdays(boolean[] workdays) {
        this.workdays = workdays;
    }

    public boolean[] getWorkdays() {
        return workdays;
    }
}