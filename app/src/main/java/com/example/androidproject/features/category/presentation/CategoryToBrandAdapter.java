package com.example.androidproject.features.category.presentation;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidproject.features.category.data.entity.CategoryEntity;
import com.example.androidproject.features.category.data.model.CategoryModel;

import java.util.List;

public class CategoryToBrandAdapter extends FragmentStateAdapter {
    private List<CategoryEntity> categoryList;

    public CategoryToBrandAdapter(@NonNull FragmentActivity fragment, List<CategoryEntity> categoryList) {
        super(fragment);
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return CategoryToBrandFragment.newInstance(categoryList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        Log.d("CategoryToBrandAdapter", "getItemCount: " + categoryList.size());
        return categoryList.size();
    }
}
