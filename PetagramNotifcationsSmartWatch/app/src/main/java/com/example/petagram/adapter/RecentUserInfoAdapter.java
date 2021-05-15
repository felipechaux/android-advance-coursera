package com.example.petagram.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.presenter.ListPerfilFragmentPresenter;
import com.example.petagram.restApi.model.RecentUserInfo;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecentUserInfoAdapter extends RecyclerView.Adapter<RecentUserInfoAdapter.RecentUserInfoViewHolder> {

    ArrayList<RecentUserInfo> userInfo;
    Activity activity;

    public RecentUserInfoAdapter(ArrayList<RecentUserInfo> userInfo, Activity activity) {
        this.userInfo = userInfo;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecentUserInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_list_user_info, parent, false);
        return new RecentUserInfoViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecentUserInfoViewHolder recentUserInfoViewHolder, int position) {
        try {
            //generar numeros aleatoreos - debido a que en la actual api no logre obtener los likes
            RecentUserInfo user = userInfo.get(position);
            recentUserInfoViewHolder.tvLikes.setText(String.valueOf(user.getLikes()));
            getImagenUrl(recentUserInfoViewHolder.imgUser, user.getMediaUrl());

            recentUserInfoViewHolder.imgUser.setOnClickListener(view -> doLike(user));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doLike(RecentUserInfo recentUserInfo) {
        int like = recentUserInfo.getLikes() + 1;
        recentUserInfo.setLikes(like);
        notifyDataSetChanged();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String getToken = task.getResult();
                    SharedPreferences myPreference = activity.getSharedPreferences(activity.getString(R.string.app_preference), Context.MODE_PRIVATE);
                    String userIG = myPreference.getString(activity.getString(R.string.user_preference), activity.getString(R.string.user_default));
                    ListPerfilFragmentPresenter presenter = new ListPerfilFragmentPresenter();
                    //registrar photo - el server enviara notificacion al confirmar el registro
                    presenter.registerLikeIG(activity, recentUserInfo.getId(), getToken, userIG);
                });
    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }

    private void getImagenUrl(ImageView image, String url) {
        try {
            Picasso.get().load(url).placeholder(R.drawable.no_image).into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class RecentUserInfoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUser;
        private TextView tvLikes;

        public RecentUserInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLikes = itemView.findViewById(R.id.textLikes);
            imgUser = itemView.findViewById(R.id.imgUser);
        }
    }
}
