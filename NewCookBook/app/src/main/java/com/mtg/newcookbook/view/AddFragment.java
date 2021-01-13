package com.mtg.newcookbook.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtg.newcookbook.R;

public class AddFragment extends Fragment {
    private lateinit FloatingActionButton addingBtn;
    private lateinit RecyclerView added;
    private lateinit Context mcontext;
    private lateinit ArrayList<Recipe> addedList;
    private lateinit RecipeAdapter addedAdapter;
    public AddFragment() {
        // Required empty public constructor;
    }
    
    override fun onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    override void onActivityResult(Int requestCode, Int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Realm realm = Realm.getDefaultInstance();
        switch(requestCode){
            REQUEST_ACCESS ->{
                Log.d(TAG, "сообщение пришло")
                if (data != null) {
                    addedList.add(searchRecipeByID(data.getStringExtra("id")));
                    Category res = addedList[addedList.size-1].Category();
                    Log.d(TAG, "${res.category} + ${res.weight}");
                    addedAdapter.notifyItemInserted(addedList.size-1);
                }

            }
        }

    }
}