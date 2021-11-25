package com.example.mathgeksfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class IntermediateLevelActivity extends AppCompatActivity implements View.OnClickListener {

    Button btonLevel1, btonLevel2, btonLevel3, btonLevel4;

    String CategoryValue = "";

    int IL1, IL2, IL3;

    TextView txtsssslvl1, txtsssslvl2, txtsssslvl3;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_level);

        btonLevel1 = findViewById(R.id.button6);
        btonLevel2 = findViewById(R.id.button8);
        btonLevel3 = findViewById(R.id.button9);


        Intent intentCategory = getIntent();
        CategoryValue = intentCategory.getStringExtra("Category");

        lockandunlocklevels();
    }

    public void LoadData(View view) {
        txtsssslvl1.setText(String.valueOf(IL1));
        txtsssslvl2.setText(String.valueOf(IL2));
        txtsssslvl3.setText(String.valueOf(IL3));
    }

    private void lockandunlocklevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        IL1 = sharedPreferences.getInt(Constant.KEY_INT_LEVEL_1, 0);
        IL2 = sharedPreferences.getInt(Constant.KEY_INT_LEVEL_2, 0);
        IL3 = sharedPreferences.getInt(Constant.KEY_INT_LEVEL_3, 0);

        if (IL1 == 1) {
            btonLevel1.setClickable(true);
            btonLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.butonlvl1));
            btonLevel1.setOnClickListener(this);
        }
        if (IL2 == 1) {
            btonLevel2.setClickable(true);
            btonLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.butonlvl2));
            btonLevel2.setOnClickListener(this);
        } else if (IL2 == 0) {
            btonLevel2.setClickable(false);
            btonLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.levellck));
        }
        if (IL3 == 1) {
            btonLevel3.setClickable(true);
            btonLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.butonlvl3));
            btonLevel3.setOnClickListener(this);
        } else if (IL3 == 0) {
            btonLevel3.setClickable(false);
            btonLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.levellck));
        }
    }

    @Override
    public void onClick(View v) {

        if (CategoryValue.equals("Intermediate")) {


            switch (v.getId()) {
                case R.id.button6:

                    Intent intentCompLevel1 = new Intent(IntermediateLevelActivity.this, QuizActivity.class);
                    intentCompLevel1.putExtra("Category", Questions.CATEGORY_INTERMEDIATE);
                    intentCompLevel1.putExtra("Level", Questions.LEVEL1);
                    startActivity(intentCompLevel1);
                    break;

                case R.id.button8:

                    Intent intentCompLevel2 = new Intent(IntermediateLevelActivity.this, QuizActivity.class);
                    intentCompLevel2.putExtra("Category", Questions.CATEGORY_INTERMEDIATE);
                    intentCompLevel2.putExtra("Level", Questions.LEVEL2);
                    startActivity(intentCompLevel2);

                    break;
                case R.id.button9:
                    Intent intentCompLevel3 = new Intent(IntermediateLevelActivity.this, QuizActivity.class);
                    intentCompLevel3.putExtra("Category", Questions.CATEGORY_INTERMEDIATE);
                    intentCompLevel3.putExtra("Level", Questions.LEVEL3);
                    startActivity(intentCompLevel3);

                    break;

            }

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IntermediateLevelActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}