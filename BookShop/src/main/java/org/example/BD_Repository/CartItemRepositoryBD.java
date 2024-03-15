package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Books;
import org.example.main.CartItem;
import org.example.main.Patterns.Observer.CartItemObservable;
import org.example.main.Patterns.Observer.OrderObserver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemRepositoryBD implements CartItemObservable {
    private SqlServer sqlServer;
    private OrderObserver observer;

    public CartItemRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(CartItem cartItem){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.CartItem(BookID, Quantity) VALUES (?,?)")){

                preparedStatement.setInt(1,cartItem.getBook().getBook_id());
                preparedStatement.setInt(2,cartItem.getQuantity());


            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public CartItem createCartItemFromResultSet(ResultSet resultSet) throws SQLException{
        BooksRepositoryDB booksRepositoryDB = new BooksRepositoryDB(sqlServer);
        int id = resultSet.getInt("BookID");
        int quantity = resultSet.getInt("Quantity");
        Books book = booksRepositoryDB.findByID(id);
        CartItem cartItem = new CartItem(book,quantity);
        return cartItem;
    }

    public List<CartItem> loadFromDB(){
        List<CartItem> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.CartItem";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createCartItemFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateQuantity(int bookid, int newquantity, int orderid) {
//        try (Connection connection = sqlServer.connect();
//             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.CartItem SET Quantity = ? WHERE BookID = ?")) {
//
//            preparedStatement.setInt(1, newquantity);
//            preparedStatement.setInt(2, id);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.Order_CartItem SET Quantity = ? WHERE BookID = ? AND OrderID = ? ")) {

            preparedStatement.setInt(1, newquantity);
            preparedStatement.setInt(2, bookid);
            preparedStatement.setInt(3,orderid);

            preparedStatement.executeUpdate();
            notify_observer("Quantity updated ",bookid,newquantity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int bookid){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.CartItem WHERE BookID = ?")) {

            preparedStatement.setInt(1, bookid);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyObserver(String event, CartItem cartItem) {

    }

    @Override
    public void notify_observer(String event, int id, int quantity){
        observer.update_event2(event,id,quantity);
    }
}
