package com.sxs.chengyupoemgame.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
  
public class DBManager {   
	
    private SQLiteDatabase db;  
    private Context mContext;
    public DBManager(Context context) {  
    	mContext = context;
        db = (new DBHelper(context)).openDatabase();  
    } 
    
    public void upDatabase(){
    	db = (new DBHelper(mContext)).updateDatabase();  
    }
    
    public String[][] queryGuankaArray() {   
    	String[][] listData = null;   
        Cursor c = db.rawQuery("SELECT * FROM sxs_level", null);  
        int idx = 0;
        while (c.moveToNext()) {
        	String [] list = c.getString(c.getColumnIndex("questions")).split(",");
        	if (listData == null) {
        		listData = new String[c.getCount()][list.length];
    		}
        	for(int i = 0;i<list.length;i++){
        		listData[idx][i] = list[i];
        	}
        	idx++;
        }  
        c.close();  
        return listData;  
    }  
    
    public QuestionVO queryQuestionById(String id) {   
    	QuestionVO item= new QuestionVO();   
        Cursor c = db.query("sxs_chengyus", null, "id=?", new String[]{id} ,null, null, null); 
        while (c.moveToNext()) {
        	item.id = c.getString(c.getColumnIndex("id"));
        	item.question = c.getString(c.getColumnIndex("question"));
        	item.answer = c.getString(c.getColumnIndex("answer"));
        	item.pid = c.getString(c.getColumnIndex("pid"));
        	item.level = c.getString(c.getColumnIndex("level"));
        }  
        c.close();  
        return item;  
    }  

    /**
     * 根据首字母获取分类
     * @param key
     * @return
     */
    public int getDbVersion() {   
    	int result = 1;
        Cursor c = db.rawQuery("SELECT * FROM sxs_version", null);
        while (c.moveToNext()) {
        	result = c.getInt(c.getColumnIndex("version"));
        }  
        c.close();  
        return result;  
    } 

    /** 
     * close database 
     */  
    public void closeDB() {  
        db.close();  
    }  
}  