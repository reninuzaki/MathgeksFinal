package com.example.mathgeksfinal;

import static com.example.mathgeksfinal.ResultActivity.SHARED_PREFERRENCE;
import static com.example.mathgeksfinal.ResultActivity.SHARED_PREFERRENCE_HIGH_SCORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    int beginnerHighScore,motivationHighScore,videoProductionHighScore;

    TextView txtResultForBeginner,txtResultForMotivationS,txtResultForVideoProductionS;

    ImageView imgHomeScreen;

    private int highScore;

    private static final String SHARED_PREFERENCE = "shread_prefrence";

    public static final String SCOREPREFERENCE = "shared_preference";
    public static final String BEGINNER = "beginner_high_score_preference";
    public static final String ADVANCE = "motivation_high_score_preference";
    public static final String INTERMEDIATE = "video_production_high_score_preference";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        txtResultForMotivationS = findViewById(R.id.txtResultForMotivationS);
        txtResultForBeginner = findViewById(R.id.txtResultForProgramingS);
        txtResultForVideoProductionS = findViewById(R.id.txtResultForVideoProductionS);

        imgHomeScreen = findViewById(R.id.img_homeS);

        loadHighScore();

        Intent intent = getIntent();

        highScore = intent.getIntExtra(Constant.SCORE,0);

    }

    private void loadHighScore() {



        SharedPreferences sharedPreferencesBeginner = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);
        beginnerHighScore = sharedPreferencesBeginner.getInt(BEGINNER,0);
        txtResultForBeginner.setText(String.valueOf(highScore));
        Log.i("Beginner SCORE", " " + beginnerHighScore);
        // txtResultForBeginner.setText("" + beginnerHighScore);


        SharedPreferences sharedPreferencesVideoProduction = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        videoProductionHighScore = sharedPreferencesVideoProduction.getInt(ADVANCE,0);
        Log.i("Video Production SCORE", " " + videoProductionHighScore);
        txtResultForVideoProductionS.setText("" + videoProductionHighScore);


        SharedPreferences sharedPreferencesVideoMotivation = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        motivationHighScore = sharedPreferencesVideoMotivation.getInt(INTERMEDIATE,0);
        Log.i("Motivation SCORE", " " + motivationHighScore);
        txtResultForMotivationS.setText("" + motivationHighScore);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScoreActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}