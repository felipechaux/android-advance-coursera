package com.example.petagram.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.petagram.R;
import com.example.petagram.adapter.MascotaAdapter;
import com.example.petagram.pojo.Mascota;
import com.example.petagram.presenter.IListMascotasFragmentPresenter;
import com.example.petagram.presenter.ListMascotasFragmentPresenter;
import com.example.petagram.view.BaseActivity;
import com.example.petagram.view.MascotasActivity;
import com.example.petagram.view.fragment.IListMascotasFragmentView;

import java.util.ArrayList;

public class ListMascotaFavorita extends BaseActivity implements IListMascotasFragmentView {

    private SwipeRefreshLayout sfiMyIndicator;
    private RecyclerView recyclerListMascotasFavoritas;
    private IListMascotasFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mascota_favorita);

        configActionBar();

        sfiMyIndicator = findViewById(R.id.sfiMyIndicator);
        recyclerListMascotasFavoritas = findViewById(R.id.rvMascotasFavoritas);
        logicIndicator();
        presenter = new ListMascotasFragmentPresenter(this, this, true);
    }

    @SuppressLint("ResourceType")
    private void logicIndicator() {
        try {
            sfiMyIndicator.setOnRefreshListener(this::refreshContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshContent() {
        try {
            presenter = new ListMascotasFragmentPresenter(this, this, true);
            sfiMyIndicator.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configActionBar() {
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.myActionBar);
            setSupportActionBar(toolbar);
            ImageButton btnFavoritos = toolbar.findViewById(R.id.btnFavoritos);
            btnFavoritos.setVisibility(View.GONE);
            ImageButton btnHome = toolbar.findViewById(R.id.imgHuellita);
            btnHome.setOnClickListener(view -> startActivity(new Intent(this, MascotasActivity.class)));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //soporte para menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mascota_menu, menu);
        return true;
    }

    //soporte para navegacion back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void generateLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerListMascotasFavoritas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> favoritePets) {
        MascotaAdapter adapter = new MascotaAdapter(favoritePets, this);
        return adapter;
    }

    @Override
    public void initAdapterMascota(MascotaAdapter adapter) {
        recyclerListMascotasFavoritas.setAdapter(adapter);
    }
}