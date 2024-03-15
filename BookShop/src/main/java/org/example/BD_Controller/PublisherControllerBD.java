package org.example.BD_Controller;

import org.example.BD_Repository.PublisherRepositoryBD;
import org.example.main.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublisherControllerBD {
    private PublisherRepositoryBD publisherRepositoryBD;

    public PublisherControllerBD(PublisherRepositoryBD PublisherRepositoryBD) {
        this.publisherRepositoryBD = PublisherRepositoryBD;
    }

    public void saveIntoDB(Publisher Publisher){
        publisherRepositoryBD.saveIntoDB(Publisher);
    }

    public Publisher createPublisherFromResultSet(ResultSet resultSet) throws SQLException{
        return publisherRepositoryBD.createPublisherFromResultSet(resultSet);
    }

    public List<Publisher> loadFromDB(){
        return publisherRepositoryBD.loadFromDB();
    }

    public Publisher findById(int id){
        return publisherRepositoryBD.findByID(id);
    }


    public void delete(int id){
        publisherRepositoryBD.delete(id);
    }
}