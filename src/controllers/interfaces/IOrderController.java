//SOLID
package controllers.interfaces;

import models.Doctor;
import models.Order;
import models.Patient;

import java.util.List;

public interface IOrderController {
    boolean createOrder(int patientId , int doctorId , String weekDay , String doctorType);
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
