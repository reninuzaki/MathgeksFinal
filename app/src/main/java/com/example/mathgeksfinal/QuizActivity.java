package com.example.mathgeksfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;



    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;


    private ArrayList<Questions> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Questions currentQuestions;
    private boolean answerd;


    private final Handler handler = new Handler();

    private long mPauseTimeRemaining;


    private int correctAns = 0, wrongAns = 0;

    private TimerDialog timerDialog;
    private CorrectDialog correctDialog;
    private WrongDialog wrongDialog;

    private PlayAudioForAnswers playAudioForAnswers;

    int FLAG = 0;

    int score =0;

    private int totalSizeofQuiz=0;

    private static final long COUNTDOWN_IN_MILLIS = 50000;
    private CountDownTimer countDownTimer;
    private long timeleftinMillis;

    private long backPressedTime;

    private String categoryValue="";
    private int levelsID = 0;

    int UNLOCK_CL2 = 0, UNLOCK_CL3 = 0;
    int UNLOCK_HL2 = 0, UNLOCK_HL3 = 0;

    private MediaPlayer mediaplayer;

    ImageView imgLifeline1;
    ImageView imgLifeline2;
    ImageView imgLifeline3;

    int LifeLineVal = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setupUI();

        Intent intentCategorywithLevel = getIntent();
        categoryValue = intentCategorywithLevel.getStringExtra("Category");
        levelsID = intentCategorywithLevel.getIntExtra("Level",0);


        fetchDB();
        Log.i("BUGBUG","onCreate() in QuizActivity");




        timerDialog = new TimerDialog(this);
        correctDialog = new CorrectDialog(this);
        wrongDialog = new WrongDialog(this);
        playAudioForAnswers = new PlayAudioForAnswers(this);

        mediaplayer = MediaPlayer.create(this,R.raw.littleidea);
        mediaplayer.setLooping(true);
        mediaplayer.start();

        imgLifeline1 = findViewById(R.id.imgLifeLine1);
        imgLifeline2 = findViewById(R.id.imgLifeLine2);
        imgLifeline3 = findViewById(R.id.imgLifeLine3);

        if(isFirstTime()){
            countDownTimer.cancel();
            textViewCountDown.setText("30s");
            ShowcaseConfig config = new ShowcaseConfig();
            config.setMaskColor(getResources().getColor(R.color.hehe2));
            config.setRenderOverNavigationBar(true);
            config.setDelay(500);

            final MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(QuizActivity.this, "quickMathOnBoarding");
            sequence.setConfig(config);
            textViewCountDown.post(new Runnable() {
                @Override
                public void run() {
                    sequence.addSequenceItem(textViewCountDown,"Try to answer the questions as before the timer runs out.","Next");
                    sequence.addSequenceItem(textViewQuestion,"Analyze the question - Don't try to MATH-it. Look for the pattern.","Next");
                    if(wrongAns == 0)
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(QuizActivity.this)
                                        .setTarget(buttonConfirmNext)
                                        .setContentText("I'll help you with this one - since it's a correct equation. Tap on correct.")
                                        .setMaskColour(getResources().getColor(R.color.btcolor))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                    else
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(QuizActivity.this)
                                        .setTarget(buttonConfirmNext)
                                        .setContentText("I'll help you with this one - since it's a wrong equation. Tap on wrong.")
                                        .setMaskColour(getResources().getColor(R.color.btcolor))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                    sequence.addSequenceItem(
                            new MaterialShowcaseView.Builder(QuizActivity.this)
                                    .setTarget(imgLifeline1)
                                    .setMaskColour(getResources().getColor(R.color.btcolor))
                                    .setContentText("This will serve as your Life line \n each incorrect response the heart will be deducted.0")
                                    .setDismissOnTargetTouch(true)
                                    .setTargetTouchable(true)
                                    .build()
                    );
                    sequence.addSequenceItem(
                            new MaterialShowcaseView.Builder(QuizActivity.this)
                                    .setTarget(textViewScore)
                                    .setContentText("This will serve as your score on every questions you answer. And you are all set!")
                                    .setMaskColour(getResources().getColor(R.color.btcolor))
                                    .setDismissOnTargetTouch(true)
                                    .setTargetTouchable(true)
                                    .withRectangleShape()
                                    .build()
                    );
                    sequence.start();
                }
            });

        }
    }

    private void setupUI(){
        textViewQuestion = findViewById(R.id.txtQuestionContainer);

        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        textViewCountDown = findViewById(R.id.txtViewTimer);



        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button);
    }

    void healthFunctionality(){


        if (wrongAns == 1){
            imgLifeline3.setVisibility(View.INVISIBLE);
            LifeLineVal--;
        }else if (wrongAns == 2){
            imgLifeline2.setVisibility(View.INVISIBLE);
            LifeLineVal--;
        }else if (wrongAns == 3){
            imgLifeline1.setVisibility(View.INVISIBLE);
            LifeLineVal--;
        }

        if (LifeLineVal == 0){
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();

            Handler delay = new Handler();
            delay.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(QuizActivity.this,ResultActivity.class);

                    intent.putExtra("UserScore",score);
                    intent.putExtra("TotalQuestion",questionTotalCount);
                    intent.putExtra("CorrectQues",correctAns);
                    intent.putExtra("WrongQues",wrongAns);
                    intent.putExtra("Category",categoryValue);
                    intent.putExtra("LevelInfo",levelsID);
                    startActivity(intent);
                    QuizActivity.this.finish();

                }
            },1000);
        }


    }

    public void fetchDB() {
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestionsWithCategoryAndLevels(levelsID,categoryValue);
        startQuiz();

    }

    public void startQuiz() {

        questionTotalCount = questionList.size();
        Collections.shuffle(questionList);

        showQuestions();   // calling showQuestion() method



        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                switch (checkedId){

                    case R.id.radio_button1:

                        rb1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);



                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));

                        break;
                    case R.id.radio_button2:
                        rb2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

                        rb1.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);



                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));

                        break;

                    case R.id.radio_button3:
                        rb3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb1.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);


                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));

                        break;

                    case R.id.radio_button4:
                        rb4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb1.setTextColor(Color.BLACK);



                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));

                        break;
                }

            }
        });

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                        quizOperation();
                    } else {

                        Toast.makeText(QuizActivity.this, "Please Select the Answer ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    public void showQuestions() {


        rbGroup.clearCheck();

        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_default));

        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);


        if (questionCounter < questionTotalCount) {
            currentQuestions = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOption1());
            rb2.setText(currentQuestions.getOption2());
            rb3.setText(currentQuestions.getOption3());
            rb4.setText(currentQuestions.getOption4());

            questionCounter++;
            answerd = false;
            buttonConfirmNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter + "/" + questionTotalCount);


            timeleftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();




        } else {

            // If Number of Questions Finishes then we need to finish the Quiz and Shows the User Quiz Performance


            Toast.makeText(this, "Quiz Finshed", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonConfirmNext.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finalResult();

                }
            }, 2000);
        }
    }

    private void quizOperation() {
        answerd = true;

        countDownTimer.cancel();

        RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbselected) + 1;

        checkSolution(answerNr, rbselected);

    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQuestions.getAnswerNr()) {
            case 1:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb1.setBackground(ContextCompat.getDrawable(
                            this, R.drawable.correct_option_background));
                    rb1.setTextColor(Color.BLACK);

                    correctAns++;


                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);


                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);


                } else {
                    changetoIncorrectColor(rbselected);

                    wrongAns++;

                    healthFunctionality();

                    String correctAnswer = (String) rb1.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);



                }
                break;
            case 2:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb2.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_background));
                    rb2.setTextColor(Color.BLACK);

                    correctAns++;


                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);



                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;


                    String correctAnswer = (String) rb2.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);
                    healthFunctionality();

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);




                }
                break;
            case 3:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb3.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_background));
                    rb3.setTextColor(Color.BLACK);

                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);



                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;


                    String correctAnswer = (String) rb3.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);
                    healthFunctionality();

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);

                }
                break;
            case 4:
                if (currentQuestions.getAnswerNr() == answerNr) {

                    rb4.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_background));
                    rb4.setTextColor(Color.BLACK);


                    correctAns++;


                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswer(FLAG);


                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;


                    String correctAnswer = (String) rb4.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);
                    healthFunctionality();

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswer(FLAG);



                }
                break;
        }
        if (questionCounter == questionTotalCount) {
            buttonConfirmNext.setText("Confirm and Finish");
        }
    }

    void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_answer_background));

        rbselected.setTextColor(Color.BLACK);
    }

    private void startCountDown(){

        countDownTimer = new CountDownTimer(timeleftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftinMillis = millisUntilFinished;

                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeleftinMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    private void updateCountDownText(){


        int minutes = (int) (timeleftinMillis/1000) /60;
        int seconds = (int) (timeleftinMillis/1000) % 60;

        //  String timeFormatted = String.format(Locale.getDefault(),"02d:%02d",minutes,seconds);

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        textViewCountDown.setText(timeFormatted);


        if (timeleftinMillis < 10000){


            textViewCountDown.setTextColor(ContextCompat.getColor(this,R.color.red));

            FLAG = 3;
            playAudioForAnswers.setAudioforAnswer(FLAG);


        }else {
            textViewCountDown.setTextColor(ContextCompat.getColor(this,R.color.white));
        }


        if (timeleftinMillis == 0 ){


            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    timerDialog.timerDialog();

                }
            },2000);



        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("BUGBUG","onRestart() in QuizActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in QuizActivity");
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("BUGBUG","onPause() in QuizActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BUGBUG","onResume() in QuizActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("BUGBUG","onStart() in QuizActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaplayer.release();
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
        Log.i("BUGBUG","onDestroy() in QuizActivity");


    }

    private void finalResult(){

        unblockTheLevels();

        Intent resultData = new Intent(QuizActivity.this,ResultActivity.class);

        resultData.putExtra("UserScore",score);
        resultData.putExtra("TotalQuestion",questionTotalCount);
        resultData.putExtra("CorrectQues",correctAns);
        resultData.putExtra("WrongQues",wrongAns);
        resultData.putExtra("Category",categoryValue);
        resultData.putExtra("LevelInfo",levelsID);
        startActivity(resultData);
        finish();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setCancelable(true);

        View view = getLayoutInflater().inflate(R.layout.alert_dialog_layout,null);
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
                countDownTimer.cancel();
                alertDialog.dismiss();
                Intent intent = new Intent(QuizActivity.this,CategoryActivity.class);
                startActivity(intent);
                QuizActivity.this.finish();
            }
        });
        alertDialog.show();

    }

    private void unblockTheLevels() {
        unlockBegnLevels();

        unlockAdvLevels();
    }

    private void unlockAdvLevels() {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        if(levelsID == 1 && categoryValue.equals("Advance")){

            UNLOCK_HL2 = correctAns;

            if(UNLOCK_HL2 >= 3){

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_ADV_LEVEL_2,1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor.putString(Constant.KEY_ADV_LEVEL_2,"Unlock");
                editor1.apply();
            }

        }else if (levelsID == 2 && categoryValue.equals("Advance")){

            UNLOCK_HL3 = correctAns;

            if(UNLOCK_HL3 >= 3){

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_ADV_LEVEL_3,1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor.putString(Constant.KEY_ADV_LEVEL_3,"Unlock");
                editor1.apply();
            }
        }
    }

    private void unlockBegnLevels() {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        if(levelsID == 1 && categoryValue.equals("Beginner")){

            UNLOCK_CL2 = correctAns;

            if(UNLOCK_CL2 >= 3){

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_BEGN_LEVEL_2,1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor.putString(Constant.KEY_BEGN_LEVEL_2,"Unlock");
                editor1.apply();
            }

        }else if (levelsID == 2 && categoryValue.equals("Beginner")){

            UNLOCK_CL3 = correctAns;

            if(UNLOCK_CL3 >= 3){

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.KEY_BEGN_LEVEL_3,1);
                editor.apply();

                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor.putString(Constant.KEY_BEGN_LEVEL_3,"Unlock");
                editor1.apply();
            }
        }
    }

    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if(!ranBefore){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }



}