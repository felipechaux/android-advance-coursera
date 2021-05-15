package com.example.petagram.view.fragment;

import com.example.petagram.adapter.MascotaAdapter;
import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

public interface IListMascotasFragmentView {

      public void generateLinearLayout();


      public MascotaAdapter createAdapter(ArrayList<Mascota> pets);


      public void initAdapterMascota(MascotaAdapter adapter);

}
