package com.example.petagram.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petagram.R;
import com.example.petagram.adapter.MascotaAdapter;
import com.example.petagram.pojo.Mascota;
import com.example.petagram.presenter.IListMascotasFragmentPresenter;
import com.example.petagram.presenter.ListMascotasFragmentPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class ListMascotasFragment extends Fragment implements IListMascotasFragmentView {
    private RecyclerView recyclerListMascotas;
    private SwipeRefreshLayout sfiMyIndicator;
    private IListMascotasFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_mascotas, container, false);
        sfiMyIndicator = v.findViewById(R.id.sfiMyIndicator);
        recyclerListMascotas = v.findViewById(R.id.rvMascotas);

        addFab(v);
        logicIndicator();
        presenter = new ListMascotasFragmentPresenter(this,getContext(),false);
        return v;
    }

    @SuppressLint("ResourceType")
    private void logicIndicator() {
        try {
            sfiMyIndicator.setOnRefreshListener(this::refreshContent);
        } catch (Exception e) {
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

    public void refreshContent() {
        try {
            presenter = new ListMascotasFragmentPresenter(this,getContext(),false);
            sfiMyIndicator.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new ListMascotasFragmentPresenter(this,getContext(),false);
    }

    @Override
    public void generateLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerListMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> pets) {
        MascotaAdapter adapter = new MascotaAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public void initAdapterMascota(MascotaAdapter adapter) {
        recyclerListMascotas.setAdapter(adapter);
    }
}