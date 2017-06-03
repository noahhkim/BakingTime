package com.example.noahkim.bakingtime.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.noahkim.bakingtime.R;

import butterknife.BindView;

/**
 * Created by noahkim on 5/10/17.
 */

public class RecipeDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static final String STEP_DETAILS = "step_details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

//        setSupportActionBar(mToolbar);

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//        }

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

