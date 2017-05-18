package com.example.noahkim.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Noah on 5/15/2017.
 */

public class Ingredient implements Parcelable {
    @SerializedName("quantity")
    private Float mQuantity;

    @SerializedName("measure")
    private String mMeasure;

    @SerializedName("ingredient")
    private String mIngredient;

    protected Ingredient(Parcel in) {
        mQuantity = in.readFloat();
        mMeasure = in.readString();
        mIngredient = in.readString();
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
        parcel.writeFloat(mQuantity);
        parcel.writeString(mMeasure);
        parcel.writeString(mIngredient);
    }

    public String getIngredientName() {
        return mIngredient;
    }

    public String getIngredientMeasure() {
        return mMeasure;
    }

    public Float getIngredientQuantity() {
        return mQuantity;
    }
}
