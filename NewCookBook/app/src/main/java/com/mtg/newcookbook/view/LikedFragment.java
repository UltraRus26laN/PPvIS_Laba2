package com.mtg.newcookbook.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtg.newcookbook.R;

public class LikedFragment extends Fragment {
    lateinit RecyclerView likedList;
    lateinit ArrayList<Recipe> recipesList;
    lateinit RecipeAdapter recipeAdapter;
    lateinit Context mcontext;
    public LikedFragment() {
        // Required empty public constructor
    }
    public static LikedFragment newInstance() {
        return new LikedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         Realm realm = Realm.getDefaultInstance()
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liked, container, false);
        likedList = view.findViewById(R.id.likedList);
        recipesList = mutableListOf();
        recipesList.addAll(realm.where<Recipe>().findAll());
        recipeAdapter = RecipeAdapter(mcontext, recipesList, this);
        likedList.adapter = recipeAdapter;
        return inflater.inflate(R.layout.fragment_liked, container, false);
    }
}