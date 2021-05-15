package com.example.petagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.petagram.view.MascotasActivity;
import com.example.petagram.view.fragment.PerfilFragment;

public class TouchWearBroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()){
            case ConstantsApp.VIEW_PROFILE_ACTION:
                Toast.makeText(context, "VIEW PROFILE", Toast.LENGTH_SHORT).show();
                viewProfileAction(context);
                break;

            case ConstantsApp.FOLLOW_UNFOLLOW_ACTION:
                Toast.makeText(context, "FOLLOW / UNFOLLOW", Toast.LENGTH_SHORT).show();
                break;

            case ConstantsApp.VIEW_USER_ACTION:
                Toast.makeText(context, "VIEW USER ", Toast.LENGTH_SHORT).show();
                viewUserAction(context);
                break;
        }

    }

    public void viewProfileAction(Context context) {
        Intent intent = new Intent(context, MascotasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    public void viewUserAction(Context context){
        Intent intent = new Intent(context, MascotasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(context.getString(R.string.view_user_action_intent),true);
        context.startActivity(intent);
    }
}

