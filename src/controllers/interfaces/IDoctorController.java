package controllers.interfaces;

import models.Doctor;

import java.util.List;

public interface IDoctorController {
    String createDoctor(String name , String surname , boolean[] workdays);
    String getDoctorById(int id);
    List<Doctor> getAllDoctors();
}
