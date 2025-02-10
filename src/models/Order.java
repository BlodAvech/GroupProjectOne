package models;

public class Order {
    private int id;
    private int patientId;
    private int doctorId ;
    private String weekday;
    private String doctorType;

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public Order()
    {
    }

    public Order(int patientId ,int doctorId , String weekday , String doctorType)
    {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.weekday = weekday;
        this.doctorType = doctorType;
    }

    public Order(int id, int patientId ,int doctorId , String weekday , String doctorType)
    {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.weekday = weekday;
        this.doctorType = doctorType;
    }

    @Override
    public String toString()
    {
        return id+" "+"Patient: "+patientId+" Doctor: "+doctorId+" WeekDay: "+weekday+" DoctorType: "+doctorType;
    }
}