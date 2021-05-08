package com.example.petagram.restApi.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("idPhotoIG")
    private String idPhotoIG;
    @SerializedName("userIG")
    private String userIG;
    @SerializedName("fcmToken")
    private String fcmToken;

    public String getIdPhotoIG() {
        return idPhotoIG;
    }

    public void setIdPhotoIG(String idPhotoIG) {
        this.idPhotoIG = idPhotoIG;
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
