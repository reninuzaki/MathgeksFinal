package com.example.mathgeksfinal;

import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreenActivity extends AppCompatActivity {

    private final static int EXIT_CODE = 100;

    private FirebaseAuth mAuth;


    TextView txtSplashText;
    ImageView imgViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        txtSplashText = findViewById(R.id.textviewLogoText);
        imgViewLogo = findViewById(R.id.imgviewLogo);

        mAuth = FirebaseAuth.getInstance();

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.trasnsition);
        imgViewLogo.setAnimation(animation);
        txtSplashText.setAnimation(animation);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                    sleep(3000);
                }catch (Exception e){

                    e.printStackTrace();
                }

                if(mAuth.getCurrentUser() != null){
                    Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{

                    Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();

                }



            }
        });
        thread.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXIT_CODE){

            if (resultCode == RESULT_OK){
                if (data.getBooleanExtra("EXIT",true)){
                    finish();
                }
            }
        }


    }

    @Override
    public void onBackPressed() {

    }
}