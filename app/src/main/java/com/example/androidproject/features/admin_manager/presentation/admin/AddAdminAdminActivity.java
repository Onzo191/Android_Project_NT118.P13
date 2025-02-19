package com.example.androidproject.features.admin_manager.presentation.admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidproject.R;
import com.example.androidproject.core.credential.UserPreferences;
import com.example.androidproject.features.auth.data.entity.UserEntity;
import com.example.androidproject.features.auth.data.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AddAdminAdminActivity extends AppCompatActivity {
    private Button btnRegister;
    private ImageButton btnBack;
    private EditText etFirstName, etLastName, etEmail, etPhone, etPassword, etConfirmPassword;
    private RadioGroup rgGender;

    //others
    private AuthRepository authRepository;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_admin_admin);
        authRepository = new AuthRepository();
        userPreferences = new UserPreferences(this);

        initInsets();
        initViews();
        setupClickListeners();
    }


    private void initInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);

        etFirstName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etVerifiedPassword);
        rgGender = findViewById(R.id.rgGender);
    }

    private void setupClickListeners() {
        btnRegister.setOnClickListener(v -> handleRegister());
        btnBack.setOnClickListener(v -> finish());
    }

    private void handleRegister() {
        // form
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String gender = getSelectedGender();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (validateInput(firstName, lastName, email, phone, password, confirmPassword, gender)) {
            CompletableFuture<FirebaseUser> registrationFuture = authRepository.addNewAccount(userPreferences.getAccount(), userPreferences.getPassword(), email, password);

            registrationFuture
                    .thenCompose(firebaseUser -> {
                        // default
                        String id = "";
                        Integer role = 0; //admin
                        Integer tier = 0;
                        Long totalSpent = 0L;
                        String addressId = "";
                        String fullAddress = "";
                        List<String> vouchers = new ArrayList<>();
                        List<String> wishlist = new ArrayList<>();

                        UserEntity userEntity = new UserEntity(id, role, tier, totalSpent, addressId, fullAddress, firstName, lastName, gender, email, phone, vouchers, wishlist);

                        return authRepository.saveUserToFirestore(firebaseUser.getUid(), userEntity);
                    })
                    .thenRun(this::finish)
                    .exceptionally(ex -> {
                        Log.e("this", "Registration failed: " + ex.getMessage());
                        return null;
                    });
        }
    }


    private String getSelectedGender() {
        int selectedId = rgGender.getCheckedRadioButtonId();
        if (selectedId == R.id.rbMale) return "Male";
        if (selectedId == R.id.rbFemale) return "Female";
        if (selectedId == R.id.rbOther) return "Other";
        return null;
    }

    private boolean validateInput(String firstName, String lastName, String email, String phone, String password, String confirmPassword, String gender) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidPhone(phone)) {
            Toast.makeText(this, "Invalid phone", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidPassword(password)) {
            Toast.makeText(this, "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhone(String phone) {
        String regex = "^((\\+84)|0)([35789])[0-9]{8}$";
        return phone.matches(regex);
    }

    private boolean isValidPassword(String password) {
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*+\\-_?]).{6,}";
        return password.matches(regex);
    }

}