package com.example.petagram.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.petagram.R;
import com.example.petagram.SendMail;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

public class EnviarMailActivity extends BaseActivity {

    private TextInputEditText textName;
    private TextInputEditText textEmail;
    private TextInputEditText textMessage;
    private Button buttonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mail);

        textName = findViewById(R.id.txtName);
        textEmail = findViewById(R.id.txtEmail);
        textMessage = findViewById(R.id.txtMessage);
        buttonSend = findViewById(R.id.buttonSend);
        configActionBar(this);
        initListeners();
    }

    private void initListeners() {
        try {
            buttonSend.setOnClickListener(this::validateForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateForm(View view) {
        try {
            if (textName.getText().toString().equals("") || textEmail.getText().toString().equals("") || textMessage.getText().toString().equals("") ) {
                Snackbar.make(view, getBaseContext().getResources().getString(R.string.info_incomplete), LENGTH_SHORT)
                        .show();
            }else{
                enviarEmail();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void enviarEmail() {
        try {
            String email = textEmail.getText().toString().trim();
            String subject = getString(R.string.mail_asunto);
            String message = textMessage.getText().toString().trim();
            SendMail sm = new SendMail(this, email, subject, message);
            sm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}