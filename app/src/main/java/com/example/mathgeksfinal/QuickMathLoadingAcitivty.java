package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class QuickMathLoadingAcitivty extends AppCompatActivity {

    private static int loadingScreenTime = 4000;
    TextView timer;
    private Boolean addition;
    private Boolean subtraction;
    private Boolean multiplication;
    private Boolean division, kidsmode, mute, flashingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_play);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean quickMathTimer = sharedPref.getBoolean(SettingsActivityy.KEY_TIMER,false);

        TextView hintText = findViewById(R.id.hintText);
        Random rd = new Random();
        switch (rd.nextInt(4)){
            case 0:
                hintText.setText(getString(R.string.color));
                break;
            case 1:
                hintText.setText(getString(R.string.panic));
                break;
            case 2:
                hintText.setText(getString(R.string.wrongs));
                break;
            case 3:
                hintText.setText(getString(R.string.friends));
                break;
        }
        timer = findViewById(R.id.timeText);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),QuickPlay.class));
                finish();
            }
        },loadingScreenTime);
        new CountDownTimer(loadingScreenTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(getString(R.string.loading_screen_timer, (int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

            }
        }.start();

        addition = sharedPref.getBoolean(SettingsActivityy.KEY_ADDITION_ONLY_QUICKMATH,false);
        subtraction = sharedPref.getBoolean(SettingsActivityy.KEY_SUBTRACTION_ONLY_QUICKMATH,false);
        multiplication = sharedPref.getBoolean(SettingsActivityy.KEY_MULTIPLICATION_ONLY_QUICKMATH,false);
        division = sharedPref.getBoolean(SettingsActivityy.KEY_DIVISION_ONLY_QUICKMATH,false);
        mute = sharedPref.getBoolean(SettingsActivityy.KEY_MUTE_MUSIC,false);
        flashingText = sharedPref.getBoolean(SettingsActivityy.KEY_FLASHING_TEXT,true);
        if(!addition || !subtraction || !multiplication || !division || kidsmode) {
            Toasty.error(QuickMathLoadingAcitivty.this,"High Score ranking disabled- check your settings", Toast.LENGTH_LONG,true).show();
        }
    }
    @Override
    public void onBackPressed() {

    }
}