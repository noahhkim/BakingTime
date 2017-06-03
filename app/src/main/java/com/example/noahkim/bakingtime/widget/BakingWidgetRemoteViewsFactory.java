package com.example.noahkim.bakingtime.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.noahkim.bakingtime.R;
import com.example.noahkim.bakingtime.model.Recipe;
import com.example.noahkim.bakingtime.ui.MainActivity;
import com.example.noahkim.bakingtime.webservice.Api;

import java.io.IOException;
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
    public RemoteViews getViewAt(int i) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        String recipeName = mRecipes.get(i).getRecipeName();
        remoteViews.setTextViewText(R.id.recipe_name, recipeName);

        // Fill in the onClick PendingIntent Template using the specific name for each recipe individually
        Intent fillInIntent = new Intent();
        Recipe currentRecipe = mRecipes.get(i);
        fillInIntent.putExtra(MainActivity.RECIPE_DETAILS, currentRecipe);
        Timber.d("recipes for intent: " + mRecipes.get(i).getRecipeName());
        remoteViews.setOnClickFillInIntent(R.id.background, fillInIntent);
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
                Timber.d("Recipes received: " + mRecipes.size());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

//    public class FetchRecipesTask extends AsyncTask<Void, Void, List<Recipe>> {
//
//        @Override
//        protected List<Recipe> doInBackground(Void... params) {
//
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//
//            try {
//                Uri builtUri = Uri.parse(mContext.getString(R.string.URL))
//                        .buildUpon()
//                        .build();
//
//                URL url = new URL(builtUri.toString());
//
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    return null;
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//                if (buffer.length() == 0) {
//                    return null;
//                }
//                JSONArray recipesArray = new JSONArray(buffer.toString());
//                mRecipes = new ArrayList<>();
//                for (int i = 0; i < recipesArray.length(); i++) {
//                    mRecipes.add(new Recipe(recipesArray.getJSONObject(i)));
//                    Timber.d("Name: " + mRecipes.get(i).getRecipeName());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (urlConnection != null) {
//                        urlConnection.disconnect();
//                    }
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (Exception e) {
//                }
//                return mRecipes;
//            }
//        }
//    }
}

