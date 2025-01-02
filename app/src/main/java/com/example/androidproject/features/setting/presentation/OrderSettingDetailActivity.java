package com.example.androidproject.features.setting.presentation;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.features.admin_manager.presentation.order.ListCheckoutItemAdapter;
import com.example.androidproject.features.admin_manager.presentation.widgets.ProductForDetailOrderAdminAdapter;
import com.example.androidproject.features.checkout.data.model.CheckoutModel;
import com.example.androidproject.features.checkout.usecase.CheckoutUseCase;
import com.example.androidproject.features.order.data.ProductDataForOrderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderSettingDetailActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private Button btnEdit;
    private CheckoutModel checkoutModelGlobal = new CheckoutModel();
    private CheckoutUseCase checkoutUseCase = new CheckoutUseCase();
    private String orderIdGlobal, statusGlobal;
    private TextView orderStatus;
    private Button btnUpdateStatus;
    private RecyclerView recycler_products_order_view;
    private ListCheckoutItemAdapter listCartItemAdapter;
    private Button button6_confirm, button7_cancel, button8_buy_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_setting_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView orderId = findViewById(R.id.tvOrderID);
        TextView orderDate = findViewById(R.id.tvOrderDate);
        TextView orderTotalPrice = findViewById(R.id.tvOrderTotalPrice);
        RecyclerView orderDetailRecyclerView = findViewById(R.id.recycler_products_order_view);
        orderStatus = findViewById(R.id.tvOrderStatus);
        recycler_products_order_view = findViewById(R.id.recycler_products_order_view);
        button6_confirm = findViewById(R.id.button6_confirm);
        button7_cancel = findViewById(R.id.button7_cancel);
        button8_buy_again = findViewById(R.id.button8_buy_again);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> finish());


        // Retrieve data from Intent
        orderId.setText(getIntent().getStringExtra("order_id"));
        orderIdGlobal = getIntent().getStringExtra("order_id");

        getCheckoutById();

        orderDate.setText(getIntent().getStringExtra("order_date"));
        orderTotalPrice.setText(getIntent().getStringExtra("order_total_price"));
        setStatusOrder(Objects.requireNonNull(getIntent().getStringExtra("order_status")));

        String order_status = getIntent().getStringExtra("order_status");

        if (order_status.equals("PENDING")) {
            button7_cancel.setVisibility(View.VISIBLE);
        } else if (order_status.equals("INTRANSIT")) {
            button6_confirm.setVisibility(View.VISIBLE);
            button7_cancel.setVisibility(View.VISIBLE);
        } else if (order_status.equals("SUCCESS")) {
            button8_buy_again.setVisibility(View.GONE);
            button6_confirm.setVisibility(View.GONE);
            button7_cancel.setVisibility(View.GONE);
        } else if (order_status.equals("FAILED")) {
            button6_confirm.setVisibility(View.GONE);
            button7_cancel.setVisibility(View.GONE);
            button8_buy_again.setVisibility(View.VISIBLE);
        }

        List<ProductDataForOrderModel> orderProductDataList = new ArrayList<>();

        ProductForDetailOrderAdminAdapter adapter = new ProductForDetailOrderAdminAdapter(orderProductDataList, this);
        orderDetailRecyclerView.setAdapter(adapter);
        orderDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        button6_confirm.setOnClickListener(v -> {
            checkoutUseCase.updateStatus(orderIdGlobal, "SUCCESS")
                    .thenAccept(r -> {
                        if (r.isRight()) {
                            setStatusOrder("SUCCESS");
                            button6_confirm.setVisibility(View.GONE);
                            button7_cancel.setVisibility(View.GONE);
                            button8_buy_again.setVisibility(View.GONE);
                        }
                    })
                    .exceptionally(e -> {
                        return null;
                    });
        });

        button7_cancel.setOnClickListener(v -> {
            checkoutUseCase.updateStatus(orderIdGlobal, "FAILED")
                    .thenAccept(r -> {
                        if (r.isRight()) {
                            setStatusOrder("FAILED");
                            button6_confirm.setVisibility(View.GONE);
                            button7_cancel.setVisibility(View.GONE);
                            button8_buy_again.setVisibility(View.VISIBLE);
                        }
                    })
                    .exceptionally(e -> {
                        return null;
                    });
        });

        button8_buy_again.setOnClickListener(v -> {
            checkoutUseCase.updateStatus(orderIdGlobal, "PENDING")
                    .thenAccept(r -> {
                        if (r.isRight()) {
                            setStatusOrder("PENDING");
                            button7_cancel.setVisibility(View.VISIBLE);
                            button8_buy_again.setVisibility(View.GONE);
                            button6_confirm.setVisibility(View.GONE);
                        }
                    })
                    .exceptionally(e -> {
                        return null;
                    });
        });
    }

    public void getCheckoutById() {

        checkoutUseCase.getCheckoutById(orderIdGlobal)
                .thenAccept(r -> {
                    if(r.isRight()) {
                        Log.d("DetailOrderAdminActivity", "Getting checkout by order id: " + orderIdGlobal);
                        checkoutModelGlobal = new CheckoutModel();
                        checkoutModelGlobal = r.getRight();

                        listCartItemAdapter = new ListCheckoutItemAdapter(checkoutModelGlobal.getProducts(), this);
                        recycler_products_order_view.setLayoutManager(new LinearLayoutManager(this));
                        recycler_products_order_view.setAdapter(listCartItemAdapter);
                    }
                    else {
                        Log.d("DetailOrderAdminActivity", "Error getting checkout by order id: " + orderIdGlobal);
                    }
                });
    }

    public void setStatusOrder(String status){
        if (status.equals("PENDING")) {
            orderStatus.setText("Đang xử lý");
            orderStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.pending_order_status));
        } else if (status.equals("INTRANSIT")) {
            orderStatus.setText("Đang giao");
            orderStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.delivery_order_status));
        } else if (status.equals("SUCCESS")) {
            orderStatus.setText("Thành công");
            orderStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.succes_order_status));
        } else if (status.equals("FAILED")) {
            orderStatus.setText("Thất bại");
            orderStatus.setBackground(ContextCompat.getDrawable(this, R.drawable.reject_order_status));
        }
    }
}