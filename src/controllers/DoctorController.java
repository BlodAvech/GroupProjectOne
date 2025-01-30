package controllers;

import controllers.interfaces.IDoctorController;
import models.Doctor;
import repositories.interfaces.IDoctorRepository;

import java.util.List;

public class DoctorController implements IDoctorController {
    private final IDoctorRepository repo;

    public DoctorController(IDoctorRepository repo) {
        this.repo = repo;
    }
    @Override
    public String createDoctor(String name, String surname, boolean[] workdays) {
        Doctor doctor = new Doctor(name, surname, workdays);
        boolean created = repo.createDoctor(doctor);
        return (created) ? "success" : "error";
    }
    @Override
    public String getDoctorById(int id) {
        Doctor doctor = repo.getDoctorById(id);
        return (doctor == null) ? "User was not found" : doctor.toString();
    }

    @Override
    public String getAllDoctors() {
        List<Doctor> doctors = repo.getAllDoctors();
        StringBuilder responce = new StringBuilder();
        for (Doctor doctor : doctors) {
            responce.append(doctor  .toString()).append("\n");
        }
        return responce.toString();
    }
}
