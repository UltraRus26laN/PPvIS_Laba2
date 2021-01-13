package com.mtg.newcookbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mtg.newcookbook.R;

public class AddIngredientActivity extends AppCompatActivity {
    private lateinit ImageButton photo;
    private lateinit TextInputEditText name;
    private lateinit TextInputEditText description;
    private lateinit TextInputEditText calories;
    private lateinit Button ready;
    private lateinit Bitmap uri;
    private lateinit Uri imageUri;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);
    
        initialise();


        ready.setOnClickListener{
            if(setError(name, this) && setError(description, this)
                && setError(calories, this) && setErrorImage(photo, this)){
                Intent intent = Intent(this, ChooseIngredientActivity::class.java);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                uri.compress(Bitmap.CompressFormat.PNG, 100, stream);

                Ingredient id = AddIngredient(name.text.toString(), calories.text.toString().toInt(),stream.toByteArray(),
                    "ingredient/${UUID.randomUUID()}.png", description.text.toString());
                
        }

        photo.setOnClickListener{
            Intent imageIntent = Intent(Intent.ACTION_PICK);
            imageIntent.type = "image/*";
            startActivityForResult(imageIntent, AddActivity.MAIN_IMAGE_GALLERY);
        }
    }
    private void initialise(){
        photo = findViewById(R.id.addIngrPhoto);
        name = findViewById(R.id.addIngrName);
        description = findViewById(R.id.addIngrDescription);
        calories = findViewById(R.id.addIngrCalories);
        ready = findViewById(R.id.addIngrReady);
    }


    companion object{
        void cleartext(String text, Int position): String{
            return text.substring(0, position) + if(position+1 >= text.length) {
                text.substring(position + 1);
            }
            else
            {
                ""
            }
        }

        void setError(TextInputEditText textField, Context context): Boolean{
            String text = textField.text.toString();
            if(textField.text.toString() == ""){
                textField.error = context.getString(R.string.errorEmpty);
                return false;
            }
            return true;
        }

        void setErrorImage(ImageButton image, Context context): Boolean{
            if(image.drawable == context.getDrawable(R.drawable.ic_baseline_add_24)){
                return false;
            }
            return true;
        }
    }

    override void onActivityResult(Int requestCode, Int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode){
            AddActivity.MAIN_IMAGE_GALLERY -> {
                if (resultCode == RESULT_OK) {
                    imageUri = imageReturnedIntent.data;
                    try {
                        uri = MediaStore.Images.Media.getBitmap(contentResolver, imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    photo.setImageBitmap(uri);
                }
            }

        }
    }
}