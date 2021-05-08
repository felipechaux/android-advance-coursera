package com.example.petagram.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.petagram.R;
import com.example.petagram.restApi.PetagramService;
import com.example.petagram.restApi.adapter.RestApiAdapter;
import com.example.petagram.restApi.model.InstagramResponse;
import com.example.petagram.restApi.model.RecentUserInfo;
import com.example.petagram.restApi.model.UserResponse;
import com.example.petagram.view.fragment.IListPerfilFragmentView;

import java.security.SecureRandom;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPerfilFragmentPresenter implements IListPerfilFragmentPresenter {

    private IListPerfilFragmentView IListPerfilFragmentView;
    private Context context;
    private ArrayList<RecentUserInfo> recentUserInfos;


    public ListPerfilFragmentPresenter(IListPerfilFragmentView iListPerfilFragmentView, Context context) {
        this.IListPerfilFragmentView = iListPerfilFragmentView;
        this.context = context;
        getInstagramInfo();
    }

    public ListPerfilFragmentPresenter() {

    }

    @Override
    public void getInstagramInfo() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        PetagramService InstagramApi = restApiAdapter.connectRestApi();
        Call<InstagramResponse> instagramResponseCall = InstagramApi.getInstagramInfo();
        instagramResponseCall.enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> call, Response<InstagramResponse> response) {
                InstagramResponse instagramResponse = response.body();
                if (response.code() == 200 && instagramResponse != null) {
                    recentUserInfos = instagramResponse.getRecentUserInfos();
                    showProfileInfo();
                } else {
                    System.out.println("error ");
                }
            }

            @Override
            public void onFailure(Call<InstagramResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso en la conexion ", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexion", t.toString());
            }
        });
    }

    public void sendTokenToServer(Activity activity, String fcmToken, String userIG) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        PetagramService petagramService = restApiAdapter.connectRestApi();
        Call<UserResponse> userResponseCall = petagramService.registerTokenID(fcmToken, userIG);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if ((response.code() == 200) && (userResponse != null)) {
                    Toast.makeText(activity, activity.getString(R.string.success_register), Toast.LENGTH_SHORT).show();
                    Toast.makeText(activity, activity.getString(R.string.fcm_token) + " " + userResponse.getFcmToken() + " \n" + activity.getString(R.string.user_register) + " " + userResponse.getUserIG(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, activity.getString(R.string.not_success_register), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(activity, activity.getString(R.string.not_success_register) + " " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void registerLikeIG(Activity activity,String idPhotoIG, String fcmToken, String userIG) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        PetagramService petagramService = restApiAdapter.connectRestApi();
        Call<UserResponse> userResponseCall = petagramService.registerLikePhoto(idPhotoIG, userIG,fcmToken);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if ((response.code() == 200) && (userResponse != null)) {
                    Toast.makeText(activity, activity.getString(R.string.success_register_like), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, activity.getString(R.string.not_success_register), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(activity, activity.getString(R.string.not_success_register) + " " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showProfileInfo() {
        IListPerfilFragmentView.initAdapterRecentUserInfo(IListPerfilFragmentView.createAdapter(recentUserInfos));
        IListPerfilFragmentView.generateGridLayout();
    }
}
