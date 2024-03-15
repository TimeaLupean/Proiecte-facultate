package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryBD {
    private SqlServer sqlServer;

    public AuthorRepositoryBD( ) {

    }

    public AuthorRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(Author author){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Author(AuthorID, FirstName, LastName, BirthDate, Address) VALUES (?,?,?,?,?)")){

                     preparedStatement.setInt(1,author.getAuthor_id());
                     preparedStatement.setString(2,author.getFirstName());
                     preparedStatement.setString(3,author.getLastName());
                     preparedStatement.setString(4,author.getBirth_date());
                     preparedStatement.setString(5,author.getAddress());

                 preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Author createAuthorFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("AuthorID");
        String fname = resultSet.getString("FirstName");
        String lname = resultSet.getString("LastName");
        String bdate = resultSet.getString("BirthDate");
        String address = resultSet.getString("Address");

        Author author = new Author(id,fname,lname,bdate,address);
        return author;
    }

    public List<Author> loadFromDB(){
        List<Author> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Author";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createAuthorFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Author findByID(int id){
//        List<Author> allAuthors = loadFromDB();
//        Author found = null;
//        for(Author author: allAuthors){
//            if(author.getAuthor_id() == id)
//                found = author;
//        }
//
//        return found;
//    }
//
//    public Author findByName(String fname, String lname){
//        List<Author> allAuthors = loadFromDB();
//        Author found = null;
//        for(Author author: allAuthors){
//            if(author.getFirstName().equals(fname) && author.getLastName().equals(lname))
//                found = author;
//        }
//
//        return found;
//    }
public Author findByID(int id){
    String sql = "SELECT * FROM labor.Author WHERE AuthorID = ?";
    try (Connection connection = sqlServer.connect();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return createAuthorFromResultSet(resultSet);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

    public Author findByName(String fname, String lname){
        String sql = "SELECT * FROM labor.Author WHERE FirstName = ? AND LastName = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createAuthorFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no author is found
    }

    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Author WHERE AuthorID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
