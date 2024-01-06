package com.example.apptravel4.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public int numOfTabs;
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity,int numOfTabs) {
        super(fragmentActivity);
        this.numOfTabs = numOfTabs;
    }

    public FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentDangNhap();
            case 1:
                return new FragmentDangKy();

            default:
                return null;
        }
        //return null;
    }

    @Override
    public int getItemCount() {
        return numOfTabs;
    }
}