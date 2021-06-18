package com.example.first;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ExchangeActivity extends AppCompatActivity implements Runnable{

    private float dollarrate;
    private float eurorate;
    private float wonrate;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        update();

        setContentView(R.layout.exchange_main);
        Button btn = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        TextView textView = findViewById(R.id.textView2);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i("TAG", "day: "+day);

        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        int lastday = sp.getInt("lastday",0);
        if(lastday!=day){
            SharedPreferences.Editor editor1 = sp.edit();
            editor1.putInt("lastday",day);
            editor1.apply();
            refresh();
            update();
        }
        /*handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    String ms = msg.obj+"";
                    Log.i("TAG", "handleMessage: ms= "+ms);
                    textView.setText(ms);
                }
                super.handleMessage(msg);
            }
        };*/

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

    public void refresh(){
        //开启线程
        try {
            Thread t = new Thread(this);
            t.start();
            t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void openOne(View v){
        show();

    }

    public void update(){
//        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
//        this.dollarrate = sp.getFloat("dollarrate",0.15f);
//        this.eurorate = sp.getFloat("eurorate",0.13f);
//        this.wonrate = sp.getFloat("wonrate",170.50f);
        RateManager rateManager = new RateManager(ExchangeActivity.this);
        dollarrate = rateManager.getLatestRate("dollarrate").getCurRate();
        eurorate = rateManager.getLatestRate("eurorate").getCurRate();
        wonrate = rateManager.getLatestRate("wonrate").getCurRate();
        Log.i("TAG", "run: "+"初始化成功");
    }

    public void show(){
//        Log.i("succeed", "openOne: start");
        Intent hello = new Intent(this, ExchangeSetingActivity.class);
//        Log.i("TAG", "openOne: 返回汇率"+dollarrate+"  "+eurorate+" "+wonrate);
        hello.putExtra("dollarrate",dollarrate);
        hello.putExtra("eurorate",eurorate);
        hello.putExtra("wonrate",wonrate);
        //Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jd.com"));
        //Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:87092173"));

        startActivityForResult(hello,0);
        //startActivity(hello);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(requestCode==0){
            /*
            Bundle bundle = data.getExtras();
            dollarrate = bundle.getDouble("dollarrate",0.15);
            eurorate = bundle.getDouble("eurorate",0.13);
            wonrate = bundle.getDouble("wonrate",170.50);
             */
            update();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        Log.i("TAG", "onCreateOptionsMenu: ...");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings)
            show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
        Log.i("TAG", "run: 。。。。。。。。");
        //耗时操作
        try {
            Thread.sleep(5000);
        }catch ( Exception e){
            System.out.println(e.getMessage());
        }
        Log.i("TAG", "run: after 5s");

        /*//发送消息
        Message msg = handler.obtainMessage();
        msg.what = 0;
        msg.obj="发送数据";
        handler.sendMessage(msg);*/

        /*URL url = null;
        try{
            String web = "http://www.chinamoney.com.cn/chinese/bkccpr/";
            url = new URL(web);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();

            String html = getStreamString(in);
            Log.i("TAG", "html= "+html);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }*/
        //调用jsoup获取页面
        try{
            String url="https://www.usd-cny.com/bankofchina.htm";
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.select("body > section > div > div > article > table > tbody > tr");
            //Log.i("TAG", "run: "+tables.size());
            SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            for(Element i : tables){
                //Log.i("TAG", "run: "+i.text());
                Elements elements = i.getElementsByTag("td");
                if(elements.size()==0){
                    continue;
                }
                //Log.i("TAG", "run: "+elements.size());
                //Log.i("TAG", "run: "+elements.get(0).text());
                String head = elements.get(0).text();
                //Log.i("TAG", "run: "+head);
                //Log.i("TAG", "run: "+head+"汇率为"+elements.get(5).text());
                if(head.equals("美元")){
                    float dr = Float.valueOf(elements.get(5).text())/100f;
                    editor.putFloat("dollarrate", dr);
                    Log.i("TAG", "run: 美元的汇率为"+elements.get(5).text());
                }else if(head.equals("欧元")){
                    float er = Float.valueOf(elements.get(5).text())/100f;
                    editor.putFloat("eurorate", er);
                    Log.i("TAG", "run: 欧元的汇率为"+elements.get(5).text());
                }else if(head.equals("韩元")){
                    float wr = Float.valueOf(elements.get(5).text())/100f;
                    editor.putFloat("wonrate", wr);
                    Log.i("TAG", "run: 韩元的汇率为"+elements.get(5).text());
                }
            }
            editor.apply();
        }catch (IOException e){
            e.printStackTrace();
        }

    }



    private String getStreamString(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"utf-8");
        while(true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0){
                break;
            }
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}