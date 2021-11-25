package com.example.mathgeksfinal;

import static com.example.mathgeksfinal.DbQuery.myPerformance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView totalUsers, score, rank, imgTv;
    private RecyclerView usersView;
    private RankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        totalUsers = findViewById(R.id.total_users);
        score = findViewById(R.id.total_score);
        rank = findViewById(R.id.rank);
        imgTv = findViewById(R.id.imgtext);


        usersView.setAdapter(adapter);

        DbQuery.getTopUsers(new MyCompleteListener() {
            @Override
            public void onSucess() {

                adapter.notifyDataSetChanged();

                if(myPerformance.getScore() != 0){

                    if( ! DbQuery.isMeOnTopList){
                        calculateRank();
                    }
                    score.setText("Scored : " + myPerformance.getScore());
                    rank.setText("Rank :" + myPerformance.getScore());
                }

            }

            @Override
            public void onFailure() {

            }
        });

        totalUsers.setText("Total Users : " + DbQuery.g_usersCount);
        imgTv.setText(myPerformance.getName().toUpperCase().substring(0,1));
    }

    private void calculateRank(){
        int lowTopSCore = DbQuery.g_userlist.get(DbQuery.g_userlist.size() -1).getScore();

        int remaining_slots = DbQuery.g_usersCount - 20;

        int myslot = (myPerformance.getScore()*remaining_slots) / lowTopSCore;

        int rank;

        if (lowTopSCore != myPerformance.getScore()){

            rank = DbQuery.g_usersCount - myslot;
        }
        else{
            rank = 21;
        }
        myPerformance.setRank(rank);

    }
}