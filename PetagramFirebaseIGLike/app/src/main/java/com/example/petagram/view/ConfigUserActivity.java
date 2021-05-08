package com.example.petagram.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.petagram.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

public class ConfigUserActivity extends BaseActivity {

    private TextInputEditText textName;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_user);

        textName = findViewById(R.id.txtName);
        buttonSave = findViewById(R.id.buttonSave);
        configActionBar(this);
        initListeners();
    }

    private void initListeners() {
        try {
            buttonSave.setOnClickListener(this::validateForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateForm(View view) {
        try {
            if (Objects.requireNonNull(textName.getText()).toString().equals("") ) {
                Snackbar.make(view, getBaseContext().getResources().getString(R.string.info_incomplete_user), LENGTH_SHORT)
                        .show();
            }else{
                saveUserPreference(view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void saveUserPreference(View view){
        try {
            //mode private - sobrescribir datos
            SharedPreferences myPreference = getSharedPreferences(getString(R.string.app_preference), Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = myPreference.edit();
            String name = Objects.requireNonNull(textName.getText()).toString();
            edit.putString(getString(R.string.user_preference),name);
            edit.apply();
            Snackbar.make(view, getBaseContext().getResources().getString(R.string.user_saved_sucess), LENGTH_SHORT)
                    .show();
            textName.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}