package controllers.interfaces;

import models.Doctor;
import models.Order;
import models.Patient;

import java.util.List;

public interface IOrderController {
    void createOrder();
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
