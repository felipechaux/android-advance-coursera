package com.example.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

//interactor
public class ConstructorPets {

    private static final int BONE = 1;
    private Context context;

    public ConstructorPets(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> getData() {
        DataBase db = new DataBase(context);
        try {
            insertPets(db);
        }catch (Exception e){
            e.printStackTrace();
        }
        return db.getAllPets();
    }

    public ArrayList<Mascota> getFavoritePets() {
        DataBase db = new DataBase(context);
        return db.getLastFivePets();
    }

    public void insertPets(DataBase db) {
        try {
            db.insertPet("Fredo", "https://png.pngtree.com/png-clipart/20190411/ourlarge/pngtree-kirky-dog-hand-painted-illustration-elements-png-image_924470.jpg");
            db.insertPet("Rami", "https://png.pngtree.com/png-clipart/20190408/ourlarge/pngtree-hand-painted-elements-of-crouching-wolf-and-dog-png-image_926556.jpg");
            db.insertPet("Feli", "https://png.pngtree.com/element_origin_min_pic/00/03/16/075688f7c3a6be5.jpg");
            db.insertPet("Pelusa", "https://png.pngtree.com/png-clipart/20190411/ourlarge/pngtree-kirkys-hand-painted-elements-for-pet-dogs-png-image_924657.jpg");
            db.insertPet("Max", "https://png.pngtree.com/element_origin_min_pic/17/07/21/103c21b14dba749cb1aee8bb17e18185.jpg");
            db.insertPet("BlackMens", "https://png.pngtree.com/png-clipart/20210119/ourlarge/pngtree-png-dog-image-png-image_2763385.jpg");
            db.insertPet("Navideño", "https://png.pngtree.com/png-vector/20201028/ourlarge/pngtree-golden-retriever-dog-wearing-a-santa-hat-for-christmas-vector-illustration-png-image_2381030.jpg");
            db.insertPet("Mins", "https://png.pngtree.com/png-vector/20200205/ourlarge/pngtree-adorable-puppy-dog-cute-with-ribbon-watercolor-illustration-png-image_2141717.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doBone(Mascota contact) {
        try{
            DataBase db = new DataBase(context);
            ContentValues contentValues = new ContentValues();
            contentValues.put(BDConstants.TABLE_BONE_PET_ID_PET, contact.getId());
            contentValues.put(BDConstants.TABLE_BONE_PET_NUMBER, BONE);
            db.insertBonePet(contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getBonesPet(Mascota pet){
        DataBase db = new DataBase(context);
        return db.getBonesPet(pet);
    }

}
