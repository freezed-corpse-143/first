package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListActivityFirsrt extends ListActivity implements Runnable{
    Handler handler;
    List<String> rates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //handler = new Handler();
        //setContentView(R.layout.activity_list_firsrt);
        //prepare data

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                Log.i("TAG", "handleMessage: ");
                if(msg.what==99){
                    Log.i("TAG", "handleMessage: "+(rates==null));
                    rates  = (List<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(ListActivityFirsrt.this, android.R.layout.simple_list_item_1, rates);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        //start thread
        refresh();
//        Log.i("TAG", "onCreate: "+(rates==null));

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

    @Override
    public void run() {
        //调用jsoup获取页面
        try{
            String url="https://www.boc.cn/sourcedb/whpj/";
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.select("body > div > div.BOC_main > div.publish > div:nth-child(3) > table > tbody > tr");

            Message msg = handler.obtainMessage();
            msg.what = 99;
            List<String> rates = new ArrayList<String>();
            for(Element i : tables){
                Elements elements = i.getElementsByTag("td");
                if(elements.size()==0){
                    continue;
                }
                String head = elements.get(0).text();
                String rate = elements.get(5).text();
                rates.add(head+"===>"+rate);
                Log.i("TAG", "run: "+head+" "+rate);
            }
            msg.obj = rates;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}