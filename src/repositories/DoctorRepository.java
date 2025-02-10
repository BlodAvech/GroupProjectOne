package repositories;

import data.interfaceces.IDB;
import models.Doctor;
import repositories.interfaces.IDoctorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            String sql = "Insert into doctors(name, surname, doctortype , workdays) VALUES (?, ? , ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, doctor.getName());
            st.setString(2, doctor.getSurname());

            boolean[] workdays = doctor.getWorkdays();
            Object[] workdaysObj = new Object[workdays.length];
            for(int i = 0 ; i < workdaysObj.length ; i++) {workdaysObj[i] = workdays[i];}

            st.setString(3 , doctor.getDoctorType());
            st.setArray(4 , connection.createArrayOf("boolean" , workdaysObj));

            st.execute();

            return true;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Doctor getDoctorById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "Select * from doctors WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Array sqlArray = rs.getArray("workdays");
                Object[] workdaysAsObjects = (Object[]) sqlArray.getArray();
                boolean[] workdays = new boolean[workdaysAsObjects.length];
                for (int i = 0; i < workdaysAsObjects.length; i++) {workdays[i] = (Boolean) workdaysAsObjects[i];}
                return new Doctor(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("doctortype"),
                        workdays);
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
            String sql = "SELECT id, name, surname , doctortype , workdays FROM doctors";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Doctor> doctors = new ArrayList<>();
            while (rs.next()){
                Array sqlArray = rs.getArray("workdays");
                Object[] workdaysAsObjects = (Object[]) sqlArray.getArray();
                boolean[] workdays = new boolean[workdaysAsObjects.length];
                for (int i = 0; i < workdaysAsObjects.length; i++) {workdays[i] = (Boolean) workdaysAsObjects[i];}
                Doctor doctor = new Doctor(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("doctortype"),
                        workdays);
                doctors.add(doctor);
            }
            return doctors;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}

