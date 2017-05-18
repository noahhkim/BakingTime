package com.example.noahkim.bakingtime.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.noahkim.bakingtime.R;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private final String RECIPEFRAGMENT_TAG = "RFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_list_frame, new RecipesFragment(), RECIPEFRAGMENT_TAG)
                    .commit();

            // Set up timber
            Timber.plant(new Timber.DebugTree());
        }
    }
}
