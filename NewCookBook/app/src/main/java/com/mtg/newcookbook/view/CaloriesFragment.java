package com.mtg.newcookbook.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtg.newcookbook.R;

public class CaloriesFragment extends Fragment {
    public CaloriesFragment() {
        // Required empty public constructor
    }
    public static CaloriesFragment newInstance() {
        return new CaloriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calories, container, false);
    }
}