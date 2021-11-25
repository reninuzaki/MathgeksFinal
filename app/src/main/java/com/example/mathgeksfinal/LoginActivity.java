package com.example.mathgeksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private Button loginb, signups;
    private FirebaseAuth mAuth;
    private Dialog progress_Dialog;
    private TextView dialogText,sign_upps;
    private RelativeLayout gsign;
    private int RC_SIGN_IN = 104;

    private TextView acc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginb = findViewById(R.id.loginB);
        sign_upps = findViewById(R.id.sign_ups);

        acc2 = findViewById(R.id.acc2);

        progress_Dialog = new Dialog(LoginActivity.this);
        progress_Dialog.setContentView(R.layout.dialog_layout);
        progress_Dialog.setCancelable(false);
        progress_Dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = findViewById(R.id.dialog_Text);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
            finish();
            Toast.makeText(this, "You are already Signed In", Toast.LENGTH_SHORT).show();

        }

        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validData()){
                    login();
                }
            }
        });




    }

    private boolean validData() {
        if(email.getText().toString().isEmpty()){
            email.setError("Enter a Valid Email");
            return false;
        }
        if(pass.getText().toString().isEmpty()){
            pass.setError("Enter a password valid");
            return false;
        }
        return true;
    }

    private void login(){

        progress_Dialog.show();

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), pass.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            progress_Dialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            progress_Dialog.dismiss();
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}