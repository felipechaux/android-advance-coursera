package com.example.petagram.restApi.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("fcmToken")
    private String fcmToken;
    @SerializedName("userIG")
    private String userIG;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getUserIG() {
        return userIG;
    }

    public void setUserIG(String userIG) {
        this.userIG = userIG;
    }
}
