package com.example.mathgeksfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout begn;

    Button btonBeginner, btonAdvance, btonIntermediate, btonSolveAPic;
    Button btonAccount;

    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        begn = findViewById(R.id.begn);


        btonBeginner = findViewById(R.id.button2);
        btonAdvance = findViewById(R.id.button3);
        btonIntermediate = findViewById(R.id.button4);

        btonSolveAPic = findViewById(R.id.button5);

        btonAccount = findViewById(R.id.bton_account);

        btonBeginner.setOnClickListener(this);
        btonAdvance.setOnClickListener(this);
        btonIntermediate.setOnClickListener(this);
        btonSolveAPic.setOnClickListener(this);

        btonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        String username = DbQuery.myProfile.getName();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button2:
                createLevelsForBeginner();

                Intent intentBeginner = new Intent(CategoryActivity.this, BeginnerLevelActivity.class);
                intentBeginner.putExtra("Category", CategoryConstants.BEGINNER);
                startActivity(intentBeginner);
                break;

            case R.id.button3:
                createLevelsForAdvance();

                Intent intentAdvance = new Intent(CategoryActivity.this, AdvanceLevelActivity.class);
                intentAdvance.putExtra("Category", CategoryConstants.ADVANCE);
                startActivity(intentAdvance);
                break;

            case R.id.button4:
                createLevelsForIntermediate();

                Intent intentIntermediate = new Intent(CategoryActivity.this, IntermediateLevelActivity.class);
                intentIntermediate.putExtra("Category", CategoryConstants.INTERMEDIATE);
                startActivity(intentIntermediate);
                break;

            case R.id.button5:

                Intent intentSolveAPic = new Intent(CategoryActivity.this, StartGamePlay.class);
                intentSolveAPic.putExtra("Category", CategoryConstants.SOLVEAPIC);
                startActivity(intentSolveAPic);
                break;
        }

    }

    private void createLevelsForBeginner() {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Constant.KEY_BEGN_LEVEL_1, 1);
        editor.putString(Constant.KEY_CAT_BEGN_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_BEGN_LEVEL_1, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_BEGN_LEVEL_1, 1);
            editor.putInt(Constant.KEY_BEGN_LEVEL_2, 0);
            editor.putInt(Constant.KEY_BEGN_LEVEL_3, 0);


        } else if (sharedPreferences.getString(Constant.KEY_CAT_BEGN_LEVEL_2, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_BEGN_LEVEL_1, 1);
            editor.putInt(Constant.KEY_BEGN_LEVEL_2, 1);
            editor.putInt(Constant.KEY_BEGN_LEVEL_3, 0);

        } else if (sharedPreferences.getString(Constant.KEY_CAT_BEGN_LEVEL_3, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_BEGN_LEVEL_1, 1);
            editor.putInt(Constant.KEY_BEGN_LEVEL_2, 1);
            editor.putInt(Constant.KEY_BEGN_LEVEL_3, 1);
        }
    }

    private void createLevelsForAdvance() {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Constant.KEY_ADV_LEVEL_1, 1);
        editor.putString(Constant.KEY_CAT_ADV_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_ADV_LEVEL_1, "N/A").equals("Unlock")) {

            //Unlock level 1
            editor.putInt(Constant.KEY_ADV_LEVEL_1, 1);
            editor.putInt(Constant.KEY_ADV_LEVEL_2, 0);
            editor.putInt(Constant.KEY_ADV_LEVEL_3, 0);

        } else if (sharedPreferences.getString(Constant.KEY_CAT_ADV_LEVEL_2, "N/A").equals("Unlock")) {

            //Unlock level 2
            editor.putInt(Constant.KEY_ADV_LEVEL_1, 1);
            editor.putInt(Constant.KEY_ADV_LEVEL_2, 1);
            editor.putInt(Constant.KEY_ADV_LEVEL_3, 0);

        } else if (sharedPreferences.getString(Constant.KEY_CAT_ADV_LEVEL_3, "N/A").equals("Unlock")) {

            //Unlock level 3
            editor.putInt(Constant.KEY_ADV_LEVEL_1, 1);
            editor.putInt(Constant.KEY_ADV_LEVEL_2, 1);
            editor.putInt(Constant.KEY_ADV_LEVEL_3, 1);
        }
    }

    private void createLevelsForIntermediate() {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constant.MY_LEVEL_PROFILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(Constant.KEY_INT_LEVEL_1, 1);
        editor.putString(Constant.KEY_CAT_INT_LEVEL_1, "Unlock");
        editor.apply();

        if (sharedPreferences.getString(Constant.KEY_CAT_INT_LEVEL_1, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_INT_LEVEL_1, 1);
            editor.putInt(Constant.KEY_INT_LEVEL_2, 0);
            editor.putInt(Constant.KEY_INT_LEVEL_3, 0);


        } else if (sharedPreferences.getString(Constant.KEY_CAT_INT_LEVEL_2, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_INT_LEVEL_1, 1);
            editor.putInt(Constant.KEY_INT_LEVEL_2, 1);
            editor.putInt(Constant.KEY_INT_LEVEL_3, 0);

        } else if (sharedPreferences.getString(Constant.KEY_CAT_INT_LEVEL_3, "N/A").equals("Unlock")) {

            editor.putInt(Constant.KEY_INT_LEVEL_1, 1);
            editor.putInt(Constant.KEY_INT_LEVEL_2, 1);
            editor.putInt(Constant.KEY_INT_LEVEL_3, 1);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}