package com.example.noahkim.bakingtime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.noahkim.bakingtime.model.Recipe;

import butterknife.ButterKnife;

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

        ButterKnife.bind(this);

        // Inflate RecipeDetailsFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_details_frame, new RecipeDetailsFragment())
                    .commit();
        }

//        // Determine if creating a two-pane or single-pane display
//        if (findViewById(R.id.recipe_step_detail_linear_layout) != null) {
//            mTwoPane = true;
//        } else {
//            mTwoPane = false;
//        }
    }
}
