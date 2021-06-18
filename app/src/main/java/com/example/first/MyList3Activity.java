package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MyList3Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    List<String> rates;
    MyAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list3);
        listView = findViewById(R.id.mylist3);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        listView.setEmptyView(findViewById(R.id.textView3));
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                Log.i("TAG", "handleMessage: ");
                if(99==msg.what){
                    Log.i("TAG", "handleMessage: "+(rates==null));
                    rates  = (List<String>)msg.obj;
//                    ListAdapter adapter = new ArrayAdapter<String>(MyList3Activity.this, android.R.layout.simple_list_item_1, rates);
//                    listView.setAdapter(adapter);
                    adapter = acceptData(rates);
                    listView.setAdapter(adapter);

                    listView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
                super.handleMessage(msg);
            }
        };

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        MyTask myTask = new MyTask();
        myTask.setHandler(handler);

        Thread t = new Thread(myTask);
        t.start();



//        progressBar.setVisibility(View.GONE);
//        listView.setVisibility(View.VISIBLE);
    }

    public MyAdapter acceptData(List<String> list){
        //准备数据
        ArrayList<Rate> listItems = new ArrayList<Rate>();
        for(int i=0;i<list.size();i++){

            String[] element = list.get(i).split("===>");
            Rate rateItem = new Rate(element[0],element[1]);
            listItems.add(rateItem);
        }

        //生成适配器
        /*SimpleAdapter listItemAdapter = new SimpleAdapter(this,
                listItems,
                R.layout.list_item,
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.ItemTitle,R.id.ItemDetail});*/
        MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item,listItems);
        return myAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition = listView.getItemAtPosition(position);
        Rate rateItem = (Rate)itemAtPosition;
        String title = rateItem.getName();
        String detail = rateItem.getRate();



        //跳转页面
        Intent compute = new Intent(this, rateComputeActivity.class);
        compute.putExtra("ItemTitle",title);
        compute.putExtra("ItemDetail",detail);
        startActivity(compute);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //删除元素
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("请确认是否删除当前的内容？")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Object itemAtPosition = listView.getItemAtPosition(position);
                        Rate rateItem = (Rate)itemAtPosition;
                        adapter.remove(rateItem);
                    }
                }).setNegativeButton("No",null);
            builder.create().show();

        return true;
    }
}