package com.example.androidproject.features.category.data.model;

public class CategoryModel {
    private String name;
    private int image;

    public CategoryModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getCategoryName() {
        return name;
    }

    public int getCategoryImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
