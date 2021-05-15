package com.example.petagram.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.petagram.ConstantsApp;
import com.example.petagram.R;
import com.example.petagram.TouchWearBroadcast;
import com.example.petagram.view.BaseActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        Log.d(TAG, "Message Notification title: " + remoteMessage.getNotification().getTitle());

        launchNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }


    private void launchNotification(String title, String message) {
        try {

            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            String CHANNEL_ID = "principal_Channel";

            Intent viewProfileIntent = new Intent(this, TouchWearBroadcast.class);
            viewProfileIntent.setAction(ConstantsApp.VIEW_PROFILE_ACTION);
            Intent followUnfollowIntent = new Intent(this, TouchWearBroadcast.class);
            followUnfollowIntent.setAction(ConstantsApp.FOLLOW_UNFOLLOW_ACTION);
            Intent viewUserIntent = new Intent(this, TouchWearBroadcast.class);
            viewUserIntent.setAction(ConstantsApp.VIEW_USER_ACTION);

            PendingIntent pendingIntentProfile = PendingIntent.getBroadcast(this, 0, viewProfileIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action actionProfile = new NotificationCompat.Action.Builder(R.drawable.ic_touch, getString(R.string.view_profile_action)
                    , pendingIntentProfile).build();

            PendingIntent pendingIntentFollowUnfollow = PendingIntent.getBroadcast(this, 0, followUnfollowIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action actionFollowUnfollow = new NotificationCompat.Action.Builder(R.drawable.ic_touch, getString(R.string.follow_unfollow_action)
                    , pendingIntentFollowUnfollow).build();

            PendingIntent pendingIntentViewUser = PendingIntent.getBroadcast(this, 0, viewUserIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action actionViewUser = new NotificationCompat.Action.Builder(R.drawable.ic_touch, getString(R.string.view_user_action)
                    , pendingIntentViewUser).build();

            NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
            wearableExtender.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.bg_androidwear_notification));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setSound(sound)
                    .setContentIntent(pendingIntentProfile)
                    .setContentIntent(pendingIntentFollowUnfollow)
                    .setContentIntent(pendingIntentViewUser)
                    .extend(wearableExtender.addAction(actionProfile))
                    .extend(wearableExtender.addAction(actionFollowUnfollow))
                    .extend(wearableExtender.addAction(actionViewUser))
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(0, builder.build());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Notification for latest versions";
                String description = "Short description example";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                NotificationManager notificationManager2 = getSystemService(NotificationManager.class);
                notificationManager2.createNotificationChannel(channel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
    }
}
