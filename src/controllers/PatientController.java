package controllers;

import controllers.interfaces.IPatientController;

public class PatientController implements IPatientController {

    @Override
    public String createPatient(String name, String surname, String doctor, String day, String time) {
        return "";
    }

    @Override
    public String getPatientById(int id) {
        return "";
    }

    @Override
    public String getAllPatients() {
        return "";
    }
}
