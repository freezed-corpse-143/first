package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ExchangeSetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_seting);

        Intent intent = getIntent();
        float dollarrate = intent.getFloatExtra("dollarrate",0.15f);
        float eurorate = intent.getFloatExtra("eurorate",0.13f);
        float wonrate = intent.getFloatExtra("wonrate",170.50f);

        EditText d = findViewById(R.id.editTextTextPersonName2);
        EditText e = findViewById(R.id.editTextTextPersonName3);
        EditText w = findViewById(R.id.editTextTextPersonName4);

        d.setText(""+dollarrate);
        e.setText(""+eurorate);
        w.setText(""+wonrate);
    }

    public void save(View v){
        EditText d = findViewById(R.id.editTextTextPersonName2);
        String dc = d.getText().toString();
        if(dc.equals("")){
            dc ="0.15";
        }
        double dollarrate = Double.valueOf(dc);

        EditText e = findViewById(R.id.editTextTextPersonName3);
        String ec = e.getText().toString();
//        Log.i("TAG", "save: ec："+ec);
        if(ec.equals("")){
            ec ="0.13";
        }
        double eurorate = Double.valueOf(ec);

        EditText w = findViewById(R.id.editTextTextPersonName4);
        String wc = w.getText().toString();
        if(wc.equals("")){
            wc = "170.50";
        }
        double wonrate = Double.valueOf(wc);

        /*
        Bundle bdl = new Bundle();
        bdl.putDouble("dollarrate",dollarrate);


        bdl.putDouble("eurorate",eurorate);


        bdl.putDouble("wonrate",wonrate);

//        Log.i("TAG", "save: 返回值"+dollarrate+"  "+eurorate+" "+wonrate);

        Intent intent = getIntent();
        intent.putExtras(bdl);


        setResult(1,intent);
        finish();*/

        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Log.i("TAG", "save: "+dollarrate+" "+eurorate+" "+wonrate);
        editor.putFloat("dollarrate",(float)dollarrate);
        editor.putFloat("eurorate",(float)eurorate);
        editor.putFloat("wonrate",(float)wonrate);
        editor.apply();
        finish();

    }
}