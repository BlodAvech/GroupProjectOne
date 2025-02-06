package models;

public class Order {
    private int id;
    private int patientId;
    private int doctorId ;
    private String weekday;
    private String time;

    public Order()
    {
    }

    public Order(int patientId ,int doctorId , String weekday ,  String time)
    {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.weekday = weekday;
        this.time = time;
    }

    public Order(int id, int patientId ,int doctorId , String weekday ,  String time)
    {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.weekday = weekday;
        this.time = time;
    }


}