package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {

    TextView txtHighScore;
    TextView txtTotalQuizQues,txtCorrectQues,txtWrongQues;

    Button btStartQuiz;
    Button btMainMenu;
    Button btldrboard;
    Button btsendtonxtlvl;

    String categoryValue;

    int BeginnerHighScore;

    private int highScore;
    public static final String SHARED_PREFERRENCE = "shread_prefrence";
    public static final String SHARED_PREFERRENCE_HIGH_SCORE = "shread_prefrence_high_score";

    public static final String SCOREPREFERENCE = "shared_preference";
    public static final String BEGINNER = "beginner_high_score_preference";
    public static final String ADVANCE = "motivation_high_score_preference";
    public static final String INTERMEDIATE = "video_production_high_score_preference";

    private long backPressedTime;

    String CategoryAgainValue = "";
    int levelsId = 0;



    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btMainMenu = findViewById(R.id.result_bt_mainmenu);
        btStartQuiz = findViewById(R.id.result_bt_playAgain);
        txtHighScore = findViewById(R.id.result_text_High_Score);
        txtTotalQuizQues = findViewById(R.id.result_total_Ques);
        txtCorrectQues = findViewById(R.id.result_Correct_Ques);
        txtWrongQues = findViewById(R.id.result_Wrong_Ques);
        btldrboard = findViewById(R.id.leadrboard);
        btsendtonxtlvl = findViewById(R.id.sendtonextlvl);

        Intent intent = getIntent();

        categoryValue = intent.getStringExtra("Category");



        highScore = intent.getIntExtra(Constant.SCORE,0);

        if(categoryValue.equals("Beginner")){
            UPDATEBeginnerHighScore(highScore);
        }

        btMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this,CategoryActivity.class);
                ResultActivity.this.finish();
                startActivity(intent);
                ResultActivity.this.finish();

            }
        });

        btStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ResultActivity.this,QuizActivity.class);
                intent.putExtra("Category",CategoryAgainValue);
                intent.putExtra("Level",levelsId);
                startActivity(intent);
                finish();
            }
        });

        loadHighScore();


        int score = intent.getIntExtra("UserScore",0);
        int totalQuestion = intent.getIntExtra("TotalQuestion",0);
        int correctQues = intent.getIntExtra("CorrectQues",0);
        int wrongQues = intent.getIntExtra("WrongQues",0);

        String CategoryAgainValue = intent.getStringExtra("Category");
        levelsId = intent.getIntExtra("Level",0);


        txtTotalQuizQues.setText(String.valueOf(totalQuestion));
        txtCorrectQues.setText(String.valueOf(correctQues));
        txtWrongQues.setText(String.valueOf(wrongQues));

        if (score > highScore){

            updatHighScore(score);
        }

        btldrboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,ScoreActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btsendtonxtlvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryValue.equals("Beginner")){
                    Intent intent = new Intent(ResultActivity.this,BeginnerLevelActivity.class);
                    intent.putExtra("Category",categoryValue);
                    startActivity(intent);
                    finish();
                }else if (categoryValue.equals("Advance")){
                    Intent intent = new Intent(ResultActivity.this,AdvanceLevelActivity.class);
                    intent.putExtra("Category",categoryValue);
                    startActivity(intent);
                    finish();
                }else if (categoryValue.equals("Intermediate")){
                    Intent intent = new Intent(ResultActivity.this,IntermediateLevelActivity.class);
                    intent.putExtra("Category",categoryValue);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void updatHighScore(int newHighScore) {

        highScore = newHighScore;
        txtHighScore.setText(String.valueOf(highScore));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERRENCE,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERRENCE_HIGH_SCORE,highScore);
        editor.apply();


    }

    private void loadHighScore() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERRENCE,MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERRENCE_HIGH_SCORE,0);
        txtHighScore.setText(String.valueOf(highScore));

        SharedPreferences sharedPreferencesPrograming = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        BeginnerHighScore = sharedPreferencesPrograming.getInt(BEGINNER,0);
        Log.i("Programing SCORE", " " + BeginnerHighScore);

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){

            Intent intent = new Intent(ResultActivity.this,CategoryActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "Press Again to go Category", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void UPDATEBeginnerHighScore(int scoreToUpdate){
        if(scoreToUpdate > BeginnerHighScore){

            BeginnerHighScore = scoreToUpdate;
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERRENCE,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(BEGINNER,BeginnerHighScore);
            editor.apply();
        }
    }
}