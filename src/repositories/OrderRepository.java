package repositories;

import controllers.PatientController;
import data.interfaceces.IDB;
import models.Doctor;
import models.Order;
import models.Patient;
import repositories.interfaces.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) {this.db = db;}

    public boolean createOrder(Patient patient) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO orders(patientid, doctorid, day, time) VALUES (?, ?, ?, ?)";
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                Order order = new Order(patient.getId(), patient.getDoctorId(), patient.getDay(), patient.getTime());
                st.setInt(1, order.getPatientId());
                st.setInt(2, order.getDoctorId());
                st.setString(3, order.getDay());
                st.setString(4, order.getTime());
                st.execute();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

        try (Connection connection = db.getConnection()) {

        try {
            connection = db.getConnection();
            String sql= "INSERT INTO order(id , patientid , doctorid , day , time) VALUES (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1 , order.getId());
            st.setString(2 , order.getPatientId());
            st.setString(3 , order.getDoctorId());
            st.setString(4 , order.getDay());
            st.setString(5 , order.getTime());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }


        return false;
    }

    @Override
    public Order getOrderById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement st =connection.prepareStatement(sql);
            st.setInt(1 , id);

            ResultSet rs= st.executeQuery();
            if(rs.next())
            {
                return new Order(rs.getInt("id"),
                        rs.getInt("patientid"),
                        rs.getInt("doctorid"),
                        rs.getString("day"),
                        rs.getString("time"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    public boolean createOrder(Patient patient, Doctor doctor) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO orders(patientid, doctorid, day, time) VALUES (?, ?, ?, ?)";
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, patient.getId());
                st.setInt(2, doctor.getId());
                st.setString(3, patient.getDay());
                st.setString(4, patient.getTime());
                st.execute();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }
    }

    @Override
    public Patient getPatientById(int id) {
        return null;
    }

    @Override
    public Doctor getDoctorById(int id) {
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM patients";
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                List<Patient> patients = new ArrayList<>();
                while (rs.next()) {
                    Patient patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("doctorId"),
                        rs.getString("day"),
                        rs.getString("time")
                    );
                    patients.add(patient);
                }
                return patients;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    @Override
        public List<Doctor> getAllDoctors() {
            try (Connection connection = db.getConnection()) {
                String sql = "SELECT * FROM doctors";
                try (PreparedStatement st = connection.prepareStatement(sql)) {
                    ResultSet rs = st.executeQuery();
                    List<Doctor> doctors = new ArrayList<>();
                    while (rs.next()) {
                        doctors.add(new Doctor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("specialization")
                        ));
                    }
                    return doctors;
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
            }
            return List.of();
        }
}
