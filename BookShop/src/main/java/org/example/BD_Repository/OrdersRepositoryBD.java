package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Books;
import org.example.main.CartItem;
import org.example.main.Orders;
import org.example.main.Patterns.Observer.CartItemObservable;
import org.example.main.Patterns.Observer.OrderObserver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepositoryBD implements OrderObserver {
    private SqlServer sqlServer;
    private List<CartItemObservable> observables;

    public OrdersRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

//    public void saveIntoDB(Orders order){
//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Orders(OrderID, Date, TotalPrice, ClientID, Status) VALUES (?,?,?,?,?)")){
//
//                preparedStatement.setInt(1,order.getOrder_id());
//                preparedStatement.setString(2, order.getDate());
//                preparedStatement.setInt(3,order.calculateTotalPrice());
//                preparedStatement.setInt(4,order.getClient_id());
//                preparedStatement.setString(5,order.getStatus());
//
//            preparedStatement.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO labor.Order_CartItem(OrderID, BookID, Quantity) VALUES (?,?,?)")){
//
//                List<CartItem> cartItems = order.getCartItems();
//                for (CartItem cartItem:cartItems){
//                    preparedStatement2.setInt(1,order.getOrder_id());
//                    preparedStatement2.setInt(2,cartItem.getBook().getBook_id());
//                    preparedStatement2.setInt(3,cartItem.getQuantity());
//            }
//
//            preparedStatement2.executeUpdate();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

//    public void saveIntoDB(Orders order) {
//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Orders(OrderID, Date, TotalPrice, ClientID, Status) VALUES (?,?,?,?,?)")) {
//
//            preparedStatement.setInt(1, order.getOrder_id());
//            preparedStatement.setString(2, order.getDate());
//            preparedStatement.setInt(3, order.calculateTotalPrice());
//            preparedStatement.setInt(4, order.getClient_id());
//            preparedStatement.setString(5, order.getStatus());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO labor.Order_CartItem(OrderID, BookID, Quantity) VALUES (?,?,?)")) {
//
//            List<CartItem> cartItems = order.getCartItems();
//            for (CartItem cartItem : cartItems) {
//                preparedStatement2.setInt(1, order.getOrder_id());
//                preparedStatement2.setInt(2, cartItem.getBook().getBook_id());
//                preparedStatement2.setInt(3, cartItem.getQuantity());
//                preparedStatement2.executeUpdate();  // Move this line inside the loop
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void saveIntoDB(Orders order) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Orders(OrderID, Date, TotalPrice, ClientID, Status) VALUES (?,?,?,?,?)")) {

            preparedStatement.setInt(1, order.getOrder_id());
            preparedStatement.setString(2, order.getDate());
            preparedStatement.setInt(3, order.calculateTotalPrice());
            preparedStatement.setInt(4, order.getClient_id());
            preparedStatement.setString(5, order.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO labor.CartItem( BookID, Quantity) VALUES (?,?)")) {

            preparedStatement2.setInt(1, 0);
            preparedStatement2.setInt(2, 0);
            preparedStatement2.executeUpdate();
            List<CartItem> cartItems = order.getCartItems();
            for (CartItem cartItem : cartItems) {
                preparedStatement2.setInt(2, cartItem.getBook().getBook_id());
                preparedStatement2.setInt(3, cartItem.getQuantity());
                preparedStatement2.executeUpdate();  // Move this line inside the loop
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO labor.Order_CartItem(OrderID, BookID, Quantity) VALUES (?,?,?)")) {
//
//            preparedStatement2.setInt(1, order.getOrder_id());
//            preparedStatement2.setInt(2, 0);
//            preparedStatement2.setInt(3, 0);
//            preparedStatement2.executeUpdate();
//            List<CartItem> cartItems = order.getCartItems();
//            for (CartItem cartItem : cartItems) {
//                preparedStatement2.setInt(1, order.getOrder_id());
//                preparedStatement2.setInt(2, cartItem.getBook().getBook_id());
//                preparedStatement2.setInt(3, cartItem.getQuantity());
//                preparedStatement2.executeUpdate();  // Move this line inside the loop
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


    public Orders createorderFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("OrderID");
        String date = resultSet.getString("Date");
        int totalprice = resultSet.getInt("TotalPrice");
        int clientid = resultSet.getInt("ClientID");
        String status = resultSet.getString("Status");

        List<CartItem> cartItems = loadFromDBIntermediaryTable(id);
        Orders order = new Orders(id,date,totalprice,clientid,status,cartItems);
        return order;
    }

    public CartItem createOrder_CartItemFromResultSet(ResultSet resultSet) throws SQLException {
        BooksRepositoryDB booksRepositoryDB = new BooksRepositoryDB(sqlServer);
        int orderid = resultSet.getInt("OrderID");
        int bookid = resultSet.getInt("BookID");
        int quantity = resultSet.getInt("Quantity");

        Books book = booksRepositoryDB.findByID(bookid);
        CartItem cartItem = new CartItem(book,quantity);
        return cartItem;
    }

    public List<CartItem> loadFromDBIntermediaryTable(int id) {
        List<CartItem> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM labor.Order_CartItem WHERE OrderID = ?");
        ) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(createOrder_CartItemFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Orders> loadFromDB(){
        List<Orders> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Orders";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createorderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Orders findByID(int id){
        String sql = "SELECT * FROM labor.Orders WHERE OrderID = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createorderFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Orders WHERE OrderID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Order_CartItem WHERE OrderID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(int Id, String newstatus) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.Orders SET Status = ? WHERE OrderID = ?")) {

            preparedStatement.setString(1,newstatus);
            preparedStatement.setInt(2, Id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Orders order) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.Orders SET Date = ?, TotalPrice = ?,Status = ? WHERE OrderID = ?")) {

            preparedStatement.setString(1,order.getDate());
            preparedStatement.setInt(2, order.calculateTotalPrice());
            //preparedStatement.setInt(3,order.getClient_id());
            preparedStatement.setString(3,order.getStatus());
            preparedStatement.setInt(4, order.getOrder_id());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO labor.Order_CartItem(OrderID, BookID, Quantity) VALUES (?,?,?)")) {

            List<CartItem> cartItems = order.getCartItems();
            for (CartItem cartItem : cartItems) {
                if (!cartItemExists(connection, order.getOrder_id(), cartItem.getBook().getBook_id())) {
                    preparedStatement2.setInt(1, order.getOrder_id());
                    preparedStatement2.setInt(2, cartItem.getBook().getBook_id());
                    preparedStatement2.setInt(3, cartItem.getQuantity());
                    preparedStatement2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update_delete(Orders order, int bookid) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Order_CartItem WHERE OrderID = ? AND BookID = ?")) {

            preparedStatement.setInt(1, order.getOrder_id()); // Set the order ID
            preparedStatement.setInt(2, bookid); // Set the book ID to be deleted
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Orders WHERE OrderID = ?")) {
//
//            preparedStatement.setInt(1, order.getOrder_id());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.Orders SET Date = ?, TotalPrice = ?,Status = ? WHERE OrderID = ?")) {


            List<CartItem> cartItems = order.getCartItems();
            int price = 0;
            for(CartItem cartItem:cartItems){
                if(cartItem.getBook().getBook_id() == bookid)
                    price = cartItem.getBook().getPrice();
            }
            preparedStatement.setString(1,order.getDate());
            preparedStatement.setInt(2, order.calculateTotalPrice()-price);
            //preparedStatement.setInt(3,order.getClient_id());
            preparedStatement.setString(3,order.getStatus());
            preparedStatement.setInt(4, order.getOrder_id());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean cartItemExists(Connection connection, int orderId, int bookId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM labor.Order_CartItem WHERE OrderID = ? AND BookID = ?")) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }


    @Override
    public void update_event(String event, CartItem cartItem) {

    }

    @Override
    public void update_event2(String event, int id, int quantity){
        System.out.println(event + "for book: "+ id + "the quantity: " + quantity);
    }

}
