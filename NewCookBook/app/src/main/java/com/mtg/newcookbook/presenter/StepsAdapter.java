package com.mtg.newcookbook.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.widget.addTextChangedListener;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.mtg.cookbook.R;
import com.mtg.cookbook.model.Step;

public class StepsAdapter {
    private LayoutInflater layoutInflater = LayoutInflater.from(context);

    class ViewHolder(View view): RecyclerView.ViewHolder(view){
        TextInputEditText StepText = view.findViewById(R.id.stepsText);
        MaterialTextView StepName = view.findViewById(R.id.stepName);
    }

    override void onCreateViewHolder(ViewGroup parent, Int viewType): ViewHolder {
        View view = layoutInflater.inflate(R.layout.activity_steps, parent, false);
        return ViewHolder(view);
    }

    override void onBindViewHolder(ViewHolder holder, Int position) {
        Step _step = steps.get(position);
        String name = holder.StepName.text.toString();
        name += " ${(position+1)}";
        holder.StepName.text = name;
        holder.StepText.addTextChangedListener(TextWatcher object {
            override void afterTextChanged(Editable s) {
                steps[position].text = holder.StepText.text.toString();
            }
            override void beforeTextChanged(
                CharSequence s, Int start,
                Int count, Int after;
            ) {
            }

            override void onTextChanged(
                CharSequence s, Int start,
                Int before, Int count;
            ) {

            }
        })
    }

    override void getItemCount(): Int {
        return steps.size;
    }
}
