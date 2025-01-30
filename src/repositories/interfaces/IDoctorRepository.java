package repositories.interfaces;

import models.Doctor;

import java.util.List;

public interface IDoctorRepository {
    boolean createDoctor(Doctor doctor);
    Doctor getDoctorByID(int id);
    List<Doctor> getAllDoctors();

    boolean createDoctor(Doctor doctor);
}
