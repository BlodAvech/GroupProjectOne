package repositories;

import controllers.PatientController;
import data.interfaceces.IDB;
import models.Patient;
import repositories.interfaces.IPatientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository implements IPatientRepository {
    private final IDB db;

    public PatientRepository(IDB db) {this.db = db;}

    @Override
    public boolean createPatient(Patient patient) {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql= "INSERT INTO patients(name , surname , doctor , day , time) VALUES (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1 , patient.getName());
            st.setString(2 , patient.getSurname());
            st.setString(3 , patient.getDoctor());
            st.setString(4 , patient.getDay());
            st.setString(5 , patient.getTime());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }


        return false;
    }

    @Override
    public Patient getPatientById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM patients WHERE id = ?";
            PreparedStatement st =connection.prepareStatement(sql);
            st.setInt(1 , id);

            ResultSet rs= st.executeQuery();
            if(rs.next())
            {
                return new Patient(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("doctor"),
                        rs.getString("day"),
                        rs.getString("time"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT id, name, surname, gender FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Patient> patients = new ArrayList<>();
            while(rs.next()){
                Patient patient = new Patient(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("doctor"),
                        rs.getString("day"),
                        rs.getString("time"));
                patients.add(patient);
            }
            return patients;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}
