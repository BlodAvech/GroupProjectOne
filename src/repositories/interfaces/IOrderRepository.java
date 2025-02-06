package repositories.interfaces;

import models.*;

import java.util.List;

public interface IOrderRepository {
    void createOrder();
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
