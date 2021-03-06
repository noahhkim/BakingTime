package com.example.noahkim.bakingtime.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.ui.fragment.DetailsFragment;
import com.example.noahkim.bakingtime.ui.fragment.StepDetailsFragment;

/**
 * Created by noahkim on 5/10/17.
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String STEP_DETAILS = "step_details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Inflate DetailsFragment
        if (savedInstanceState == null) {
            if (getResources().getBoolean(R.bool.isTablet)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_details_frame, new DetailsFragment())
                        .commit();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.steps_details_frame, new StepDetailsFragment())
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.recipe_details_frame, new DetailsFragment())
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

