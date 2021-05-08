package com.example.petagram.restApi.adapter;

import com.example.petagram.restApi.ConstantsRestApi;
import com.example.petagram.restApi.PetagramService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {


    public PetagramService connectRestApi(){

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(ConstantsRestApi.TIME_OUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder().client(clientBuilder.build()).baseUrl(ConstantsRestApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(PetagramService.class);
    }
}
