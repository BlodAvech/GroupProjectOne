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
    public void createOrder() {
        Connection connection = null;

        try {
            connection = db.getConnection();
            String sql= "";
            PreparedStatement st = connection.prepareStatement(sql);



        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
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
}
