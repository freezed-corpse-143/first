package com.example.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class RateManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public RateManager(Context context){
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(RateItem item){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        db.insert(TBNAME,null,values);
        db.close();
    }

    public List<RateItem> listAll(){
        List<RateItem> ret = new ArrayList<RateItem>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null,null,null,null,null,"ID");

        if(cursor!=null){
            while(cursor.moveToNext()){
                RateItem item = new RateItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
                ret.add(item);
            }
        }
        return ret;
    }

    public RateItem getLatestRate(String cur){
        RateItem item = new RateItem();
        item.setCurName(cur);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null,null,null,null,null,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                if(cursor.getString(1).equals(cur)){
                    item.setCurRate(cursor.getString(2));
                }
            }
        }
        if(item.getCurRate()==0.000){
            item.setCurRate("0");
        }
        return item;
    }
}

