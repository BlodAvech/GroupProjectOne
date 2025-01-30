package repositories;

import data.interfaceces.IDB;
import models.Doctor;
import repositories.interfaces.IDoctorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository implements IDoctorRepository {
    private final IDB db;

    public DoctorRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createDoctor(Doctor doctor){
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "Insert into Doctors(name, surname, workingDays) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, Doctor.getName());
            st.setString(2, Doctor.getSurname());
            st.setBoolean(3, Doctor.getworkingDays());

            st.execute();

            return true;
        }(catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Doctor getDoctorByID(int id) {
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return List.of();
    }

    @Override
    public Doctor getDoctorById(Int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "Select * from Doctor WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Doctors(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("workingDays"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors(){
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "SELECT id, name, surname, workingDays FROM Doctors";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Doctor> doctors = new ArrayList<>();
            while (rs.next()){
                Doctor doctor = new Doctor(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("workingDays"));
                doctors.add(doctor);
            }
            return doctors;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}

