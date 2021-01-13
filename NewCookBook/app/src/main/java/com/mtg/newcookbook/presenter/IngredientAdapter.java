package com.mtg.newcookbook.presenter;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.mtg.cookbook.R;
import com.mtg.cookbook.model.Ingredients;
import com.mtg.cookbook.view.ChooseIngredientActivity;

public class IngredientAdapter {
    class ViewHolder(View view): RecyclerView.ViewHolder(view) {
        TextInputEditText numOfIngredients  = view.findViewById(R.id.numText);
        TextView ingredientName = view.findViewById(R.id.ingredientBtn);
        Spinner spinner = view.findViewById(R.id.typeNum);

    }

    companion object{
        const Int INGREDIENT_CALL = 123;
    }
    private LayoutInflater layoutInflater = LayoutInflater.from(context);
    private ArrayList<Ingredients> ingredients = ingredient;
    private List<String> typesOfNum = arrayListOf("KG", "G", "ML", "L", "Items");
    ArrayAdapter<String> spinnerAdapter
    init {
        spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, typesOfNum);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    override void onCreateViewHolder(ViewGroup parent, Int viewType): ViewHolder {
        View view = layoutInflater.inflate(R.layout.activity_ingredients, parent, false);
        return ViewHolder(view);
    }

    override void onBindViewHolder(ViewHolder holder, Int position) {
        ingredients = ingredients.get(position);
        holder.spinner.adapter = spinnerAdapter;
        holder.ingredientName.text = _ingredient.Name;
        holder.ingredientName.setOnClickListener {
            Intent intent = Intent(context, ChooseIngredientActivity::class.java);
            intent.putExtra("position", position);
            activity.startActivityForResult(intent, INGREDIENT_CALL);
        }
    }

    override void getItemCount(): Int {
        return ingredients.size;
    }
}
