package com.example.first;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Double.valueOf;

public class TemperatureConversionActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperatrueconversion_main);
        Button btn = findViewById(R.id.button3_tc);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        EditText editText = findViewById(R.id.editTextTextPersonName2_tc);
        String content = editText.getText().toString();
        String result;
        if (content.equals("")){
            result = "转换失败" + "\n" +"请输入标准格式，例如：36";
        }else{
            double number = valueOf(content);
            if(number < -273.15){
                result = "转换失败" + "\n" + "你输入的温度低于最低温度-273.15℃";
            }else{
                result = "转换后的温度为";
                result += String.format("%.2f℃",number * 9 / 5 + 32);
                result += "华氏温度 = 摄氏温度 * 9 / 5 + 32";
            }
        }
        TextView show = findViewById(R.id.textView4_tc);
        show.setText(result);
    }
}