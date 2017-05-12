package com.example.noahkim.bakingtime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Noah on 5/11/2017.
 */

public class RecipesFragment extends Fragment {
    @BindView(R.id.recyclerview_recipes)
    RecyclerView mRecyclerView;
    List<Recipe> mDummyList;
    private RecipesAdapter mRecipesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipes_fragment, container, false);
        ButterKnife.bind(this, rootView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mDummyList = new ArrayList<Recipe>() {{
            add(new Recipe("cookies"));
            add(new Recipe("cake"));
            add(new Recipe("pizza"));
            add(new Recipe("hamburger"));
        }};

        mRecipesAdapter = new RecipesAdapter(getActivity(), mDummyList);

        mRecyclerView.setAdapter(mRecipesAdapter);

        return rootView;
    }
}
