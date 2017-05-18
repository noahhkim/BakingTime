package com.example.noahkim.bakingtime.ui;

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
import com.example.noahkim.bakingtime.model.Ingredient;
import com.example.noahkim.bakingtime.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/17/2017.
 */

public class RecipeDetailsFragment extends Fragment {
    @BindView(R.id.recyclerview_ingredients)
    RecyclerView mIngredientsRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Recipe mRecipe;
    private IngredientsAdapter mIngredientsAdapter;
    private List<Ingredient> mIngredients = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, rootView);

        initRecyclerView();

        getIngredients();

        return rootView;
    }

    private void initRecyclerView() {
        mIngredientsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mIngredientsRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void getIngredients() {
        mRecipe = getActivity().getIntent().getExtras().getParcelable(RecipesAdapter.RECIPE_DETAILS);
        mIngredients = mRecipe.getRecipeIngredients();
        mIngredientsAdapter = new IngredientsAdapter(getContext(), mIngredients);
        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);
    }
}
