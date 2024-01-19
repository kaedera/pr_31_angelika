package com.example.pr_31_angelika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GestureDetectorCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    Button btnNotification, btnNotification2, btnNotification3;
    private GestureDetectorCompat mDetector;
    String ID_CHANEL ="ID_NOTIFICATION";
    NotificationManager manager;
    NotificationChannel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnNotification = findViewById(R.id.btnNot);
        btnNotification.setOnClickListener(this);

        btnNotification2 = findViewById(R.id.btnNot2);
        btnNotification2.setOnClickListener(this);

        btnNotification3 = findViewById(R.id.btnNot3);
        btnNotification3.setOnClickListener(this);

        MainActivity3.MyGestureListener gestureListener = new MainActivity3.MyGestureListener();
        mDetector = new GestureDetectorCompat(this, gestureListener);

        channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    ID_CHANEL,
                    "NameChanel",
                    NotificationManager.IMPORTANCE_DEFAULT);
        }
        manager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNot) {
            Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                    .setContentTitle("Котик за компьютером")
                    .setContentText("Картинка котика")
                    .setSmallIcon(R.drawable.cat)
                    .setColor(Color.DKGRAY)
                    .build();//собранное уведомление
            manager.notify(1, notification);
        }
        else if (view.getId() == R.id.btnNot2){
            Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                    .setContentTitle("Калькулятор")
                    .setContentText("Картинка калькулятора")
                    .setSmallIcon(R.drawable.calculator)
                    .setColor(Color.DKGRAY)
                    .build();//собранное уведомление
            manager.notify(2, notification);
        }
        else if (view.getId() == R.id.btnNot3){
            Notification notification = new NotificationCompat.Builder(this, ID_CHANEL)
                    .setContentTitle("Вам пришли деньги!")
                    .setContentText("Деньги с неба")
                    .setSmallIcon(R.drawable.money)
                    .setColor(Color.DKGRAY)
                    .build();//собранное уведомление
            manager.notify(3, notification);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if ((event1.getY() - event2.getY() > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)) {
                // Свайп вниз
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
            return super.onFling(event1, event2, velocityX, velocityY);
        }
    }
}