package com.example.petagram.restApi.model;

import com.google.gson.annotations.SerializedName;

public class RecentUserInfo {

    @SerializedName("id")
    private String id;

    @SerializedName("media_url")
    private String mediaUrl;

    private int likes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

