package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClientsRepositoryBD {
    private SqlServer sqlServer;

    public ClientsRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(Clients client){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Clients(ClientID, FirstName, LastName, BirthDate, Address, Email) VALUES (?,?,?,?,?,?)")){

                preparedStatement.setInt(1,client.getClient_id());
                preparedStatement.setString(2,client.getFirstName());
                preparedStatement.setString(3,client.getLastName());
                preparedStatement.setString(4,client.getBirth_date());
                preparedStatement.setString(5,client.getAddress());
                preparedStatement.setString(6,client.getEmail());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Clients createClientFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("ClientID");
        String fname = resultSet.getString("FirstName");
        String lname = resultSet.getString("LastName");
        String bdate = resultSet.getString("BirthDate");
        String address = resultSet.getString("Address");
        String email = resultSet.getString("Email");

        Clients client = new Clients(id,fname,lname,bdate,address,email);
        return client;
    }

    public List<Clients> loadFromDB(){
        List<Clients> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Clients";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createClientFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Clients findByID(int id){
//        List<Clients> allClients = loadFromDB();
//        Clients found = null;
//        for(Clients client:allClients){
//            if(client.getClient_id() == id)
//                found = client;
//        }
//
//        return found;
//    }

    public Clients findByID(int id){
        String sql = "SELECT * FROM labor.Clients WHERE ClientID = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createClientFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateEmail(int Id, String newemail) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.Clients SET Email = ? WHERE ClientID = ?")) {

            preparedStatement.setString(1,newemail);
            preparedStatement.setInt(2, Id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Clients WHERE ClientID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
