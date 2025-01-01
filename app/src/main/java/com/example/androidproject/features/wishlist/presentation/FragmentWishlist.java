package com.example.androidproject.features.wishlist.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.core.errors.Failure;
import com.example.androidproject.features.product.data.entity.ProductEntity;
import com.example.androidproject.features.wishlist.data.repository.WishlistRepository;

import java.util.List;

public class FragmentWishlist extends Fragment {

    // views
    private RecyclerView recyclerView;
    private LinearLayout llEmptyWishlist;

    //others
    private Bundle userDataBundle;
    private List<ProductEntity> wishlistItems;
    private List<String> wishlist;
    private WishlistAdapter wishlistAdapter;
    private WishlistRepository wishlistRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wishlistRepository = new WishlistRepository();
        if (getArguments() != null) {
            userDataBundle = getArguments();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        initViews(view);

        loadWishlist();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rcWishList);
        llEmptyWishlist = view.findViewById(R.id.llEmptyWishlist);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void loadWishlist() {
        String userId = userDataBundle.getString("uid");

        wishlistRepository.getWishlist(userId)
                .thenAccept(either -> {
                    if (either.isRight()) {
                        wishlist = either.getRight();

                        if (wishlist.isEmpty()) {
                            showEmptyWishlist();
                        } else {
                            fetchProductDetails();
                        }
                    } else {
                        showError(either.getLeft());
                    }
                })
                .exceptionally(e -> {
                    showError(new Failure("An error occurred"));
                    return null;
                });
    }

    private void fetchProductDetails() {
        wishlistRepository.getProductsByIds(wishlist)
                .thenAccept(either -> {
                    if (either.isRight()) {
                        List<ProductEntity> products = either.getRight();
                        if (products.isEmpty()) {
                            showEmptyWishlist();
                        } else {
                            wishlistItems = products;
                            showWishlist();
                        }
                    } else {
                        showError(either.getLeft());
                    }
                })
                .exceptionally(e -> {
                    showError(new Failure("An error occurred while fetching products"));
                    return null;
                });
    }

    private void showError(Failure failure) {
        Log.e("FragmentWishlist", "Error: " + failure.getErrorMessage());
    }

    private void showEmptyWishlist() {
        recyclerView.setVisibility(View.GONE);
        llEmptyWishlist.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showWishlist() {
        recyclerView.setVisibility(View.VISIBLE);
        llEmptyWishlist.setVisibility(View.GONE);

        if (wishlistAdapter == null) {
            wishlistAdapter = new WishlistAdapter(getContext(), wishlistItems, this::onAddToCart, this::onRemoveFromWishlist);
            recyclerView.setAdapter(wishlistAdapter);
        } else {
            wishlistAdapter.notifyDataSetChanged();
        }
    }

    private void onAddToCart(ProductEntity item) {
        Toast.makeText(getContext(), item.getId(), Toast.LENGTH_SHORT).show();
    }

    //only remove
    @SuppressLint("NotifyDataSetChanged")
    private void onRemoveFromWishlist(String productId) {
        //remove id and detail
        wishlist.remove(productId);

        for (int i = 0; i < wishlistItems.size(); i++) {
            if (wishlistItems.get(i).getId().equals(productId)) {
                wishlistItems.remove(i);
                break;
            }
        }

        String userId = userDataBundle.getString("uid");

        wishlistRepository.updateWishlist(userId, wishlist)
                .thenAccept(either -> {
                    if (either.isRight()) {
                        wishlistAdapter.notifyDataSetChanged();
                        if (wishlist.isEmpty()) {
                            showEmptyWishlist();
                        }
                        Toast.makeText(getContext(), "Product removed from Wishlist", Toast.LENGTH_SHORT).show();
                    } else {
                        showError(either.getLeft());
                    }
                })
                .exceptionally(e -> {
                    Log.e("FragmentWishlist", "Error: " + e.getMessage());
                    return null;
                });
    }
}