package com.mtg.newcookbook.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ingredients {
    
    String ID;
    String Name;
    Int Calories;
    ByteArray Image;
    String ImagePath;
    String Description;
    Ingredients(){
    }
}
