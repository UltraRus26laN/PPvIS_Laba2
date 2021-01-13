package com.mtg.newcookbook.presenter;

public interface Downloader {
    void searchRecipe(String name){
        Database database = FirebaseDatabase.getInstance().getReference(Checking.RecipeGroup);
        Query query = database.orderByChild("name");
    }
}
