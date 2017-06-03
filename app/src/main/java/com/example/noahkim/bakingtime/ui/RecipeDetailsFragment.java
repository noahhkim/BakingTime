package com.example.noahkim.bakingtime.ui;

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
import com.example.noahkim.bakingtime.adapters.IngredientsAdapter;
import com.example.noahkim.bakingtime.adapters.StepsAdapter;
import com.example.noahkim.bakingtime.model.Ingredient;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/17/2017.
 */

public class RecipeDetailsFragment extends Fragment implements StepsAdapter.ListItemClickListener {
    @BindView(R.id.recyclerview_ingredients_list)
    RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.recyclerview_steps_list)
    RecyclerView mStepsRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private RecyclerView.LayoutManager mLayoutManager;
    private Recipe mRecipe;
    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;
    private List<Ingredient> mIngredients = new ArrayList<>();
    public static List<Step> STEPS = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, rootView);

        // retrieve data from intent
        mRecipe = getActivity().getIntent().getExtras().getParcelable(MainActivity.RECIPE_DETAILS);

        // set Toolbar text
        if (mToolbar != null && getActivity() instanceof RecipeDetailsActivity) {
            mToolbar.setTitle(mRecipe.getRecipeName());
        }

        // set up back arrow for RecipeDetails toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        getDetails();

        initRecyclerView();

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
        mIngredientsAdapter = new IngredientsAdapter(getContext(), mIngredients);
        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);

        // attach steps to recyclerview
        STEPS = mRecipe.getRecipeSteps();
        mStepsAdapter = new StepsAdapter(this, STEPS);
        mStepsRecyclerView.setAdapter(mStepsAdapter);
    }

    @Override
    public void onItemClick(int itemIndex) {
        if (!getResources().getBoolean(R.bool.isTablet)) {
            Intent intent = new Intent(getActivity(), RecipeStepDetailActivity.class);
            intent.putExtra(RecipeDetailsActivity.STEP_DETAILS, itemIndex);
            startActivity(intent);
        } else {
            RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
            recipeStepDetailFragment.step_index = itemIndex;
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_details_frame, recipeStepDetailFragment)
                    .commit();
        }

    }
}
