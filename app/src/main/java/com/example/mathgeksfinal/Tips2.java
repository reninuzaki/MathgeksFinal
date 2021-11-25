package com.example.mathgeksfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Tips2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Tips2.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}