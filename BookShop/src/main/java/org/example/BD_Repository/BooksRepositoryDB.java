package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Author;
import org.example.main.Books;
import org.example.main.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksRepositoryDB {
    private SqlServer sqlServer;

    public BooksRepositoryDB(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(Books book){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Books(BookId, Title, PublishingYear, Price, AuthorID, CategoryID) VALUES (?,?,?,?,?,?)")){

                preparedStatement.setInt(1,book.getBook_id());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setInt(3,book.getPublishing_year());
                preparedStatement.setInt(4,book.getPrice());
                preparedStatement.setInt(5,book.getAuthor().getAuthor_id());
                preparedStatement.setInt(6,book.getCategory().getCategory_id());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Books createBookFromResultSet(ResultSet resultSet) throws SQLException {
        AuthorRepositoryBD authorRepositoryBD = new AuthorRepositoryBD(sqlServer);
        CategoryRepositoryBD categoryRepositoryBD = new CategoryRepositoryBD(sqlServer);
        int id = resultSet.getInt("BookId");
        String title = resultSet.getString("Title");
        int publishingYear = resultSet.getInt("PublishingYear");
        int price = resultSet.getInt("Price");
        int authorid = resultSet.getInt("AuthorID");
        int categoryid = resultSet.getInt("CategoryID");
        Author author = authorRepositoryBD.findByID(authorid);
        Category category = categoryRepositoryBD.findByID(categoryid);
        Books book = new Books(id,title,publishingYear,author,price,category);
        return book;
    }

    public List<Books> loadFromDB(){
        List<Books> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Books";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createBookFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Books findByID(int id){
//        List<Books> allBooks = loadFromDB();
//        Books found = null;
//        for(Books book : allBooks){
//            if(book.getBook_id() == id)
//                found = book;
//        }
//
//        return found;
//    }

    public Books findByID(int id){
        String sql = "SELECT * FROM labor.Books WHERE BookId = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createBookFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updatePrice(int Id, int newPrice) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE labor.Books SET Price = ? WHERE BookId = ?")) {

            preparedStatement.setInt(1, newPrice);
            preparedStatement.setInt(2, Id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Books WHERE BookId = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
