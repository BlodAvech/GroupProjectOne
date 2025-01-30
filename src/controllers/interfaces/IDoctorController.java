package controllers.interfaces;

public interface IDoctorController {
    String createDoctor(String name , String surname , boolean[] workdays);
    String getDoctorById(int id);
    String getAllDoctors();
}
