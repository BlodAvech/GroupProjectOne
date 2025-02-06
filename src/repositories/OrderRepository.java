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


    @Override
    public void createOrder(int patientId, int doctorId, String weekday, String time) {
        Connection connection = null;

        try {
            connection = db.getConnection();  // Подключение к БД

            String sql = """
            INSERT INTO orders (patientId, doctorId, weekday, time)
            SELECT p.id, d.id, ?, ?
            FROM patients p
            JOIN doctors d ON p.id = ? AND d.id = ?;
        """;

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(3, patientId);
            st.setInt(4, doctorId);
            st.setString(1, weekday);
            st.setString(2, time);

            st.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
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
                        rs.getInt("customerId"),
                        rs.getInt("doctorId"),
                        rs.getString("day"),
                        rs.getString("time"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT * FROM orders";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Order> orders = new ArrayList<>();
            while(rs.next()){
                Order order = new Order(rs.getInt("id"),
                        rs.getInt("customerId"),
                        rs.getInt("doctorId"),
                        rs.getString("day"),
                        rs.getString("time"));
                orders.add(order);
            }
            return orders;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

}
