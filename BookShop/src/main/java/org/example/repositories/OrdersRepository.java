package org.example.repositories;


import org.example.main.CartItem;
import org.example.main.Orders;
import org.example.main.Patterns.Observer.CartItemObservable;
import org.example.main.Patterns.Observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class OrdersRepository implements OrderObserver {

    private List<Orders> orders = new ArrayList<>();
    private List<CartItemObservable> observables;

    public OrdersRepository(List<Orders> orders) {
        this.orders = orders;
    }

    public Orders findById(int targetOrderId) {
        for (Orders order : orders) {
            if (order.getOrder_id() == targetOrderId) {
                return order;
            }
        }
        System.out.println("Could not find order with Id: " + targetOrderId);
        return null;
    }

    public List<Orders> findAll() {
        if (orders.isEmpty()) {
            System.out.println("There are no orders");
            return null;
        }
        return orders;
    }


    public boolean save(Orders order) {
        boolean saved = false;
        for (Orders item : orders) {
            if (order.getOrder_id() == item.getOrder_id()) {
                System.out.println("Order already exists");
                return saved;
            }

        }
        orders.add(order);
        for (Orders item : orders) {
            if (order.getOrder_id() == item.getOrder_id())
                saved = true;
        }

        return saved;
    }

    public boolean update(Orders updatedOrder) {
        boolean updated = false;
        for (Orders order : orders) {
            if (order.getOrder_id() == updatedOrder.getOrder_id()) {
                order.setDate(updatedOrder.getDate());
                order.setClient_id(updatedOrder.getClient_id());
                order.setStatus(updatedOrder.getStatus());
                order.calculateTotalPrice();
                order.setCartItems(updatedOrder.getCartItems());
                updated = true;
                break;
            }
        }
        if (updated == false)
            System.out.println("Order was not updated");

        return updated;
    }

    public boolean delete(int targetOrderId) {
        boolean deleted = false;
        Orders orderToRemove = null;
        for (Orders order : orders) {
            if (order.getOrder_id() == targetOrderId) {
                orderToRemove = order;
                break;
            }
        }
        if (orderToRemove == null) {
            System.out.println("Order does not exist");
            deleted = false;
        }
        if (orderToRemove != null) {
            orders.remove(orderToRemove);
            deleted = true;
        }
        return deleted;
    }



    @Override
    public void update_event(String event, CartItem cartItem) {
        for(Orders order : orders){
            List<CartItem> cartItems = order.getCartItems();
            for(CartItem cartItem1 : cartItems){
                if(cartItem.equals(cartItem1)){
                    System.out.println(event);
                }
            }
        }
    }

    @Override
    public void update_event2(String event, int id, int quantity){

    }
}