//SOLID
package repositories.interfaces;

import models.*;

import java.util.List;

public interface IOrderRepository {
    boolean createOrder(Order order);
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
