package com.example.noahkim.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Noah on 5/15/2017.
 */

public class Ingredient implements Parcelable {

    protected Ingredient(Parcel in) {
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
