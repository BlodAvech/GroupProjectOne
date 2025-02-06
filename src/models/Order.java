package models;

public class Order {
    private Patient patient;
    private  Doctor doctor ;
    private String weekday;
    private String time;

    public Order()
    {
    }

    public Order(Patient patient ,Doctor doctor , String weekday ,  String time)
    {
        this.patient = patient;
        this.doctor = doctor;
        this.weekday = weekday;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}