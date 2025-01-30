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
        return super.toString();
    }

    public void setWorkdays(boolean[] workdays) {
        this.workdays = workdays;
    }

    public boolean[] getWorkdays() {
        return workdays;
    }
}