package com.example.androidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewholder> {
    private List<Category> categories;
    private Context context;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categories, parent, false);
        return new CategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewholder holder, int position) {
        Category category = categories.get(position);
        holder.categoryName.setText(category.getCategoryName());
        holder.categoryImage.setImageResource(category.getCategoryImage());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewholder extends  RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.imageViewCategory);
            categoryName = itemView.findViewById(R.id.textViewCategory);
        }
    }
}
