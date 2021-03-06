package com.miris.hues.miris_hues_android.listener;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.miris.hues.miris_hues_android.tts.CognitiveTTS;

/**
 * Created by secret on 10/15/17.
 */

public class SnowNotificationListenerService extends NotificationListenerService {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NotificationListener", "[snowdeer] onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("NotificationListener", "[snowdeer] onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("NotificationListener", "[snowdeer] onDestroy()");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[snowdeer] onNotificationPosted() - " + sbn.toString());
        Log.i("NotificationListener", "[snowdeer] PackageName:" + sbn.getPackageName());
        Log.i("NotificationListener", "[snowdeer] PostTime:" + sbn.getPostTime());

        Notification notificatin = sbn.getNotification();
        Bundle extras = notificatin.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        int smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON);
        Bitmap largeIcon = ((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON));
        String text = extras.getString(Notification.EXTRA_TEXT);
        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

        Log.i("NotificationListener", "[snowdeer] Title:" + title);
        Log.i("NotificationListener", "[snowdeer] Text:" + text);
        Log.i("NotificationListener", "[snowdeer] Sub Text:" + subText);
        CognitiveTTS cognitiveTTS = new CognitiveTTS(this, text);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[snowdeer] onNotificationRemoved() - " + sbn.toString());
    }
}
