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

import com.example.apptravel4.dao.UserDao;
import com.example.apptravel4.database.MyDatabase;
import com.example.apptravel4.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        TextView textViewDangKy = findViewById(R.id.textViewDangKy);
        usernameEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.buttonDN);
        textViewDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isValidEmail(usernameEditText.getText().toString().trim())) {
//                    // Thực hiện kiểm tra đăng nhập ở đây
//                    if (checkLogin()) {
//                        // Nếu đăng nhập thành công, chuyển đến MainActivity
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish(); // Đóng LoginActivity
//                    } else {
//                        // Hiển thị thông báo lỗi nếu đăng nhập không thành công
//                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // Hiển thị thông báo lỗi về định dạng email không hợp lệ
//                    Toast.makeText(LoginActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

                // Thực hiện kiểm tra đăng nhập ở đây
                if (checkLogin()) {
                    // Nếu đăng nhập thành công, chuyển đến MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Đóng LoginActivity
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
//
private boolean checkLogin() {
    final String username = usernameEditText.getText().toString().trim();
    final String password = passwordEditText.getText().toString().trim();

    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
        Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        return false;
    }

    new AsyncTask<Void, Void, Boolean>() {
        @Override
        protected Boolean doInBackground(Void... voids) {
            // Kiểm tra thông tin đăng nhập trong cơ sở dữ liệu
            UserDao userDao = MyDatabase.getInstance(LoginActivity.this).userDao();
            User user = userDao.login(username, password);

            if (user != null) {
                // Đăng nhập thành công
                return true;
            } else {
                // Đăng nhập thất bại
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isSuccess) {
            super.onPostExecute(isSuccess);

            if (isSuccess) {
                // Đăng nhập thành công
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Đóng LoginActivity
            } else {
                // Đăng nhập thất bại
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }.execute();

    return true;
}

}

