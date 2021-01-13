package com.mtg.newcookbook.presenter;

import android.util.Log;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.mtg.cookbook.model.Ingredients;
import com.mtg.cookbook.model.Recipe;

public interface Sender {
    void sendRecipe(Recipe recipe){
        RecipeFire recipeFire = returnRecipeFire(recipe);
        Log.d("Sender", recipeFire.id);
        Log.d("Sender", recipeFire.Name);
        Log.d("Sender", recipeFire.MainImagePath);

    }

    void sendIngredient(Ingredients ingredients){
        IngredientsFire ingredientsFire = returnIngredientFire(ingredients);
        
        

    }
}
