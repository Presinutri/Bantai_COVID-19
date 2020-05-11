package com.practice.bantaicovid_19.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.practice.bantaicovid_19.MainActivity;
import com.practice.bantaicovid_19.R;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String EXTRA_TYPE = "type";
    public static final int REMINDER_ID = 101;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "Ayo baca tips tentang aktivitas apa saja yang dapat dilakukan dirumah selama pandemi";
        String title = "Inspirasi untuk beraktivitas dirumah";
        showNotification(context, title, message, REMINDER_ID);
    }

    private void showNotification(Context context, String title, String message, int ReminderId) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Daily Notification Channel";

        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, REMINDER_ID,
                contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_tips)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            builder.setChannelId(CHANNEL_ID);
            notificationManagerCompat.createNotificationChannel(channel);
        }

        Notification notification = builder.build();
        notificationManagerCompat.notify(ReminderId, notification);
    }
}
