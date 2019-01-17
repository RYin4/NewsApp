package com.example.rkjc.news_app_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.AssetManager;


//Have the service send a notification with a cancel action, and all needed strings/images.
// (To get icons/images, right click the drawable folder, select New/Vector asset.
// Click the image, other icons will be visible. Choose from those).
// The cancel action should be performed by an IntentService (see the Udacity Hydration App example covered in class) and dismiss the notification.
// It shouldn't cancel the job scheduling.

public class NotificationUtils {

    public static final int NOTIFICATION_ID = 001;
    public static final int NOTIFICATION_PENDING_ID = 002;
    private static final int CANCEL_PENDING_ID = 003;
    private static final String NOTIFICATION_CHANNEL_ID = "notification_channel";

    public static void clearAllNotifications(Context context) {

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancelAll();

    }

    public static void NotifySync(Context context) {

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "notification_channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("News Auto Sync Notification")
                .setContentText("News retrieved! Display has been updated.")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentIntent(contentIntent(context))
                .addAction(cancelAction(context))
                .setAutoCancel(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        Log.i("Notification Generator", "Notification Sent");
    }

    public static NotificationCompat.Action cancelAction(Context context) {

        Intent cancelIntent = new Intent(context, SyncIntentService.class);
        cancelIntent.setAction("0");
        PendingIntent cancelPendingIntent = PendingIntent.getService(context, CANCEL_PENDING_ID, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action cancelAct = new NotificationCompat.Action(R.drawable.ic_cancel_black_24dp, "Close Notification", cancelPendingIntent);
        return cancelAct;

    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                NOTIFICATION_PENDING_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_android_black_24dp);
        return largeIcon;
    }
}
