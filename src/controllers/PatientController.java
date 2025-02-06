package controllers;

import controllers.interfaces.IPatientController;
import models.Patient;
import repositories.interfaces.IPatientRepository;

import java.util.List;

public class PatientController implements IPatientController {
    private final IPatientRepository repo;

    public PatientController(IPatientRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createPatient(String name, String surname, String day, String time) {
        Patient patient = new Patient(name, surname, day, time);
        boolean created = repo.createPatient(patient);
        return (created) ? "User was created" : "User creation was failed";
    }

    @Override
    public String getPatientById(int id){
        Patient Patient = repo.getPatientById(id);
        return (Patient == null) ? "User was not found" : Patient.toString();
    }


    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = repo.getAllPatients();
        return patients;
    }
}