package com.example.noahkim.bakingtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.ui.RecipeDetailsActivity;
import com.example.noahkim.bakingtime.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecipesAdapter is backed by a list of {@link Recipe} objects which populate
 * the RecyclerView in MainActivity
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemViewHolder> {
    public static final String RECIPE_DETAILS = "recipe_details";
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
    public void onBindViewHolder(RecipeItemViewHolder holder, final int position) {
        final Recipe currentRecipe = mRecipes.get(position);
        holder.mRecipeNameView.setText(currentRecipe.getRecipeName());
        holder.mRecipeNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecipeDetailsActivity.class);
                intent.putExtra(RECIPE_DETAILS, currentRecipe);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) return 0;
        return mRecipes.size();
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_card)
        TextView mRecipeNameView;

        public RecipeItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
