package org.example.BD_Controller;

import org.example.BD_Repository.CategoryRepositoryBD;
import org.example.main.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryControllerBD {
    private CategoryRepositoryBD categoryRepositoryBD;

    public CategoryControllerBD(CategoryRepositoryBD CategoryRepositoryBD) {
        this.categoryRepositoryBD = CategoryRepositoryBD;
    }

    public void saveIntoDB(Category Category){
        categoryRepositoryBD.saveIntoDB(Category);
    }

    public Category createCategoryFromResultSet(ResultSet resultSet) throws SQLException{
        return categoryRepositoryBD.createCategoryFromResultSet(resultSet);
    }

    public List<Category> loadFromDB(){
        return categoryRepositoryBD.loadFromDB();
    }

    public Category findById(int id){
        return categoryRepositoryBD.findByID(id);
    }

    public Category findByType(String type){
        return categoryRepositoryBD.findByType(type);
    }

    public void delete(int id){
        categoryRepositoryBD.delete(id);
    }
}