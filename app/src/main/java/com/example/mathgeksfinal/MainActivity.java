package com.example.mathgeksfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button bton_beginner;
    Button bton_tipsandguide;
    Button leadrboard;

    int totalCount, length;


    ImageButton about2;

    private MediaPlayer mediaPlayer;
    private boolean mute;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private Button muteButton;

    public static Context context;

    SharedPreferences sharedPref;

    Button btquickplay;

    Button settings;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    ImageButton musicbuton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bton_beginner = findViewById(R.id.bton_beginner);

        bton_tipsandguide = findViewById(R.id.bt_tipsandguides);

        leadrboard = findViewById(R.id.muteButton);


        about2 = findViewById(R.id.about);

        settings = findViewById(R.id.settings);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        ConstraintLayout constraintLayout = findViewById(R.id.mainlayout);


        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();






        bton_beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });

        bton_tipsandguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TipsAndGuideActivity.class);
                startActivity(intent);
            }
        });



        leadrboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(intent);
            }
        });



        mediaPlayer = MediaPlayer.create(this,R.raw.up_your_street);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();





        about2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,about.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });


        }



    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer.isPlaying() && !mute){
            mediaPlayer.pause();
            length = mediaPlayer.getCurrentPosition();
        }    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!mediaPlayer.isPlaying() && !mute) {
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);

        View view = getLayoutInflater().inflate(R.layout.alert_dialog_back,null);

        Button cancelB = view.findViewById(R.id.cancelB);
        Button confirm = view.findViewById(R.id.confirmB);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                MainActivity.this.finish();
                MainActivity.super.onBackPressed();
            }
        });
        alertDialog.show();
    }
}
