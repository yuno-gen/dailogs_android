package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class Score_notification extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        ArrayList<String> arr2 = getIntent().getExtras().getStringArrayList("ans2");
        int a1 = getIntent().getExtras().getInt("ans1", -1);
        int a3 = getIntent().getExtras().getInt("ans3", -1);
        TextView score = findViewById(R.id.scoreboard);
        int result =check_score(a1, arr2, a3);
        score.setText(" "+result);
        if(result>1){
            ImageView fail = findViewById(R.id.thumbsdown);
            fail.setVisibility(View.GONE);
        }
        else{
            ImageView pass = findViewById(R.id.thumbsup);
            pass.setVisibility(View.GONE);
        }


    }

    protected int check_score(int a1, ArrayList<String> arr, int a3){
        int score=0;
        if(a1==1){
            score+=1;
        }
        if(arr.contains("Android") && arr.contains("Vue JS") && arr.contains("Quasar Framework")){
            score+=1;
        }

        if (a3==1) {
             score += 1;
        }


        return score;
    }
}
