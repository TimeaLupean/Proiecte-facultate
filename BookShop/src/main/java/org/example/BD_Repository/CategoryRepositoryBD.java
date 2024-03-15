package org.example.BD_Repository;

import org.example.SqlServer;
import org.example.main.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CategoryRepositoryBD {
    private SqlServer sqlServer;

    public CategoryRepositoryBD(SqlServer sqlServer) {
        this.sqlServer = sqlServer;
    }

    public void saveIntoDB(Category category){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO labor.Category(CategoryID, Type) VALUES (?,?)")){

                preparedStatement.setInt(1,category.getCategory_id());
                preparedStatement.setString(2,category.getType());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Category createCategoryFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("CategoryID");
        String type = resultSet.getString("Type");

        Category category = new Category(id,type);
        return category;
    }

    public List<Category> loadFromDB(){
        List<Category> result = new ArrayList<>();
        try (Connection connection = sqlServer.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM labor.Category";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    result.add(createCategoryFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public Category findByID(int id){
//        List<Category> allCategories = loadFromDB();
//        Category found = null;
//        for(Category category:allCategories){
//            if(category.getCategory_id() == id)
//                found = category;
//        }
//
//        return found;
//    }

    public Category findByID(int id){
        String sql = "SELECT * FROM labor.Category WHERE CategoryID = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createCategoryFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Category findByType(String type){
        String sql = "SELECT * FROM labor.Category WHERE Type = ?";
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createCategoryFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public Category findByType(String type){
//        List<Category> allCategories = loadFromDB();
//        Category found = null;
//        for(Category category:allCategories){
//            if(category.getType().equals(type))
//                found = category;
//        }
//
//        return found;
//    }

    public void delete(int id){
        try (Connection connection = sqlServer.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM labor.Category WHERE CategoryID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
