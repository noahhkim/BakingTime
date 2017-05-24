package com.example.noahkim.bakingtime.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Recipe;

/**
 * Created by noahkim on 5/10/17.
 */

public class RecipeDetailsActivity extends AppCompatActivity {
    private Recipe mRecipe;
    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Inflate RecipeDetailsFragment
        if (savedInstanceState == null) {
            if (getResources().getBoolean(R.bool.isTablet)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_details_frame, new RecipeDetailsFragment())
                        .commit();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.steps_details_frame, new RecipeStepDetailFragment())
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_details_frame, new RecipeDetailsFragment())
                        .commit();
            }

        }
    }
}

