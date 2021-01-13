package com.mtg.newcookbook.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textview.MaterialTextView;
import com.mtg.cookbook.R;
import com.mtg.cookbook.model.Category;

public class CategoryAdapter {
    class ViewHolder(View view, OnCategoryListenr onCategoryListener): RecyclerView.ViewHolder(view), View.OnClickListener{
        MaterialTextView categoryName = view.findViewById<MaterialTextView>(R.id.categoryName);
        private onCategoryListenr onCategoryListener = onCategoryListenr
        init {
            view.setOnClickListener(this);
        }

        override void onClick(View p0) {
            onCategoryListener.onCategoryClick(adapterPosition);
        }
    }

    private LayoutInflater layoutInflater = LayoutInflater.from(context)
    private CategoryList categoryList = category;
    private OnCategoryListener onCategoryListener = onCategoryListenr;

    override void onCreateViewHolder(ViewGroup parent, Int viewType): ViewHolder {
        View view = layoutInflater.inflate(R.layout.activity_category, parent, false);
        return ViewHolder(view, onCategoryListener);
    }

    override void onBindViewHolder(ViewHolder holder, Int position) {
        CategoryList categor = categoryList[position];
        holder.categoryName.text = categor.category;

    }

    override void getItemCount(): Int {
        return categoryList.size;
    }
    interface OnCategoryListener{
        void onCategoryClick(Int position);
    }
}
