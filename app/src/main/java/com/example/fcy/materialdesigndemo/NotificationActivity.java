package com.example.fcy.materialdesigndemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Created by fcy on 2017/9/5.
 */

public class NotificationActivity extends AppCompatActivity {
    private NotificationActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        activity=this;
        findViewById(R.id.basicNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(activity);
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                PendingIntent pendingIntent=PendingIntent.getActivity(activity,0,intent,0);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.jingpin));
                builder.setContentTitle("Title");
                builder.setContentText("Content");
                builder.setSubText("IT is reallay basic");
                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1001,builder.build());
            }
        });
        findViewById(R.id.expandNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RemoteViews contentView=new RemoteViews(getPackageName(),R.layout.notification);
                contentView.setTextViewText(R.id.textView,"show me when collapsed");

                Notification.Builder builder = new Notification.Builder(activity);
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                PendingIntent pendingIntent=PendingIntent.getActivity(activity,0,intent,0);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.jingpin));
                builder.setContentTitle("Title");
                builder.setContentText("Content");
                builder.setSubText("It is reallay basic");
                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification=builder.build();
                notification.contentView=contentView;
                notification.bigContentView=contentView;
                notificationManager.notify(1002,notification);
            }
        });
    }
}
