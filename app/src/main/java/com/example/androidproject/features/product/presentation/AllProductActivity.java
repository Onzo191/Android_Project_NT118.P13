package com.example.androidproject.features.product.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.core.utils.ItemData;
import com.example.androidproject.features.brand.data.model.BrandModel;
import com.example.androidproject.features.brand.usecase.BrandUseCase;
import com.example.androidproject.features.category.data.model.CategoryModel;
import com.example.androidproject.features.category.usecase.CategoryUseCase;
import com.example.androidproject.features.product.data.model.ProductModelFB;
import com.example.androidproject.features.product.usecase.ProductUseCase;

import java.util.ArrayList;
import java.util.List;

public class AllProductActivity extends AppCompatActivity {
    private Spinner categorySpinner;
    private Spinner brandSpinner;
    private RecyclerView rvAllProduct;
    private ImageView img_search;
    private EditText edt_search;
    private ProductUseCase productUseCase = new ProductUseCase();
    private CategoryUseCase categoryUseCase = new CategoryUseCase();
    private BrandUseCase brandUseCase = new BrandUseCase();
    private String categoryId = null;
    private String brandId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_product);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        img_search = findViewById(R.id.img_search);
        edt_search = findViewById(R.id.edt_search);

        getBrandIntent();
        getCategoryIntent();
        setSpinnerValues();

        fetchProducts(categoryId, brandId, null);

        img_search.setOnClickListener(v -> {
           String search = edt_search.getText().toString();
              fetchProducts(categoryId, brandId, search);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setSpinnerValues() {
        List<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel(
                "Tất cả danh mục",
                "",
                ""
        ));

        List<BrandModel> brandList = new ArrayList<>();
        brandList.add(new BrandModel(
                "Tất cả thương hiệu",
                "",
                ""
        ));

        ArrayAdapter<CategoryModel> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<BrandModel> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, brandList);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner = findViewById(R.id.spinnerCategory);
        brandSpinner = findViewById(R.id.spinnerBrand);

        categoryUseCase.getCategoryListForAllProduct()
                        .thenAccept(r -> {
                            if(r.isRight()) {
                                categoryAdapter.addAll(r.getRight());
                                categorySpinner.setAdapter(categoryAdapter);
                            }
                        });

        brandUseCase.getBrandListForAllProduct()
                        .thenAccept(r -> {
                            if(r.isRight()) {
                                brandAdapter.addAll(r.getRight());
                                brandSpinner.setAdapter(brandAdapter);
                            }
                        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryId = categoryList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                brandId = brandList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fetchProducts(String categoryId, String brandId, String search) {
        productUseCase.getAllProducts(
                categoryId,
                brandId,
                search,
                null,
                "10"
        ).thenAccept(r -> {
            if(r.isRight()) {
                List<ProductModelFB> productList = r.getRight();
                rvAllProduct = findViewById(R.id.rvAllProduct);
                rvAllProduct.setAdapter(new AllProductListAdapter(productList, this));
                rvAllProduct.setLayoutManager(new GridLayoutManager(this, 2));
            }
        });
    }

    private void getBrandIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("brandId")) {
            brandId = intent.getStringExtra("brandId");
        }
    }

    private void getCategoryIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("categoryId")) {
            categoryId = intent.getStringExtra("categoryId");
        }
    }
}