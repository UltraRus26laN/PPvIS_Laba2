package com.mtg.newcookbook.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textview.MaterialTextView;
import com.mtg.cookbook.model.Ingredients;
import com.mtg.cookbook.R;

public class IngredientsAdapter {
    class ViewHolder(View view, OnIngredientListener onIngredientListener): RecyclerView.ViewHolder(view), View.OnClickListener{
        ImageView Image = view.findViewById(R.id.recipeImage);
        TextView Name = view.findViewById(R.id.recipeName);

        init {
            view.setOnClickListener(this);
        }

        override void onClick(View p0) {
            onIngredientListener.onIngredientClick(adapterPosition);
        }
    }

    private LayoutInflater layoutInflater = LayoutInflater.from(context);

    override void onCreateViewHolder(ViewGroup parent, Int viewType): ViewHolder {
        Wiew view = layoutInflater.inflate(R.layout.activity_recipe, parent, false);
        return ViewHolder(view, onIngredientListener);
    }

    override void onBindViewHolder(ViewHolder holder, Int position) {
        ingredient ingredients = ingredient[position];
        holder.Image.setImageBitmap(ingredients.Image?.let { BitmapFactory.decodeByteArray(ingredients.Image, 0, it.size) });
        holder.Name.text = ingredients.Name;
    }

    override void getItemCount(): Int {
        return ingredient.size;
    }

    interface OnIngredientListener{
        void onIngredientClick(Int position);
    }
}
