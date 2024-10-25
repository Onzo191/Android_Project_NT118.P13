package com.example.androidproject.features.home.usecase;

import com.example.androidproject.R;
import com.example.androidproject.features.banner.data.model.BannerModel;
import com.example.androidproject.features.brand.data.model.BrandModel;
import com.example.androidproject.features.category.data.model.CategoryModel;
import com.example.androidproject.features.product.data.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class HomeUseCase {
    public List<CategoryModel> getCategoriesList() {
        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("Laptop", R.drawable.image_laptop));
        categoryList.add(new CategoryModel("Phone", R.drawable.image_phone));
        categoryList.add(new CategoryModel("Controller", R.drawable.image_controller));
        categoryList.add(new CategoryModel("Monitor", R.drawable.image_monitor));
        categoryList.add(new CategoryModel("Keyboard", R.drawable.image_keyboard));

        return categoryList;
    }

    public List<BannerModel> getBannersList() {
        List<BannerModel> bannerList = new ArrayList<>();
        bannerList.add(new BannerModel(R.drawable.image_slider_1));
        bannerList.add(new BannerModel(R.drawable.image_slider_1));
        bannerList.add(new BannerModel(R.drawable.image_slider_1));

        return bannerList;
    }

    public List<ProductModel> getProductsList() {
        List<ProductModel> productList = new ArrayList<>();
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1), true));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));
        productList.add(new ProductModel("Legion 5 2021", R.drawable.image_product, 1299, 12, new BrandModel(1, "Lenovo", R.drawable.image_asus_logo,1),false));

        return productList;
    }

    public int getColumns(int col) {
        return col;
    }
}
