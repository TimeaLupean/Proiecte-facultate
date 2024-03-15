package org.example.repositories;
import org.example.main.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private List<Category> categories = new ArrayList<>();

    public CategoryRepository(List<Category> categories) {
        this.categories = categories;
    }

    public Category findById(int targetCategoryId) {
        for (Category category : categories) {
            if (category.getCategory_id() == targetCategoryId) {
                return category;
            }
        }
        System.out.println("Could not find category with Id: " + targetCategoryId);
        return null;
    }

    public Category findByType(String type){
        for (Category category : categories) {
            if (category.getType() == type) {
                return category;
            }
        }
        System.out.println("Could not find category type ");
        return null;
    }

    public List<Category> findAll() {
        if (categories.isEmpty()) {
            System.out.println("There was no category found");
            return null;
        }
        return categories;
    }


    public boolean save(Category category) {
        boolean saved = false;
        for (Category item : categories) {
            if (category.getCategory_id() == item.getCategory_id()) {
                System.out.println("category already exists");
                return saved;
            }

        }
        categories.add(category);
        for (Category item : categories) {
            if (category.getCategory_id() == item.getCategory_id())
                saved = true;
        }

        return saved;
    }

    public boolean update(Category updatedCategory) {
        boolean updated = false;
        for (Category category : categories) {
            if (category.getCategory_id() == updatedCategory.getCategory_id()) {
                category.setType(updatedCategory.getType());
                updated = true;
                break;
            }
        }
        if (updated == false)
            System.out.println("Category was not updated");

        return updated;
    }

    public boolean delete(int targetCategoryId) {
        boolean deleted = false;
        Category categoryToRemove = null;
        for (Category category : categories) {
            if (category.getCategory_id() == targetCategoryId) {
                categoryToRemove = category;
                break;
            }
        }
        if (categoryToRemove == null) {
            System.out.println("category does not exist");
            deleted = false;
        }
        if (categoryToRemove != null) {
            categories.remove(categoryToRemove);
            deleted = true;
        }
        return deleted;


    }
}