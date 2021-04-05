package com.example.planmytrip.ui.hotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.planmytrip.R;

public class HotelFragment extends Fragment {

    private HotelViewModel hotelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotelViewModel =
                new ViewModelProvider(this).get(HotelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hotel, container, false);
        System.out.println(root.toString());

        return root;
    }
}