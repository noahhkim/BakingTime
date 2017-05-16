package com.example.noahkim.bakingtime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noahkim.bakingtime.model.Recipe;

import java.util.List;

/**
 * RecipesAdapter is backed by a list of {@link Recipe} objects which populate
 * the RecyclerView in RecipesActivity
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemViewHolder> {
    private Context mContext;
    private List<Recipe> mRecipes;

    public RecipesAdapter(Context context, List<Recipe> recipes) {
        mContext = context;
        mRecipes = recipes;
    }


    @Override
    public RecipeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout, parent, false);
        return new RecipeItemViewHolder(view);
    }

    // Create a new TextView for each item referenced by the Adapter
    @Override
    public void onBindViewHolder(RecipeItemViewHolder holder, int position) {
        Recipe currentRecipe = mRecipes.get(position);
        holder.mTextView.setText(currentRecipe.getRecipeName());
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) return 0;
        return mRecipes.size();
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;

        public RecipeItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recipe_card);
        }
    }

}
