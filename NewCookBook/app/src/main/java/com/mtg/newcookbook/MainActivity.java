package com.mtg.newcookbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mtg.newcookbook.R;
import com.mtg.newcookbook.view.AddFragment;
import com.mtg.newcookbook.view.CaloriesFragment;
import com.mtg.newcookbook.view.LikedFragment;
import com.mtg.newcookbook.view.SearchFragment;
import com.mtg.newcookbook.view.SettingsFragment;

public class MainActivity extends AppCompatActivity {


    Fragment likedFragment = LikedFragment.newInstance();
    Fragment caloriesFragment = CaloriesFragment.newInstance();
    Fragment searchFragment = SearchFragment.newInstance();
    Fragment addFragment = AddFragment.newInstance();
    Fragment menuFragment = SettingsFragment.newInstance();


    private BottomNavigationView bottomNavigationView;


    private void setValues(){
        bottomNavigationView = findViewById(R.id.bottom_menu);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setValues();
        loadFragment(likedFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.to_favorite:{
                        loadFragment(likedFragment);
                        return true;
                    }
                    case R.id.to_eating_menu:{
                        loadFragment(caloriesFragment);
                        return true;
                    }
                    case R.id.to_search:{
                        loadFragment(searchFragment);
                        return true;
                    }
                    case R.id.to_add:{
                        loadFragment(addFragment);
                        return true;
                    }
                    case R.id.to_menu:{
                        loadFragment(menuFragment);
                    }
                }
                return false;
            }
        });

    }
}