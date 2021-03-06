package com.example.noahkim.bakingtime.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.adapters.IngredientAdapter;
import com.example.noahkim.bakingtime.adapters.StepAdapter;
import com.example.noahkim.bakingtime.model.Ingredient;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.model.Step;
import com.example.noahkim.bakingtime.ui.activity.DetailsActivity;
import com.example.noahkim.bakingtime.ui.activity.MainActivity;
import com.example.noahkim.bakingtime.ui.activity.StepDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/17/2017.
 */

public class DetailsFragment extends Fragment implements StepAdapter.ListItemClickListener {
    @BindView(R.id.recyclerview_ingredients_list)
    RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.recyclerview_steps_list)
    RecyclerView mStepsRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private RecyclerView.LayoutManager mLayoutManager;
    private IngredientAdapter mIngredientAdapter;
    private StepAdapter mStepAdapter;
    private Recipe mRecipe;
    private List<Ingredient> mIngredients = new ArrayList<>();
    public static List<Step> STEPS_LIST = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, rootView);

        mRecipe = null;
        Bundle intentBundle = getActivity().getIntent().getExtras();
        if (intentBundle != null) {
            // Retrieve data from intent
            mRecipe = intentBundle.getParcelable(MainActivity.RECIPE_DETAILS);

            // set Toolbar text
            if (mToolbar != null && getActivity() instanceof DetailsActivity) {
                mToolbar.setTitle(mRecipe.getRecipeName());
            }
            // Get ingredients and steps and attach them to adapter
            getDetails();

            // Initialize RecyclerView
            initRecyclerView();
        }

        // set up back arrow for RecipeDetails toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        return rootView;
    }

    private void initRecyclerView() {
        mIngredientsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mIngredientsRecyclerView.setLayoutManager(mLayoutManager);

        mStepsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mStepsRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void getDetails() {
        // attach ingredients adapter to recyclerview
        mIngredients = mRecipe.getRecipeIngredients();
        mIngredientAdapter = new IngredientAdapter(getContext(), mIngredients);
        mIngredientsRecyclerView.setAdapter(mIngredientAdapter);

        // attach steps to recyclerview
        STEPS_LIST = mRecipe.getRecipeSteps();
        mStepAdapter = new StepAdapter(this, STEPS_LIST);
        mStepsRecyclerView.setAdapter(mStepAdapter);
    }

    @Override
    public void onItemClick(int itemIndex) {

        if (!getResources().getBoolean(R.bool.isTablet)) {
            Intent intent = new Intent(getActivity(), StepDetailsActivity.class);
            intent.putExtra(DetailsActivity.STEP_DETAILS, itemIndex);
            startActivity(intent);

        } else {
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_details_frame, stepDetailsFragment)
                    .commit();
        }
    }
}
