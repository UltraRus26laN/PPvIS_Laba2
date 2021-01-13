package com.mtg.newcookbook.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Recipe {
    String id;
    String Name;
    ByteArray MainImage;
    String MainImagePath;
    String Description;
    Category Category;
    RealmList<ByteArray> Images;
    RealmList<Step> Steps;
    Int Calories;
    RealmList<String> ImagePaths;
    RealmList<Ingredients> Ingredients;
    Int weight;
    Recipe(){

    }
}
