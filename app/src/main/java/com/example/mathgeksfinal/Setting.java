package com.example.mathgeksfinal;

import static com.example.mathgeksfinal.AppController.StopSound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class Setting extends AppCompatActivity {

    private Context mContext;
    private boolean music;
    private Switch musicbox;

    public static final String KEY_MUTE_MUSIC="mute_switch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        musicbox = findViewById(R.id.music_checkbox);
        musicbox.setChecked(true);
        mContext = Setting.this;
        AppController.currentActivity = this;

        populateMusicEnalbeContents();


    }

    public void viewClickHandler(View view) {

        switch (view.getId()) {


            case R.id.music_checkbox:
                switchMusicEnableCheckbox();
                break;
        }

    }


        private void switchMusicEnableCheckbox() {

        music = !music;
        if (music){
            SettingPreference.setMusicEnableDisable(mContext,true);
            AppController.playMusic();

        }else {
            SettingPreference.setMusicEnableDisable(mContext,false);
            StopSound();
        }

        populateMusicEnalbeContents();

    }


    protected void populateMusicEnalbeContents(){

        if (SettingPreference.getMusicEnableDisable(mContext)){
            AppController.playMusic();
            musicbox.setChecked(true);
        }else {
            StopSound();
            musicbox.setChecked(false);
        }
        music = SettingPreference.getMusicEnableDisable(mContext);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}