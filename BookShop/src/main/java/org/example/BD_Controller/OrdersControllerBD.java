package org.example.BD_Controller;

import org.example.BD_Repository.OrdersRepositoryBD;
import org.example.main.CartItem;
import org.example.main.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersControllerBD {
    private OrdersRepositoryBD ordersRepositoryBD;

    public OrdersControllerBD(OrdersRepositoryBD ordersRepositoryBD) {
        this.ordersRepositoryBD = ordersRepositoryBD;
    }

    public void saveIntoDB(Orders order){
        ordersRepositoryBD.saveIntoDB(order);
    }

    public Orders createOrdersFromResultSet(ResultSet resultSet) throws SQLException{
        return ordersRepositoryBD.createorderFromResultSet(resultSet);
    }

    public List<Orders> loadFromDB(){
        return ordersRepositoryBD.loadFromDB();
    }

    public Orders findById(int id){
        return ordersRepositoryBD.findByID(id);
    }

    public void updateStatus(int id, String status){ordersRepositoryBD.updateStatus(id,status);}
    public void update(Orders order){ordersRepositoryBD.update(order);}
    public void update_delete(Orders order, int bookid){ordersRepositoryBD.update_delete(order, bookid);}

    public void delete(int id){
        ordersRepositoryBD.delete(id);
    }

    public void addItemToOrder(int orderId, CartItem cartItem){
        Orders order = ordersRepositoryBD.findByID(orderId);

        if(order != null){
            order.addCartItem(cartItem);
            ordersRepositoryBD.update(order);
        }
    }

//    public void removeItemFromOrder(int orderId,CartItem cartItem){
//        Orders order=ordersRepositoryBD.findByID(orderId);
//        if(order!=null){
//            order.removeCartItem(cartItem);
//            ordersRepositoryBD.update_delete(order);
//        }
//    }

    public void removeItemFromOrder(int orderId, CartItem cartItem) {
        Orders order = ordersRepositoryBD.findByID(orderId);
        if (order != null) {
            order.removeCartItem(cartItem);
            ordersRepositoryBD.update_delete(order, cartItem.getBook().getBook_id());
        }
    }
}