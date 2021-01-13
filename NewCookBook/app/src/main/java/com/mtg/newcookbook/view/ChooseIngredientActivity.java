package com.mtg.newcookbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mtg.newcookbook.R;

public class ChooseIngredientActivity extends AppCompatActivity {
    lateinit TextInputEditText searchIngredientText;
    lateinit FloatingActionButton searchIngredientBtn;
    lateinit ProgressBar progressBar;
    lateinit MaterialTextView progressText;
    lateinit FloatingActionButton addBtn;
    lateinit MaterialTextView nothingText;
    lateinit RecyclerView ingredients;
    lateinit IngredientsAdapter ingredientsAdapter;
    lateinit MutableList<Ingredients> ingredientList;
    Int pos = 999999;
    Int ADD_INGREDIENT_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ingredient);
        initialize()

        intent arguments = intent.extras;
        if (arguments != null){
            pos = intent.getIntExtra("position", 999999);
        }

        searchIngredientBtn.setOnClickListener{
            nothingText.visibility = View.INVISIBLE;
            LoadingView(true);
            ingredientList.clear();
            String results = SearchIngredientByName(searchIngredientText.text.toString())
            ingredientList.addAll(results);
            LoadingView(false);
            if (results.size == 0){
                nothingText.visibility = View.VISIBLE;
            }
            for (i=0; i<ingredientList.size; i++){
                Log.d("ChooseIngredient", ingredientList[i].Name);
                ingredientsAdapter.notifyItemInserted(i);
            }
        }
        addBtn.setOnClickListener{
            Intent addIngrIntent = Intent(this, AddIngredientActivity::class.java);
            startActivityForResult(addIngrIntent, ADD_INGREDIENT_CODE);
        }

    }
        void initialize(){
        searchIngredientText = findViewById(R.id.searchIngredientText);
        searchIngredientBtn = findViewById(R.id.searchIngredientBtn);
        progressBar = findViewById(R.id.searchIngredientProgressBar);
        progressText = findViewById(R.id.textLoadingIngredient);
        addBtn = findViewById(R.id.addIngredientBtn);
        nothingText = findViewById(R.id.nothingFound);
        ingredients = findViewById(R.id.ingredientSearchList);
        ingredientList = mutableListOf();
        ingredientsAdapter = IngredientsAdapter(this, ingredientList, this);
        ingredients.adapter = ingredientsAdapter;
    }

    void LoadingView(Boolean viewable){
        if (viewable){
            progressBar.visibility = View.VISIBLE;
            progressText.visibility = View.VISIBLE;
        }
        else {
            progressBar.visibility = View.INVISIBLE;
            progressText.visibility = View.INVISIBLE;
        }
    }

    override void onIngredientClick(Int position) {
        Intent intent = Intent(this, IngredientsActivity::class.java)
        intent.putExtra("id", ingredientList[position].ID)
        intent.putExtra("name", ingredientList[position].Name);
        if (pos!= 999999) {
            intent.putExtra("position", pos);
            Log.d("ChooseActivity", "$pos");
        }
        else{
            intent.putExtra("position", 0);
            Log.d("ChooseActivity", "position doesn't changed");
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    override void onActivityResult(Int requestCode, Int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            ADD_INGREDIENT_CODE -> {
                if(resultCode == RESULT_OK){
                    Intent intent = Intent(this, IngredientsActivity::class.java);
                    if (data != null) {
                        Log.d("ChooseActivity", data.getStringExtra("id"));
                        intent.putExtra("id", data.getStringExtra("id"));
                        intent.putExtra("name", data.getStringExtra("name"));
                        intent.putExtra("position", pos);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            }
        }
    }
}