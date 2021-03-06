package com.example.noahkim.bakingtime.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.adapters.RecipeAdapter;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.webservice.Api;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Noah on 5/11/2017.
 */

public class RecipesFragment extends Fragment {
    @BindView(R.id.recyclerview_recipes)
    RecyclerView mRecipesRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Recipe> mRecipes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);
        // Bind the views
        ButterKnife.bind(this, rootView);

        getRecipes();

        initRecyclerView();

        return rootView;
    }

    private void initRecyclerView() {
        mRecipesRecyclerView.setHasFixedSize(true);

        if (getResources().getBoolean(R.bool.isTablet)) {
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        }

        mRecipesRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void getRecipes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Api recipeApi = retrofit.create(Api.class);
        Call<List<Recipe>> recipeCall = recipeApi.getRecipes();
        recipeCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipes = response.body();
                mRecipeAdapter = new RecipeAdapter(getContext(), mRecipes);
                mRecipesRecyclerView.setAdapter(mRecipeAdapter);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
