package com.example.final_project;

import static com.example.final_project.R.layout.activity_display_message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        StringBuffer sbf = new StringBuffer(message);
        sbf.reverse();

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(sbf);
        createNotificationChannel();
        makeNotification("OnCreate");

    }
    @Override
    protected void onDestroy() {
        makeNotification("onDestroy");
        super.onDestroy();
    }

    public void BackToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        onDestroy();
    }




    String getDateTime() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        return String.format(Locale.getDefault(), "%04d-%02d-%02d %02d:%02d:%02d:%03d",
                year, month, day, hours, minutes, seconds,currentTimeMillis%1000);
    }

    NotificationManager notifyManager;
    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("channel_ID", getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(getLocalClassName());
        notifyManager = getSystemService(NotificationManager.class);
        notifyManager.createNotificationChannel(channel);
    }
    protected void makeNotification(String methodName)
    {
        String stringTime = getDateTime();
        String stringClassName = getClass().getSimpleName();

        Notification notify = new Notification.Builder(this, "channel_ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(stringClassName)
                .setContentText(methodName + ": " + stringTime)
                .build();
        notifyManager.notify((int)System.currentTimeMillis(), notify);
    }





}