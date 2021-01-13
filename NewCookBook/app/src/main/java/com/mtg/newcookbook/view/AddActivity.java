package com.mtg.newcookbook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mtg.newcookbook.R;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    companion object{
        const Int MAIN_IMAGE_GALLERY = 1
        const Int NOT_MAIN_IMAGE1 = 2
        const Int NOT_MAIN_IMAGE2 = 3
        const Int NOT_MAIN_IMAGE3 = 4
        const Int NOT_MAIN_IMAGE4 = 5
        const Int CATEGORY = 6
    }

     ImageButton mainPhotoButton;
     ImageButton notMainPhoto1;
     ImageButton notMainPhoto2;
     ImageButton notMainPhoto3;
     ImageButton notMainPhoto4;
     TextInputEditText nameText;
     TextInputEditText descriptionText;
     TextInputLayout categoryLayout;
     TextInputEditText categoryText;
     RecyclerView ingredients;
     ImageButton addIngredient;
     RecyclerView steps;
     ImageButton addStep;
     Button readyBtn;

     Bitmap selectedMainImage;
     Bitmap selectedImage1;
     Bitmap selectedImage2;
     Bitmap selectedImage3;
     Bitmap selectedImage4;
     IngredientAdapter ingredientAdapter;
     StepsAdapter stepsAdapter;
     ArrayList<Ingredients> ingredientList;
     ArrayList<Step> stepList;
     notMainPhotoChanged1 = false;
     notMainPhotoChanged2 = false;
     notMainPhotoChanged3 = false;
     notMainPhotoChanged4 = false;

    @Override
     void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initialize();
        Realm realm = Realm.getDefaultInstance();
        ingredientAdapter = IngredientAdapter(this, ingredientList, this);
        ingredients.adapter = ingredientAdapter

        stepsAdapter = StepsAdapter(this, stepList);
        steps.adapter = stepsAdapter;

        addIngredient.setOnClickListener {
            ingredientList.add(Ingredients("0", "Set Ingredient"));
            ingredientAdapter.notifyItemInserted(ingredientList.size - 1);
        }

        addStep.setOnClickListener {
            stepList.add(Step(""));
            stepsAdapter.notifyItemInserted(stepList.size - 1);
        }

        mainPhotoButton.setOnClickListener{
            Intent imageIntent = Intent(Intent.ACTION_PICK);
            imageIntent.type = "image/*";
            startActivityForResult(imageIntent, MAIN_IMAGE_GALLERY);
        }

        notMainPhoto1.setOnClickListener{
            Intent imageIntent = Intent(Intent.ACTION_PICK);
            imageIntent.type = "image/*";
            startActivityForResult(imageIntent, NOT_MAIN_IMAGE1);
        }
        notMainPhoto2.setOnClickListener{
            Intent imageIntent = Intent(Intent.ACTION_PICK);
            imageIntent.type = "image/*";
            startActivityForResult(imageIntent, NOT_MAIN_IMAGE2);
        }
        notMainPhoto3.setOnClickListener{
            Intent imageIntent = Intent(Intent.ACTION_PICK);
            imageIntent.type = "image/*";
            startActivityForResult(imageIntent, NOT_MAIN_IMAGE3);
        }
        notMainPhoto4.setOnClickListener{
            Intent imageIntent = Intent(Intent.ACTION_PICK);
            imageIntent.type = "image/*";
            startActivityForResult(imageIntent, NOT_MAIN_IMAGE4);
        }
        Int calories = 0;
        RealmList<Ingredients> allIngredient = new RealmList();
        for (i=0; i<ingredientList.size; i++) {
            allIngredient.add(ingredientList[i]);
        }
        stepList = stepsAdapter.steps;
        RealmList<Step> allSteps = new RealmList();
        for (i=0; i<stepList.size; i++){
            allSteps.add(stepList[i]);
        }
        for (i=0; i<=ingredientList.size; i++){
            calories += ingredientList[i].Calories;
        }
        categoryLayout.setOnClickListener{
            Intent intent = Intent(this, SetCategoryActivity::class.java);
            startActivityForResult(intent, CATEGORY);
        }
        readyBtn.setOnClickListener {
            if(AddIngredientActivity.setError(nameText, this)
                && AddIngredientActivity.setError(descriptionText, this)
                && AddIngredientActivity.setError(categoryText, this)
                && AddIngredientActivity.setErrorImage(mainPhotoButton, this)){
                ByteArrayOutputStream mainStream = new ByteArrayOutputStream();
                selectedMainImage.compress(Bitmap.CompressFormat.PNG, 100, mainStream);
                ByteArrayOutputStream notMainStream1 = new ByteArrayOutputStream();
                if (notMainPhotoChanged1)
                    selectedImage1.compress(Bitmap.CompressFormat.PNG, 100, notMainStream1);
                ByteArrayOutputStream notMainStream2 = new ByteArrayOutputStream();
                if (notMainPhotoChanged2)
                    selectedImage2.compress(Bitmap.CompressFormat.PNG, 100, notMainStream2);
                ByteArrayOutputStream notMainStream3 = new ByteArrayOutputStream();
                if (notMainPhotoChanged3)
                    selectedImage3.compress(Bitmap.CompressFormat.PNG, 100, notMainStream3);
                ByteArrayOutputStream notMainStream4 = new ByteArrayOutputStream();
                if (notMainPhotoChanged4)
                    selectedImage4.compress(Bitmap.CompressFormat.PNG, 100, notMainStream4);

                RealmList<ByteArray> images = new RealmList<ByteArray>();
                RealmList<String> imagePath = new RealmList<String>();
                if (notMainPhotoChanged1){
                    images.add(notMainStream1.toByteArray());
                    imagePath.add("recipe/${UUID.randomUUID()}.png");
                }
                if (notMainPhotoChanged2){
                    images.add(notMainStream2.toByteArray());
                    imagePath.add("recipe/${UUID.randomUUID()}.png");
                }
                if (notMainPhotoChanged3){
                    images.add(notMainStream3.toByteArray());
                    imagePath.add("recipe/${UUID.randomUUID()}.png");
                }
                if (notMainPhotoChanged4){
                    images.add(notMainStream4.toByteArray());
                    imagePath.add("recipe/${UUID.randomUUID()}.png");
                }

                Category cat = realm.where<Category>().equalTo("category", categoryText.text.toString()).findFirst()Category();
                Log.d("ADDDDD", "${cat.category} + ${cat.weight}");
                Recipe id = addRecipe(nameText.text.toString(),
                        mainStream.toByteArray(),
                        "recipe/${UUID.randomUUID()}.png",
                        categoryText.text.toString(),
                        descriptionText.text.toString(),
                        images,
                        allSteps,
                        calories,
                        imagePath,
                        allIngredient,
                        cat.weight);
                Intent intent = Intent(this, AddFragment::class.java);
                Log.d("Checking online", "online");
                    if (id != null) {
                        Log.d("AddActivity", searchRecipeByID(id)?.id);
                        Recipe recipe = searchRecipeByID(id);
                        sendRecipe(recipe);
                        Log.d("Checking online", "sent");
                    }
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }



    @Override
    void onActivityResult(Int requestCode, resultCode: Int, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode){
            MAIN_IMAGE_GALLERY -> {
                if (resultCode == RESULT_OK) {
                    Image selectedImage = imageReturnedIntent!!.data;
                    try {
                        selectedMainImage = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mainPhotoButton.setImageBitmap(selectedMainImage);
                }
            }
            NOT_MAIN_IMAGE1 -> {
                Image selectedImage = imageReturnedIntent!!.data;
                try {
                    selectedImage1 = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notMainPhotoChanged1 = true;
                notMainPhoto1.setImageBitmap(selectedImage1);
            }
            NOT_MAIN_IMAGE2 -> {
                Image selectedImage = imageReturnedIntent!!.data;
                try {
                    selectedImage2 = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notMainPhotoChanged2 = true;
                notMainPhoto2.setImageBitmap(selectedImage2);
            }
            NOT_MAIN_IMAGE3 -> {
                Image selectedImage = imageReturnedIntent!!.data;
                try {
                    selectedImage3 = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notMainPhotoChanged3 = true;
                notMainPhoto3.setImageBitmap(selectedImage3);
            }
            NOT_MAIN_IMAGE4 -> {
                Image selectedImage = imageReturnedIntent!!.data;
                try {
                    selectedImage4 = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notMainPhotoChanged4 = true;
                notMainPhoto4.setImageBitmap(selectedImage4);
            }
            
            CATEGORY ->{
                if (resultCode == RESULT_OK){
                    String CategoryName = imageReturnedIntent?.getStringExtra("category");
                    categoryText.setText(CategoryName);
                }
            }
        }
    }

    private void initialize(){
        mainPhotoButton = findViewById(R.id.mainPhotoButton);
        notMainPhoto1 = findViewById(R.id.notMainPhoto1);
        notMainPhoto2 = findViewById(R.id.notMainPhoto2);
        notMainPhoto3 = findViewById(R.id.notMainPhoto3);
        notMainPhoto4 = findViewById(R.id.notMainPhoto4);
        nameText = findViewById(R.id.nameText);
        descriptionText = findViewById(R.id.descriptionText);
        categoryLayout = findViewById(R.id.categoryLayout);
        categoryText = findViewById(R.id.categoryText);
        ingredients = findViewById(R.id.ingredients);
        addIngredient = findViewById(R.id.addIngredient);
        steps = findViewById(R.id.steps);
        addStep = findViewById(R.id.addStep);
        readyBtn = findViewById(R.id.readyBtn);
    }
}