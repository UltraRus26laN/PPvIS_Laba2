package com.mtg.newcookbook.presenter;

import com.google.firebase.database.FirebaseDatabase;
import com.mtg.cookbook.model.Category;
import com.mtg.cookbook.model.Ingredients;
import com.mtg.cookbook.model.Recipe;
import com.mtg.cookbook.model.Step;
import io.realm.Realm;
import io.realm.RealmList;
import java.util.*;

public interface RecipeDataBase {
    void searchRecipeByID(String id): Recipe {
        Realm realm = Realm.getDefaultInstance();
        return realm.where<Recipe>().equalTo("id", id).findFirst()Recipe();
    }

    void addRecipe(String name, ByteArray mainImage, String mainImagePath, String category, String definition, RealmList<ByteArray> images,
                  RealmList<Step> steps, Int calories, RealmList<String> imagePath, RealmList<Ingredients> ingredients, Int weight): String{
        Realm realm = Realm.getDefaultInstance();
        String id = UUID.randomUUID().toString();
        Recipe recipe = new Recipe();

        recipe.id = id;
        recipe.Name = name;
        recipe.MainImage = mainImage;
        recipe.Description = definition;
        recipe.MainImagePath = mainImagePath;
        recipe.Category = findCategoryByName(category);
        recipe.Calories = calories;
        recipe.Images = images;
        recipe.ImagePaths = imagePath;
        recipe.Steps = steps;
        recipe.Ingredients = ingredients;
        recipe.weight = weight;
        realm.beginTransaction();
        realm.copyToRealm(recipe);
        realm.commitTransaction();
        return id;
    }
    voidfindCategoryByName(String name): Category{
        Realm realm = Realm.getDefaultInstance();
        return realm.where<Category>().equalTo("category", name).findFirst()Category();
    }
}
