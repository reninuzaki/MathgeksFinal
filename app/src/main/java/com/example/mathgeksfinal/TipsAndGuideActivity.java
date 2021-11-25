package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.zip.Inflater;

public class TipsAndGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView tip1,tip2,tip3,tip4,tip5,tip6,tip7,tip8;

    private MediaPlayer mediaplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_and_guide);

        setTitle("Activity 1");



        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);
        tip4 = findViewById(R.id.tip4);
        tip5 = findViewById(R.id.tip5);
        tip6 = findViewById(R.id.tip6);
        tip7 = findViewById(R.id.tip7);
        tip8 = findViewById(R.id.tip8);


        tip1.setOnClickListener(this);
        tip2.setOnClickListener(this);
        tip3.setOnClickListener(this);
        tip4.setOnClickListener(this);
        tip5.setOnClickListener(this);
        tip6.setOnClickListener(this);
        tip7.setOnClickListener(this);
        tip8.setOnClickListener(this);





        mediaplayer = MediaPlayer.create(this,R.raw.bensoundpsychedelic);
        mediaplayer.setLooping(true);
        mediaplayer.start();

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch(v.getId()){
            case R.id.tip1:
                i = new Intent(this,Tips1.class);
                startActivity(i);
                break;
            case R.id.tip2:
                i = new Intent(this,Tips2.class);
                startActivity(i);
                break;
            case R.id.tip4:
                i = new Intent(this,Tips4.class);
                startActivity(i);
                break;
            case R.id.tip5:
                i = new Intent(this,Guide11.class);
                startActivity(i);
                break;
            case R.id.tip3:
                i = new Intent(this,tips3.class);
                startActivity(i);
                break;
            case R.id.tip6:
                i = new Intent(this,tips5.class);
                startActivity(i);
                break;
            case R.id.tip7:
                i = new Intent(this,Tips7.class);
                startActivity(i);
                break;
            case R.id.tip8:
                i = new Intent(this,Tips8.class);
                startActivity(i);
                break;
            default:break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaplayer.stop();
        TipsAndGuideActivity.this.finish();
        Intent intent = new Intent(TipsAndGuideActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}