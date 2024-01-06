package com.example.apptravel4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apptravel4.model.Place;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Place> {
    private Context context;
    private List<Place> restaurantList;

    public RestaurantAdapter(Context context, List<Place> restaurantList) {
        super(context, R.layout.item_nhahang, restaurantList);
        this.context = context;
        this.restaurantList = restaurantList;
    }


    public int getItemCount() {
        return restaurantList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_nhahang, null);
        }

        // Lấy thông tin của nhà hàng tại vị trí position
        Place restaurant = restaurantList.get(position);

        // Hiển thị thông tin trong các TextView của mỗi mục
        TextView nameTextView = view.findViewById(R.id.txtTenNhaHang);
        TextView addressTextView = view.findViewById(R.id.txtDiaChi);
        TextView ratingTextView = view.findViewById(R.id.txtDanhGia);

        nameTextView.setText(restaurant.name);
        addressTextView.setText(restaurant.vicinity);
        ratingTextView.setText(String.valueOf(restaurant.rating));

        return view;
    }
}

