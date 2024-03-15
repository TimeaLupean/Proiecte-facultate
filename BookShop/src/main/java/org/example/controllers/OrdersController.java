package org.example.controllers;

import org.example.main.CartItem;
import org.example.main.Orders;
import org.example.repositories.OrdersRepository;

import java.util.List;

public class OrdersController {
    private OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void createOrder(int orderId, String date, int clientId, int totalPrice, String status, List<CartItem>cartItems) {
        Orders order = new Orders(orderId, date, clientId, totalPrice, status, cartItems);
        ordersRepository.save(order);
    }


    public Orders findOrderById(int orderId) {
        return ordersRepository.findById(orderId);
    }

    public List<Orders> viewAllOrders() {
        return ordersRepository.findAll();
    }

    public void updateOrder(int orderId, String date, int totalPrice, int clientId, String status,List<CartItem>cartItems) {
        Orders updatedOrder = new Orders(orderId, date, totalPrice,clientId, status,cartItems);
        ordersRepository.update(updatedOrder);
    }

    public void deleteOrder(int orderId) {
        ordersRepository.delete(orderId);
    }

    public void addItemToOrder(int orderId, CartItem cartItem){
        Orders order = ordersRepository.findById(orderId);

        if(order != null){
            order.addCartItem(cartItem);
            ordersRepository.update(order);
        }
    }

    public void removeItemFromOrder(int orderId,CartItem cartItem){
        Orders order=ordersRepository.findById(orderId);
        if(order!=null){
            order.removeCartItem(cartItem);
            ordersRepository.update(order);
        }
    }
}

