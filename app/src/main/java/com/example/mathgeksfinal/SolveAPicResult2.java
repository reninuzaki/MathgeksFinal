package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SolveAPicResult2 extends AppCompatActivity {

    TextView tvPoints, tvPersonalBest;

    SharedPreferences sharedPreferences;

    Button btrestart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_apic_result2);

        tvPoints = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        int points = getIntent().getExtras().getInt("points");

        btrestart = findViewById(R.id.restart);

        tvPoints.setText("" + points);

        sharedPreferences = getSharedPreferences("pref", 0);

        int pointsSP = sharedPreferences.getInt("pointsSP", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(points > pointsSP){
            // If true, store points in pointsSP
            pointsSP = points;
            // Put the value in editor with the key "pointsSP"
            editor.putInt("pointsSP", pointsSP);
            // This will overwrite existing value of the key "pointsSP".
            // To save the changes call commit() on editor.
            editor.commit();
        }
        // Set tvPersonalBest with the value of pointsSP
        tvPersonalBest.setText("" + pointsSP);

        btrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SolveAPicResult2.this,StartGameSolveAPic.class);
                startActivity(intent);

                finish();
            }
        });

    }

    public void restart(View view) {
        // Create an Intent object to launch StartGame Activity
        Intent intent = new Intent(SolveAPicResult2.this, StartGameSolveAPic.class);
        startActivity(intent);
        // Finish GameOver Activity
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}