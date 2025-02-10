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
    public String createPatient(String name, String surname) {
        Patient patient = new Patient(name, surname);
        boolean created = repo.createPatient(patient);
        return (created) ? "User was created" : "User creation was failed";
    }

    @Override
    public Patient getPatientById(int id){
        Patient patient = repo.getPatientById(id);
        return patient;
    }


    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = repo.getAllPatients();
        return patients;
    }
}