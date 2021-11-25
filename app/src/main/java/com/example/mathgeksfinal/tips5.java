package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class tips5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips5);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tips5.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}