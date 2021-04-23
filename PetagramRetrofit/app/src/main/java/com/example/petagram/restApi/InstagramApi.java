package com.example.petagram.restApi;

import com.example.petagram.restApi.model.InstagramResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstagramApi {

    @GET(ConstantsRestApi.URL_GET_RECENT_INFO)
    Call<InstagramResponse> getInstagramInfo();

}
