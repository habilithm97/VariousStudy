package com.example.variousstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

/*
*상단 알림
 -다른 사람들로부터 메세지를 받았을 때나 단말의 상태를 표시할 때 사용함
 -앱이 실행되지 않은 상태에서도 사용자에게 메세지가 왔다는 것을 알려주어야하는데,
 -> 이럴 때에는 백그라운드에서 동작하는 서비스에서 알림을 표시하면됨

 -알림은 NotificationCompat.Builder 객체를 이용해서 만든 Notification 객체로 만들 수 있고,
 -> NotificationManager 시스템 서비스를 이용해서 화면 상단에 띄울 수 있음
 */

public class NotifyActivity extends AppCompatActivity {

    NotificationManager manager;

    private static String CHANNEL_ID = "channel_id";
    private static String CHANNEL_NAME = "channel_name";

    private static String CHANNEL_ID2 = "channel_id2";
    private static String CHANNEL_NAME2 = "channel_name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotify2();
            }
        });

        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotify();
            }
        });
    }

    public void showNotify2() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); // 매니저 객체 참조

        NotificationCompat.Builder builder = null; // 빌더 객체 생성

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 버전 이후라면
            if(manager.getNotificationChannel(CHANNEL_ID2) == null) { // 알림 채널이 널이면
                manager.createNotificationChannel(new NotificationChannel( // 채널 생성
                        CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_DEFAULT
                ));

                builder = new NotificationCompat.Builder(this, CHANNEL_ID2); // 빌더 객체 생성
            } else {
                builder = new NotificationCompat.Builder(this);
            }

            Intent intent = new Intent(this, NotifyActivity.class);
            // PendingIntent를 만들어서 Notification 객체를 만들 때 설정할 것임
            // 여기서 PendingIntent란? 인텐트와 비슷하지만 시스템에서 대기하는 역할을 하고, 원하는 상황이 만들어졌을 때 시스템에 의해 해석되고 처리됨
            // (시스템에서 즉시 해석하고 처리하지만 원하는 상황이 올 때까지는 보관하고 있게됨)
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            // 빌더 객체가 생성되면 알림 정보 설정 가능
            builder.setContentTitle("상단 알림 테스트");
            builder.setContentText("알림 메시지입니다. ");
            builder.setSmallIcon(R.drawable.notify);

            builder.setAutoCancel(true); // 알림 클릭 시 삭제됨
            builder.setContentIntent(pendingIntent); // 빌더에 PendingIntent 객체 설정

            Notification notify = builder.build(); // Notification 객체 생성

            manager.notify(2, notify); // 매니저 객체로 Notification 객체를 파라미터로 전달하면 상단에 알림이 띄워짐
        }
    }

    public void showNotify() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); // 매니저 객체 참조

        NotificationCompat.Builder builder = null; // 빌더 객체 생성

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 버전 이후라면
            if(manager.getNotificationChannel(CHANNEL_ID) == null) { // 알림 채널이 널이면
                manager.createNotificationChannel(new NotificationChannel( // 채널 생성
                        CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
                ));

                builder = new NotificationCompat.Builder(this, CHANNEL_ID); // 빌더 객체 생성
            } else {
                builder = new NotificationCompat.Builder(this);
            }
            // 빌더 객체가 생성되면 알림 정보 설정 가능
            builder.setContentTitle("상단 알림 테스트");
            builder.setContentText("알림 메시지입니다. ");
            builder.setSmallIcon(R.drawable.notify);

            Notification notify = builder.build(); // Notification 객체 생성

            manager.notify(1, notify); // 매니저 객체로 Notification 객체를 파라미터로 전달하면 상단에 알림이 띄워짐
        }
    }
}