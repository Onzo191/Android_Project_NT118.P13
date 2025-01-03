package com.example.androidproject.features.admin_manager.presentation.category;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.androidproject.R;

public class DetailCategoryAdminActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_category_admin);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView categoryID = findViewById(R.id.tvCategoryID);
        TextView categoryName = findViewById(R.id.tvCategoryName);
        TextView categoryQuantity = findViewById(R.id.tvCategoryQuantity);
        TextView categoryDescription = findViewById(R.id.tvCategoryDescription);
        ImageView categoryImage = findViewById(R.id.ivCategoryImage);

        setupButtons();

        categoryID.setText(getIntent().getStringExtra("category_id"));
        categoryName.setText(getIntent().getStringExtra("category_name"));
        categoryQuantity.setText(getIntent().getStringExtra("category_quantity"));
        categoryDescription.setText("helo world");

        Glide.with(this)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7U_69OLMV-mASOOC1CdFjJ50-yUmU5hv5UQ&s")
                .into(categoryImage);
    }

    private void setupButtons() {
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        btnEdit = findViewById(R.id.btn_admin_category_edit);
        btnEdit.setOnClickListener(v -> {
            Toast.makeText(this, "Edit button clicked", Toast.LENGTH_SHORT).show();
        });
    }
}