package com.example.noahkim.bakingtime.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.ui.fragment.RecipeStepDetailFragment;

/**
 * Created by Noah on 5/17/2017.
 */

public class RecipeStepDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);

        // Inflate RecipeDetailsStepsFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.steps_details_frame, new RecipeStepDetailFragment())
                    .commit();
        }
    }
}
