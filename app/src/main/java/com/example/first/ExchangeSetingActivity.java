package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExchangeSetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_seting);

        Intent intent = getIntent();
        double dollarrate = intent.getDoubleExtra("dollarrate",0.15);
        double eurorate = intent.getDoubleExtra("eurorate",0.13);
        double wonrate = intent.getDoubleExtra("wonrate",170.50);

        EditText d = findViewById(R.id.editTextTextPersonName2);
        EditText e = findViewById(R.id.editTextTextPersonName3);
        EditText w = findViewById(R.id.editTextTextPersonName4);

        d.setText(""+dollarrate);
        e.setText(""+eurorate);
        w.setText(""+wonrate);
    }

    public void save(View v){

        Bundle bdl = new Bundle();

        EditText d = findViewById(R.id.editTextTextPersonName2);
        String dc = d.getText().toString();
        if(dc.equals("")){
            dc ="0.15";
        }
        double dollarrate = Double.valueOf(dc);
        bdl.putDouble("dollarrate",dollarrate);

        EditText e = findViewById(R.id.editTextTextPersonName3);
        String ec = d.getText().toString();
        if(ec.equals("")){
            ec ="0.13";
        }
        double eurorate = Double.valueOf(ec);
        bdl.putDouble("eurorate",eurorate);

        EditText w = findViewById(R.id.editTextTextPersonName4);
        String wc = w.getText().toString();
        if(wc.equals("")){
            wc = "170.50";
        }
        double wonrate = Double.valueOf(wc);
        bdl.putDouble("wonrate",wonrate);

        Intent intent = getIntent();
        intent.putExtras(bdl);

        setResult(1,intent);
        finish();
    }
}