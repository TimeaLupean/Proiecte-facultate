package org.example.BD_Controller;

import org.example.BD_Repository.BooksRepositoryDB;
import org.example.main.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BooksControllerBD {
    private BooksRepositoryDB booksRepositoryBD;

    public BooksControllerBD(BooksRepositoryDB booksRepositoryBD) {
        this.booksRepositoryBD = booksRepositoryBD;
    }

    public void saveIntoDB(Books book){
        booksRepositoryBD.saveIntoDB(book);
    }

    public Books createBooksFromResultSet(ResultSet resultSet) throws SQLException {
        return booksRepositoryBD.createBookFromResultSet(resultSet);
    }

    public List<Books> loadFromDB(){
        return booksRepositoryBD.loadFromDB();
    }

    public Books findById(int id){
        return booksRepositoryBD.findByID(id);
    }

    public void updatePrice(int id, int newprice){booksRepositoryBD.updatePrice(id,newprice);}
    public void delete(int id){
        booksRepositoryBD.delete(id);
    }
}