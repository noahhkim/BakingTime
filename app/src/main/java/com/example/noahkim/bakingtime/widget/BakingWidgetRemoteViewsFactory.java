package com.example.noahkim.bakingtime.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.webservice.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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
        getRecipes();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
//        if (mRecipes == null) return 0;
        Timber.d("Recipes: " + mRecipes.size());
        return mRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        remoteViews.setTextViewText(R.id.recipe_name, mRecipes.get(i).getRecipeName());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private void getRecipes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api recipeApi = retrofit.create(Api.class);
        Call<List<Recipe>> recipeCall = recipeApi.getRecipes();
        recipeCall.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipes = response.body();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
