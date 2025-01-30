package controllers.interfaces;

public interface IPatientController {
    String createPatient(String name , String surname , String doctor , String day , String time);
    String getPatientById(int id);
    String getAllPatients();
}
