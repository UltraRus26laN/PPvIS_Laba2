package com.mtg.newcookbook.presenter;

import com.google.firebase.database.FirebaseDatabase;
import com.mtg.cookbook.model.Ingredients;
import com.mtg.cookbook.model.Recipe;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.*;

public interface IngredientDataBase {
    void SearchIngredientByName(String name): RealmResults<Ingredients>{
        Realm realm = Realm.getDefaultInstance();
        return realm.where<Ingredients>().equalTo("Name", name).findAll();
    }

    void SearchIngredientByID(String id): Ingredients{
        Realm realm = Realm.getDefaultInstance();
        return realm.where<Ingredients>().equalTo("ID", id).findFirst();
    }

    void AddIngredient(String name, Int calories, ByteArray image, String imagePath, String description): String{
        Realm realm = Realm.getDefaultInstance();
        Ingredients ingredients = Ingredients();
        Database database = FirebaseDatabase.getInstance().getReference(Checking.IngredientGroup);
        String id = UUID.randomUUID().toString();
        ingredients.ID = id;
        ingredients.Name = name;
        ingredients.Calories = calories;
        ingredients.Description = description;
        ingredients.Image = image;
        ingredients.ImagePath = imagePath;
        realm.beginTransaction();
        realm.copyToRealm(ingredients);
        realm.commitTransaction();
        return id;
    }
}
