package com.example.mathgeksfinal;

import android.provider.ContactsContract;
import android.util.ArrayMap;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbQuery {

    public static FirebaseFirestore g_firestore;

    public static ProfileModel myProfile = new ProfileModel("NA",null);

    public static List<RankModel> g_userlist = new ArrayList<>();
    public static int g_usersCount = 0;
    public static boolean isMeOnTopList = false;


    public static RankModel myPerformance = new RankModel("NULL",0,-1);

    public static void createUserData(String email, String name, final MyCompleteListener completeListener){

        Map<String, Object> userData = new ArrayMap<>();

        userData.put("EMAIL_ID",email);
        userData.put("NAME",name);
        userData.put("TOTAL_SCORE",0);

        DocumentReference userdDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        WriteBatch batch = g_firestore.batch();

        batch.set(userdDoc,userData);

        DocumentReference coundDoc = g_firestore.collection("USERS").document("TOTAL_USERS");
        batch.update(coundDoc,"COUNT", FieldValue.increment(1));

        batch.commit()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        completeListener.onSucess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();
                    }
                });


    }

    public static void getUserData(final MyCompleteListener completeListener){
        g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        myProfile.setName(documentSnapshot.getString("NAME"));
                        myProfile.setEmail(documentSnapshot.getString("EMAIL_ID"));

                        myPerformance.setScore(documentSnapshot.getLong("TOTAL_SCORE").intValue());
                        myPerformance.setName(documentSnapshot.getString("NAME"));

                        completeListener.onSucess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }

    public static void loadData(final MyCompleteListener completeListener){
        loadCategories(new MyCompleteListener() {
            @Override
            public void onSucess() {
                getUserData(new MyCompleteListener() {
                    @Override
                    public void onSucess() {

                        getUsersCount(completeListener);

                    }

                    @Override
                    public void onFailure() {
                        completeListener.onFailure();
                    }
                });
            }

            @Override
            public void onFailure() {
                completeListener.onFailure();
            }
        });
    }

    public static void getTopUsers(MyCompleteListener completeListener){

        g_userlist.clear();

        String myUID = FirebaseAuth.getInstance().getUid();

        g_firestore.collection("USERS")
                .whereGreaterThan("TOTAL_SCORE",0)
                .orderBy("TOTAL_SCORE", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        int rank = 1;
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            g_userlist.add(new RankModel(
                                doc.getString("NAME"),
                                    doc.getLong("TOTAL_SCORE").intValue(),
                                    rank
                            ));

                            if(myUID.compareTo(doc.getId())==0){
                                isMeOnTopList = true;

                            }
                            rank++;
                        }
                        completeListener.onSucess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    public static void getUsersCount(final MyCompleteListener completeListener){
        g_firestore.collection("USERS").document("TOTAL_USERS")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        g_usersCount = documentSnapshot.getLong("COUNT").intValue();

                        completeListener.onSucess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.onFailure();
                    }
                });
    }

    private static void loadCategories(MyCompleteListener myCompleteListener) {
    }
}
