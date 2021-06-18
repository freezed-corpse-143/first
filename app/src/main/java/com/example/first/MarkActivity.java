package com.example.first;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarkActivity extends AppCompatActivity{
    TextView scoreA;
    TextView scoreB;

    int score_a;
    int score_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mark_main);
        scoreA = findViewById(R.id.textView2);
        scoreB = findViewById(R.id.textview3);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);

    }
    public void onClick_1(View v){
        addScoreA(1);
        update();
    }
    public void onClick_2(View v){
        addScoreA(2);
        update();
    }
    public void onClick_3(View v){
        addScoreA(3);
        update();
    }
    public void onClick_4(View v){
        addScoreB(1);
        update();
    }
    public void onClick_5(View v){
        addScoreB(2);
        update();
    }
    public void onClick_6(View v){
        addScoreB(3);
        update();
    }
    public void onClick_7(View v){
        score_a = 0;
        score_b = 0;
        update();
    }
    public void addScoreA(int addscore){
        String content = this.scoreA.getText().toString();
        if(content.equals(""))
            content = "0";
        int oldScore = Integer.valueOf(content);
        score_a = oldScore+addscore;
    }
    public void addScoreB(int addscore){
        String content = this.scoreB.getText().toString();
        if(content.equals(""))
            content = "0";
        int oldScore = Integer.valueOf(content);
        score_b = oldScore + addscore;
    }

    public void update(){
        scoreA.setText(""+score_a);
        scoreB.setText(""+score_b);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("score_a",score_a);
        outState.putInt("score_b",score_b);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        score_a = savedInstanceState.getInt("score_a",0);
        score_b = savedInstanceState.getInt("score_b",0);
        update();
        super.onRestoreInstanceState(savedInstanceState);
    }


}