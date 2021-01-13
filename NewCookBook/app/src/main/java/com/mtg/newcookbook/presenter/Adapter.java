package com.mtg.newcookbook.presenter;

public interface Adapter {
    void returnIngredientFire(Ingredienta ingredient): IngredientsFire{
        return IngredientsFire(ingredient.ID, ingredient.Name,
        ingredient.Calories, ingredient.ImagePath, ingredient.Description);
    }

    void returnRecipeFire(Recipe recipe): RecipeFire{
        ArrayList<steps> steps = ArrayList<Step>();
        steps.addAll(recipe.Steps)
        List<step> step = steps.toList();
        ArrayList<imagePaths> imagePaths = ArrayList<String>();
        imagePaths.addAll(recipe.ImagePaths);
        List<imagePath> imagePath = imagePaths.toList();
        ArrayList<ingredients> ingredients = ArrayList<IngredientsFire>()
        for (int i=0; i<=recipe.Ingredients.size; i++){
            ingredients.add(returnIngredientFire(recipe.Ingredients[i]))
        }
        List<ingredient> ingredient = ingredients.toList();
        Category category = recipe.Category;
        RecipeFire recipeFire = RecipeFire(recipe.id, recipe.Name, recipe.MainImagePath, recipe.Description, category.category,
        step, recipe.Calories, imagePath, ingredient);
        Log.d("Adapter", recipeFire.id);
        Log.d("Adapter", recipeFire.Name);
        Log.d("Adapter", recipeFire.MainImagePath);
        Log.d("Adapter", recipeFire.Description);
        Log.d("Adapter", recipeFire.Category);
        Log.d("Adapter", recipeFire.Calories.toString());
        return recipeFire;
    }
}
