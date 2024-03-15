package org.example.BD_Controller;


import org.example.BD_Repository.ShippingRepositoryBD;
import org.example.main.Shipping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShippingControllerBD {
    private ShippingRepositoryBD shippingRepositoryBD;

    public ShippingControllerBD(ShippingRepositoryBD ShippingRepositoryBD) {
        this.shippingRepositoryBD = ShippingRepositoryBD;
    }

    public void saveIntoDB(Shipping Shipping){
        shippingRepositoryBD.saveIntoDB(Shipping);
    }

    public Shipping createShippingFromResultSet(ResultSet resultSet) throws SQLException{
        return shippingRepositoryBD.createShippingFromResultSet(resultSet);
    }

    public List<Shipping> loadFromDB(){
        return shippingRepositoryBD.loadFromDB();
    }

    public Shipping findById(int id){
        return shippingRepositoryBD.findByID(id);
    }

    public void delete(int id){
        shippingRepositoryBD.delete(id);
    }
}