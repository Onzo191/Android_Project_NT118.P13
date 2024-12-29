package com.example.androidproject.features.product.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.androidproject.features.brand.data.model.BrandModel;
import com.google.firebase.Timestamp;

import java.util.List;

public class ProductEntity implements Parcelable {
    private String id;
    private String name;
    private List<String> images;
    private double price;
    private int stockQuantity;
    private String brandId;
    private String categoryId;
    private boolean hidden;
    private String description;
    private double rating;
    ProductOptions availableOptions;
    ProductOptions variants;
    List<ProductOption> options;
    private com.google.firebase.Timestamp createdAt;
    private com.google.firebase.Timestamp updatedAt;
    private BrandModel brand;

    public ProductEntity() {
    }

    public ProductEntity(String name, List<String> images, double price, int stockQuantity, String brandId, String categoryId, ProductOptions availableOptions, ProductOptions variants, String description) {
        this.name = name;
        this.images = images;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.hidden = false;
        this.availableOptions = availableOptions;
        this.variants = variants;
        this.description = description;
        this.createdAt = Timestamp.now();
        this.updatedAt = null;
        this.rating = 0;
    }

    public ProductEntity(String name, List<String> images, double price, int stockQuantity, String brandId, String categoryId, List<ProductOption> options, String description) {
        this.name = name;
        this.images = images;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.hidden = false;
        this.options = options;
        this.createdAt = Timestamp.now();
        this.updatedAt = null;
        this.rating = 0;
        this.description = description;
    }

    protected ProductEntity(Parcel in) {
        id = in.readString();
        name = in.readString();
        images = in.createStringArrayList();
        price = in.readDouble();
        stockQuantity = in.readInt();
        brandId = in.readString();
        categoryId = in.readString();
        hidden = in.readByte() != 0;
        description = in.readString();
        rating = in.readDouble();
        options = in.createTypedArrayList(ProductOption.CREATOR);
        createdAt = in.readParcelable(Timestamp.class.getClassLoader());
        updatedAt = in.readParcelable(Timestamp.class.getClassLoader());
        brand = in.readParcelable(BrandModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductEntity> CREATOR = new Parcelable.Creator<ProductEntity>() {
        @Override
        public ProductEntity createFromParcel(Parcel in) {
            return new ProductEntity(in);
        }

        @Override
        public ProductEntity[] newArray(int size) {
            return new ProductEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeStringList(images);
        dest.writeDouble(price);
        dest.writeInt(stockQuantity);
        dest.writeString(brandId);
        dest.writeString(categoryId);
        dest.writeByte((byte) (hidden ? 1 : 0));
        dest.writeString(description);
        dest.writeDouble(rating);
        dest.writeTypedList(options);
        dest.writeParcelable(createdAt, flags);
        dest.writeParcelable(updatedAt, flags);
        dest.writeParcelable((Parcelable) brand, flags);
    }

    public BrandModel getBrand() {
        return brand;
    }

    public void setBrand(BrandModel brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductOptions getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(ProductOptions availableOptions) {
        this.availableOptions = availableOptions;
    }

    public ProductOptions getVariants() {
        return variants;
    }

    public void setVariants(ProductOptions variants) {
        this.variants = variants;
    }

    public String getName() {
        return name;
    }

    public List<String> getImages() {
        return images;
    }

    public double getPrice() {
        return price;
    }

    public String getBrandId() {
        return brandId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addImageUrl(String imageUrl) {
        this.images.add(imageUrl);
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductOption> getOptions() {
        return options;
    }

    public void setOptions(List<ProductOption> options) {
        this.options = options;
    }
}
