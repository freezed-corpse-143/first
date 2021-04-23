package com.example.first;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExchangeActivity extends AppCompatActivity {

    private double dollarrate = 0.15;
    private double eurorate = 0.13;
    private double wonrate = 170.50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange_main);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
    }
    public void onClick(View v) {
        EditText editText = findViewById(R.id.editTextTextPersonName);

        String content = editText.getText().toString();
        double number;
        double result;
        Log.i("判断", "onClick: "+(content.equals("")||content==null));
        if (content.equals("")||content==null) {

            Log.i("TAG", "onClick: aaaaaaaa");
            Toast.makeText(this, "请输入正确的金额数字（大于0），例如：20、53", Toast.LENGTH_SHORT).show();
        } else {
            number = Double.valueOf(content);
            if (v.getId() == R.id.button) {
                result = number * dollarrate;
            } else if (v.getId() == R.id.button2) {
                result = number * eurorate;
            } else {
                result = number * wonrate;
            }
            TextView textView = findViewById(R.id.textView2);
            textView.setText(String.format("%.2f",result));
        }
    }

    public void openOne(View v){
        Log.i("succeed", "openOne: start");
        Intent hello = new Intent(this, ExchangeSetingActivity.class);
        hello.putExtra("dollarrate",dollarrate);
        hello.putExtra("eurorate",eurorate);
        hello.putExtra("wonrate",wonrate);
        //Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jd.com"));
        //Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:87092173"));
        //startActivityfor(hello);
        startActivityForResult(hello,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(requestCode==0&&resultCode==1){
            Bundle bundle = data.getExtras();
            dollarrate = bundle.getDouble("dollarrate",0.15);
            eurorate = bundle.getDouble("eurorate",0.13);
            wonrate = bundle.getDouble("wonrate",170.50);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}