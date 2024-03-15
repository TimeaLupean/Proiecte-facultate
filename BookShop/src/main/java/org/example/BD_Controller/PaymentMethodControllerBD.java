package org.example.BD_Controller;


import org.example.BD_Repository.PaymentMethodRepositoryBD;
import org.example.main.PaymentMethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentMethodControllerBD {
    private PaymentMethodRepositoryBD paymentMethodRepositoryBD;

    public PaymentMethodControllerBD(PaymentMethodRepositoryBD paymentMethodRepositoryBD) {
        this.paymentMethodRepositoryBD = paymentMethodRepositoryBD;
    }

    public void saveIntoDB(PaymentMethod PaymentMethod){
        paymentMethodRepositoryBD.saveIntoDB(PaymentMethod);
    }

    public PaymentMethod createPaymentMethodFromResultSet(ResultSet resultSet) throws SQLException{
        return paymentMethodRepositoryBD.createPaymentMethodFromResultSet(resultSet);
    }

    public List<PaymentMethod> loadFromDB(){
        return paymentMethodRepositoryBD.loadFromDB();
    }

    public PaymentMethod findById(int id){
        return paymentMethodRepositoryBD.findByID(id);
    }

    public void updateStatus(int id, String status){paymentMethodRepositoryBD.updateStatus(id,status);}

    public void delete(int id){
        paymentMethodRepositoryBD.delete(id);
    }
}