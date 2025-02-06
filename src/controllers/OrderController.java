package controllers;

import controllers.interfaces.IOrderController;
import models.*;
import repositories.interfaces.IOrderRepository;

import java.util.List;

public class OrderController implements IOrderController {
    private  final IOrderRepository repo;
    public OrderController(IOrderRepository repo) { this.repo = repo; }

    @Override
    public void createOrder(int patientId , int doctorId , String weekDay , String time){
        repo.createOrder(patientId , doctorId , weekDay , time);
    }

    @Override
    public Order getOrderById(int id) {
        Order order = repo.getOrderById(id);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = repo.getAllOrders();
        return orders;
    }

}