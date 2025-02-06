package controllers.interfaces;

import models.Doctor;
import models.Patient;

public interface IOrderController {
    String createOrder(Patient patient, Doctor doctor, String weekDay, String time);
    String getOrderById(int id);
    String getAllOrders();
}
