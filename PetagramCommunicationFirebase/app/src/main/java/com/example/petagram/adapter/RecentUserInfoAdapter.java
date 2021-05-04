package com.example.petagram.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.R;
import com.example.petagram.restApi.model.RecentUserInfo;
import com.squareup.picasso.Picasso;

import java.security.SecureRandom;
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
            SecureRandom rand = new SecureRandom();
            int randomNum = rand.nextInt((40 - 12) + 1) + 12;

            RecentUserInfo user = userInfo.get(position);
            recentUserInfoViewHolder.tvLikes.setText(String.valueOf(randomNum));
            getImagenUrl(recentUserInfoViewHolder.imgUser, user.getMediaUrl());

        } catch (Exception e) {
            e.printStackTrace();
        }

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
