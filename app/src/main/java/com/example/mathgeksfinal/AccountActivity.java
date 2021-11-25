package com.example.mathgeksfinal;

import static com.example.mathgeksfinal.DbQuery.myPerformance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mathgeksfinal.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Locale;

public class AccountActivity extends AppCompatActivity {

    TextView signup;
    TextView profile_img_text, name, score, rank, signin;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        name = findViewById(R.id.name);

        signup = findViewById(R.id.signup);

        signin = findViewById(R.id.logout);

        profile_img_text = findViewById(R.id.profilename);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();
        profile_img_text.setText(userID.toUpperCase().substring(0,1));

       DocumentReference documentReference = fStore.collection("Users").document(userID);
       documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
         @Override
          public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
             name.setText(documentSnapshot.getString("fName"));

            }
      });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(AccountActivity.this,CategoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}




