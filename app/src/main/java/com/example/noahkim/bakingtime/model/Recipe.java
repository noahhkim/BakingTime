package com.example.noahkim.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {@link Recipe} represents a recipe that the user can select from the menu.
 * It contains a recipe name.
 */

public class Recipe implements Parcelable {

    @SerializedName("name")
    private String mRecipeName;

    @SerializedName("id")
    private int mRecipeId;

    @SerializedName("ingredients")
    private List<Ingredient> mRecipeIngredients;

    @SerializedName("servings")
    private int mRecipeServings;

    public Recipe(String recipeName, int recipeId, List<Ingredient> recipeIngredients, int recipeServings) {

        mRecipeName = recipeName;
        mRecipeId = recipeId;
        mRecipeIngredients = recipeIngredients;
        mRecipeServings = recipeServings;
    }

//    public Recipe(String recipeName) {
//
//        mRecipeName = recipeName;
//    }


    protected Recipe(Parcel in) {
        mRecipeName = in.readString();
        mRecipeId = in.readInt();
        mRecipeIngredients = in.createTypedArrayList(Ingredient.CREATOR);
        mRecipeServings = in.readInt();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mRecipeName);
        parcel.writeInt(mRecipeId);
        parcel.writeTypedList(mRecipeIngredients);
        parcel.writeInt(mRecipeServings);
    }

    public String getRecipeName() {
        return mRecipeName;
    }

    public List<Ingredient> getRecipeIngredients() {
        return mRecipeIngredients;
    }
}
