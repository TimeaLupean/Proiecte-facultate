package org.example.main;

public class Category {
    private int category_id;
    private String type;

    public Category(int category_id, String type) {
        this.category_id = category_id;
        this.type = type;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                ", type='" + type + '\'' +
                '}';
    }
}
