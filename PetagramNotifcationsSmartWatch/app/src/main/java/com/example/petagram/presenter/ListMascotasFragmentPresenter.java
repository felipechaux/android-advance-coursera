package com.example.petagram.presenter;

import android.content.Context;

import com.example.petagram.db.ConstructorPets;
import com.example.petagram.pojo.Mascota;
import com.example.petagram.view.fragment.IListMascotasFragmentView;

import java.util.ArrayList;

public class ListMascotasFragmentPresenter implements IListMascotasFragmentPresenter {

    private IListMascotasFragmentView iListMascotasFragmentView;
    private Context context;
    private ConstructorPets constructorPets;
    private ArrayList<Mascota> pets;

    public ListMascotasFragmentPresenter(IListMascotasFragmentView iListMascotasFragmentView, Context context, boolean isFavoritePets) {
        this.iListMascotasFragmentView = iListMascotasFragmentView;
        this.context = context;
        getBDPets(isFavoritePets);
    }

    @Override
    public void getBDPets(boolean isFavoritePets) {
        constructorPets = new ConstructorPets(context);
        if(isFavoritePets){
            pets = constructorPets.getFavoritePets();
        }else{
            pets = constructorPets.getData();
        }
        showPets();
    }

    @Override
    public void showPets() {
        iListMascotasFragmentView.initAdapterMascota(iListMascotasFragmentView.createAdapter(pets));
        iListMascotasFragmentView.generateLinearLayout();
    }
}
