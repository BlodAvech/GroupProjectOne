//SOLID
package controllers.interfaces;

import models.Patient;

import java.util.List;

public interface IPatientController {
    String createPatient(String name , String surname);
    Patient getPatientById(int id);
    List<Patient> getAllPatients();
}
