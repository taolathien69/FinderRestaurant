package com.example.apptravel4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnMap, btnNhaHang, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        btnMap = findViewById(R.id.buttonMap);
        btnNhaHang =findViewById(R.id.buttonNhaHang);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến MapActivity khi nhấn nút "Xem vị trí"
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        btnNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến MapActivity khi nhấn nút "Xem vị trí"
                Intent intent = new Intent(MainActivity.this, ListRestaurantActivity.class);
                startActivity(intent);
            }
        });
        // Khai báo và ánh xạ Button đăng xuất
        btnLogout = findViewById(R.id.buttonLogout);

        // Thiết lập sự kiện click cho Button đăng xuất
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi phương thức xử lý đăng xuất
                logout();
            }
        });
    }
    private void logout() {
        // Thực hiện các bước cần thiết để đăng xuất, ví dụ: xóa thông tin đăng nhập, chuyển đến màn hình đăng nhập, v.v.
        // Ví dụ đơn giản: Chuyển đến màn hình đăng nhập (LoginActivity)
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Đóng MainActivity để ngăn người dùng quay lại khi nhấn nút Back
    }
}