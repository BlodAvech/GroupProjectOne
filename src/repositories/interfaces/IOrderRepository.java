package repositories.interfaces;

import models.Doctor;
import models.Patient;

import java.util.List;

public interface IOrderRepository {
    boolean createOrder(Patient patient, Doctor doctor);
    Patient getPatientById(int id);
    Doctor getDoctorById(int id);
    List<Patient> getAllPatients();
    List<Doctor> getAllDoctors();
}
