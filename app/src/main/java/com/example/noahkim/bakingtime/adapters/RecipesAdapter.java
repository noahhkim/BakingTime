package com.example.noahkim.bakingtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.ui.MainActivity;
import com.example.noahkim.bakingtime.ui.RecipeDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 * RecipesAdapter is backed by a list of {@link Recipe} objects which populate
 * the RecyclerView in MainActivity
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemViewHolder> {
    private Context mContext;
    private List<Recipe> mRecipes;
    public static final int NO_POSITION = 0;

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
        int[] myImageList = new int[]{R.drawable.nutella_pie,
                R.drawable.brownies,
                R.drawable.yellow_cake,
                R.drawable.cheesecake};
        Recipe currentRecipe = mRecipes.get(position);
        holder.mRecipeNameView.setText(currentRecipe.getRecipeName());
        Picasso.with(mContext)
                .load(myImageList[position])
                .into(holder.mRecipeImageView);
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) return 0;
        return mRecipes.size();
    }

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_card)
        TextView mRecipeNameView;
        @BindView(R.id.recipe_image)
        ImageView mRecipeImageView;


        public RecipeItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != NO_POSITION) {
                Recipe currentRecipe = mRecipes.get(getAdapterPosition());
                Intent intent = new Intent(mContext, RecipeDetailsActivity.class);
                intent.putExtra(MainActivity.RECIPE_DETAILS, currentRecipe);
                mContext.startActivity(intent);
                Timber.d("List item clicked");
            }
        }
    }

}
