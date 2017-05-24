package com.example.noahkim.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Noah on 5/15/2017.
 */
public class Step implements Parcelable {
    @SerializedName("id")
    private int mStepId;

    @SerializedName("shortDescription")
    private String mStepShortDescription;

    @SerializedName("description")
    private String mStepDescription;

    @SerializedName("videoURL")
    private String mStepVideoUrl;

    protected Step(Parcel in) {
        mStepId = in.readInt();
        mStepShortDescription = in.readString();
        mStepDescription = in.readString();
        mStepVideoUrl = in.readString();
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
        parcel.writeInt(mStepId);
        parcel.writeString(mStepShortDescription);
        parcel.writeString(mStepDescription);
        parcel.writeString(mStepVideoUrl);
    }

    public int getStepId() {
        return mStepId;
    }

    public String getStepShortDescription() {
        return mStepShortDescription;
    }

    public String getStepDescription() {
        return mStepDescription;
    }

    public String getStepVideoUrl() {
        return mStepVideoUrl;
    }
}
