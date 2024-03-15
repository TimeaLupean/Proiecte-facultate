package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Books;
import org.example.main.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherRepositoryBD {

    private SqlServer sqlServer;

    public PublisherRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(Publisher publisher) {
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Publishers(PublisherID, Name, Address, FiscalCode) VALUES (?,?,?,?)")) {

                preparedStatement.setInt(1, publisher.getPublisher_id());
                preparedStatement.setString(2, publisher.getName());
                preparedStatement.setString(3, publisher.getAddress());
                preparedStatement.setInt(4, publisher.getFiscal_code());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO labor.Books_Publishers(BookId, PublisherID) VALUES (?,?)")) {

                List<Books> books = publisher.getBooks();
                for (Books book : books) {
                    preparedStatement2.setInt(1, book.getBook_id());
                    preparedStatement2.setInt(2, publisher.getPublisher_id());
                }

            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Publisher createPublisherFromResultSet(ResultSet resultSet) throws SQLException {
        BooksRepositoryDB booksRepositoryDB = new BooksRepositoryDB(sqlServer);
        int id = resultSet.getInt("PublisherID");
        String name = resultSet.getString("Name");
        String address = resultSet.getString("Address");
        int fiscalCode = resultSet.getInt("FiscalCode");

        List<Books> books = loadFromDBIntermediaryTable(id);
        Publisher publisher = new Publisher(id, name, address, fiscalCode,books);
        return publisher;
    }

    public Books createBooks_PublishersFromResultSet(ResultSet resultSet) throws SQLException {
        BooksRepositoryDB booksRepositoryDB = new BooksRepositoryDB(sqlServer);
        int publisherId = resultSet.getInt("PublisherID");
        int bookId = resultSet.getInt("PublisherID");

        Books book = booksRepositoryDB.findByID(bookId);
        return book;
    }

    public List<Books> loadFromDBIntermediaryTable(int id) {
        List<Books> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM labor.Books_Publishers WHERE PublisherId = ?");
        ) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(createBooks_PublishersFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Publisher> loadFromDB(){
        List<Publisher> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Publishers";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createPublisherFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Publisher findByID(int id){
//        List<Publisher> allPublishers = loadFromDB();
//        Publisher found = null;
//        for(Publisher publisher:allPublishers){
//            if(publisher.getPublisher_id() == id)
//                found = publisher;
//        }
//
//        return found;
//    }

    public Publisher findByID(int id){
        String sql = "SELECT * FROM labor.Publishers WHERE PublisherId = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createPublisherFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Publishers WHERE PublisherID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Books_Publishers WHERE PublisherID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}