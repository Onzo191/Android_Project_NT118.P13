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
        categoryList.add(new CategoryModel("1","Laptop", R.drawable.image_laptop, "Laptop description"));
        categoryList.add(new CategoryModel("2","Phone", R.drawable.image_phone, "Phone description"));
        categoryList.add(new CategoryModel("3","Controller", R.drawable.image_controller, "Controller description"));
        categoryList.add(new CategoryModel("4","Monitor", R.drawable.image_monitor, "Monitor description"));
        categoryList.add(new CategoryModel("5","Keyboard", R.drawable.image_keyboard, "Keyboard description"));

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

    public List<String> getProductDetailImgList() {
        List<String> productDetailImgList = new ArrayList<>();
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");
        productDetailImgList.add("https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg");

        return productDetailImgList;
    }

    public List<String> getColorList() {
        List<String> colorsList = new ArrayList<>();
        colorsList.add("#FF0000");
        colorsList.add("#00FF00");
        colorsList.add("#0000FF");
        colorsList.add("#FFFF00");
        colorsList.add("#FF00FF");
        colorsList.add("#00FFFF");
        colorsList.add("#000000");
        colorsList.add("#FFFFFF");

        return colorsList;
    }

    public List<String> getOptionsList() {
        List<String> optionsList = new ArrayList<>();
        optionsList.add("16GB");
        optionsList.add("32GB");
        optionsList.add("64GB");
        optionsList.add("128GB");

        return optionsList;
    }

    public String getImgProductDetail() {
        return "https://laptopaz.vn/media/lib/2413_laptopaz-lenovo-ideapad-3-15ITL05-3.jpg";
    }

    public int getColumns(int col) {
        return col;
    }
}
