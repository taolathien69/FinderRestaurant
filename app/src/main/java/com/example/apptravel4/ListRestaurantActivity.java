package com.example.apptravel4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptravel4.model.Place;
import com.example.apptravel4.model.PlacesApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListRestaurantActivity extends AppCompatActivity {
    private Button btnMap;
    private List<Place> restaurantList = new ArrayList<>();
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listnhahang);
        btnMap = findViewById(R.id.button11);

        // Khởi tạo Adapter và ListView
        adapter = new RestaurantAdapter(this, restaurantList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        callPlacesApi();

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRestaurantActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void callPlacesApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlacesApiService apiService = retrofit.create(PlacesApiService.class);

        Call<PlacesApiResponse> call = apiService.getNearbyRestaurants("latitude,longitude", 1000, "restaurant", "AIzaSyDAnlXnGXzsSdvwBR2qx84jgXAC4JZ4p7E");

        call.enqueue(new Callback<PlacesApiResponse>() {
            @Override
            public void onResponse(Call<PlacesApiResponse> call, Response<PlacesApiResponse> response) {
                if (response.isSuccessful()) {
                    List<Place> places = response.body().results;

                    // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                    adapter.clear();
                    adapter.addAll(places);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("APIError", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PlacesApiResponse> call, Throwable t) {
                Log.e("NetworkError", "Error: " + t.getMessage());
            }
        });
    }
}
