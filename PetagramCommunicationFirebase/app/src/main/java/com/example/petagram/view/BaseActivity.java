package com.example.petagram.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petagram.R;
import com.example.petagram.presenter.ListMascotasFragmentPresenter;
import com.example.petagram.presenter.ListPerfilFragmentPresenter;
import com.example.petagram.restApi.adapter.RestApiAdapter;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mascota_menu, menu);
        mContext = this;

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_contacto:
                startActivity(new Intent(BaseActivity.this, EnviarMailActivity.class));
                finish();
                return true;
            case R.id.item_acerca:
                startActivity(new Intent(BaseActivity.this, BioActivity.class));
                finish();
                return true;
            case R.id.item_configuracion_cuenta:
                startActivity(new Intent(BaseActivity.this, ConfigUserActivity.class));
                finish();
                return true;

            case R.id.item_notifications:
                sendToken();
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

    public void sendToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String getToken = task.getResult();
                    Log.d("token es", getToken);
                    ListPerfilFragmentPresenter presenter = new ListPerfilFragmentPresenter();
                    SharedPreferences myPreference = this.getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
                    String userIG = myPreference.getString(getString(R.string.user_preference), getString(R.string.user_default));

                    presenter.sendTokenToServer(this,getToken,userIG);

                });
    }


}
