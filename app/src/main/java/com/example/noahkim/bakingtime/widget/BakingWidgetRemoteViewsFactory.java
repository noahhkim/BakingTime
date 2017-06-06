package com.example.noahkim.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Ingredient;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.ui.MainActivity;
import com.example.noahkim.bakingtime.webservice.Api;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Noah on 5/24/2017.
 */

public class BakingWidgetRemoteViewsFactory implements RemoteViewsFactory {
    private Context mContext;
    private List<Recipe> mRecipes;

    public BakingWidgetRemoteViewsFactory(Context context) {
        mContext = context;
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api recipeApi = retrofit.create(Api.class);
        Call<List<Recipe>> recipeCall = recipeApi.getRecipes();
        try {
            mRecipes = recipeCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (mRecipes == null) return 0;
        return mRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final Recipe currentRecipe = mRecipes.get(position);
//        final Ingredient currentIngredient = currentRecipe.getRecipeIngredients().get(position);

        // Set recipe name in the widget
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        String recipeName = currentRecipe.getRecipeName();
        remoteViews.setTextViewText(R.id.widget_recipe_name, recipeName);

        // Set ingredients in the widget
        remoteViews.removeAllViews(R.id.widget_ingredients_list);
        for (int i = 0; i < currentRecipe.getRecipeIngredients().size(); i++) {
            RemoteViews ingRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_item_layout);
            Ingredient currentIngredient = currentRecipe.getRecipeIngredients().get(i);
            ingRemoteViews.setTextViewText(R.id.ingredient_name, currentIngredient.getIngredientName());
            ingRemoteViews.setTextViewText(R.id.ingredient_measure, currentIngredient.getIngredientMeasure());
            ingRemoteViews.setTextViewText(R.id.ingredient_quantity, currentIngredient.getIngredientQuantity().toString());
            remoteViews.addView(R.id.widget_ingredients_list, ingRemoteViews);
        }

        // Fill in the onClick PendingIntent Template using the specific name for each recipe individually
        if (position != -1) {
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(MainActivity.RECIPE_DETAILS, currentRecipe);
            remoteViews.setOnClickFillInIntent(R.id.background, fillInIntent);
        }
        return remoteViews;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}

