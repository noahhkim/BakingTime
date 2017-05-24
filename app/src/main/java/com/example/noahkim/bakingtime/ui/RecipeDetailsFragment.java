package com.example.noahkim.bakingtime.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.adapters.IngredientsAdapter;
import com.example.noahkim.bakingtime.adapters.RecipesAdapter;
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

        initRecyclerView();

        getDetails();

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
        // retrieve data
        mRecipe = getActivity().getIntent().getExtras().getParcelable(RecipesAdapter.RECIPE_DETAILS);

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
    public void onItemClick(int clickedItem) {
        if (!getResources().getBoolean(R.bool.isTablet)) {
            Intent intent = new Intent(getActivity(), RecipeStepDetailActivity.class);
            intent.putExtra(StepsAdapter.STEP_DETAILS, clickedItem);
            startActivity(intent);
        } else {
            RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
            recipeStepDetailFragment.step_index = clickedItem;
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_details_frame, recipeStepDetailFragment)
                    .commit();
        }

    }
}
