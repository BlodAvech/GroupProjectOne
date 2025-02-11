//SOLID
package controllers;

import controllers.interfaces.IDoctorController;
import models.Doctor;
import models.Patient;
import repositories.interfaces.IDoctorRepository;

import java.util.List;

public class DoctorController implements IDoctorController {
    private final IDoctorRepository repo;

    public DoctorController(IDoctorRepository repo) {
        this.repo = repo;
    }
    @Override
    public String createDoctor(String name, String surname ,  String doctorType, boolean[] workdays) {
        Doctor doctor = new Doctor(name, surname,doctorType , workdays);
        boolean created = repo.createDoctor(doctor);
        return (created) ? "success" : "error";
    }
    @Override
    public Doctor getDoctorById(int id) {
        Doctor doctor = repo.getDoctorById(id);
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = repo.getAllDoctors();
        return doctors;
    }
}
