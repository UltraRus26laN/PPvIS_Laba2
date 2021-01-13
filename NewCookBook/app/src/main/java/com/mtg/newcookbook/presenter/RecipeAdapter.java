package com.mtg.newcookbook.presenter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mtg.cookbook.model.Recipe;
import com.mtg.cookbook.R;
import com.mtg.cookbook.model.Category;

public class RecipeAdapter {
     private LayoutInflater layoutInflater = LayoutInflater.from(context);
    private List<Recipe> recipes = recipe;

    override void onCreateViewHolder(ViewGroup parent, Int viewType): ViewHolder {
        View view = layoutInflater.inflate(R.layout.activity_recipe, parent, false);
        return ViewHolder(view, onRecipeListener);
    }

    override void onBindViewHolder(ViewHolder holder, Int position) {
        recipes recipe = recipes[position];
        Category category = recipe.Category;
        holder.recipeName.text = recipe.Name;
        holder.recipeAbout.text = recipe.Description;
        holder.recipeCategory.text = category.category;
        holder.recipeImage.setImageBitmap(recipe.MainImage.let { BitmapFactory.decodeByteArray(recipe.MainImage, 0, it.size) });
    }

    override void getItemCount(): Int {
        return recipes.size;
    }

    class ViewHolder(View view, OnRecipeListener onRecipeListener): RecyclerView.ViewHolder(view), View.OnClickListener{
        TextView recipeName = view.findViewById(R.id.recipeName);
        ImageView recipeImage = view.findViewById(R.id.recipeImage);
        TextView recipeAbout = view.findViewById(R.id.recipeAbout);
        TextView recipeCategory = view.findViewById(R.id.recipeCategory);
        init {
            view.setOnClickListener(this);
        }

        override void onClick(View p0) {
            onRecipeListener.onRecipeClick(adapterPosition);
        }

    }

    interface OnRecipeListener{
        void onRecipeClick(Int position);
    }
}
