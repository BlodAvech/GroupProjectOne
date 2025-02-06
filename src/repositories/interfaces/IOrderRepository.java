package repositories.interfaces;

import models.*;

import java.util.List;

public interface IOrderRepository {
    void createOrder(int patientId , int doctorId , String weekDay , String time);
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
