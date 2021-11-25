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

public class AdvanceLevelActivity extends AppCompatActivity implements View.OnClickListener {

    Button btonLevel1, btonLevel2 , btonLevel3, btonLevel4;

    String CategoryValue = "";

    int HL1, HL2, HL3;

    TextView txtsssslvl1, txtsssslvl2, txtsssslvl3 ;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_level);

        btonLevel1 = findViewById(R.id.button6);
        btonLevel2 = findViewById(R.id.button8);
        btonLevel3 = findViewById(R.id.button9);



        Intent intentCategory = getIntent();
        CategoryValue = intentCategory.getStringExtra("Category");

        lockandunlocklevels();
    }

    public void LoadData(View view){
        txtsssslvl1.setText(String.valueOf(HL1));
        txtsssslvl2.setText(String.valueOf(HL2));
        txtsssslvl3.setText(String.valueOf(HL3));
    }

    private void lockandunlocklevels() {

        SharedPreferences sharedPreferences =
                getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        HL1 = sharedPreferences.getInt(Constant.KEY_ADV_LEVEL_1, 0);
        HL2 = sharedPreferences.getInt(Constant.KEY_ADV_LEVEL_2, 0);
        HL3 = sharedPreferences.getInt(Constant.KEY_ADV_LEVEL_3, 0);

        if (HL1 == 1) {
            btonLevel1.setClickable(true);
            btonLevel1.setBackground(ContextCompat.getDrawable(this, R.drawable.butonlvl1));
            btonLevel1.setOnClickListener(this);
        }
        if (HL2 == 1) {
            btonLevel2.setClickable(true);
            btonLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.butonlvl2));
            btonLevel2.setOnClickListener(this);
        } else if (HL2 == 0) {
            btonLevel2.setClickable(false);
            btonLevel2.setBackground(ContextCompat.getDrawable(this, R.drawable.levellck));
        }
        if (HL3 == 1) {
            btonLevel3.setClickable(true);
            btonLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.butonlvl3));
            btonLevel3.setOnClickListener(this);
        } else if (HL3 == 0) {
            btonLevel3.setClickable(false);
            btonLevel3.setBackground(ContextCompat.getDrawable(this, R.drawable.levellck));
        }
    }

    @Override
    public void onClick(View v) {

        if (CategoryValue.equals("Advance")){


            switch (v.getId()){
                case R.id.button6:

                    Intent intentCompLevel1 = new Intent(AdvanceLevelActivity.this, QuizActivity.class);
                    intentCompLevel1.putExtra("Category", Questions.CATEGORY_ADVANCE);
                    intentCompLevel1.putExtra("Level",Questions.LEVEL1);
                    AdvanceLevelActivity.this.finish();
                    startActivity(intentCompLevel1);
                    break;

                case R.id.button8:

                    Intent intentCompLevel2 = new Intent(AdvanceLevelActivity.this,QuizActivity.class);
                    intentCompLevel2.putExtra("Category",Questions.CATEGORY_ADVANCE);
                    intentCompLevel2.putExtra("Level",Questions.LEVEL2);
                    AdvanceLevelActivity.this.finish();
                    startActivity(intentCompLevel2);

                    break;
                case R.id.button9:
                    Intent intentCompLevel3 = new Intent(AdvanceLevelActivity.this,QuizActivity.class);
                    intentCompLevel3.putExtra("Category",Questions.CATEGORY_ADVANCE);
                    intentCompLevel3.putExtra("Level",Questions.LEVEL3);
                    AdvanceLevelActivity.this.finish();
                    startActivity(intentCompLevel3);

                    break;

            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdvanceLevelActivity.this,CategoryActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}