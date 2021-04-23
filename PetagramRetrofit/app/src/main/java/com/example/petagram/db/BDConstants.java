package com.example.petagram.db;

//final / nadie puede alterar datos, clase constante
public final class BDConstants {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PET = "mascota";
    public static final String TABLE_PET_ID = "id_mascota";
    public static final String TABLE_PET_NAME = "nombre_macota";
    public static final String TABLE_PET_PHOTO_URL = "photo_macota_url";

    public static final String TABLE_BONE_PET = "huesitos_mascota";
    public static final String TABLE_BONE_PET_ID = "id_huesito_mascota";
    public static final String TABLE_BONE_PET_ID_PET = "id_mascota";
    public static final String TABLE_BONE_PET_NUMBER = "huesito_number";

    //se tienen en cuenta ultimas 5 mascotas para mostrar
}
