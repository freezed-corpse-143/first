package com.example.first;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTask implements Runnable{
    private Handler handler;
    private Message msg;
    public void setHandler(Handler handler){ this.handler = handler;}

    @Override
    public void run() {
        //调用jsoup获取页面
        try{

            String url="https://www.boc.cn/sourcedb/whpj/";
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.select("body > div > div.BOC_main > div.publish > div:nth-child(3) > table > tbody > tr");
//            Log.i("TAG", "onCreate: "+"运行成功");
            msg = handler.obtainMessage();
            msg.what = 99;
            List<String> rates = new ArrayList<String>();
            for(Element i : tables){
                Elements elements = i.getElementsByTag("td");
                if(elements.size()==0){
                    continue;
                }
                String head = elements.get(0).text();
                String rate = String.format("%.2f",100/Double.valueOf(elements.get(5).text()));
                rates.add(head+"===>"+rate);
//                Log.i("TAG", "run: "+head+" "+rate);
            }
            msg.obj = rates;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
