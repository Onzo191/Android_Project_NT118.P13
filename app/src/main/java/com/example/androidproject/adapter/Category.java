package com.example.androidproject.adapter;

public class Category {
    private String name;
    private int image;

    public Category(String name, int image) {
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
