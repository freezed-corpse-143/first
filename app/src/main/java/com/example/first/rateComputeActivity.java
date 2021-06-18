package com.example.first;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class rateComputeActivity extends AppCompatActivity {
    double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_compute);

        Intent intent = getIntent();
        String title = intent.getStringExtra("ItemTitle");
        String detail = intent.getStringExtra("ItemDetail");

        TextView textView = findViewById(R.id.textView19);//外币名称textView控件
        textView.setText(title);

        EditText editText = findViewById(R.id.editTextTextPersonName5);//input输入框控件
        TextView textView1 = findViewById(R.id.textView31);//汇率转换结果textView控件

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = editText.getText().toString();
                result = Double.valueOf(input) * Double.valueOf(detail);
                textView1.setText(input+"RMB="+String.format("%.2f",result)+title);
            }
        });
    }
}