package com.example.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.petagram.pojo.Mascota;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(@Nullable Context context) {
        super(context, BDConstants.DATABASE_NAME, null, BDConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            String queryCreateTablePet = "CREATE TABLE " + BDConstants.TABLE_PET + " ( " +
                    BDConstants.TABLE_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BDConstants.TABLE_PET_NAME + " TEXT," +
                    BDConstants.TABLE_PET_PHOTO_URL + " TEXT" +
                    ")";

            String queryCreateTablePetBones = "CREATE TABLE " + BDConstants.TABLE_BONE_PET + " ( " +
                    BDConstants.TABLE_BONE_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BDConstants.TABLE_BONE_PET_ID_PET + " INTEGER," +
                    BDConstants.TABLE_BONE_PET_NUMBER + " INTEGER," +
                    "FOREIGN KEY (" + BDConstants.TABLE_BONE_PET_ID_PET + ")" +
                    "REFERENCES " + BDConstants.TABLE_PET + "(" + BDConstants.TABLE_PET_ID + ")" +
                    ")";

            sqLiteDatabase.execSQL(queryCreateTablePet);
            sqLiteDatabase.execSQL(queryCreateTablePetBones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BDConstants.TABLE_PET);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BDConstants.TABLE_BONE_PET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Mascota> getAllPets() {
        ArrayList<Mascota> pets = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "SELECT * FROM " + BDConstants.TABLE_PET;
            Cursor data = db.rawQuery(query, null);
            while (data.moveToNext()) {
                Mascota currentPet = new Mascota();
                currentPet.setId(data.getInt(0));
                currentPet.setNombre(data.getString(1));
                currentPet.setUrlImage(data.getString(2));

                String queryBones = "SELECT COUNT("+ BDConstants.TABLE_BONE_PET_NUMBER +") as BONES" +
                        " FROM " + BDConstants.TABLE_BONE_PET+
                        " WHERE " + BDConstants.TABLE_BONE_PET_ID_PET + " = " + currentPet.getId();

                Cursor regBones = db.rawQuery(queryBones, null);
                if (regBones.moveToNext()) {
                    currentPet.setHuesitos(regBones.getInt(0));
                } else {
                    currentPet.setHuesitos(0);
                }
                pets.add(currentPet);
            }
            //cerrar conexion
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return pets;
    }

    public ArrayList<Mascota> getLastFivePets() {
        ArrayList<Mascota> pets = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "SELECT * FROM " + BDConstants.TABLE_PET;
            Cursor data = db.rawQuery(query, null);
            while (data.moveToNext()) {
                Mascota currentPet = new Mascota();
                currentPet.setId(data.getInt(0));
                currentPet.setNombre(data.getString(1));
                currentPet.setUrlImage(data.getString(2));

                String queryBones = "SELECT COUNT("+ BDConstants.TABLE_BONE_PET_NUMBER +") as BONES" +
                        " FROM " + BDConstants.TABLE_BONE_PET+
                        " WHERE " + BDConstants.TABLE_BONE_PET_ID_PET + " = " + currentPet.getId()+
                        " LIMIT 5";

                Cursor regBones = db.rawQuery(queryBones, null);
                if (regBones.moveToNext()) {
                    // al tener al menos un like quedara en la lista de mascotas favoritas
                    if(regBones.getInt(0)>0){
                        currentPet.setHuesitos(regBones.getInt(0));
                        //solo agregar 5 mascotas a la lista de favoritos
                        if(pets.size()<=4){
                            pets.add(currentPet);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return pets;
    }


    public void insertBonePet(ContentValues contentValues) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(BDConstants.TABLE_BONE_PET, null, contentValues);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPet(String name, String photo) {
        try {
            //si la mascota ya existe - no se guarda en bd
            if (!isPetExist(name)) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(BDConstants.TABLE_PET_NAME, name);
                contentValues.put(BDConstants.TABLE_PET_PHOTO_URL, photo);
                db.insert(BDConstants.TABLE_PET, null, contentValues);
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBonesPet(Mascota pet) {
        int likes = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String query = "SELECT COUNT(" + BDConstants.TABLE_BONE_PET_NUMBER + ") " +
                    " FROM " + BDConstants.TABLE_BONE_PET +
                    " WHERE " + BDConstants.TABLE_BONE_PET_ID_PET + "=" + pet.getId();
            Cursor reg = db.rawQuery(query, null);

            if (reg.moveToNext()) {
                likes = reg.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return likes;
    }

    //validacion si existe mascota
    private boolean isPetExist(String value) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        try {
            String query = "SELECT * FROM " + BDConstants.TABLE_PET + " WHERE " + BDConstants.TABLE_PET_NAME + " = ?";
            String[] whereArgs = {value};
            cursor = db.rawQuery(query, whereArgs);
            count = cursor.getCount();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return count >= 1;
    }

}
