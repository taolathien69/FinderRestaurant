package com.example.apptravel4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptravel4.database.MyDatabase;
import com.example.apptravel4.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, editTextTextEmailAddress;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);
        TextView textViewDangKy = findViewById(R.id.textView3);
        usernameEditText = findViewById(R.id.editTextText);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        registerButton = findViewById(R.id.button);
        textViewDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignActivity();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidEmail(editTextTextEmailAddress.getText().toString().trim())) {
                    // Thực hiện kiểm tra và thêm người dùng mới vào cơ sở dữ liệu ở đây
                    if (registerUser()) {
                        // Nếu đăng ký thành công, chuyển đến LoginActivity hoặc MainActivity tùy thuộc vào yêu cầu
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Đóng RegisterActivity
                    } else {
                        // Hiển thị thông báo lỗi nếu đăng ký không thành công
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Hiển thị thông báo lỗi về định dạng email không hợp lệ
                    Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                }
            }
        });

//                // Thực hiện kiểm tra và thêm người dùng mới vào cơ sở dữ liệu ở đây
//                if (registerUser()) {
//                    // Nếu đăng ký thành công, chuyển đến LoginActivity hoặc MainActivity tùy thuộc vào yêu cầu
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish(); // Đóng RegisterActivity
//                } else {
//                    // Hiển thị thông báo lỗi nếu đăng ký không thành công
//                    // Ví dụ: Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
    public void openSignActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean registerUser() {
        // Lấy thông tin từ EditText
        final String username = usernameEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String email = editTextTextEmailAddress.getText().toString().trim();

        // Kiểm tra xem có dữ liệu đầu vào hợp lệ không
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            // Hiển thị thông báo lỗi nếu dữ liệu không hợp lệ
            Toast.makeText(RegisterActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Sử dụng AsyncTask để thực hiện truy cập cơ sở dữ liệu trong một luồng riêng biệt
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                // Thực hiện kiểm tra và thêm người dùng mới vào cơ sở dữ liệu ở đây, sử dụng SQLite
                // Trả về true nếu đăng ký thành công, ngược lại trả về false
                User newUser = new User();
                newUser.username = username;
                newUser.password = password;
                newUser.email = email;

                // Thực hiện thêm người dùng mới vào cơ sở dữ liệu
                MyDatabase.getInstance(RegisterActivity.this).userDao().insert(newUser);

                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                super.onPostExecute(isSuccess);

                // Xử lý kết quả sau khi hoàn thành
                if (isSuccess) {
                    // Nếu đăng ký thành công, chuyển đến LoginActivity hoặc MainActivity tùy thuộc vào yêu cầu
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Đóng RegisterActivity
                } else {
                    // Hiển thị thông báo lỗi nếu đăng ký không thành công
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

        return true; // Trả về true vì AsyncTask không trả về giá trị (Void)
    }
}

