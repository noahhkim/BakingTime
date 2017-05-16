package com.example.noahkim.bakingtime.webservice;

import com.example.noahkim.bakingtime.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Noah on 5/15/2017.
 */

public interface Api {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}
