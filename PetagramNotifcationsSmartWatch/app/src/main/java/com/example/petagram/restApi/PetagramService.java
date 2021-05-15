package com.example.petagram.restApi;

import com.example.petagram.restApi.model.InstagramResponse;
import com.example.petagram.restApi.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PetagramService {

    @GET(ConstantsRestApi.URL_GET_RECENT_INFO)
    Call<InstagramResponse> getInstagramInfo();

    @FormUrlEncoded
    @POST(ConstantsRestApi.URL_FIREBASE_REGISTER_USER)
    Call<UserResponse> registerTokenID(@Field("fcmToken") String fcToken,@Field("userIG") String userIG );

    @FormUrlEncoded
    @POST(ConstantsRestApi.URL_FIREBASE_REGISTER_LIKE_PHOTO)
    Call<UserResponse> registerLikePhoto(@Field("idPhotoIG") String idPhotoIG,@Field("userIG") String userIG,@Field("fcmToken") String fcmToken);

}
