package org.example.BD_Controller;

import org.example.BD_Repository.AuthorRepositoryBD;
import org.example.main.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorControllerBD {
    private AuthorRepositoryBD authorRepositoryBD;

    public AuthorControllerBD(AuthorRepositoryBD authorRepositoryBD) {
        this.authorRepositoryBD = authorRepositoryBD;
    }

    public void saveIntoDB(Author author){
        authorRepositoryBD.saveIntoDB(author);
    }

    public Author createAuthorFromResultSet(ResultSet resultSet) throws SQLException{
        return authorRepositoryBD.createAuthorFromResultSet(resultSet);
    }

    public List<Author> loadFromDB(){
        return authorRepositoryBD.loadFromDB();
    }

    public Author findById(int id){
        return authorRepositoryBD.findByID(id);
    }

    public Author findByName(String firstName, String lastName){
        return authorRepositoryBD.findByName(firstName,lastName);
    }

    public void delete(int id){
        authorRepositoryBD.delete(id);
    }
}
