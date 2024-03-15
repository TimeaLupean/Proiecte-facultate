package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Shipping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ShippingRepositoryBD {
    private SqlServer sqlServer;

    public ShippingRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(Shipping shipping){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Shipping(ShippingID, Address, ShippingMethod) VALUES (?,?,?)")){

                preparedStatement.setInt(1,shipping.getShipping_id());
                preparedStatement.setString(2,shipping.getAddress());
                preparedStatement.setString(3,shipping.getShipping_method());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Shipping createShippingFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("ShippingID");
        String address = resultSet.getString("Address");
        String shippingmethod = resultSet.getString("ShippingMethod");

        Shipping shipping = new Shipping(id,address,shippingmethod);
        return shipping;
    }

    public List<Shipping> loadFromDB(){
        List<Shipping> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Shipping";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createShippingFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Shipping findByID(int id){
//        List<Shipping> allShippings = loadFromDB();
//        Shipping found = null;
//        for(Shipping shipping:allShippings){
//            if(shipping.getShipping_id() == id)
//                found = shipping;
//        }
//
//        return found;
//    }
public Shipping findByID(int id){
    String sql = "SELECT * FROM labor.Shipping WHERE ShippingID = ?";
    try (Connection connection = sqlServer.connect();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return createShippingFromResultSet(resultSet);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Shipping WHERE ReviewId = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
