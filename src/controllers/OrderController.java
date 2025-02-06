package controllers;

import controllers.interfaces.IOrderController;
import models.*;

public class OrderController implements IOrderController {
    private  final  IOrderRepository repo;
    public OrderController(IOrderRepository repo) { this.repo = repo; }

    @Override
    public String createOrder(Patient patient, Doctor doctor, String weekDay, String time){
        Order order = new Order(patient, doctor, weekDay, time);
        boolean created = repo.createOrder(order);
        return ( created ? "Order created" : "Order not created") ;


    }

    @Override
    public String getOrderById(int id) {
        Order order = repo.getOrderById(id);
        return (Order == null) ? "Order was not found": Order.toString();
    }

    @Override
    public String getAllOrders() {
        List<Order> orders = repo. getAllOrders();
        StringBuilder responce = new StringBuilder();
        for (Order order : orders) {
            responce.append(order.toString()).append("\n");
        }

        return responce.toString();
    }

}