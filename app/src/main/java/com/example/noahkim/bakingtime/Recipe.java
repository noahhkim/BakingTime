package com.example.noahkim.bakingtime;

/**
 * {@link Recipe} represents a recipe that the user can select from the menu.
 * It contains a recipe name.
 */

public class Recipe {

    private String mRecipeName;

    public Recipe(String recipeName) {
        mRecipeName = recipeName;
    }


    public String getRecipeName() {
        return mRecipeName;
    }
}
