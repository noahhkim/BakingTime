package com.example.noahkim.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Noah on 5/15/2017.
 */
public class Step implements Parcelable{
    protected Step(Parcel in) {
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
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
