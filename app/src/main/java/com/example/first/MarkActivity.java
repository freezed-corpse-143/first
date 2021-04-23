package com.example.first;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarkActivity extends AppCompatActivity{
    TextView scoreA;
    TextView scoreB;
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
        showScoreA(1);
    }
    public void onClick_2(View v){
        showScoreA(2);
    }
    public void onClick_3(View v){
        showScoreA(3);
    }
    public void onClick_4(View v){
        showScoreB(1);
    }
    public void onClick_5(View v){
        showScoreB(2);
    }
    public void onClick_6(View v){
        showScoreB(3);
    }
    public void onClick_7(View v){
        this.scoreA.setText(0+"");
        this.scoreB.setText(0+"");
    }
    public void showScoreA(int scoreA){
        String content = this.scoreA.getText().toString();
        if(content == "")
            content = "0";
        int oldScore = Integer.valueOf(content);
        this.scoreA.setText(scoreA+oldScore+"");
    }
    public void showScoreB(int scoreB){
        String content = this.scoreB.getText().toString();
        if(content == "")
            content = "0";
        int oldScore = Integer.valueOf(content);
        this.scoreB.setText(scoreB+oldScore+"");
    }

}