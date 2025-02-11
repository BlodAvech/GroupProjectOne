//SOLID
package controllers.interfaces;

import models.Doctor;
import models.Patient;

import java.util.List;

public interface IDoctorController {
    String createDoctor(String name , String surname , String doctorType , boolean[] workdays);
    Doctor getDoctorById(int id);
    List<Doctor> getAllDoctors();
}
