package com.example.variousstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

/*
*Vibrator라는 시스템 서비스 객체를 사용해서 진동을 제어할 수 있음
 -vibrate() 메서드의 파라미터로 long 타입 값이 전달됨(시간값, 진동을 얼마나 지속 시킬지 설정함)

*Ringtone 객체를 사용해서 API에서 제공하는 소리를 재생할 수 있음(직접 음원 파일 제작해서 사용 가능 -> MediaPlayer 객체 사용 가능)
 -play() 메서드로 소리를 울림
 */
public class SoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Button vibrateBtn = (Button)findViewById(R.id.vibrateBtn);
        vibrateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

                // 안드로이드 버전 26부터 vibrate() 메서드의 파라미터가 변경되었기 때문에
                if(Build.VERSION.SDK_INT >= 26) { // 최신 버전
                    vibrator.vibrate(VibrationEffect.createOneShot(1000, 50)); // 지속시간과 음량
                } else { // 구버전
                    vibrator.vibrate(1000);
                }
            }
        });

        Button soundBtn = (Button)findViewById(R.id.soundBtn);
        soundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // API의 기본 음원 참조
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
                ringtone.play();
            }
        });

        Button mediaSoundBtn = (Button)findViewById(R.id.mediaSoundBtn);
        mediaSoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                //player.start();
            }
        });
    }
}