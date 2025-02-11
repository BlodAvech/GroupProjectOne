//LSP,ISP,DIP
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
    public boolean createOrder(Order order) {
        Connection connection = null;

        try {
            connection = db.getConnection();  // Подключение к БД

            String sql = """
            INSERT INTO orders (patientid, doctorid, weekday, doctortype)
            VALUES (?, ?, ?, ?);
            """;

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, order.getPatientId());
            st.setInt(2, order.getDoctorId());
            st.setString(3, order.getWeekday());
            st.setString(4, order.getDoctorType());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
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
                        rs.getString("weekday"),
                        rs.getString("doctortype"));
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
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getString("weekday"),
                        rs.getString("doctortype"));
                orders.add(order);
            }
            return orders;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

}
