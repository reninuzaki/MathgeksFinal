package com.example.mathgeksfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText name, email, pass, confirmpass;
    private Button signupButton;
    private ImageView backbutton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private String emailSTR, passSTR, confirmSTR, nameSTR;

    String userID;


    private Dialog progressDialog;

    private TextView dialogText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        email = findViewById(R.id.emailID);
        pass = findViewById(R.id.password);
        confirmpass = findViewById(R.id.confirm_password);
        backbutton = findViewById(R.id.backbutton);
        signupButton = findViewById(R.id.signup);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        progressDialog = new Dialog(SignupActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogText = findViewById(R.id.dialog_Text);

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
            finish();
            Toast.makeText(this, "You are already Signed In", Toast.LENGTH_SHORT).show();

        }

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    signupnewUser();
                }
            }
        });
    }
    private boolean validate() {
        nameSTR = name.getText().toString().trim();
        passSTR = pass.getText().toString().trim();
        emailSTR = email.getText().toString().trim();
        confirmSTR = confirmpass.getText().toString().trim();

        if (emailSTR.isEmpty()) {
            email.setError("Enter your name");
            return false;
        }
        if(passSTR.isEmpty()){
            pass.setError("Password is required");
            return false;
        }
        if(pass.length() <6){
            pass.setError("Password must be atleast 6 characters");
            return false;
        }
        return true;
    }

    private void signupnewUser() {

        String nameST = name.getText().toString();
        String emailST = email.getText().toString();


        mAuth.createUserWithEmailAndPassword(emailSTR,passSTR)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Sign up Sucessfully", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("Users").document(userID);
                            Map<String,Object> user= new HashMap<>();
                            user.put("fName",nameST);
                            user.put("email",emailST);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is create "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
                            finish();

                        } else {
                            Toast.makeText(SignupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    }
