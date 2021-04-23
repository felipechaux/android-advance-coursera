package com.example.petagram.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.petagram.restApi.InstagramApi;
import com.example.petagram.restApi.adapter.RestApiAdapter;
import com.example.petagram.restApi.model.InstagramResponse;
import com.example.petagram.restApi.model.RecentUserInfo;
import com.example.petagram.view.fragment.IListPerfilFragmentView;

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


    @Override
    public void getInstagramInfo() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        InstagramApi InstagramApi=restApiAdapter.connectRestApiInstagram();
        Call<InstagramResponse> instagramResponseCall= InstagramApi.getInstagramInfo();
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
                Log.e("Fallo la conexion",t.toString());
            }
        });
    }

    @Override
    public void showProfileInfo() {
        IListPerfilFragmentView.initAdapterRecentUserInfo(IListPerfilFragmentView.createAdapter(recentUserInfos));
        IListPerfilFragmentView.generateGridLayout();
    }
}
