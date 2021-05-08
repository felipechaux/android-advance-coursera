package com.example.petagram.restApi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InstagramResponse {

    @SerializedName("data")
    private ArrayList<RecentUserInfo> recentUserInfos;


    public ArrayList<RecentUserInfo> getRecentUserInfos() {
        return recentUserInfos;
    }

    public void setRecentUserInfos(ArrayList<RecentUserInfo> recentUserInfos) {
        this.recentUserInfos = recentUserInfos;
    }
}

