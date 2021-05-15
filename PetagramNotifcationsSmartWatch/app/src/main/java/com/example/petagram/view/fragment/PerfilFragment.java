package com.example.petagram.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagram.CircleTransform;
import com.example.petagram.R;
import com.example.petagram.adapter.RecentUserInfoAdapter;
import com.example.petagram.presenter.IListPerfilFragmentPresenter;
import com.example.petagram.presenter.ListPerfilFragmentPresenter;
import com.example.petagram.restApi.model.RecentUserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements IListPerfilFragmentView {

    private ImageView imagePerfil;
    private RecyclerView recyclerPerfil;
    private TextView textViewNombre;
    private IListPerfilFragmentPresenter presenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view =inflater.inflate(R.layout.fragment_perfil_user, container, false);
        imagePerfil = view.findViewById(R.id.imagePerfil);
        textViewNombre=view.findViewById(R.id.textName);
        recyclerPerfil=view.findViewById(R.id.rvPerfil);

        addFab(view);
        loadImageBio();

        presenter = new ListPerfilFragmentPresenter(this,getContext());
        loadUserPreference();
        return view;
    }

    private void loadUserPreference() {
        try {
            SharedPreferences myPreference = getActivity().getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
            String user = myPreference.getString(getString(R.string.user_preference),getString(R.string.user_default));
            textViewNombre.setText(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void addFab(View v) {
        try {
            FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
            fab.setOnClickListener(view -> {
                Snackbar.make(view, "Foto", Snackbar.LENGTH_SHORT).show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadImageBio() {
        try {
            Picasso.get().load("https://www.shareicon.net/data/256x256/2016/09/15/829459_man_512x512.png").transform(new CircleTransform())
                    .into(imagePerfil);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void generateGridLayout() {
        GridLayoutManager llm = new GridLayoutManager(getActivity(),3);
        recyclerPerfil.setLayoutManager(llm);
    }

    @Override
    public RecentUserInfoAdapter createAdapter(ArrayList<RecentUserInfo> recentUserInfos) {
        RecentUserInfoAdapter adapter = new RecentUserInfoAdapter(recentUserInfos, getActivity());
        return adapter;
    }

    @Override
    public void initAdapterRecentUserInfo(RecentUserInfoAdapter adapter) {
        recyclerPerfil.setAdapter(adapter);
    }
}