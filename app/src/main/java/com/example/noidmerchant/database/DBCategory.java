package com.example.noidmerchant.database;

public class DBCategory {
    public static String categoryName;

    public DBCategory(String categoryName) {
        DBCategory.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        DBCategory.categoryName = categoryName;
    }
}
