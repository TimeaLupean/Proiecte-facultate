package org.example.BD_Controller;

import org.example.BD_Repository.ClientsRepositoryBD;
import org.example.main.Clients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientsControllerBD {
    private ClientsRepositoryBD clientsRepositoryBD;

    public ClientsControllerBD(ClientsRepositoryBD clientsRepositoryBD) {
        this.clientsRepositoryBD = clientsRepositoryBD;
    }

    public void saveIntoDB(Clients client){
        clientsRepositoryBD.saveIntoDB(client);
    }

    public Clients createClientsFromResultSet(ResultSet resultSet) throws SQLException{
        return clientsRepositoryBD.createClientFromResultSet(resultSet);
    }

    public List<Clients> loadFromDB(){
        return clientsRepositoryBD.loadFromDB();
    }

    public Clients findById(int id){
        return clientsRepositoryBD.findByID(id);
    }

    public void updateEmail(int id, String email){clientsRepositoryBD.updateEmail(id,email);}

    public void delete(int id){
        clientsRepositoryBD.delete(id);
    }
}