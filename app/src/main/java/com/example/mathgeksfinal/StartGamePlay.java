package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Random;

public class StartGamePlay extends AppCompatActivity {

    private static int loadScreenTime = 4000;
    TextView timer;
    private boolean beginner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_play);

        SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(this);

        TextView hintText= findViewById(R.id.hintText);

        Random rd = new Random();
        switch (rd.nextInt(4)){
            case 0:
                hintText.setText(getString(R.string.time_resets));
                break;
            case 1:
                hintText.setText(getString(R.string.improve));
                break;
            case 2:
                hintText.setText(getString(R.string.difficulty));
                break;
            case 3:
                hintText.setText(getString(R.string.friends));
                break;
        }
        timer= findViewById(R.id.timeText);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),StartGameSolveAPic.class));
                finish();
            }
        },loadScreenTime);
        new CountDownTimer(loadScreenTime, 10000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(getString(R.string.loading_screen_timer, (int) millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }


    @Override
    public void onBackPressed() {

    }
}