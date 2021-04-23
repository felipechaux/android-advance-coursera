package com.example.petagram.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petagram.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mascota_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_contacto:
                startActivity(new Intent(BaseActivity.this,EnviarMailActivity.class));
                finish();
                return true;
            case R.id.item_acerca:
                startActivity(new Intent(BaseActivity.this,BioActivity.class));
                finish();
                return true;
            case R.id.item_configuracion_cuenta:
                startActivity(new Intent(BaseActivity.this,ConfigUserActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void configActionBar(Context context) {
        try {
            Toolbar toolbar = findViewById(R.id.myActionBar);
            setSupportActionBar(toolbar);
            ImageButton btnFavoritos = toolbar.findViewById(R.id.btnFavoritos);
            ImageButton btnHome = toolbar.findViewById(R.id.imgHuellita);
            btnFavoritos.setOnClickListener(view -> startActivity(new Intent(context, ListMascotaFavorita.class)));
            btnHome.setOnClickListener(view -> startActivity(new Intent(context, MascotasActivity.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
